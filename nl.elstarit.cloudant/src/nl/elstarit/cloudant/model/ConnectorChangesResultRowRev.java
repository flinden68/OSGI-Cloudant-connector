/**
 *
 */
package nl.elstarit.cloudant.model;

import com.cloudant.client.api.model.ChangesResult;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorChangesResultRowRev {

	private String rev;

	public ConnectorChangesResultRowRev(final ChangesResult.Row.Rev rev){
		this.rev = rev.getRev();
	}

	public String getRev() {
		return rev;
	}

	public void setRev(final String rev) {
		this.rev = rev;
	}
}
