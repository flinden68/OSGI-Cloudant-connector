#A basic Cloudant connector plugin#
A basic cloudant-connector as a OSGi plugin which can be used on top of Domino, to connect to a Cloudant database.
To establish this connection I have used the open source project, Java Cloudant. https://github.com/cloudant/java-cloudant

There is is extensive java doc with all the possiblities with this project.

In the repository I will provide also a feature project and an update site. Which can be imported in a Update Site database on a Domino server

#Getting started
Download the update site and point your update site database to the site.xml to import the plugin in the update site.
And Go..
In the xsp.properties of the nsf select the "nl.elstarit.cloudant.XspLibrary" at the Page generation tab

#Important
The connector is asking for a account, this can be calculated by the following syntax, username:password@username

#Using the plugin#
#First thing to do is to make connection with the cloudant server.


CloudantConnector connector = new CloudantConnector();

#Initialize the connector, the most basic version.

connector.initCloudantClient(account, username, password, dbName, create);

#Initialize the connector, for a local Cloudant server.

connector.initCloudantClientLocal(url, username, password, dbName, create);

#Initialize the connector, via a Proxy.

connector.initCloudantClientProxy(url, username, password, dbName, create, proxyUrl, proxyUser, proxyPassword);

#Initialize the connector, via a SSL.

connector.initCloudantClientSsl(url, username, password, dbName, create, proxyUrl, proxyUser, SslSocketFactory);

#Initialize the connector, advanced version which time outs. The TimeUnit is a enum, see https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/TimeUnit.html

connector.initCloudantClientAdvanced(account, username, password, dbName, create, connectTimeout, readTimeout, timeUnit);

#Initialize the connector, advanced version which time outs for a Local Cloudant server. The TimeUnit is a enum, see https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/TimeUnit.html

connector.initCloudantClientLocalAdvanced(url, username, password, dbName, create, connectTimeout, readTimeout, timeUnit);


#Check if there is already a connection established.

boolean isConnected = connector.isConnectedToCloudant();

#Get all databases in your cloudant instance.

List<String> dbs = (List<String>) connector.getDocumentConnector().findAllDatabases();

#Switch to a specific database.

connector.switchDatabase(String dbName, boolean create);

#Create a database

connector.getDocumentConnector().createDb(String dbName);

#Delete a database

connector.getDocumentConnector().deleteDb(String dbName);


#All the document related methods are in the documentConnector, there are methods to handle documents, even bulk actions

#CRUD for bulk documents from a List of POJO's

connector.getDocumentConnector().createBulk(List<Object> objects);

connector.getDocumentConnector().updateBulk(List<Object> objects);

connector.getDocumentConnector().deleteBulk(List<Object> objects);

List<Object> list = connector.getDocumentConnector().findAllDocuments(Object.class);

List<Object> list = connector.getDocumentConnector().findAllDocumentIds();

#Get a list of objects direct from a view.

List<Object> list = connector.getDocumentConnector()findAllDocumentsFromView(cls, designDoc, viewName, String keyType, limit);

#CRUD single document, in the ConnectorResponse will be the id, revId and status

ConnectorResponse response = connector.getDocumentConnector().save(final Object obj);

connector.getDocumentConnector().delete(final Object obj);

ConnectorResponse response = connector.getDocumentConnector().update(final Object obj);

Object obj = connector.getDocumentConnector().find(final Class<?> cls, final String id);

#Add attachemnt to specific document, if docId or docRev is null therer will be a new document created.

ConnectorResponse response = connector.getDocumentConnector().saveStandAloneAttachment(InputStream, name, contenType, docId, revId)

#Query the database and make use of the indices. The searchIndexId syntax could be 'company/ftsearchCompanies'

List<?> connector.getQueryConnector().search(final String searchIndexId, final Class<?> cls, final Integer queryLimit, final String query)
To learn about the query syntax see, https://docs.cloudant.com/search.html

#To get all the indices

List<?> allIndices();

#To find documents using an index

List<?> findByIndex(final String selectorJson, final Class<?> cls)

#Permissions
To get the Permissions of database.
Map<String, HashSet<CloudantPermissions>> connector.getDatabaseConnector().getPermissions();

Or to set Permissions
connector.getDatabaseConnector().setPermissions(final String userNameorApikey, final Set<CloudantPermissions> permissions)

#Design documents
updates, map of updates, where the value of the map is the javascript of the selection
designDocument: the name of the designDocument to create or update

connector.getDocumentConnector().createDesignDocument(final Map<String, String> updates, final String designDocument)

connector.getDocumentConnector().updateDesignDocument(final Map<String, String> updates, final String designDocument)

#Views
Programmatically create, update and deleter views
For the create and update methods the parameters are the json to be put in views part or the indexes part of the design documents
"configurations": {
    "map": "function (doc) {\n    if(doc.dataType == \"CONFIG\"){\n    emit(doc._id, 1);\n  }\n}"
   }

createView(final String viewJson, final String indexJson, final String viewName);

updateView(final String viewJson, final String indexJson, final String viewName);

deleteView(final String viewName)
