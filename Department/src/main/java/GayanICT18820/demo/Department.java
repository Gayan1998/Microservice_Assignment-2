package GayanICT18820.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Department {
    public static void main(String[] args) {
        SpringApplication.run(Department.class, args);
    }
}
