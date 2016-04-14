package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class DatabaseConnector {

	private Database db;
	private CloudantClient client;

	private List<?> abstractList;

	public DatabaseConnector(){}

	public DatabaseConnector(final CloudantClient client, final String dbName, final boolean create) throws PrivilegedActionException{
		initDatabase(client, dbName, create);
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
	public void deleteDb(final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.deleteDbImpl(dbName);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createDb(final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.createDbImpl(dbName);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDatabases() throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DatabaseConnector.this.findAllDatabasesImpl();
				return null;
			}
		});

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
