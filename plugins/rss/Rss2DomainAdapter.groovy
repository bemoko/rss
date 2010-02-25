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
 * Retrive an RSS2 feed and make available to rendering templates by local domain objects.  This
 * technique is useful if you wish to aggregate, cache or persist the feed content.
 */ 
class Rss2DomainAdapter extends AbstractPlugin {
  def url
 
  void initialise(Map p) {
    url = p.url
  }
  
  def Rss2Feed getFeed() {
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
    def feedResponse = feedResponseRoot.channel
 
    def feed=new Rss2Feed (
      title : feedResponse.title,
      link : feedResponse.link,
      description : feedResponse.description,
      language : feedResponse.language,
      copyright : feedResponse.copyright,
      managingEditor : feedResponse.managingEditor,
      webMaster : feedResponse.webMaster,
      pubDate : feedResponse.pubDate,
      lastBuildDate : feedResponse.lastBuildDate,
      generator : feedResponse.generator,
      docs : feedResponse.docs,
      cloud : [
        'domain' : feedResponse.cloud.@domain,
        'port' : feedResponse.cloud.@port,
        'path' : feedResponse.cloud.@path,
        'registerProcedure':feedResponse.cloud.@registerProcedure,
        'protocol' :feedResponse.cloud.@protocol
      ],
      ttl :feedResponse.ttl,
      image : [
        'url' :feedResponse.image.url,
        'title' :feedResponse.image.title,
        'link' :feedResponse.image.link,
        'width' :feedResponse.image.width,
        'height' :feedResponse.image.height
      ],
      rating:feedResponse.rating,
      textInput : [
        'title' :feedResponse.textInput.title,
        'description':feedResponse.textInput.description,
        'name' :feedResponse.textInput.name,
        'link' :feedResponse.textInput.link
      ],
      skipHours : feedResponse.skipHours.list(),
      skipDays : feedResponse.skipDays.list()
    )
 
    feedResponse.item.each {
      feed.items << new Rss2Item(
        title : it.title,
        link : it.link,
        description : it.description,
        author : it.author,
        categories : it.category.list(),
        comments : it.comments,
        enclosure : [
          'url' :feedResponse.enclosure.@url,
          'length' :feedResponse.enclosure.@length,
          'type' :feedResponse.type.@type
        ],
        guid :it.guid,
        pubDate :it.pubDate,
        source : [
          'url' :feedResponse.source.@url,
          'text' :feedResponse.source
        ]
       )
     }
    return feed
  }
}