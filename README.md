# makerchecker
To setup DB for this application, please follow below steps

1. Setup mysql at 3306(localhost:3306/testdb).
2. Create a db named testdb it mysql.
3. Run sql queries from file /maker-checker/src/main/resources/sql/ddl.sql to create tables and master data.

To deploy the applcaition in Jboss please follow below steps:-
1. run mvn clean install command
2. Copy the war file from target folder
3. paste it under jboss-eap-6.3\standalone\deployments
4. And access it by localhost:<jboss_port>/<project-name>/
