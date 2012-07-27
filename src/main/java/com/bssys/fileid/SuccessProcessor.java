package com.bssys.fileid;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.dom4j.Document;




public class SuccessProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		Document dom4jdocument = exchange.getIn().getBody(Document.class);
		Object s = exchange.getProperty("original");
		exchange.setProperty("dom4jOriginal", dom4jdocument);
	}
}
