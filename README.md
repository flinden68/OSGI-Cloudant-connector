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

CloudantConnector connector = new CloudantConnector(account, username, password);

#Check if there is already a connection established.

boolean isConnected = connector.isConnectedToCloudant();

#Get all databases in your cloudant instance.

List<String> dbs = (List<String>) connector.findAllDatabases();

#Connect to a specific database.

connector.setDatabase(String dbName, boolean create);

#Create a database

connector.createDb(String dbName);

#Delete a database

connector.deleteDb(String dbName);


#When the database is set, there are methods to handle documents, even bulk actions

#CRUD for bulk documents from a List of POJO's

connector.createBulk(List<Object> objects);

connector.updateBulk(List<Object> objects);

connector.deleteBulk(List<Object> objects);

List<Object> list = connector.findAllDocuments(Object.class);

List<Object> list = connector.findAllDocumentIds();

#CRUD single document
connector.save(final Object obj);

connector.delete(final Object obj);

connector.update(final Object obj);

Object obj = connector.find(final Class<?> cls, final String id);