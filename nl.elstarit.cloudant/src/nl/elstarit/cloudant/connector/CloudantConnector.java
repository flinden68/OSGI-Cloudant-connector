package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

/**
 * https://github.com/cloudant/java-cloudant
 * @author frankvanderlinden
 *
 */
public class CloudantConnector {

	private CloudantClient client;
	private DatabaseConnector databaseConnector;
	private DocumentConnector documentConnector;

	public CloudantConnector(final String account, final String username, final String password,final String dbName, final boolean create) throws PrivilegedActionException{
		initCloudantClient(account, username, password);

		if(dbName != null || !"".equals(dbName)){
			initCloudantDatabase(dbName, create);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClient(final String account, final String username, final String password) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.cloudantClientImpl(account, username,  password);
				return null;
			}
		});
	}

	public void initCloudantDatabase(final String dbName, final boolean create) throws PrivilegedActionException{
		if(databaseConnector == null){
			databaseConnector = new DatabaseConnector(client, dbName, create);
		}
	}

	public boolean isConnectedToCloudant(){
		return client != null;
	}

	public void initCloudantDocumentConnector(){
		if(documentConnector == null){
			documentConnector = new DocumentConnector();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void switchDatabase(final CloudantClient client, final String dbName, final boolean create) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.switchDatabaseImpl(client, dbName, create);
				return null;
			}
		});
	}

	private void switchDatabaseImpl(final CloudantClient client, final String dbName, final boolean create){
		databaseConnector.setDb(client.database(dbName, create));
	}

	private void cloudantClientImpl(final String account, final String username, final String password){
		if(!isConnectedToCloudant()){
			client = ClientBuilder.account(account)
					.username(username)
					.password(password)
					.build();
		}
	}

	public DatabaseConnector getDatabaseConnector() {
		return databaseConnector;
	}

	public void setDatabaseConnector(final DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	public DocumentConnector getDocumentConnector() {
		return documentConnector;
	}

	public void setDocumentConnector(final DocumentConnector documentConnector) {
		this.documentConnector = documentConnector;
	}

}
