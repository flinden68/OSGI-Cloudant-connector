package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private final static Logger LOGGER = Logger.getLogger(CloudantConnector.class.getName());
	private CloudantClient client;
	private DatabaseConnector databaseConnector;
	private DocumentConnector documentConnector;

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
	 * @param dbName
	 * @param create
	 */
	public void initCloudantDatabaseConnector(final String dbName, final boolean create){
		if(databaseConnector == null){
			databaseConnector = new DatabaseConnector(client, dbName, create);
		}
	}

	/**
	 *
	 * @return boolean, if client is already connected
	 */
	public boolean isConnectedToCloudant(){
		return client != null;
	}

	public void initCloudantDocumentConnector(){
		if(documentConnector == null){
			documentConnector = new DocumentConnector();
			documentConnector.setDb(databaseConnector.getDb());
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

}
