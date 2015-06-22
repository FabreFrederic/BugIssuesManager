# BugIssuesManager
Bug issues manager

To launch the integration tests :
mvn clean install verify -P postgresql-it
You must have docker

To launch server with remote debug :
export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8100,server=y,suspend=n" && mvn tomcat7:run
