/**
 *
 */
package nl.elstarit.cloudant.model;

import com.cloudant.client.api.model.DbInfo;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorDbInfo {

	private String dbName;
	private int diskFormatVersion;
	private long diskSize;
	private long docCount;
	private String docDelCount;
	private long instanceStartTime;
	private long purgeSeq;
	private boolean compactRunning;

	public ConnectorDbInfo(final DbInfo dbInfo){
		dbName = dbInfo.getDbName();
		diskFormatVersion = dbInfo.getDiskFormatVersion();
		diskSize = dbInfo.getDiskSize();
		docCount = dbInfo.getDocCount();
		docDelCount = dbInfo.getDocDelCount();
		instanceStartTime = dbInfo.getInstanceStartTime();
		purgeSeq = dbInfo.getPurgeSeq();
		compactRunning = dbInfo.isCompactRunning();
	}

	public String getDbName() {
		return dbName;
	}
	public void setDbName(final String dbName) {
		this.dbName = dbName;
	}
	public int getDiskFormatVersion() {
		return diskFormatVersion;
	}
	public void setDiskFormatVersion(final int diskFormatVersion) {
		this.diskFormatVersion = diskFormatVersion;
	}
	public long getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(final long diskSize) {
		this.diskSize = diskSize;
	}
	public long getDocCount() {
		return docCount;
	}
	public void setDocCount(final long docCount) {
		this.docCount = docCount;
	}
	public String getDocDelCount() {
		return docDelCount;
	}
	public void setDocDelCount(final String docDelCount) {
		this.docDelCount = docDelCount;
	}
	public long getInstanceStartTime() {
		return instanceStartTime;
	}
	public void setInstanceStartTime(final long instanceStartTime) {
		this.instanceStartTime = instanceStartTime;
	}
	public long getPurgeSeq() {
		return purgeSeq;
	}
	public void setPurgeSeq(final long purgeSeq) {
		this.purgeSeq = purgeSeq;
	}
	public boolean isCompactRunning() {
		return compactRunning;
	}
	public void setCompactRunning(final boolean compactRunning) {
		this.compactRunning = compactRunning;
	}

}
