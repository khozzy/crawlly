#  Indeed Job Crawler

Application for fetching latest jobs offers, parsing corresponding employers website - extracting data, and sending results via e-mail or dropbox

## Technologies & Details

- Java EE7
- WildFly 8.0.0.Beta1
- Java API for JSON Processing (JSR-353)
- JavaMail
- MySQL
- DropBox API

## Deploying manually: 

Be sure that WildFly server is properly configured. You need:
- mail session
- jdbc resource

## How to install:

```
mvn clean install
```

