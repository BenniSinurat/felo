package org.felo.api.data;

import java.util.Map;
import java.util.TreeMap;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.module.http.internal.ParameterMap;
import org.mule.transformer.AbstractMessageTransformer;

public class MessageTransformer extends AbstractMessageTransformer {

	@Override
	@SuppressWarnings("unchecked")
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		ParameterMap queryParam = message.getInboundProperty("http.query.params");
		String method = message.getInboundProperty("http.method");
		if (method.equalsIgnoreCase("GET")) {
			Map<String, Object> requestPayload = new TreeMap<String, Object>();
			requestPayload.putAll(queryParam);
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : requestPayload.entrySet()) {
				sb.append("/" + entry.getKey() + "=" + entry.getValue());
			}
			requestPayload.put("requestAuth", message.getInboundProperty("requestauth"));
			requestPayload.put("requestAuthContent", sb.toString());
			return requestPayload;
		} else {
			System.out.println("Payload : " + message.getPayload());
			Map<String, Object> requestPayload = new TreeMap<String, Object>();
			requestPayload.putAll(message.getPayload(Map.class));
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : requestPayload.entrySet()) {
				sb.append("/" + entry.getKey() + "=" + entry.getValue());
			}
			requestPayload.put("requestAuth", message.getInboundProperty("requestauth"));
			requestPayload.put("requestAuthContent", sb.toString());
			return requestPayload;
		}
	}

}
