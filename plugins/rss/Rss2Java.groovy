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
package rss;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;

import java.util.Map;

import rss.adapters.Rss2ChannelAdapter;

import com.bemoko.live.platform.mwc.plugins.AbstractPlugin;

/*
 * Retrive an RSS2 feed and make available to rendering templates
 *
 * NOTE : this class is written in Java syntax.  Currently the file has a 
 * ".groovy" extension, however following the next release of the product 
 * (v1.3) these files will be written with a ".java" extension
 */
public class Rss2Java extends AbstractPlugin {
  private String url;
  
  public void initialise(Map p) {
    url = (String) p.get("url");
  }
  
  public Object getFeed() {
    GPathResult feedResponseRoot;
    
    try {
      feedResponseRoot = new XmlSlurper(false, false).parse(url);
    } catch (Exception e) {
      /*
       * A failure could be caused by a fragile remote service treat as a
       * temporarily unavailable situation
       */
      e.printStackTrace();
      return null;
    }
    return new Rss2ChannelAdapter((GPathResult) feedResponseRoot.getProperty("channel"));
  }
}