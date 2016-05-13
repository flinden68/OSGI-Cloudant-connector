#A basic Cloudant connector plugin#
A basic cloudant-connector as a OSGi plugin which can be used on top of Domino, to connect to a Cloudant database.
To establish this connection I have used the open source project, Java Cloudant. https://github.com/cloudant/java-cloudant

There is is extensive java doc with all the possiblities with this project.

In the repository I will provide also a feature project and an update site. Which can be imported in a Update Site database on a Domino server

#Getting started
Download the update site and point your update site database to the site.xml to import the plugin in the update site.
And Go..
In the xsp.properties of the nsf select the "nl.elstarit.cloudant.XspLibrary" at the Page generation tab

#Using the plugin#
#First thing to do is to make connection with the cloudant server.

CloudantConnector connector = new CloudantConnector(account, username, password, dbName, create);

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

List<Object> list = connector.findAllDocuments(Object.class);

List<Object> list = connector.findAllDocumentIds();

#CRUD single document

connector.getDocumentConnector().save(final Object obj);

connector.getDocumentConnector().delete(final Object obj);

connector.getDocumentConnector().update(final Object obj);

Object obj = connector.getDocumentConnector().find(final Class<?> cls, final String id);

#Add attachemnt to specific document, if docId or docRev is null therer will be a new document created.

ConnectorResponse response = connector.getDocumentConnector().saveAttachment(InputStream, name, contenType, docId, revId)

#Query the database and make use of the indices.

List<?> connector.getQueryConnector().search(final String searchIndexId, final Class<?> cls, final Integer queryLimit, final String query)
To learn about the query syntax see, https://docs.cloudant.com/search.html

To get all the indices

List<?> allIndices();

To find documents using an index

List<?> findByIndex(final String selectorJson, final Class<?> cls)
