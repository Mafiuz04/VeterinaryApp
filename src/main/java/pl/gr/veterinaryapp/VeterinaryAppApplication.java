package pl.gr.veterinaryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class VeterinaryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryAppApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
