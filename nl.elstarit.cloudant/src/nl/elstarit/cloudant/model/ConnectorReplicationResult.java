/**
 *
 */
package nl.elstarit.cloudant.model;

import java.util.List;

import com.cloudant.client.api.model.ReplicationResult;
import com.cloudant.client.api.model.ReplicationResult.ReplicationHistory;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorReplicationResult {

	private List<ConnectorReplicationHistory> histories;
	private String localId;
	private String sessionId;
	private String sourceLastSeq;
	private boolean isOk;

	public ConnectorReplicationResult(final ReplicationResult result){
		loadHistories(result);

	}

	private void loadHistories(final ReplicationResult result){
		for(final ReplicationHistory history : result.getHistories()){
			histories.add( new ConnectorReplicationHistory(history));
		}
	}

	public List<ConnectorReplicationHistory> getHistories() {
		return histories;
	}

	public void setHistories(final List<ConnectorReplicationHistory> histories) {
		this.histories = histories;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(final String localId) {
		this.localId = localId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSourceLastSeq() {
		return sourceLastSeq;
	}

	public void setSourceLastSeq(final String sourceLastSeq) {
		this.sourceLastSeq = sourceLastSeq;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(final boolean isOk) {
		this.isOk = isOk;
	}
}
