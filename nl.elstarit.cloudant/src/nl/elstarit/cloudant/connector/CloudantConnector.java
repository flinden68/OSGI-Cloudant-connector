package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;

import nl.elstarit.cloudant.log.CloudantLogger;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

/**
 * <pre>
 * {@code
 * CloudantConnector connector = new CloudantConnector(account, username, password, dbName, false);
 *
 * connector.switchDatabase(dbName, create)
 * }
 * </pre>
 * @author frank van der linden
 */
public class CloudantConnector {

	private CloudantClient client;
	private DatabaseConnector databaseConnector;
	private DocumentConnector documentConnector;
	private QueryConnector queryConnector;
	private ChangesConnector changesConnector;

	/**
	 *
	 * @param account
	 * @param username
	 * @param password
	 * @param dbName
	 * @param create
	 */
	public CloudantConnector(final String account, final String username, final String password,final String dbName, final boolean create){
		initCloudantClient(account, username, password);

		initCloudantDatabaseConnector(dbName, create);
		initCloudantDocumentConnector();
		initCloudantQueryConnector();
		initCloudantChangesConnector();
	}

	/**
	 *
	 * @param account
	 * @param username
	 * @param password
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClient(final String account, final String username, final String password){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientImpl(account, username,  password);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @return boolean, if client is already connected
	 */
	public boolean isConnectedToCloudant(){
		return client != null;
	}

	/**
	 *
	 * @param dbName
	 * @param create
	 */
	private void initCloudantDatabaseConnector(final String dbName, final boolean create){
		if(databaseConnector == null){
			databaseConnector = new DatabaseConnector(client, dbName, create);
		}
	}

	private void initCloudantDocumentConnector(){
		if(documentConnector == null){
			documentConnector = new DocumentConnector();
			documentConnector.setDb(databaseConnector.getDb());
		}
	}

	private void initCloudantQueryConnector(){
		if(queryConnector == null){
			queryConnector = new QueryConnector();
			queryConnector.setDb(databaseConnector.getDb());
		}
	}

	private void initCloudantChangesConnector(){
		if(changesConnector == null){
			changesConnector = new ChangesConnector();
			changesConnector.setDb(databaseConnector.getDb());
		}
	}

	/**
	 *
	 * @param dbName
	 * @param create
	 */
	public void switchDatabase(final String dbName, final boolean create){
		databaseConnector.setDb(client.database(dbName, create));
		if(documentConnector != null){
			documentConnector.setDb(databaseConnector.getDb());
		}

		if(queryConnector != null){
			queryConnector.setDb(databaseConnector.getDb());
		}
	}

	private void cloudantClientImpl(final String account, final String username, final String password){
		if(!isConnectedToCloudant()){
			client = ClientBuilder.account(account)
					.username(username)
					.password(password)
					.build();
		}
	}

	/*
	 * Getters and Setters
	 */
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

	public QueryConnector getQueryConnector() {
		return queryConnector;
	}

	public void setQueryConnector(final QueryConnector queryConnector) {
		this.queryConnector = queryConnector;
	}

}
