package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class DatabaseConnector {

	private Database db;

	private List<?> abstractList;

	public DatabaseConnector(){}

	public DatabaseConnector(final CloudantClient client, final String dbName, final boolean create){

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initDatabase(final CloudantClient client, final String dbName, final boolean create) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.initDatabaseImpl(client, dbName, create);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteDb(final CloudantClient client, final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.deleteDbImpl(client, dbName);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createDb(final CloudantClient client,final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.createDbImpl(client, dbName);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDatabases(final CloudantClient client) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.findAllDatabasesImpl(client);
				return null;
			}
		});

		return abstractList;
	}

	/*
	 * Impl methods
	 */
	private void initDatabaseImpl(final CloudantClient client, final String dbName, final boolean create){
		db = client.database(dbName, create);
	}

	private void findAllDatabasesImpl(final CloudantClient client){
		abstractList = client.getAllDbs();
	}

	private void deleteDbImpl(final CloudantClient client,final String dbName){
		client.deleteDB(dbName);
	}

	private void createDbImpl(final CloudantClient client,final String dbName){
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
