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
 * Implementation of core rss2 attributes.
 * User's should extend these classes to include rss2 custom namespaced fields
 */
package rss.domain
import java.text.SimpleDateFormat

class Rss2Feed{
    private def DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
    
	List items=[]
	
	//mandatory elements
	String title
	String link
	String description
	
	//optional elements
	String language
	String copyright
	String managingEditor
	String webMaster
	String pubDate
	String lastBuildDate
	
	Date getPubDate() { 
       DATE_FORMAT.parse(pubDate)
    }
    Date getLastBuildDate() { 
       DATE_FORMAT.parse(lastBuildDate)
    }
	
	List categories =[]
	String generator
	String docs
	def cloud=[:] //map of domain, port, path, registerProcedure,protocol
	def ttl
	def image=[:] //map of url, title,link,width,height,description
	String rating
	def textInput =[:] //map of title, description, name,link
	List skipHours
	List skipDays
}