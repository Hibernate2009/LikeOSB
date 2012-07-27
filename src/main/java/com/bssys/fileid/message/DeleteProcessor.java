package com.bssys.fileid.message;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.camel.Exchange;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DeleteProcessor {
	
	public void execute(String invariable, String xpath, Exchange exchange ) {
		
		String strVariable = exchange.getProperty(invariable, String.class);
		
		Document document = null;
	    try {
	    	SAXReader reader = new SAXReader();
			document = reader.read(new StringReader(strVariable));
			Node node = document.selectSingleNode(xpath);
			Element parent = node.getParent();
			if (parent!=null){
				parent.remove(node);
			}
			StringWriter stringWriter = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			XMLWriter writer = new XMLWriter(stringWriter, format );
		    writer.write(document);
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
