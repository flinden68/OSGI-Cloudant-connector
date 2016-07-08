package nl.elstarit.cloudant.model;

import com.cloudant.client.api.model.Response;

public class ConnectorResponse{

	private String error;
	private String id;
	private String rev;
	private String reason;
	private int statusCode;

	public ConnectorResponse(){}

	@Override
	public String toString() {
		return "ConnectorResponse [error=" + error + ", id=" + id + ", rev="
				+ rev + ", reason=" + reason + ", statusCode=" + statusCode
				+ "]";
	}

	public ConnectorResponse(final Response response){
		this.error = response.getError();
		this.id = response.getId();
		this.rev = response.getRev();
		this.reason = response.getReason();
		this.statusCode = response.getStatusCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((rev == null) ? 0 : rev.hashCode());
		result = prime * result + statusCode;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ConnectorResponse other = (ConnectorResponse) obj;
		if (error == null) {
			if (other.error != null) {
				return false;
			}
		} else if (!error.equals(other.error)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (reason == null) {
			if (other.reason != null) {
				return false;
			}
		} else if (!reason.equals(other.reason)) {
			return false;
		}
		if (rev == null) {
			if (other.rev != null) {
				return false;
			}
		} else if (!rev.equals(other.rev)) {
			return false;
		}
		if (statusCode != other.statusCode) {
			return false;
		}
		return true;
	}

	public String getError() {
		return error;
	}

	public String getId() {
		return id;
	}

	public String getRev() {
		return rev;
	}

	public String getReason() {
		return reason;
	}

	public int getStatusCode() {
		return statusCode;
	}


}
