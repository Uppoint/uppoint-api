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
 * Model definition for TimeFrame.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Uppoint API. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class TimeFrame extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long end;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long start;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getEnd() {
    return end;
  }

  /**
   * @param end end or {@code null} for none
   */
  public TimeFrame setEnd(java.lang.Long end) {
    this.end = end;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getStart() {
    return start;
  }

  /**
   * @param start start or {@code null} for none
   */
  public TimeFrame setStart(java.lang.Long start) {
    this.start = start;
    return this;
  }

  @Override
  public TimeFrame set(String fieldName, Object value) {
    return (TimeFrame) super.set(fieldName, value);
  }

  @Override
  public TimeFrame clone() {
    return (TimeFrame) super.clone();
  }

}
