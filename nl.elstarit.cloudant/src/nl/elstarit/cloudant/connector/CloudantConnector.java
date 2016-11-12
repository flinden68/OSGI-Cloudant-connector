package nl.elstarit.cloudant.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.SSLSocketFactory;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import nl.elstarit.cloudant.log.CloudantLogger;

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

	public CloudantConnector(){

	}

	/**
	 *
	 * @param account
	 * @param username
	 * @param password
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClient(final String account, final String username, final String password,final String dbName, final boolean create){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientImpl(account, username,  password);
					CloudantConnector.this.initAllConnectors(dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClientLocal(final String url, final String username, final String password,final String dbName, final boolean create){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientImpl(url, username,  password);
					CloudantConnector.this.initAllConnectors(dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClientProxy(final String account, final String username, final String password,final String dbName, final boolean create, final String proxyUrl, final String proxyUser, final String proxyPassword){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientProxyImpl(account, username,  password, proxyUrl, proxyUser, proxyPassword);
					CloudantConnector.this.initAllConnectors(dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClientSsl(final String account, final String username, final String password,final String dbName, final boolean create, final SSLSocketFactory factory, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientSslImpl(account, username,  password, factory, connectTimeout, readTimeout, timeUnit);
					CloudantConnector.this.initAllConnectors(dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClientAdvanced(final String account, final String username, final String password,final String dbName, final boolean create, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientAdvancedImpl(account, username,  password, connectTimeout, readTimeout, timeUnit);
					CloudantConnector.this.initAllConnectors(dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initCloudantClientLocalAdvanced(final String url, final String username, final String password,final String dbName, final boolean create, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					CloudantConnector.this.cloudantClientLocalAdvancedImpl(url, username,  password, connectTimeout, readTimeout, timeUnit);
					CloudantConnector.this.initAllConnectors(dbName, create);
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

	private void initAllConnectors(final String dbName, final boolean create){
		initCloudantDatabaseConnector(dbName, create);
		initCloudantDocumentConnector();
		initCloudantQueryConnector();
		initCloudantChangesConnector();
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

	private void cloudantClientLocalImpl(final String url, final String username, final String password){
		try {
			if(!isConnectedToCloudant()){

				client = ClientBuilder.url(new URL(url))
						.username(username)
						.password(password)
						.build();
			}
		} catch (final MalformedURLException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	private void cloudantClientProxyImpl(final String account, final String username, final String password, final String proxyUrl, final String proxyUser, final String proxyPassword){
		try {
			if(!isConnectedToCloudant()){

				client = ClientBuilder.account(account)
						.username(username)
						.password(password)
						.proxyURL(new URL(proxyUrl))
						.proxyUser(proxyUser)
						.proxyPassword(proxyPassword)
						.build();
			}
		} catch (final MalformedURLException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	private void cloudantClientSslImpl(final String account, final String username, final String password, final SSLSocketFactory factory, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		if(!isConnectedToCloudant()){
			client = ClientBuilder.account(account)
					.username(username)
					.password(password)
					.customSSLSocketFactory(factory)
					.build();
		}
	}

	private void cloudantClientAdvancedImpl(final String account, final String username, final String password, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		if(!isConnectedToCloudant()){
			client = ClientBuilder.account(account)
					.username(username)
					.password(password)
					.connectTimeout(connectTimeout, timeUnit)
					.readTimeout(readTimeout, timeUnit)
					.build();
		}
	}

	private void cloudantClientLocalAdvancedImpl(final String url, final String username, final String password, final long connectTimeout, final long readTimeout, final TimeUnit timeUnit){
		try {
			if(!isConnectedToCloudant()){
				client = ClientBuilder.url(new URL(url))
						.username(username)
						.password(password)
						.connectTimeout(connectTimeout, timeUnit)
						.readTimeout(readTimeout, timeUnit)
						.build();
			}
		} catch (final MalformedURLException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
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

	public ChangesConnector getChangesConnector() {
		return changesConnector;
	}

	public void setChangesConnector(final ChangesConnector changesConnector) {
		this.changesConnector = changesConnector;
	}

}
