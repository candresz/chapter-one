package com.chapterone.chapteroneapp.services.implement;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;
import com.chapterone.chapteroneapp.repositories.ClientBookRepository;
import com.chapterone.chapteroneapp.services.ClientBookService;
import com.chapterone.chapteroneapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientBookServiceImplement implements ClientBookService {
    @Autowired
    ClientBookRepository clientBookRepository;

    @Override
    public void saveClientBook(ClientBook clientBook) {
        clientBookRepository.save(clientBook);
    }

    @Override
    public ClientBook findByClient(Client client) {
        return clientBookRepository.findByClient(client);
    }

    @Override
    public void deleteByClientIdAndBookId(Long clientId, Long bookId) {
        clientBookRepository.deleteByClientIdAndBookId(clientId, bookId);
    }


}
