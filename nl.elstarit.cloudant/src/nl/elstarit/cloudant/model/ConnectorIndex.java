/**
 *
 */
package nl.elstarit.cloudant.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cloudant.client.api.model.Index;
import com.cloudant.client.api.model.IndexField;

/**
 * @author frankvanderlinden
 *
 */
public class ConnectorIndex {

	private String designDocId;
	private String name;
	private String type;
	private List<ConnectorIndexField> fields;

	public ConnectorIndex(final Index index){
		this.designDocId = index.getDesignDocId();
		this.name = index.getName();
		this.type = index.getType();
		loadFields(index.getFields());
	}

	private void loadFields(final Iterator<IndexField> iterator){
		this.fields = new ArrayList<ConnectorIndexField>();
		while (iterator.hasNext()) {
			final ConnectorIndexField field = new ConnectorIndexField(iterator.next().getName(), iterator.next().getOrder().toString());
			this.fields.add(field);
		}
	}

	public String getDesignDocId() {
		return designDocId;
	}

	public void setDesignDocId(final String designDocId) {
		this.designDocId = designDocId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public List<ConnectorIndexField> getFields() {
		return fields;
	}

	public void setFields(final List<ConnectorIndexField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "ConnectorIndex [designDocId=" + designDocId + ", name=" + name
				+ ", type=" + type + ", fields=" + fields + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((designDocId == null) ? 0 : designDocId.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ConnectorIndex other = (ConnectorIndex) obj;
		if (designDocId == null) {
			if (other.designDocId != null) {
				return false;
			}
		} else if (!designDocId.equals(other.designDocId)) {
			return false;
		}
		if (fields == null) {
			if (other.fields != null) {
				return false;
			}
		} else if (!fields.equals(other.fields)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}
}
