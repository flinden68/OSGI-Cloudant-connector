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

	public DocumentConnector(){}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(final Database db, final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.saveImpl(db, obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(final Database db, final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.updateImpl(db, obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(final Database db, final Object obj) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.removeImpl(db, obj);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object find(final Database db, final Class<?> cls, final String id) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findImpl(db,cls,id);
				return null;
			}
		});

		return clazz;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createBulk(final Database db, final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.createBulkImpl(db, list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBulk(final Database db, final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.updateBulkImpl(db, list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteBulk(final Database db, final List<?> list) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.removeBulkImpl(db, list);
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentIds(final Database db) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findAllDocumentIdsImpl(db);
				return null;
			}
		});

		return abstractList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentsById(final Database db, final Class<?> cls, final String[] docIds) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findAllDocumentsByIdImpl(db, cls, docIds);
				return null;
			}
		});

		return abstractList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocuments(final Database db, final Class<?> cls) throws PrivilegedActionException{
		AccessController.doPrivileged(new PrivilegedExceptionAction() {
			@Override
			public Object run() throws Exception {
				DocumentConnector.this.findAllDocumentsImpl(db, cls);
				return null;
			}
		});

		return abstractList;
	}

	/*
	 * Impl methods
	 */

	private void saveImpl(final Database db, final Object obj){
		db.save(obj);
	}

	private void updateImpl(final Database db, final Object obj){
		db.update(obj);
	}

	private void removeImpl(final Database db, final Object obj){
		db.remove(obj);
	}

	private void findImpl(final Database db, final Class<?> cls, final String id){
		clazz = db.find(cls, id);
	}

	private void updateBulkImpl(final Database db, final List<?> list){
		db.bulk(list);
	}

	private void createBulkImpl(final Database db, final List<?> list){
		db.bulk(list);
	}

	private void removeBulkImpl(final Database db, final List<?> list){
		for(final Object obj : list){

			db.remove(obj);
		}
	}

	private void findAllDocumentsImpl(final Database db, final Class<?> cls){
		try {
			abstractList = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(cls);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentIdsImpl(final Database db){
		try {
			abstractList = db.getAllDocsRequestBuilder().build().getResponse().getDocIds();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentsByIdImpl(final Database db, final Class<?> cls, final String[] docIds){
		try {
			abstractList = db.getAllDocsRequestBuilder().keys(docIds).includeDocs(true).build().getResponse().getDocsAs(cls);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
