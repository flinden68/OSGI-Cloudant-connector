package nl.elstarit.cloudant.connector;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.cloudant.client.api.model.DesignDocument;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorResponse;

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
	public ConnectorResponse save(final Object obj){
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
		return getConnectorResponse();
	}

	/**
	 *
	 * @param obj
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConnectorResponse update(final Object obj){
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

		return getConnectorResponse();
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
	 * @param designDoc, name of the design doc
	 * @param viewName, name of the View
	 * @param keyType, specify what the key is for type, default STRING, but could also NUMBER or BOOLEAN
	 * @param limit, the limit or results returned
	 * @return List of all documents based from a view
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentsFromView(final Class<?> cls, final String designDoc, final String viewName, final String keyType, final int limit){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findAllDocumentsFromViewImpl(cls, designDoc, viewName, keyType, limit);
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
	 * @param designDoc, name of the design doc
	 * @param viewName, name of the View
	 * @param keyType, specify what the key is for type, default STRING, but could also NUMBER or BOOLEAN
	 * @param limit, the limit or results returned
	 * @param startKey, key where to start in the view
	 * @param endKey, key where to end in the view
	 * @return List of all documents based from a view
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDocumentsFromViewKeys(final Class<?> cls, final String designDoc, final String viewName, final String keyType, final int limit, final String startKey, final String endKey){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DocumentConnector.this.findAllDocumentsFromViewKeysImpl(cls, designDoc, viewName, keyType, limit, startKey, endKey );
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
	public ConnectorResponse saveStandAloneAttachment(final InputStream inputStream, final String name, final String contentType, final String docId, final String docRev){
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
		final Response response = getDb().save(obj);
		setConnectorResponse(new ConnectorResponse(response));
	}

	private void updateImpl(final Object obj){
		final Response response = getDb().update(obj);
		setConnectorResponse(new ConnectorResponse(response));
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

	@SuppressWarnings("unused")
	private void findAllDocumentsFromViewImpl(final Class<?> cls, final String designDoc, final String viewName, final String keyType, final int limit){
		try {
			final List<?> list = getDb().getViewRequestBuilder(designDoc,viewName)
					.newRequest(Key.Type.STRING, Object.class)
					.limit(limit)
					.includeDocs(true)
					.build()
					.getResponse()
					.getDocsAs(cls);

			setAbstractList(list);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void findAllDocumentsFromViewKeysImpl(final Class<?> cls, final String designDoc, final String viewName, final String keyType, final int limit, final String startKey, final String endKey){
		try {
			final List<?> list = getDb().getViewRequestBuilder(designDoc,viewName)
					.newRequest(Key.Type.STRING, Object.class)
					.startKey(startKey)
					.endKey(endKey)
					.limit(limit)
					.includeDocs(true)
					.build()
					.getResponse()
					.getDocsAs(cls);

			setAbstractList(list);
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

	private void createDesignDocumentImpl(final Map<String, String> updates, final String designDocument){
		final DesignDocument ddoc = new DesignDocument();
		ddoc.setId("_design/"+designDocument);
		getDb().save(ddoc);
	}

	private void updateDesignDocumentImpl(final Map<String, String> updates, final String designDocument){
		final DesignDocument ddoc = getDb().find(DesignDocument.class, "_design/"+designDocument);
		// Call setters to update values
		final Map<String, String> newUpdates = new HashMap<String, String>();
		newUpdates.put("newUpdateHandler", "function (doc, req) { ... }");
		ddoc.setUpdates(updates);
		// Update the design document
		getDb().update(ddoc);
	}


	@SuppressWarnings("rawtypes")
	private Key.Type getKeyType(final String keyType){
		if("BOOLEAN".equals(keyType)){
			return Key.Type.BOOLEAN;
		}else if("NUMBER".equals(keyType)){
			return Key.Type.NUMBER;
		}
		return Key.Type.STRING;
	}


}
