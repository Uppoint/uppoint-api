/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-02-18 22:11:37 UTC)
 * on 2016-03-03 at 21:29:19 UTC 
 * Modify at your own risk.
 */

package com.appspot.uppoint_api.uppointApi.model;

/**
 * Model definition for SyncPayload.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Uppoint API. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class SyncPayload extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedCategories;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedCities;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedCountries;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedEvents;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedProfessions;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedServiceTypes;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> deletedServices;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long lastSyncTimestamp;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ProUserPayload profile;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<CategoryPayload> updatedCategories;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<CityPayload> updatedCities;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<CountryPayload> updatedCountries;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<EventPayload> updatedEvents;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<ProfessionPayload> updatedProfessions;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<ServiceTypePayload> updatedServiceTypes;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<UserDefinedServicePayload> updatedServices;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedCategories() {
    return deletedCategories;
  }

  /**
   * @param deletedCategories deletedCategories or {@code null} for none
   */
  public SyncPayload setDeletedCategories(java.util.List<java.lang.String> deletedCategories) {
    this.deletedCategories = deletedCategories;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedCities() {
    return deletedCities;
  }

  /**
   * @param deletedCities deletedCities or {@code null} for none
   */
  public SyncPayload setDeletedCities(java.util.List<java.lang.String> deletedCities) {
    this.deletedCities = deletedCities;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedCountries() {
    return deletedCountries;
  }

  /**
   * @param deletedCountries deletedCountries or {@code null} for none
   */
  public SyncPayload setDeletedCountries(java.util.List<java.lang.String> deletedCountries) {
    this.deletedCountries = deletedCountries;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedEvents() {
    return deletedEvents;
  }

  /**
   * @param deletedEvents deletedEvents or {@code null} for none
   */
  public SyncPayload setDeletedEvents(java.util.List<java.lang.String> deletedEvents) {
    this.deletedEvents = deletedEvents;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedProfessions() {
    return deletedProfessions;
  }

  /**
   * @param deletedProfessions deletedProfessions or {@code null} for none
   */
  public SyncPayload setDeletedProfessions(java.util.List<java.lang.String> deletedProfessions) {
    this.deletedProfessions = deletedProfessions;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedServiceTypes() {
    return deletedServiceTypes;
  }

  /**
   * @param deletedServiceTypes deletedServiceTypes or {@code null} for none
   */
  public SyncPayload setDeletedServiceTypes(java.util.List<java.lang.String> deletedServiceTypes) {
    this.deletedServiceTypes = deletedServiceTypes;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDeletedServices() {
    return deletedServices;
  }

  /**
   * @param deletedServices deletedServices or {@code null} for none
   */
  public SyncPayload setDeletedServices(java.util.List<java.lang.String> deletedServices) {
    this.deletedServices = deletedServices;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLastSyncTimestamp() {
    return lastSyncTimestamp;
  }

  /**
   * @param lastSyncTimestamp lastSyncTimestamp or {@code null} for none
   */
  public SyncPayload setLastSyncTimestamp(java.lang.Long lastSyncTimestamp) {
    this.lastSyncTimestamp = lastSyncTimestamp;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public ProUserPayload getProfile() {
    return profile;
  }

  /**
   * @param profile profile or {@code null} for none
   */
  public SyncPayload setProfile(ProUserPayload profile) {
    this.profile = profile;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<CategoryPayload> getUpdatedCategories() {
    return updatedCategories;
  }

  /**
   * @param updatedCategories updatedCategories or {@code null} for none
   */
  public SyncPayload setUpdatedCategories(java.util.List<CategoryPayload> updatedCategories) {
    this.updatedCategories = updatedCategories;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<CityPayload> getUpdatedCities() {
    return updatedCities;
  }

  /**
   * @param updatedCities updatedCities or {@code null} for none
   */
  public SyncPayload setUpdatedCities(java.util.List<CityPayload> updatedCities) {
    this.updatedCities = updatedCities;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<CountryPayload> getUpdatedCountries() {
    return updatedCountries;
  }

  /**
   * @param updatedCountries updatedCountries or {@code null} for none
   */
  public SyncPayload setUpdatedCountries(java.util.List<CountryPayload> updatedCountries) {
    this.updatedCountries = updatedCountries;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<EventPayload> getUpdatedEvents() {
    return updatedEvents;
  }

  /**
   * @param updatedEvents updatedEvents or {@code null} for none
   */
  public SyncPayload setUpdatedEvents(java.util.List<EventPayload> updatedEvents) {
    this.updatedEvents = updatedEvents;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ProfessionPayload> getUpdatedProfessions() {
    return updatedProfessions;
  }

  /**
   * @param updatedProfessions updatedProfessions or {@code null} for none
   */
  public SyncPayload setUpdatedProfessions(java.util.List<ProfessionPayload> updatedProfessions) {
    this.updatedProfessions = updatedProfessions;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ServiceTypePayload> getUpdatedServiceTypes() {
    return updatedServiceTypes;
  }

  /**
   * @param updatedServiceTypes updatedServiceTypes or {@code null} for none
   */
  public SyncPayload setUpdatedServiceTypes(java.util.List<ServiceTypePayload> updatedServiceTypes) {
    this.updatedServiceTypes = updatedServiceTypes;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<UserDefinedServicePayload> getUpdatedServices() {
    return updatedServices;
  }

  /**
   * @param updatedServices updatedServices or {@code null} for none
   */
  public SyncPayload setUpdatedServices(java.util.List<UserDefinedServicePayload> updatedServices) {
    this.updatedServices = updatedServices;
    return this;
  }

  @Override
  public SyncPayload set(String fieldName, Object value) {
    return (SyncPayload) super.set(fieldName, value);
  }

  @Override
  public SyncPayload clone() {
    return (SyncPayload) super.clone();
  }

}
