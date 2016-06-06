/**
 *
 */
package nl.elstarit.cloudant.model;

import com.cloudant.client.api.model.ReplicationResult.ReplicationHistory;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorReplicationHistory {

	private long docsRead;
	private long docsWritten;
	private long docWriteFailures;
	private String endLastSeq;
	private String endTime;
	private long missingChecked;
	private long missingFound;
	private String recordedSeq;
	private String sessionId;
	private String startLastSeq;
	private String startTime;

	public ConnectorReplicationHistory(final ReplicationHistory history){
		docsRead = history.getDocsRead();
		docsWritten = history.getDocsWritten();
		docWriteFailures = history.getDocWriteFailures();
		endLastSeq = history.getEndLastSeq();
		endTime = history.getEndTime();
		missingChecked = history.getMissingChecked();
		missingFound = history.getMissingFound();
		recordedSeq = history.getRecordedSeq();
		sessionId = history.getSessionId();
		startLastSeq = history.getStartLastSeq();
		startTime = history.getStartTime();
	}

	public long getDocsRead() {
		return docsRead;
	}

	public void setDocsRead(final long docsRead) {
		this.docsRead = docsRead;
	}

	public long getDocsWritten() {
		return docsWritten;
	}

	public void setDocsWritten(final long docsWritten) {
		this.docsWritten = docsWritten;
	}

	public long getDocWriteFailures() {
		return docWriteFailures;
	}

	public void setDocWriteFailures(final long docWriteFailures) {
		this.docWriteFailures = docWriteFailures;
	}

	public String getEndLastSeq() {
		return endLastSeq;
	}

	public void setEndLastSeq(final String endLastSeq) {
		this.endLastSeq = endLastSeq;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	public long getMissingChecked() {
		return missingChecked;
	}

	public void setMissingChecked(final long missingChecked) {
		this.missingChecked = missingChecked;
	}

	public long getMissingFound() {
		return missingFound;
	}

	public void setMissingFound(final long missingFound) {
		this.missingFound = missingFound;
	}

	public String getRecordedSeq() {
		return recordedSeq;
	}

	public void setRecordedSeq(final String recordedSeq) {
		this.recordedSeq = recordedSeq;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStartLastSeq() {
		return startLastSeq;
	}

	public void setStartLastSeq(final String startLastSeq) {
		this.startLastSeq = startLastSeq;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

}
