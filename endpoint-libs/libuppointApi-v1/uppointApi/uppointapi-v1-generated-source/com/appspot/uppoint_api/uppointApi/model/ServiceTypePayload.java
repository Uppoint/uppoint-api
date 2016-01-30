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
 * Model definition for ServiceTypePayload.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Uppoint API. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ServiceTypePayload extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String professionKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<LocalePair> translation;

  static {
    // hack to force ProGuard to consider LocalePair used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(LocalePair.class);
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public ServiceTypePayload setKey(java.lang.String key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getProfessionKey() {
    return professionKey;
  }

  /**
   * @param professionKey professionKey or {@code null} for none
   */
  public ServiceTypePayload setProfessionKey(java.lang.String professionKey) {
    this.professionKey = professionKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<LocalePair> getTranslation() {
    return translation;
  }

  /**
   * @param translation translation or {@code null} for none
   */
  public ServiceTypePayload setTranslation(java.util.List<LocalePair> translation) {
    this.translation = translation;
    return this;
  }

  @Override
  public ServiceTypePayload set(String fieldName, Object value) {
    return (ServiceTypePayload) super.set(fieldName, value);
  }

  @Override
  public ServiceTypePayload clone() {
    return (ServiceTypePayload) super.clone();
  }

}
