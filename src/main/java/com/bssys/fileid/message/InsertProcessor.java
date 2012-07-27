package com.bssys.fileid.message;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.apache.camel.Exchange;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class InsertProcessor {

	public void execute(String variable, String xpath, String invariable, Exchange exchange) {

		String strVariable = exchange.getProperty(variable, String.class);
		String strInVariable = exchange.getProperty(invariable, String.class);

		Document docVariable = null;
		Document docInVariable = null;
		try {
			SAXReader reader = new SAXReader();
			docVariable = reader.read(new StringReader(strVariable));
			docInVariable = reader.read(new StringReader(strInVariable));
			
			List <Element>nodes = docInVariable.selectNodes(xpath);
			for(Element element: nodes){
				element.add(docVariable.getRootElement());
			}
			StringWriter stringWriter = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(stringWriter, format );
		    writer.write(docInVariable);
		    writer.close();
		    
		    exchange.setProperty(invariable, stringWriter.toString());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
