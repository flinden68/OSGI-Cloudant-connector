/**
 *
 */
package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.cloudant.client.api.model.Index;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorIndex;


/**
 * @author frankvanderlinden
 *
 */
public class QueryConnector extends BaseConnector {

	public QueryConnector(){

	}

	/**
	 *
	 * @param searchIndexId
	 * @param cls
	 * @param queryLimit
	 * @param query
	 * @return List of query result
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> search(final String searchIndexId, final Class<?> cls, final Integer queryLimit, final String query){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					QueryConnector.this.searchImpl(searchIndexId, cls, queryLimit, query);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> allIndices(){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					QueryConnector.this.allIndicesImpl();
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findByIndex(final String selectorJson, final Class<?> cls){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					QueryConnector.this.findByIndexImpl(selectorJson, cls);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createIndex(final Map<String, Object> search, final String searchindex){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					QueryConnector.this.createIndexImpl(search, searchindex);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

	}

	/*
	 * Impl methods
	 */

	private void searchImpl(final String searchIndexId, final Class<?> cls, final Integer queryLimit, final String query){
		setAbstractList(getDb().search(searchIndexId)
				.limit(queryLimit)
				.includeDocs(true)
				.query(query, cls));
	}

	private void allIndicesImpl(){
		final List<ConnectorIndex> connectorIndices = new ArrayList<ConnectorIndex>();
		final List<Index> indices = getDb().listIndices();
		for(final Index index : indices){
			connectorIndices.add(new ConnectorIndex(index));
		}

		setAbstractList(connectorIndices);
	}

	private void findByIndexImpl(final String selectorJson, final Class<?> cls){
		setAbstractList(getDb().findByIndex(selectorJson, cls));
	}

	private void createIndexImpl(final Map<String, Object> search, final String searchindex){

		final Map<String, Object> indexes = new HashMap<String, Object>();
		indexes.put("search", search);
		final Map<String, Object> ddoc = new HashMap<String, Object>();
		ddoc.put("_id", "_design/"+searchindex);
		ddoc.put("indexes", indexes);

		getDb().save(ddoc);
	}


}
