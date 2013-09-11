#  GlassFish clickstart

This clickstart sets up a build service, repository and a basic GlassFish app with continuous deployment.
All built by maven. 

<imc src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/glassfish_icon.png"/>

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/glassfish4-clickstart/master/clickstart.json"><img src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/deployInstantly.png"/></a>

Launch this clickstart and glory could be yours too ! Use it as a building block if you like.
You can launch this on Cloudbees via a clickstart automatically, or follow the instructions below. 

This is based on the GlassFish stack that you can read more about <a href="https://github.com/CloudBees-community/glassfish4-clickstack">here.</a>


# Deploying manually: 


## Create a Glassfish4 container

```
bees app:create -a my-glassfish4-app -t glassfish4
```

## Create a MySQL Database

```
bees db:create my-glassfish4-db
```

## Bind the MySQL Database to the Glassfish container

```
bees app:bind -a my-glassfish4-app -db my-glassfish4-db -as mydb
```

Supported JNDI names:

 * `jdbc/mydb` : unqualified relative JNDI name is supported / **OK**
 * `java:comp/env/jdbc/mydb`: qualified private name is supported / **OK**
 * <del><code>java:jdbc/mydb</code></del> and <del><code>java:/jdbc/mydb</code></del>: qualified relative names are not supported by Glassfish / **KO**
 * <del><code>java:global/env/jdbc/mydb</code></del>: qualified global name is not supported by Glassfish / **KO**

Samples:

```java
Context ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("jdbc/mydb");
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
```

## Configure Jdbc Realm

```
bees config:set -a my-glassfish4-app -R glassfish4.auth-realm.database=mydb
```

## Deploy Application

```
bees app:deploy -a my-glassfish4-app path/to/my/app.war
```

## Jenkins Job

Create a new Maven project in Jenkins, changing the following:

* Add this git repository (or yours, with this code) on Jenkins
* Also check "Deploy to CloudBees" with those parameters:

        Applications: First Match
        Application Id: MYAPP_ID
        Filename Pattern: target/*.war

where `MYAPP_ID`is the name of your application.


