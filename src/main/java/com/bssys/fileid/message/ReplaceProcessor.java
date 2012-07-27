package com.bssys.fileid.message;

import java.io.StringReader;

import org.apache.camel.Exchange;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ReplaceProcessor {
	
	public void execute(Exchange exchange, String xpath, String variable, String invariable){
		
		String strVariable = exchange.getProperty(variable, String.class);
		String strInVariable = exchange.getProperty(invariable, String.class);

		Document docVariable = null;
		Document docInVariable = null;
		try {
			SAXReader reader = new SAXReader();
			docVariable = reader.read(new StringReader(strVariable));
			docInVariable = reader.read(new StringReader(strInVariable));
			Node node = docInVariable.selectSingleNode(xpath);
			Element parent = node.getParent();
			if (parent!=null){
				parent.remove(node);
				parent.add(docVariable);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
