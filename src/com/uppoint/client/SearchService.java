package com.uppoint.client;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.uppoint.core.mapper.ProUserMapper;
import com.uppoint.core.mapper.UserDefinedServiceMapper;
import com.uppoint.core.model.Event;
import com.uppoint.core.model.ProUser;
import com.uppoint.core.model.UserDefinedService;

class SearchService {

	private final DatastoreService datastore;
	private final ProUserMapper proUserMapper;
	private final UserDefinedServiceMapper serviceMapper;

	SearchService() {
		this.datastore = DatastoreServiceFactory.getDatastoreService();
		this.proUserMapper = new ProUserMapper();
		this.serviceMapper = new UserDefinedServiceMapper();
	}

	SearchResults search(Key countryKey, Key cityKey, Key professionKey, Key serviceTypeKey, long start, long end) {
		final Query query = new Query(ProUser.SearchIndex.KIND)
				.setFilter(createSearchIndexFilter(countryKey, cityKey, professionKey, serviceTypeKey)).setKeysOnly();
		final Iterator<Entity> resultIter = this.datastore.prepare(query).asIterator();
		final List<SearchResult> searchResultList = mapQueryResults(resultIter, serviceTypeKey, start, end);
		final SearchResults searchResults = new SearchResults();
		searchResults.setResults(searchResultList);
		return searchResults;
	}

	private List<SearchResult> mapQueryResults(final Iterator<Entity> resultIter, Key serviceTypeKey, long start,
			long end) {
		final Map<Key, Entity> userEntities = fetchResultUsers(resultIter);
		final List<SearchResult> searchResultList = new LinkedList<>();
		for (Entity userEntity : userEntities.values()) {
			final List<TimeFrame> occupiedFrames = fetchOccupiedFrames(userEntity, start, end);
			if (!hasFreeTime(occupiedFrames, start, end)) {
				continue;
			}

			final SearchResult result = new SearchResult();
			result.setOccupiedFrames(occupiedFrames);

			final ProUser user = this.proUserMapper.toModel(userEntity);
			result.setUser(user);

			final List<UserDefinedService> services = fetchServicesForUser(userEntity, serviceTypeKey);
			result.setServices(services);

			searchResultList.add(result);
		}
		return searchResultList;
	}

	private static boolean hasFreeTime(List<TimeFrame> occupiedFrames, long start, long end) {
		if ((occupiedFrames == null) || (occupiedFrames.isEmpty())) {
			return true;
		}

		final int size = occupiedFrames.size();
		if (size == 1) {
			final TimeFrame timeFrame = occupiedFrames.get(0);
			return (timeFrame.getStart() > start) || (timeFrame.getEnd() < end);
		}

		int freeTime = 0;
		for (int i = 1; i < size; i++) {
			freeTime += occupiedFrames.get(i).getStart() - occupiedFrames.get(i - 1).getEnd();
		}
		return freeTime > 0;
	}

	private List<TimeFrame> fetchOccupiedFrames(Entity userEntity, long start, long end) {
		final List<Filter> subfilters = new LinkedList<>();
		Collections.addAll(subfilters, new Query.FilterPredicate("startTime", FilterOperator.GREATER_THAN, start - 1),
				new Query.FilterPredicate("endTime", FilterOperator.LESS_THAN, end + 1));

		final Query query = new Query(Event.class.getSimpleName(), userEntity.getKey())
				.addProjection(new PropertyProjection("startTime", Long.class))
				.addProjection(new PropertyProjection("endTime", Long.class))
				.setFilter(new Query.CompositeFilter(CompositeFilterOperator.AND, subfilters))
				.addSort("startTime");

		final Iterator<Entity> eventIter = this.datastore.prepare(query).asIterator();
		final List<TimeFrame> occupiedFrames = new LinkedList<>();
		while (eventIter.hasNext()) {
			final TimeFrame timeFrame = new TimeFrame();
			final Entity event = eventIter.next();
			timeFrame.setStart((long) event.getProperty("startTime"));
			timeFrame.setEnd((long) event.getProperty("endTime"));
			occupiedFrames.add(timeFrame);
		}

		return occupiedFrames;
	}

	private List<UserDefinedService> fetchServicesForUser(Entity userEntity, Key serviceTypeKey) {
		final Query query = new Query(UserDefinedService.class.getSimpleName(), userEntity.getKey())
				.setFilter(new Query.FilterPredicate("serviceTypeKey", FilterOperator.EQUAL, serviceTypeKey));
		final Iterator<Entity> serviceIter = this.datastore.prepare(query).asIterator();
		final List<UserDefinedService> services = new LinkedList<>();
		while (serviceIter.hasNext()) {
			services.add(this.serviceMapper.toModel(serviceIter.next()));
		}
		return services;
	}

	private Map<Key, Entity> fetchResultUsers(final Iterator<Entity> resultIter) {
		final List<Key> userKeys = new LinkedList<>();
		while (resultIter.hasNext()) {
			userKeys.add(resultIter.next().getParent());
		}
		final Map<Key, Entity> userEntities = this.datastore.get(userKeys);
		return userEntities;
	}

	private Filter createSearchIndexFilter(Key countryKey, Key cityKey, Key professionKey, Key serviceTypeKey) {
		final List<Query.Filter> predicates = new LinkedList<>();

		if (countryKey != null) {
			predicates.add(new Query.FilterPredicate(ProUser.SearchIndex.PROPERTY_COUNTRY_KEY, FilterOperator.EQUAL,
					countryKey));
		}

		if (cityKey != null) {
			predicates.add(
					new Query.FilterPredicate(ProUser.SearchIndex.PROPERTY_CITY_KEY, FilterOperator.EQUAL, cityKey));
		}

		if (professionKey != null) {
			predicates.add(new Query.FilterPredicate(ProUser.SearchIndex.PROPERTY_PROFESSION_KEY, FilterOperator.EQUAL,
					professionKey));
		}

		if (serviceTypeKey != null) {
			predicates.add(new Query.FilterPredicate(ProUser.SearchIndex.PROPERTY_SERVICE_TYPES, FilterOperator.EQUAL,
					serviceTypeKey));
		}

		return new Query.CompositeFilter(CompositeFilterOperator.AND, predicates);
	}

}
