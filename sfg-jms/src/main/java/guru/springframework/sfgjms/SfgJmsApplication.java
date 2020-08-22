package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

    public static void main(String[] args) throws Exception {

        // set up the embedded ActiveMQ server
        // see http://activemq.apache.org/components/artemis/documentation/latest/embedding-activemq.html
        // actually, if the server is in the classpath, Spring Boot will auto-config a server for us
//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//        .setPersistenceEnabled(false)
//        .setJournalDirectory("target/data/journal")
//        .setSecurityEnabled(false)
//        .addAcceptorConfiguration("invm", "vm://0"));
//
//        server.start();

        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
