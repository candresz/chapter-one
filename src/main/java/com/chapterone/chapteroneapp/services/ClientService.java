package com.chapterone.chapteroneapp.services;

import com.chapterone.chapteroneapp.dto.ClientDTO;
import com.chapterone.chapteroneapp.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    List<ClientDTO> getAllClientsDTO();
    Client findClientById(Long id);

    ClientDTO findClientDTOById(Long id);
    Client findClientByEmail(String email);

    void saveClient(Client client);

}
