package nl.elstarit.cloudant.connector;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.model.Permissions;

import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.CloudantPermissions;

/**
 * <pre>
 * {@code
 * CloudantConnector connector = new CloudantConnector(account, username, password, dbName, false);
 *
 * List<String> dbs = (List<String>) connector.getDatabaseConnector().findAllDatabases();
 * }
 * </pre>
 * @author frank van der linden
 */

public class DatabaseConnector extends BaseConnector {
	private CloudantClient client;
	private Map<String, HashSet<CloudantPermissions>> abstractSet;

	public DatabaseConnector(){}

	public DatabaseConnector(final CloudantClient client, final String dbName, final boolean create){
		initDatabase(client, dbName, create);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initDatabase(final CloudantClient client, final String dbName, final boolean create){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.initDatabaseImpl(client, dbName, create);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteDb(final String dbName){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.deleteDbImpl(dbName);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createDb(final String dbName){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.createDbImpl(dbName);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> findAllDatabases(){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.findAllDatabasesImpl();
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPermissions(final String userNameorApikey, final Set<CloudantPermissions> permissions){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.setPermissionsImpl(userNameorApikey, permissions);
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		//return getAbstractList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashSet<CloudantPermissions>> getPermissions(){
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				@Override
				public Object run() throws Exception {
					DatabaseConnector.this.getPermissionsImpl();
					return null;
				}
			});
		} catch (final PrivilegedActionException e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}

		return getAbstractSet();
	}

	private void setCLient(final CloudantClient client){
		this.client = client;
	}

	/*
	 * Impl methods
	 */
	private void initDatabaseImpl(final CloudantClient client, final String dbName, final boolean create){
		setCLient(client);
		setDb(client.database(dbName, create));
	}

	private void findAllDatabasesImpl(){
		setAbstractList(client.getAllDbs());
	}

	private void deleteDbImpl(final String dbName){
		client.deleteDB(dbName);
	}

	private void createDbImpl(final String dbName){
		client.createDB(dbName);
	}

	private void setPermissionsImpl(final String userNameorApikey, final Set<CloudantPermissions> permissions){
		final Set<Permissions> perms = new HashSet<Permissions>();
		for(final CloudantPermissions perm : permissions){
			if(perm.name().equals(Permissions._admin.name())){
				perms.add(Permissions._admin);
			}else if(perm.name().equals(Permissions._db_updates.name())){
				perms.add(Permissions._design);
			}else if(perm.name().equals(Permissions._design.name())){
				perms.add(Permissions._reader);
			}else if(perm.name().equals(Permissions._reader.name())){
				perms.add(Permissions._replicator);
			}else if(perm.name().equals(Permissions._replicator.name())){
				perms.add(Permissions._security);
			}else if(perm.name().equals(Permissions._shards.name())){
				perms.add(Permissions._shards);
			}else if(perm.name().equals(Permissions._writer.name())){
				perms.add(Permissions._writer);
			}
		}

		getDb().setPermissions(userNameorApikey, EnumSet.copyOf(perms));
	}

	@SuppressWarnings("rawtypes")
	private void getPermissionsImpl(){
		final Map<String, EnumSet<Permissions>> permissions = getDb().getPermissions();
		final Map<String, HashSet<CloudantPermissions>> permissionsMap = new HashMap<String, HashSet<CloudantPermissions>>();

		try{
			for (final Map.Entry<String, EnumSet<Permissions>> entry : permissions.entrySet()) {

				final HashSet<CloudantPermissions> perms = new HashSet<CloudantPermissions>();
				for(final Iterator it = entry.getValue().iterator();it.hasNext();){
					final Permissions perm = (Permissions)it.next();

					if(perm.name().equals(CloudantPermissions._admin.name())){
						perms.add(CloudantPermissions._admin);
					}else if(perm.name().equals(CloudantPermissions._db_updates.name())){
						perms.add(CloudantPermissions._design);
					}else if(perm.name().equals(CloudantPermissions._design.name())){
						perms.add(CloudantPermissions._reader);
					}else if(perm.name().equals(CloudantPermissions._reader.name())){
						perms.add(CloudantPermissions._replicator);
					}else if(perm.name().equals(CloudantPermissions._replicator.name())){
						perms.add(CloudantPermissions._security);
					}else if(perm.name().equals(CloudantPermissions._shards.name())){
						perms.add(CloudantPermissions._shards);
					}else if(perm.name().equals(CloudantPermissions._writer.name())){
						perms.add(CloudantPermissions._writer);
					}

				}

				permissionsMap.put(entry.getKey(), perms);
			}
			setAbstractSet(permissionsMap);
		} catch (final Exception e) {
			CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	public Map<String, HashSet<CloudantPermissions>> getAbstractSet() {
		return abstractSet;
	}

	public void setAbstractSet(final Map<String, HashSet<CloudantPermissions>> abstractSet) {
		this.abstractSet = abstractSet;
	}

}
