package nl.elstarit.cloudant.connector;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.logging.Level;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorResponse;

import com.cloudant.client.api.model.Response;

/**
 * @author frank van der linden
 *
 */

public class DocumentConnector extends BaseConnector {

	public DocumentConnector(){}

	/**
	 *
	 * @param obj
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void save(final Object obj){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.saveImpl(obj);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @param obj
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(final Object obj){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.updateImpl(obj);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @param obj
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(final Object obj){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.removeImpl(obj);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @param cls
	 * @param id
	 * @return Document
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object find(final Class<?> cls, final String id){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findImpl(cls,id);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getClazz();
	}

	/**
	 *
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createBulk(final List<?> list){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.createBulkImpl(list);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBulk(final List<?> list){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.updateBulkImpl(list);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}


	/**
	 *
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteBulk(final List<?> list){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.removeBulkImpl(list);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * @return List of All docIds
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentIds(){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findAllDocumentIdsImpl();
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	/**
	 *
	 * @param cls
	 * @param docIds
	 * @return List of all documents based on a array of docIds
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentsById(final Class<?> cls, final String[] docIds){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findAllDocumentsByIdImpl(cls, docIds);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	/**
	 *
	 * @param cls
	 * @return List of all documents in the database
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocuments(final Class<?> cls){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findAllDocumentsImpl(cls);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	/**
	 *
	 * @param inputStream
	 * @param name
	 * @param contentType
	 * @param docId
	 * @param docRev
	 * @return ConnectorResponse, with the revId, docId and more...
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConnectorResponse saveAttachment(final InputStream inputStream, final String name, final String contentType, final String docId, final String docRev){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.saveAttachmentImpl(inputStream, name, contentType, docId, docRev);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getConnectorResponse();
	}

	/*
	 * Impl methods
	 */

	private void saveImpl(final Object obj){
		getDb().save(obj);
	}

	private void updateImpl(final Object obj){
		getDb().update(obj);
	}

	private void removeImpl(final Object obj){
		getDb().remove(obj);
	}

	private void findImpl(final Class<?> cls, final String id){
		setClazz(getDb().find(cls, id));
	}

	private void updateBulkImpl(final List<?> list){
		getDb().bulk(list);
	}

	private void createBulkImpl(final List<?> list){
		getDb().bulk(list);
	}

	private void removeBulkImpl(final List<?> list){
		for(final Object obj : list){

			getDb().remove(obj);
		}
	}

	private void findAllDocumentsImpl(final Class<?> cls){
		try {
			setAbstractList(getDb().getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(cls));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentIdsImpl(){
		try {
			setAbstractList(getDb().getAllDocsRequestBuilder().build().getResponse().getDocIds());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllDocumentsByIdImpl(final Class<?> cls, final String[] docIds){
		try {
			setAbstractList(getDb().getAllDocsRequestBuilder().keys(docIds).includeDocs(true).build().getResponse().getDocsAs(cls));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void saveAttachmentImpl(final InputStream inputStream, final String name, final String contentType, final String docId, final String docRev){
		Response response = null;
		if(docId != null || docRev != null){
			response = getDb().saveAttachment(inputStream, name, contentType, docId, docRev);
		}else{
			response = getDb().saveAttachment(inputStream, name, contentType);
		}

		setConnectorResponse(new ConnectorResponse(response));
	}

	/*
	 * Getters and Setters
	 */

}
