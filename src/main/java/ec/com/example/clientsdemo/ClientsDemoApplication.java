package ec.com.example.clientsdemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ClientsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ClientService userService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Client>> typeReference = new TypeReference<List<Client>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/static/json/test.json");
			try {
				List<Client> clientsData = mapper.readValue(inputStream,typeReference);
				List<Client> clients = new ArrayList<>();
				clients.addAll(clientsData);

				for (int i = 0; i < 400000; i++) {
					Random rand = new Random();
					int name = rand.nextInt(1000);
					int city = rand.nextInt(1000);
					int address = rand.nextInt(1000);
					int phone = rand.nextInt(1000);

					Client client = new Client();
					client.setName(clientsData.get(name).getName());
					client.setCity(clientsData.get(city).getCity());
					client.setAddress(clientsData.get(address).getAddress());
					client.setPhone(clientsData.get(phone).getPhone());
					clients.add(client);
				}


				userService.saveAll(clients);
				System.out.println("clients Saved!");
			} catch (IOException e){
				System.out.println("Unable to save clients: " + e.getMessage());
			}
		};
	}

}
