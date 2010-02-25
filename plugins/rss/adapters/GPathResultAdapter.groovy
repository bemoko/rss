/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright @2010 bemoko 
 */

/*
 * Adapter that allows a GPathResult to be accessed with
 * dot notation in a Freemarker template
 *
 * NOTE : this class is written in Java syntax.  Currently the file has a 
 * ".groovy" extension, however following the next release of the product 
 * (v1.3) these files will be written with a ".java" extension
 */
package rss.adapters;

import groovy.util.slurpersupport.GPathResult;

public class GPathResultAdapter {
  private GPathResult result;

  public GPathResultAdapter(GPathResult result) {
    this.result = result;
  }

  public Object get(String key) {
    return result.getProperty(key);
  }

}