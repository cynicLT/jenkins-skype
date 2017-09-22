# Jenkins-Skype
Application used to send messages from Jenkins to specific skype chat. Based on [taksan's skype-java-api](https://github.com/taksan/skype-java-api)

### Communication API:
```java
/{groupIdBase64}/skype.json - POST message from Jenkins with predefined structure.
```

To interact must be defined `Skype Group ID` in `Base64` format

### Skype message template
* completed.ftl - message template about completed Jenkins job
* finalized.ftl - message template about finalized Jenkins job
* started.ftl - message template about started Jenkins job


