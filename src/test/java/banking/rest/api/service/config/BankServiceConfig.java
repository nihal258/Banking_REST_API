package banking.rest.api.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import banking.rest.api.data.BankEntityRepository;
import banking.rest.api.service.BankService;

@Configuration
public class BankServiceConfig {

	@Bean
    public BankService bankService() {
       //BankService bankService = new BankService
       //return bankService;
		return null;
    }
}
