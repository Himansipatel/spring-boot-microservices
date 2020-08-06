package com.springlearnig.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getReatTemplate() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(); //it allows to create timeout property
        clientHttpRequestFactory.setConnectTimeout(3000);
        return new RestTemplate(clientHttpRequestFactory);
    }

}

//    @Bean
//    public WebClient.Builder getWebClientBuilder(){
//        return WebClient.builder();
//    }

    /*why annotation is loadbalanced taht not only is doing service discovery but it is also doing
    load balancing client side load balancing

    java -Dserver.port=8221 -jar movie-info-service-0.0.1-SNAPSHOT.jar
    java -jar movie-info-service-0.0.1-SNAPSHOT.jar ///basically run class with main method inside it


    Apache Maven is a popular build tool, that takes your project’s Java source code, compiles it,
    tests it and converts it into an executable Java program: either a .jar or a .war file.

    mvn clean install :->
    mvn clean install is the command to do just that.

You are calling the mvn executable, which means you need Maven installed on your machine.

You are using the clean command, which will delete all previously compiled Java sources and
 resources (like .properties) in your project. Your build will start from a clean slate.

Install will then compile, test & package your Java project and even install/copy your built .jar/.war file
 into your local Maven repository.


------------------------------------------------------------------------------------

    Now what exactly does a build tool do? Maven does three things rather well:

Dependency Management: Maven lets you easily include 3rd party dependencies (think libraries/frameworks
 such as Spring) in your project. An equivalent in other languages would be Javascript’s npm, Ruby’s gems or
 PHP’s composer.

Compilation through convention: In theory you could compile big Java projects with a ton of classes, by hand with
the javac command line compiler (or automate that with a bash script). This does however only work for toy projects.
 Maven expects a certain directory structure for your Java source code to live in and when you later do a mvn clean
 install , the whole compilation and packaging work will be done for you.

Everything Java: Maven can also run code quality checks, execute test cases and even deploy applications to remote
servers, through plugins. Pretty much every possible task you can think of.

------------------A pom.xml file contains everything needed to describe your Java project.

    + myproject
    + -- src
        + -- main
            + -- java
                 MyApp.java
    + -- target
        + -- classes (upon mvn compile)
             MyApp.class

        myproject.jar (upon mvn package or mvn install)

    pom.xml


    install JAR to local maven repository :->

    mvn install:install-file \
   -Dfile=<path-to-file> \
   -DgroupId=<group-id> \
   -DartifactId=<artifact-id> \
   -Dversion=<version> \
   -Dpackaging=<packaging> \
   -DgeneratePom=true
    */



