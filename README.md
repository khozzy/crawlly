#  Indeed Job Crawler

Application for fetching latest jobs offers, parsing corresponding employers website - extracting data.

## Technologies & Details

- Java EE7
- Glassfish 4
- Primefaces 3.5
- Java API for JSON Processing (JSR-353)
- MySQL

## Deploying manually: 

Be sure that Glassfish container is properly configured. You will need a proper jdbc connection.

```
mvn clean package glassfish:{deploy|redeploy|undeploy}
```

