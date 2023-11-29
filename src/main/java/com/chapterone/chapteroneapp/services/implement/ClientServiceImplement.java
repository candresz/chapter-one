package com.chapterone.chapteroneapp.services.implement;

import com.chapterone.chapteroneapp.dto.ClientDTO;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.repositories.ClientRepository;
import com.chapterone.chapteroneapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientDTO> getAllClientsDTO() {
        return getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public ClientDTO findClientDTOById(Long id) {
         return new ClientDTO(findClientById(id));
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
    clientRepository.save(client);
    }

}
