
#How to build:
Use "mvn clean package" to build a jar file and use "cf push" to read manifest.yml and then push to CloudFoundry.

#How to run the demo:

##First way - Standalone:
java -version \#see Java note below

java -Dsun.net.spi.nameservice.provider.1=dns,sun -Dsun.net.spi.nameservice.nameservers=8.8.8.8 -Dsun.net.spi.nameservice.domain=pcf-on-aws.net -Djava.net.preferIPv4Stack=true -jar target\DNSResolveConf-0.0.1-SNAPSHOT.war


Visit: 

http://localhost:8080

##Second way - Deploy to middleware:
copy target/DNSResolveConf-0.0.1-SNAPSHOT.war to the container deployment path:
###Tomcat
**Dir:** CATALINA_HOME/webapps  
**Env:** set CATALINA_OPTS=-Dsun.net.spi.nameservice.provider.1=dns,sun -Dsun.net.spi.nameservice.nameservers=8.8.8.8 -Dsun.net.spi.nameservice.domain=pcf-on-aws.net -Djava.net.preferIPv4Stack=true  
**Cmd:** CATALINA_HOME/bin/catalina.sh start

###JBoss - JBOSS_HOME/standalone/
**Dir:** JBOSS_HOME/standardlone/deployments  
**Env:** set JAVA_OPTS=-Dsun.net.spi.nameservice.provider.1=dns,sun -Dsun.net.spi.nameservice.nameservers=8.8.8.8 -Dsun.net.spi.nameservice.domain=pcf-on-aws.net -Djava.net.preferIPv4Stack=true    
**Cmd:** JBOSS_HOME/standalone/bin/



*When using Java 6, both Tomcat and JBoss failed to run using this method, please use Java 7 or above*

#Note to how Java handle DNS settings:

Java 1.7 or above:

You can specific multiple sun.net.spi.nameservice.provider.[n]  Provided will be used for HA according to the priority (smaller N in provider.[N] means higher priority)


Java 1.6 and below:

You should only specific only one provider, the order of specification is important, ie:

java -Dsun.net.spi.nameservice.provider.2=default -Dsun.net.spi.nameservice.provider.1=dns,sun    => Only defualt will be used, "dns,sun" won't be used.



