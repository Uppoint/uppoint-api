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
 * (build: 2016-02-18 22:11:37 UTC)
 * on 2016-02-28 at 15:20:20 UTC 
 * Modify at your own risk.
 */

package com.appspot.uppoint_api.uppointApi.model;

/**
 * Model definition for AddressPayload.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Uppoint API. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class AddressPayload extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String cityKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String countryKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float latitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float longitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String number;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String street;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCityKey() {
    return cityKey;
  }

  /**
   * @param cityKey cityKey or {@code null} for none
   */
  public AddressPayload setCityKey(java.lang.String cityKey) {
    this.cityKey = cityKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCountryKey() {
    return countryKey;
  }

  /**
   * @param countryKey countryKey or {@code null} for none
   */
  public AddressPayload setCountryKey(java.lang.String countryKey) {
    this.countryKey = countryKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getLatitude() {
    return latitude;
  }

  /**
   * @param latitude latitude or {@code null} for none
   */
  public AddressPayload setLatitude(java.lang.Float latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getLongitude() {
    return longitude;
  }

  /**
   * @param longitude longitude or {@code null} for none
   */
  public AddressPayload setLongitude(java.lang.Float longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNumber() {
    return number;
  }

  /**
   * @param number number or {@code null} for none
   */
  public AddressPayload setNumber(java.lang.String number) {
    this.number = number;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStreet() {
    return street;
  }

  /**
   * @param street street or {@code null} for none
   */
  public AddressPayload setStreet(java.lang.String street) {
    this.street = street;
    return this;
  }

  @Override
  public AddressPayload set(String fieldName, Object value) {
    return (AddressPayload) super.set(fieldName, value);
  }

  @Override
  public AddressPayload clone() {
    return (AddressPayload) super.clone();
  }

}
