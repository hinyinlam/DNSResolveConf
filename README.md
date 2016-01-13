
Use "mvn clean package" to build a jar file and use "cf push" to read manifest.yml and then push to CloudFoundry.

How to run the demo:
java -version
java -Dsun.net.spi.nameservice.provider.1=dns,sun -Dsun.net.spi.nameservice.nameservers=8.8.8.8 -Dsun.net.spi.nameservice.domain=pcf-on-aws.net -Djava.net.preferIPv4Stack=true -jar target\DNSResolveConf-0.0.1-SNAPSHOT.jar

Visit: 
http://localhost:8080


Java 1.7 or above:
You can specific multiple sun.net.spi.nameservice.provider.[n]  Provided will be used for HA according to the priority (smaller N in provider.[N] means higher priority)


Java 1.6 and below:
You should only specific only one provider, the order of specification is important, ie:

java -Dsun.net.spi.nameservice.provider.2=default -Dsun.net.spi.nameservice.provider.1=dns,sun    => Only defualt will be used, "dns,sun" won't be used.


