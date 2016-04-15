/**
 *
 */
package nl.elstarit.cloudant.model;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorIndexField {

	public enum SortOrder {
		asc,
		desc
	}

	private final String  name;
	private final String order;

	public ConnectorIndexField(final java.lang.String name, final String sortOrder){
		this.order = sortOrder;
		this.name = name;
	}

	public String  getOrder(){
		return order;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		return "ConnectorIndexField [name=" + name + ", order=" + order + "]";
	}
}
