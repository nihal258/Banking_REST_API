package banking.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class BankingRestApi
{
    public static void main(String[] args)
    {
    	SpringApplication.run(BankingRestApi.class, args);
    }
}
