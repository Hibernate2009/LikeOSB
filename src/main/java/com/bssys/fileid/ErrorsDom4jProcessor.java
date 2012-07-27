package com.bssys.fileid;

import java.io.StringWriter;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class ErrorsDom4jProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Document original = exchange.getProperty("dom4jOriginal", Document.class);
		Document response = exchange.getIn().getBody(Document.class);
		
		List<Element> nodes = response.selectNodes("//responseResult/recordResults[recordResult[resultCode='-1']]");
		for (Element element: nodes){
			String uuid = element.attributeValue("recordUUID");
			Node removeNode =original.selectSingleNode("//record[@recordUUID='"+uuid+"']");
			removeNode.getParent().remove(removeNode);
			int r = 0;
		}
		String fileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);
		exchange.getOut().setHeader(Exchange.FILE_NAME, "RQ_" + fileName);
		
		StringWriter stringWriter = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		XMLWriter writer = new XMLWriter(stringWriter, format );
	    writer.write(original);
	    writer.close();
		
		exchange.getOut().setBody(stringWriter.toString());
	}
}
