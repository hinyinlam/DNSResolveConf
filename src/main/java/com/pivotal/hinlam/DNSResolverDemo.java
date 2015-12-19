package com.pivotal.hinlam;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNSResolverDemo {
	@RequestMapping("/")
    public String showDNSResult(){
		
		String message = "This page show the system properties and also domain suffix.<br/>\n<h2>The query is: \"www\", result depend on the JVM settings of:</h2><br/><br/>";
		message = message + "-Dsun.net.spi.nameservice.provider <br/> -Dsun.net.spi.nameservice.nameservers=[IP] <br/> -Dsun.net.spi.nameservice.domain=[Domain-Suffice]<br/>";
				
		message = message + "<br/><h2>Here is a list of DNS related system properties:</h2><br/>";
		Properties ps = System.getProperties();
		for(Object p: ps.keySet()){
			String key=p.toString();
			if(key.startsWith("sun.net")){
				message = message + "<br/>\n" + key + ": " + ps.getProperty(key);
			}
		}
		
		message = message + "<br/><h2>Here is a list of query result from querying \"www\" without any suffix:</h2><br/>";
		InetAddress dnsResults[];
		try {
			dnsResults = InetAddress.getAllByName("www");
			for(InetAddress result: dnsResults){
				message = message + result.toString() + "<br/>\n";
			}
		} catch (UnknownHostException e) {
			message += e.getMessage();
		}
		message = message + "<br/><h2>Here is a list of query result from querying \"wildcard-test\" without any suffix:</h2><br/>";
		try {
			dnsResults = InetAddress.getAllByName("wildcard-test");
			for(InetAddress result: dnsResults){
				message = message + result.toString() + "<br/>\n";
			}
		} catch (UnknownHostException e) {
			message += e.getMessage();
		}
    	return message;
    }
}
