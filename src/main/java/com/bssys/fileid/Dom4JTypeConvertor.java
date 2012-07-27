package com.bssys.fileid;

import java.io.StringReader;

import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.TypeConverter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Dom4JTypeConvertor implements TypeConverter{

	@Override
	public <T> T convertTo(Class<T> type, Object value) {
		// TODO Auto-generated method stub
		String originalValue = (String)value;
		SAXReader reader = new SAXReader();
		Document document = null;
	    try {
			document = reader.read(new StringReader(originalValue));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T)document;
	}

	@Override
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {
		// TODO Auto-generated method stub
		return convertTo(type, value);
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Object value) throws NoTypeConversionAvailableException {
		// TODO Auto-generated method stub
		return convertTo(type, value);
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Exchange exchange, Object value) throws NoTypeConversionAvailableException {
		// TODO Auto-generated method stub
		return convertTo(type, value);
	}

}
