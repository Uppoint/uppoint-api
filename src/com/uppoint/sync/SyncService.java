package com.uppoint.sync;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.uppoint.core.mapper.CategoryMapper;
import com.uppoint.core.mapper.CityMapper;
import com.uppoint.core.mapper.CountryMapper;
import com.uppoint.core.mapper.EventMapper;
import com.uppoint.core.mapper.Mapper;
import com.uppoint.core.mapper.ProUserMapper;
import com.uppoint.core.mapper.ProfessionMapper;
import com.uppoint.core.mapper.ServiceTypeMapper;
import com.uppoint.core.mapper.UserDefinedServiceMapper;
import com.uppoint.core.model.Address;
import com.uppoint.core.model.Category;
import com.uppoint.core.model.City;
import com.uppoint.core.model.Country;
import com.uppoint.core.model.Event;
import com.uppoint.core.model.Model;
import com.uppoint.core.model.ProUser;
import com.uppoint.core.model.Profession;
import com.uppoint.core.model.ServiceType;
import com.uppoint.core.model.UserDefinedService;

public class SyncService {

	public static final long TIMESTAMP_NONE = -1;

	private final DatastoreService datastore;

	private final Mapper<Country> countryMapper;
	private final Mapper<City> cityMapper;
	private final Mapper<Category> categoryMapper;
	private final Mapper<Profession> professionMapper;
	private final Mapper<ServiceType> serviceTypeMapper;
	private final Mapper<ProUser> userMapper;
	private final Mapper<UserDefinedService> userDefinedServiceMapper;
	private final Mapper<Event> eventMapper;

	public SyncService() {
		this.datastore = DatastoreServiceFactory.getDatastoreService();

		this.countryMapper = new CountryMapper();
		this.cityMapper = new CityMapper();
		this.categoryMapper = new CategoryMapper();
		this.professionMapper = new ProfessionMapper();
		this.serviceTypeMapper = new ServiceTypeMapper();
		this.userMapper = new ProUserMapper();
		this.userDefinedServiceMapper = new UserDefinedServiceMapper();
		this.eventMapper = new EventMapper();
	}

	public SyncPayload sync(Key userKey, SyncPayload syncPayload, long timestamp) {
		applyRemoteChanges(userKey, syncPayload);
		return fetchLocalChanges(userKey, timestamp);
	}

	private SyncPayload fetchLocalChanges(Key userKey, long timestamp) {
		final SyncPayload localPayload = new SyncPayload();
		fetchUpdatedNomenclatures(timestamp, localPayload);
		fetchUpdatedUserData(userKey, timestamp, localPayload);
		fetchDeletedKeys(userKey, timestamp, localPayload);
		localPayload.setLastSyncTimestamp(Calendar.getInstance().getTimeInMillis());
		return localPayload;
	}

	private void fetchUpdatedUserData(Key userKey, long timestamp, final SyncPayload localPayload) {
		final List<Event> updatedEvents = fetchUpdatedEvents(userKey, timestamp);
		localPayload.setUpdatedEvents(updatedEvents);

		final List<UserDefinedService> updatedServices = fetchUpdatedServices(userKey, timestamp);
		localPayload.setUpdatedServices(updatedServices);

		final ProUser profile = fetchProfile(userKey, timestamp);
		localPayload.setProfile(profile);
	}

	private void fetchUpdatedNomenclatures(long timestamp, SyncPayload localPayload) {
		final Iterator<Entity> countryEntityIter = fetchUpdated(Country.class.getSimpleName(), null, timestamp);
		localPayload.setUpdatedCountries(mapEntityIterator(countryEntityIter, this.countryMapper));

		final Iterator<Entity> cityEntityIter = fetchUpdated(City.class.getSimpleName(), null, timestamp);
		localPayload.setUpdatedCities(mapEntityIterator(cityEntityIter, this.cityMapper));

		final Iterator<Entity> categoryEntityIter = fetchUpdated(Category.class.getSimpleName(), null, timestamp);
		localPayload.setUpdatedCategories(mapEntityIterator(categoryEntityIter, this.categoryMapper));

		final Iterator<Entity> professionEntityIter = fetchUpdated(Profession.class.getSimpleName(), null, timestamp);
		localPayload.setUpdatedProfessions(mapEntityIterator(professionEntityIter, this.professionMapper));

		final Iterator<Entity> serviceTypeEntityIter = fetchUpdated(ServiceType.class.getSimpleName(), null, timestamp);
		localPayload.setUpdatedServiceTypes(mapEntityIterator(serviceTypeEntityIter, this.serviceTypeMapper));
	}

	private void fetchDeletedKeys(Key userKey, long timestamp, SyncPayload localPayload) {
		localPayload.setDeletedCountries(fetchDeleted(Country.class.getSimpleName(), null, timestamp));
		localPayload.setDeletedCities(fetchDeleted(City.class.getSimpleName(), null, timestamp));
		localPayload.setDeletedCategories(fetchDeleted(Category.class.getSimpleName(), null, timestamp));
		localPayload.setDeletedProfessions(fetchDeleted(Profession.class.getSimpleName(), null, timestamp));
		localPayload.setDeletedServiceTypes(fetchDeleted(ServiceType.class.getSimpleName(), null, timestamp));
		localPayload.setDeletedEvents(fetchDeleted(Event.class.getSimpleName(), userKey, timestamp));
		localPayload.setDeletedServices(fetchDeleted(UserDefinedService.class.getName(), userKey, timestamp));
	}

	private ProUser fetchProfile(Key userKey, long timestamp) {
		try {
			final Entity entity = this.datastore.get(userKey);
			final ProUser profile = this.userMapper.toModel(entity);
			if (profile.getLastUpdateTime() > timestamp) {
				return profile;
			}
		} catch (EntityNotFoundException e) {
			// ignore
		}
		return null;
	}

	private List<UserDefinedService> fetchUpdatedServices(Key userKey, long timestamp) {
		final Iterator<Entity> resultIterator = fetchUpdated(UserDefinedService.class.getSimpleName(), userKey,
				timestamp);
		return mapEntityIterator(resultIterator, this.userDefinedServiceMapper);
	}

	private List<Event> fetchUpdatedEvents(Key userKey, long timestamp) {
		final Iterator<Entity> resultIterator = fetchUpdated(Event.class.getSimpleName(), userKey, timestamp);
		return mapEntityIterator(resultIterator, this.eventMapper);
	}

	private void applyRemoteChanges(Key userKey, SyncPayload syncPayload) {
		this.datastore.beginTransaction();
		try {
			final Entity searchIndex = getSearchIndex(userKey);
			long timestamp = Calendar.getInstance().getTimeInMillis();
			applyProfileChanges(syncPayload, timestamp, userKey, searchIndex);
			applyServiceChanges(userKey, syncPayload, timestamp, searchIndex);
			applyEventChanges(userKey, syncPayload, timestamp);
			applyDeletions(syncPayload, timestamp, searchIndex);
			this.datastore.put(searchIndex);
			this.datastore.getCurrentTransaction().commit();
		} catch (Exception e) {
			this.datastore.getCurrentTransaction().rollback();
		}
	}

	private Entity getSearchIndex(Key userKey) {
		Entity searchIndex = fetchSearchIndex(userKey);
		if (searchIndex == null) {
			searchIndex = new Entity(ProUser.SearchIndex.KIND, userKey);
		}
		return searchIndex;
	}

	private void updateSearchIndexFromProfile(Entity searchIndex, final ProUser profile) {
		final Address address = profile.getAddress();
		if (address != null) {
			final Key countryKey = address.getCountryKey();
			searchIndex.setIndexedProperty(ProUser.SearchIndex.PROPERTY_COUNTRY_KEY, countryKey);

			final Key cityKey = address.getCityKey();
			searchIndex.setProperty(ProUser.SearchIndex.PROPERTY_CITY_KEY, cityKey);
		}

		searchIndex.setIndexedProperty(ProUser.SearchIndex.PROPERTY_PROFESSION_KEY, profile.getProfessionKey());
	}

	private Entity fetchSearchIndex(Key userKey) {
		final Query query = new Query(ProUser.SearchIndex.KIND, userKey);
		return this.datastore.prepare(query).asSingleEntity();
	}

	private void applyDeletions(SyncPayload syncPayload, long timestamp, Entity searchIndex) {
		final List<Entity> deletedEntities = new LinkedList<>();
		applyDeletedServices(syncPayload, timestamp, searchIndex, deletedEntities);
		applyDeletedEvents(syncPayload, timestamp, deletedEntities);
		this.datastore.put(deletedEntities);
	}

	private void applyDeletedEvents(SyncPayload syncPayload, long timestamp, final List<Entity> deletedEntities) {
		final List<String> deletedEvents = syncPayload.getDeletedEvents();
		if (deletedEvents == null || deletedEvents.isEmpty()) {
			return;
		}

		for (String key : deletedEvents) {
			final Entity deletedEntity = createDeletedEntity(timestamp, key);
			deletedEntities.add(deletedEntity);
		}
	}

	private Entity createDeletedEntity(long timestamp, String key) {
		final Key entityKey = KeyFactory.stringToKey(key);
		final Entity deletedEntity = new Entity(entityKey);
		deletedEntity.setIndexedProperty(Model.PROPERTY_IS_DELETED, true);
		deletedEntity.setIndexedProperty(Model.PROPERTY_LAST_UPDATE, timestamp);
		return deletedEntity;
	}

	private void applyDeletedServices(SyncPayload syncPayload, long timestamp, Entity searchIndex,
			final List<Entity> deletedEntities) {
		final List<String> deletedServices = syncPayload.getDeletedServices();
		if ((deletedServices == null) || deletedServices.isEmpty()) {
			return;
		}

		@SuppressWarnings("unchecked") // we put the list there
		final List<Key> availableServiceTypes = (List<Key>) searchIndex
				.getProperty(ProUser.SearchIndex.PROPERTY_SERVICE_TYPES);
		for (String key : deletedServices) {
			final Entity deletedEntity = createDeletedEntity(timestamp, key);
			deletedEntities.add(deletedEntity);

			if (availableServiceTypes != null) {
				availableServiceTypes.remove(deletedEntity.getKey());
			}
		}

	}

	private void applyEventChanges(Key userKey, SyncPayload syncPayload, long timestamp) {
		final List<Event> events = syncPayload.getUpdatedEvents();
		if ((events != null) && !events.isEmpty()) {
			final List<Entity> eventEntities = new LinkedList<>();
			final Iterator<Key> keyIter = generateKeys(userKey, Event.class.getSimpleName(), events.size());
			for (Event event : events) {
				if (canPut(event)) {
					event.setKey(keyIter.next());
					final Entity eventEntity = this.eventMapper.toEntity(event);
					eventEntity.setIndexedProperty(Model.PROPERTY_LAST_UPDATE, timestamp);
					eventEntity.setIndexedProperty(Model.PROPERTY_IS_DELETED, false);
					eventEntities.add(eventEntity);
				}
			}
			this.datastore.put(eventEntities);
		}
	}

	private boolean canPut(Event event) {
		final List<Query.Filter> subFilters = new LinkedList<>();
		Collections.addAll(subFilters,
				new Query.CompositeFilter(CompositeFilterOperator.AND, Arrays.asList(
						new Query.FilterPredicate("startTime", FilterOperator.LESS_THAN_OR_EQUAL, event.getStartTime()),
						new Query.FilterPredicate("endTime", FilterOperator.GREATER_THAN_OR_EQUAL, event.getStartTime()))),
				new Query.CompositeFilter(CompositeFilterOperator.AND, Arrays.asList(
						new Query.FilterPredicate("startTime", FilterOperator.LESS_THAN_OR_EQUAL, event.getEndTime()),
						new Query.FilterPredicate("endTime", FilterOperator.GREATER_THAN_OR_EQUAL, event.getEndTime()))));
		final Query.CompositeFilter filter = new Query.CompositeFilter(CompositeFilterOperator.OR, subFilters);
		final Query query = new Query(Event.class.getSimpleName()).setFilter(filter).setKeysOnly();
		final PreparedQuery preparedQuery = this.datastore.prepare(query);
		final int overlappingEvents = preparedQuery.countEntities(FetchOptions.Builder.withDefaults());
		return overlappingEvents == 0;
	}

	private void applyServiceChanges(Key userKey, SyncPayload syncPayload, long timestamp, Entity searchIndex) {
		final List<UserDefinedService> services = syncPayload.getUpdatedServices();
		if ((services != null) && !services.isEmpty()) {
			final Iterator<Key> keyIterator = generateKeys(userKey, UserDefinedService.class.getSimpleName(),
					services.size());
			final List<Entity> serviceEntities = new LinkedList<>();
			@SuppressWarnings("unchecked")
			List<Key> availableServiceTypes = (List<Key>) searchIndex
					.getProperty(ProUser.SearchIndex.PROPERTY_SERVICE_TYPES);
			if (availableServiceTypes == null) {
				availableServiceTypes = new LinkedList<>();
			}
			for (UserDefinedService service : services) {
				service.setKey(keyIterator.next());
				final Entity serviceEntity = this.userDefinedServiceMapper.toEntity(service);
				serviceEntity.setIndexedProperty(Model.PROPERTY_LAST_UPDATE, timestamp);
				serviceEntity.setIndexedProperty(Model.PROPERTY_IS_DELETED, false);
				serviceEntities.add(serviceEntity);

				final Key sericeTypeKey = service.getSeriveTypeKey();
				if (!availableServiceTypes.contains(sericeTypeKey)) {
					availableServiceTypes.add(sericeTypeKey);
				}
			}
			searchIndex.setIndexedProperty(ProUser.SearchIndex.PROPERTY_SERVICE_TYPES, availableServiceTypes);
			this.datastore.put(serviceEntities);
		}
	}

	private Iterator<Key> generateKeys(Key userKey, String kind, int num) {
		final KeyRange keyRange = this.datastore.allocateIds(userKey, kind, num);
		return keyRange.iterator();
	}

	private void applyProfileChanges(SyncPayload syncPayload, long timestamp, Key userKey, Entity searchIndex) {
		final ProUser profile = syncPayload.getProfile();
		if (profile != null) {
			updateSearchIndexFromProfile(searchIndex, profile);

			try {
				final Entity originalProfileEntity = this.datastore.get(userKey);
				originalProfileEntity.setPropertiesFrom(this.userMapper.toEntity(profile));
				originalProfileEntity.setIndexedProperty(Model.PROPERTY_LAST_UPDATE, timestamp);
				this.datastore.put(originalProfileEntity);
			} catch (EntityNotFoundException e) {
				// can't happen
			}
		}
	}

	private Iterator<Entity> fetchUpdated(String kind, Key userKey, long timestamp) {
		return fetchByTimestamp(kind, userKey, timestamp, false /* is updated */);
	}

	private List<String> fetchDeleted(String kind, Key userKey, long timestamp) {
		final Iterator<Entity> iter = fetchByTimestamp(kind, userKey, timestamp,
				true /* is deleted */);
		return mapEntityKeyIterator(iter);
	}

	private Iterator<Entity> fetchByTimestamp(String kind, Key userKey, long timestamp, boolean deleted) {
		Query query = null;
		if (userKey == null) {
			query = new Query(kind);
		} else {
			query = new Query(kind, userKey);
		}
		query.setFilter(createStatusAndTimestampFilter(timestamp, deleted));
		if (deleted) {
			query.setKeysOnly();
		}
		final PreparedQuery preparedQuery = this.datastore.prepare(query);
		final Iterator<Entity> resultIterator = preparedQuery.asIterator();
		return resultIterator;
	}

	private Query.Filter createStatusAndTimestampFilter(long timestamp, boolean isDeleted) {
		final Query.Filter statusFilter = new Query.FilterPredicate(Model.PROPERTY_IS_DELETED, FilterOperator.EQUAL,
				isDeleted);
		if (TIMESTAMP_NONE == timestamp) {
			return statusFilter;
		} else {
			final Query.Filter timestampFilter = new Query.FilterPredicate(Model.PROPERTY_LAST_UPDATE,
					FilterOperator.GREATER_THAN, timestamp);
			return new Query.CompositeFilter(CompositeFilterOperator.AND, Arrays.asList(statusFilter, timestampFilter));
		}
	}

	private <M extends Model> List<M> mapEntityIterator(Iterator<Entity> iterator, Mapper<M> mapper) {
		final List<M> result = new LinkedList<>();
		while (iterator.hasNext()) {
			result.add(mapper.toModel(iterator.next()));
		}

		return result;
	}

	private List<String> mapEntityKeyIterator(Iterator<Entity> iterator) {
		final List<String> keys = new LinkedList<>();
		while (iterator.hasNext()) {
			final Key key = iterator.next().getKey();
			keys.add(KeyFactory.keyToString(key));
		}

		return keys;
	}
}
