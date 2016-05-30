/**
 *
 */
package nl.elstarit.cloudant.model;

import java.util.List;

import com.cloudant.client.api.model.ChangesResult;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorChangesResult {

	private String lastSeq;
	private List<ConnectorChangesResultRow> results;

	public ConnectorChangesResult(final ChangesResult result){
		loadResults(result);
		lastSeq = result.getLastSeq();
	}

	private void loadResults(final ChangesResult result){
		for(final ChangesResult.Row row : result.getResults()){
			results.add( new ConnectorChangesResultRow(row));
		}
	}

	public String getLastSeq() {
		return lastSeq;
	}

	public void setLastSeq(final String lastSeq) {
		this.lastSeq = lastSeq;
	}

	public List<ConnectorChangesResultRow> getResults() {
		return results;
	}

	public void setResults(final List<ConnectorChangesResultRow> results) {
		this.results = results;
	}
}
