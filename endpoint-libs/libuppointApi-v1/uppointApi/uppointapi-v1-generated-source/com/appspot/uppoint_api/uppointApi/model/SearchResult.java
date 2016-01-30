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
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2016-01-08 17:48:37 UTC)
 * on 2016-01-30 at 13:38:46 UTC 
 * Modify at your own risk.
 */

package com.appspot.uppoint_api.uppointApi.model;

/**
 * Model definition for SearchResult.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Uppoint API. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class SearchResult extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<TimeFrame> occupiedFrames;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<UserDefinedServicePayload> services;

  static {
    // hack to force ProGuard to consider UserDefinedServicePayload used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(UserDefinedServicePayload.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private ProUserPayload user;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<TimeFrame> getOccupiedFrames() {
    return occupiedFrames;
  }

  /**
   * @param occupiedFrames occupiedFrames or {@code null} for none
   */
  public SearchResult setOccupiedFrames(java.util.List<TimeFrame> occupiedFrames) {
    this.occupiedFrames = occupiedFrames;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<UserDefinedServicePayload> getServices() {
    return services;
  }

  /**
   * @param services services or {@code null} for none
   */
  public SearchResult setServices(java.util.List<UserDefinedServicePayload> services) {
    this.services = services;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public ProUserPayload getUser() {
    return user;
  }

  /**
   * @param user user or {@code null} for none
   */
  public SearchResult setUser(ProUserPayload user) {
    this.user = user;
    return this;
  }

  @Override
  public SearchResult set(String fieldName, Object value) {
    return (SearchResult) super.set(fieldName, value);
  }

  @Override
  public SearchResult clone() {
    return (SearchResult) super.clone();
  }

}
