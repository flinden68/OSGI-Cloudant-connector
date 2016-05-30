/**
 *
 */
package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorChangesResult;

import com.cloudant.client.api.Changes;
import com.cloudant.client.api.model.ChangesResult;
import com.cloudant.client.api.model.DbInfo;

/**
 * @author frankvanderlinden
 *
 */
public class ChangesConnector extends BaseConnector {

	private ConnectorChangesResult result;

	public ChangesConnector(){

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConnectorChangesResult normalFeed(final int limit, final String filter){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					ChangesConnector.this.normalFeed(limit, filter);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConnectorChangesResult continuesFeed(final long heartBeat){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					ChangesConnector.this.continuesFeedImpl(heartBeat);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getResult();
	}


	/**
	 * Impl methods
	 */

	// feed type normal
	public void normalFeedImpl(final int limit, final String filter){

		final String since = getDb().info().getUpdateSeq(); // latest update seq
		final ChangesResult changeResult = getDb().changes()
				.since(since)
				.limit(limit)
				.filter(filter)
				.getChanges();

		if(changeResult != null){
			result = new ConnectorChangesResult(changeResult);
		}
	}

	// feed type continuous
	public void continuesFeedImpl(final long heartBeat){

		final DbInfo dbInfo = getDb().info();
		final String since = dbInfo.getUpdateSeq();
		final Changes changes = getDb().changes()
				.includeDocs(true)
				.since(since)
				.heartBeat(heartBeat)
				.continuousChanges();

		if(changes != null){
			result = new ConnectorChangesResult(changes.getChanges());
		}

	}

	public ConnectorChangesResult getResult() {
		return result;
	}

	public void setResult(final ConnectorChangesResult result) {
		this.result = result;
	}
}
