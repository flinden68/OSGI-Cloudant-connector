/**
 *
 */
package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorIndex;

import com.cloudant.client.api.model.Index;


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

}
