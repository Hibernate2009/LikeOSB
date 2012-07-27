package com.bssys.fileid.message;

import org.apache.camel.Exchange;

public class AssignProcessor {
	

	public void execute(String variable, String expression, Exchange exchange) {
		int r=0;
		exchange.setProperty(variable, expression);
		exchange.getOut().setBody(expression);
	}
}
