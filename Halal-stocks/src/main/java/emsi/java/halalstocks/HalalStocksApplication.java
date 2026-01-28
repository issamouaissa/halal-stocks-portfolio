package emsi.java.halalstocks;

import emsi.java.halalstocks.entities.Action;
import emsi.java.halalstocks.entities.Client;
import emsi.java.halalstocks.entities.Operation;
import emsi.java.halalstocks.enums.OperationType;
import emsi.java.halalstocks.repositories.ActionRepository;
import emsi.java.halalstocks.repositories.ClientRepository;
import emsi.java.halalstocks.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HalalStocksApplication {

    public static void main(String[] args) {

        SpringApplication.run(HalalStocksApplication.class, args);
    }


}
