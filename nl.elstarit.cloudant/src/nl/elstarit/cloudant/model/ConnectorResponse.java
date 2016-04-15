package nl.elstarit.cloudant.model;

import com.cloudant.client.api.model.Response;

public class ConnectorResponse extends Response {

	private String message;

	public ConnectorResponse(){}

	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}

}
