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
 * Test implementation of core rss2 feed processing against reference example at http://cyber.law.harvard.edu/rss/examples/rss2sample.xml
 *
 * groovy rss/tests/Rss2CoreTest.groovy 
 */ 
package feed.tests

import groovy.util.GroovyTestCase
import rss.Rss2DomainAdapter

class Rss2DomainAdapterTest extends GroovyTestCase {
    
    def feedSupply = new Rss2DomainAdapter()
    def testUrl =  "file:///" 
      + new File(getClass().protectionDomain.codeSource.location.path).parent 
      + "/Rss2CoreTestSample.xml"
    def feed
    
    void setUp() {
      feedSupply.initialise(['url':testUrl])
      feed=feedSupply.getFeed()
    }
    
    void testFeed() {
      assertNotNull("Feed does not contain any content",feed)
      assertEquals("Feed title is incorrect",'Liftoff News',feed.title)
      assertEquals("Feed link is incorrect",'http://liftoff.msfc.nasa.gov/',feed.link)
      assertEquals("Feed description is incorrect",'Liftoff to Space Exploration.',feed.description)
      assertEquals("Feed language is incorrect",'en-us',feed.language)
      assertEquals("Feed pubDate is incorrect",Date.parse("EEE, dd MMM yyyy HH:mm:ss z","Tue, 10 Jun 2003 04:00:00 GMT"),feed.pubDate)
      assertEquals("Feed lastBuildDate is incorrect",Date.parse("EEE, dd MMM yyyy HH:mm:ss z","Tue, 10 Jun 2003 09:41:01 GMT"),feed.lastBuildDate)
      assertEquals("Feed docs is incorrect",'http://blogs.law.harvard.edu/tech/rss',feed.docs)
      assertEquals("Feed generator is incorrect",'Weblog Editor 2.0',feed.generator)
      assertEquals("Feed managingEditor is incorrect",'editor@example.com',feed.managingEditor)
      assertEquals("Feed webMaster is incorrect",'webmaster@example.com',feed.webMaster)
    }
    
    void testItems() {
      assertNotNull("Feed does not contain any content",feed)
      assertEquals("Incorrect number of items",4,feed.items.size())
      
      def item1=feed.items[0]
      assertEquals("Item title is incorrect",'Star City',item1.title)
      assertEquals("Item link is incorrect",'http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp',item1.link)
      assertEquals("Item description is incorrect","How do Americans get ready to work with Russians aboard the International Space Station? They take a crash course in culture, language and protocol at Russia's <a href=\"http://howe.iki.rssi.ru/GCTC/gctc_e.htm\">Star City</a>.",item1.description)
      assertEquals("Item pubDate is incorrect",Date.parse("EEE, dd MMM yyyy HH:mm:ss z","Tue, 03 Jun 2003 09:39:21 GMT"),item1.pubDate)
      assertEquals("Item guid is incorrect",'http://liftoff.msfc.nasa.gov/2003/06/03.html#item573',item1.guid)
    }
}