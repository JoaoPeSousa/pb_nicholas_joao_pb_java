package br.edu.infnet.at_nicholas_joao_pb_java;

import br.edu.infnet.at_nicholas_joao_pb_java.services.ApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {ApiService.class})
public class AtNicholasJoaoPbJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtNicholasJoaoPbJavaApplication.class, args);
	}

}
