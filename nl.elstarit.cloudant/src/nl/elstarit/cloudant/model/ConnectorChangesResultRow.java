/**
 *
 */
package nl.elstarit.cloudant.model;

import java.util.List;

import com.cloudant.client.api.model.ChangesResult;
import com.google.gson.JsonObject;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorChangesResultRow {

	private List<ConnectorChangesResultRowRev> changes;
	private JsonObject doc;
	private String id;
	private String seq;
	private boolean deleted;


	public ConnectorChangesResultRow(final ChangesResult.Row row){
		loadChangesResults(row);
		doc = row.getDoc();
		id = row.getId();
		seq = row.getSeq();
		deleted = row.isDeleted();
	}

	private void loadChangesResults(final ChangesResult.Row row){
		for(final ChangesResult.Row.Rev rev : row.getChanges()){
			changes.add(new ConnectorChangesResultRowRev(rev));

		}
	}

	public List<ConnectorChangesResultRowRev> getChanges() {
		return changes;
	}

	public void setChanges(final List<ConnectorChangesResultRowRev> changes) {
		this.changes = changes;
	}

	public JsonObject getDoc() {
		return doc;
	}

	public void setDoc(final JsonObject doc) {
		this.doc = doc;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(final String seq) {
		this.seq = seq;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}

}
