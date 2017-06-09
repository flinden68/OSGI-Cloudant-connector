/**
 *
 */
package nl.elstarit.cloudant.connector;

import java.util.List;

import com.cloudant.client.api.Database;

import nl.elstarit.cloudant.model.ConnectorDbInfo;
import nl.elstarit.cloudant.model.ConnectorResponse;

/**
 * @author frankvanderlinden
 * http://codenav.org/code.html?project=/com/cloudant/cloudant-client/1.0.0-beta1&path=/Source%20Packages/test.java.com.cloudant.tests/ChangeNotificationsTest.java
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

	public ConnectorDbInfo getConnectorDbInfo(){
		return new ConnectorDbInfo(db.info());
	}

}
