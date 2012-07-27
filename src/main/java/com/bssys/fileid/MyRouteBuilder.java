/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bssys.fileid;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;
import org.dom4j.Document;

/**
 * A Camel Router
 */
public class MyRouteBuilder extends RouteBuilder {

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		Main.main(args);
	}

	/**
	 * Let's configure the Camel routing rules using Java code...
	 */
	public void configure() {

		// TODO create Camel routes here.
		this.getContext().getTypeConverterRegistry().addTypeConverter(Document.class, String.class, new Dom4JTypeConvertor());

		from("file:c:/data/inbox").convertBodyTo(String.class).processRef("successProcessor").to("cxf:bean:opcWSEndpoint").to("file:c:/data/outbox");
		
		from("file:c:/data/inbox3").
		beanRef("assign","execute('original', ${body})").
		beanRef("delete","execute('original', '//record[@recordUUID=1]')").
		beanRef("assign","execute('raw','<a>Oops</a>')").
		beanRef("insert","execute('raw','//records', 'original')").
		setBody().simple("${property.original}").
		log("${property.original}").
		to("file:c:/data/outbox");

	}
}
