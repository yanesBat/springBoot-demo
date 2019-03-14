package ec.com.example.clientsdemo;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(
            value = "/clients",
            method = RequestMethod.GET
    )

    public Page<Client> getClients(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "500") int size,
                                   @RequestParam(name = "search", required = false) String search) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (search != null && !search.isEmpty()) {
            booleanBuilder.or(QClient.client.name.containsIgnoreCase(search));
            booleanBuilder.or(QClient.client.city.containsIgnoreCase(search));
            booleanBuilder.or(QClient.client.address.containsIgnoreCase(search));
            booleanBuilder.or(QClient.client.phone.containsIgnoreCase(search));
        }

        return clientRepository.findAll(booleanBuilder.getValue(),
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name")));

    }

    @RequestMapping(
            value = "/allClients",
            method = RequestMethod.GET
    )
    public List<Client> getAllClients() {

        return clientRepository.findAll();

    }
}
