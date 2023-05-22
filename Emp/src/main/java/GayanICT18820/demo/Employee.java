package GayanICT18820.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Employee {
    public static void main(String[] args) {
        SpringApplication.run(Employee.class, args);
    }
}
