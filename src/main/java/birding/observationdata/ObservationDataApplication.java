package birding.observationdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ObservationDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ObservationDataApplication.class, args);
    }
}