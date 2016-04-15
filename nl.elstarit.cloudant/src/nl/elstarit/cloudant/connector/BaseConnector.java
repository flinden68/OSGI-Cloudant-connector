/**
 *
 */
package nl.elstarit.cloudant.connector;

import java.util.List;

import nl.elstarit.cloudant.model.ConnectorResponse;

import com.cloudant.client.api.Database;

/**
 * @author frankvanderlinden
 *
 */
public class BaseConnector {

	private Object clazz = null;
	private ConnectorResponse connectorResponse = null;

	private List<?> abstractList;
	private Database db;

	public BaseConnector(){

	}

	public Database getDb() {
		return db;
	}

	public void setDb(final Database db) {
		this.db = db;
	}

	public List<?> getAbstractList() {
		return abstractList;
	}

	public void setAbstractList(final List<?> abstractList) {
		this.abstractList = abstractList;
	}

	public Object getClazz() {
		return clazz;
	}

	public ConnectorResponse getConnectorResponse() {
		return connectorResponse;
	}

	public void setClazz(final Object clazz) {
		this.clazz = clazz;
	}

	public void setConnectorResponse(final ConnectorResponse connectorResponse) {
		this.connectorResponse = connectorResponse;
	}

}
