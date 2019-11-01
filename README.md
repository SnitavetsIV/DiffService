[![CircleCI](https://circleci.com/gh/SnitavetsIV/DiffService/tree/master.svg?style=svg)](https://circleci.com/gh/SnitavetsIV/DiffService/tree/master)

# WAES Assignment Scalable Web App
 Codding task for WAES interview process
 
## Task description
- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both
 endpoints
    - <host>/v1/diff/{ID}/left and <host>/v1/diff/{ID}/right
- The provided data needs to be diff-ed and the results shall be available on a third end
point
    - <host>/v1/diff/{ID}
- The results shall provide the following info in JSON format
    - If equal return that
    - If not of equal size just return that
    - If of same size provide insight in where the diffs are, actual diffs are not needed.
        - So mainly offsets + length in the data

## Technical stack
 - Java 11 - Language
 - SpringBoot, SpringJPA
 - H2 - In memory SQL Database
 - JUnit - Testing
 - Mockito - Testing
 - Lombok - Java library to write less code
 - Gradle - Dependency Management
 - Swagger2 - REST API documentation Documentation
 - google-java-format plugin for IDEA - Google Java Formatting style

## Running the REST application
- To immediately run the application:  `./gradlew clean bootRun`
- To run only unit tests: `./gradlew clean test`
- To run only integration tests: `./gradlew clean integrationTest`
- To package application run: `./gradlew clean bootJar` 
- To open API Docs use this url `{host}:{port}/swagger-ui.html`

## Authors
 - Ilya Snitavets - [LinkedIn](http://linkedin.com/in/ilya-snitavets)
 
## Suggestions for improvement

- Increase the maturity model (Richardson Maturity Model);
- Implement OAuth 2 with several grant types to secure the endpoints
- Store Diff results in DB to avoid performing the comparison again.
- ELK stack to store logs
- RabbitMQ implementation to perform data comparison asynchronously
