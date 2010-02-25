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
 
package rss
 
import com.bemoko.live.platform.mwc.plugins.AbstractPlugin
import rss.domain.Rss2Item
import rss.domain.Rss2Feed

/*
 * Retrive an RSS2 feed and make available to rendering templates
 */
class Rss2 extends AbstractPlugin {
  def url 

  void initialise(Map p) {
    url = p.url
  }
  
  def getFeed() {
    def feedResponseRoot
        
    try {
      feedResponseRoot = new XmlSlurper(false,false).parse(url)
    } catch (Exception e){
      /*
       * A failure could be caused by a fragile remote service
       * treat as a temporarily unavailable situation
       */
      e.printStackTrace()
      return null
    }
    return new adapters.Rss2ChannelAdapter(feedResponseRoot.channel)
  }  
}