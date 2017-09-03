mysql -u root --password=mysql < db/sql-with-insert.sql
mvn install
mvn -P run-tomcat-mysql,-acceptance-tests-cargo cargo:run
