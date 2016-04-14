package nl.elstarit.cloudant.connector;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;

import com.cloudant.client.api.Database;


public class DocumentConnector {

	private Object clazz = null;
	private List<?> abstractList;
	private Database db;

	public DocumentConnector(){}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.saveImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(final Database db, final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.updateImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(final Database db, final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.removeImpl(obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object find(final Class<?> cls, final String id) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findImpl(cls,id);
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
				DocumentConnector.this.createBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBulk(final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.updateBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteBulk(final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.removeBulkImpl(list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentIds() throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findAllDocumentIdsImpl();
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
				DocumentConnector.this.findAllDocumentsByIdImpl(cls, docIds);
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
				DocumentConnector.this.findAllDocumentsImpl(cls);
				return null;
			}
		});

		return abstractList;
	}

	/*
	 * Impl methods
	 */

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

	public Database getDb() {
		return db;
	}

	public void setDb(final Database db) {
		this.db = db;
	}

}
