
Notes:
1. Maven project using Spring 4.0.6.RELEASE, Hibernate 4.3.6.Final and HSQLDB 2.3.2.
2. Basic CRUD tests in OpServerServiceIntegrationTest
3. Basic datasource testing in DBTests
4. Built using maven, JDK 1.7
5. Spring beans are profile aware, currently only supporting dev/test. With more  time would like to have validated switching database sources if java component was run against a production environment
6. No exception handling chaining in place, would like to have created own exception handler 
7. Service implementation is transactional
8. No proper/generic approach to validating input params implemented
9. Each command is mapped to a command object
10. ENUM is used to display the different input values and to display the input descriptions
11. Using com.thoughtworks.xstream to map the xml to entities

TODO
1. More tests around the Immutability of ServerEntity
2. More tests around db access
3. More tests around transactional annotations

Suggestions to improve
1. Introduce ehcache to cache commonly used methods, this would prevent DB calls for get/find
2. Introduce better way of handling input params
3. Introduce a validator util class to ensure all fields for the save/update method are ok

To Run:
mvn package
mvn exec:java -Dspring.profiles.active="dev"
- note: requires the spring profile to be used, default params were not added to pom.xml

Sample Commands
0) Quit
1) Help
2) Count
3) Add
3 -output /Users/JLong/Dropbox/DimensionData/OpsourceSimpleApp/src/test/resources/testServer.xml
4) Edit
4 -id 8ebc025a-1ad1-4a54-aa6e-18f45dae6244 UpdateMe
5) Delete
5 -id 8ebc025a-1ad1-4a54-aa6e-18f45dae6244
6) List