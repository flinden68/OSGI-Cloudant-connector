package nl.elstarit.cloudant.connector;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

/**
 * https://github.com/cloudant/java-cloudant
 * @author frankvanderlinden
 *
 */
public class CloudantConnector {

	private CloudantClient client;
	private Database db;
	private Object clazz = null;
	private List<?> abstractList;

	public CloudantConnector(final String account, final String username, final String password) throws PrivilegedActionException{
		initCloudantClient(account, username, password);
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

	public boolean isConnectedToCloudant(){
		return client != null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setDatabase(final String dbName, final boolean createDb) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.setDatabaseImpl(dbName, createDb);
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<?> findAllDatabases() throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.findAllDatabasesImpl();
				return null;
			}
		});

		return abstractList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.saveImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.updateImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.removeImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object find(final Class<?> cls, final String id) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.findImpl(cls,id);
				return null;
			}
		});

		return clazz;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createBulk(final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.createBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBulk(final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.updateBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteBulk(final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.removeBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentIds() throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.findAllDocumentIdsImpl();
				return null;
			}
		});

		return abstractList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentsById(final Class<?> cls, final String[] docIds) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.findAllDocumentsByIdImpl(cls, docIds);
				return null;
			}
		});

		return abstractList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocuments(final Class<?> cls) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.findAllDocumentsImpl(cls);
				return null;
			}
		});

		return abstractList;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteDb(final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.deleteDbImpl(dbName);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createDb(final String dbName) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				CloudantConnector.this.createDbImpl(dbName);
				return null;
			}
		});
	}


	/*Implementation methods*/
	private void saveImpl(final Object obj){
		db.save(obj);
	}

	private void updateImpl(final Object obj){
		db.update(obj);
	}

	private void removeImpl(final Object obj){
		db.remove(obj);
	}

	private void findImpl(final Class<?> cls, final String id){
		clazz = db.find(cls, id);
	}

	private void updateBulkImpl(final List<?> list){
		db.bulk(list);
	}

	private void createBulkImpl(final List<?> list){
		db.bulk(list);
	}

	private void removeBulkImpl(final List<?> list){
		for(final Object obj : list){
			db.remove(obj);
		}
	}

	private void findAllDocumentsImpl(final Class<?> cls){
		try {
			abstractList = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(cls);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentIdsImpl(){
		try {
			abstractList = db.getAllDocsRequestBuilder().build().getResponse().getDocIds();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentsByIdImpl(final Class<?> cls, final String[] docIds){
		try {
			abstractList = db.getAllDocsRequestBuilder().keys(docIds).includeDocs(true).build().getResponse().getDocsAs(cls);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	/*Database actions*/
	private void findAllDatabasesImpl(){
		abstractList = client.getAllDbs();
	}

	private void setDatabaseImpl(final String dbName, final boolean createDb){
		db = client.database(dbName, createDb);
	}

	private void deleteDbImpl(final String dbName){
		client.deleteDB(dbName);
	}

	private void createDbImpl(final String dbName){
		client.createDB(dbName);
	}

	public void cloudantClientImpl(final String account, final String username, final String password){
		if(!isConnectedToCloudant()){
			client = ClientBuilder.account(account)
					.username(username)
					.password(password)
					.build();
		}
	}

}
