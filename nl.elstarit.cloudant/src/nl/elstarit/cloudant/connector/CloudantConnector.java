package nl.elstarit.cloudant.connector;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

/**
 * https://github.com/cloudant/java-cloudant
 * @author frankvanderlinden
 *
 */
public class CloudantConnector {

	private CloudantClient client;

	public CloudantConnector(){

	}

	public void connectToCloudant(final String account, final String username, final String password){
		client = ClientBuilder.account(account)
				.username(username)
				.password(password)
				.build();
	}

	public CloudantClient getClient() {
		return client;
	}

	public void setClient(final CloudantClient client) {
		this.client = client;
	}
}
