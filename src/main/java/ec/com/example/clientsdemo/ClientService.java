package ec.com.example.clientsdemo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository userRepository) {
        this.clientRepository = userRepository;
    }

    public Page<Client> list(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client save(Client user) {
        return clientRepository.save(user);
    }

    public void saveAll(List<Client> users) {
        clientRepository.saveAll(users);
    }
}
