package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.logging.Level;

import nl.elstarit.cloudant.log.CloudantLogger;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

/**
 * <pre>
 * {@code
 * CloudantConnector connector = new CloudantConnector(account, username, password, dbName, false);
 *
 * List<String> dbs = (List<String>) connector.getDatabaseConnector().findAllDatabases();
 * }
 * </pre>
 * @author frank van der linden
 */

public class DatabaseConnector {
	private Database db;
	private CloudantClient client;

	private List<?> abstractList;

	public DatabaseConnector(){}

	public DatabaseConnector(final CloudantClient client, final String dbName, final boolean create){
		initDatabase(client, dbName, create);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initDatabase(final CloudantClient client, final String dbName, final boolean create){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.initDatabaseImpl(client, dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteDb(final String dbName){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.deleteDbImpl(dbName);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createDb(final String dbName){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.createDbImpl(dbName);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDatabases(){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.findAllDatabasesImpl();
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return abstractList;
	}

	private void setCLient(final CloudantClient client){
		this.client = client;
	}

	/*
	 * Impl methods
	 */
	private void initDatabaseImpl(final CloudantClient client, final String dbName, final boolean create){
		setCLient(client);
		db = client.database(dbName, create);
	}

	private void findAllDatabasesImpl(){
		abstractList = client.getAllDbs();
	}

	private void deleteDbImpl(final String dbName){
		client.deleteDB(dbName);
	}

	private void createDbImpl(final String dbName){
		client.createDB(dbName);
	}

	/*
	 * Getters and Setters
	 */

	public Database getDb() {
		return db;
	}

	public void setDb(final Database db) {
		this.db = db;
	}

}
