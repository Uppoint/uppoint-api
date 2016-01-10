package com.uppoint.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.uppoint.core.mapper.ProUserMapper;
import com.uppoint.core.model.ProUser;

class SearchService {

	private final DatastoreService datastore;
	private final ProUserMapper proUserMapper;

	SearchService() {
		this.datastore = DatastoreServiceFactory.getDatastoreService();
		this.proUserMapper = new ProUserMapper();
	}

	SearchResults search(Key countryKey, Key cityKey, Key professionKey, Key serviceTypeKey) {
		final Query query = new Query(ProUser.SearchIndex.KIND)
				.setFilter(createSearchIndexFilter(countryKey, cityKey, professionKey, serviceTypeKey)).setKeysOnly();
		final Iterator<Entity> resultIter = this.datastore.prepare(query).asIterator();
		final List<SearchResult> searchResultList = mapQueryResults(resultIter);
		final SearchResults searchResults = new SearchResults();
		searchResults.setResults(searchResultList);
		return searchResults;
	}

	private List<SearchResult> mapQueryResults(final Iterator<Entity> resultIter) {
		final Map<Key, Entity> userEntities = fetchResultUsers(resultIter);
		final List<SearchResult> searchResultList = new LinkedList<>();
		for (Entity userEntity : userEntities.values()) {
			final ProUser user = this.proUserMapper.toModel(userEntity);
			final SearchResult result = new SearchResult();
			result.setUser(user);
			searchResultList.add(result);
		}
		return searchResultList;
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
