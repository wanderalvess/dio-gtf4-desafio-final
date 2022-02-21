package com.curso.dio.labpadroesprojetospring.service.impl;

import com.curso.dio.labpadroesprojetospring.model.Address;
import com.curso.dio.labpadroesprojetospring.model.AddressRepository;
import com.curso.dio.labpadroesprojetospring.model.Client;
import com.curso.dio.labpadroesprojetospring.model.ClientRepository;
import com.curso.dio.labpadroesprojetospring.service.ClientService;
import com.curso.dio.labpadroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClientService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author wanderalvess
 */
@Service
public class ClientServiceImpl implements ClientService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Client> findAll() {
        // Buscar todos os clientes
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        //Buscar clientes com o id
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        saveClientCep(client);
    }

    @Override
    public void update(Long id, Client client) {
        // Buscar clientes por id, caso exista:
        Optional<Client> clientBd = clientRepository.findById(id);
        if (clientBd.isPresent()) {
            saveClientCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        // Deletar clientes por ID
        clientRepository.deleteById(id);
    }

    private void saveClientCep (Client client) {
        // Verificar se o endereço do cliente já existe (pelo CEP)
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            //Caso não exista, integrar com o viacep e persisitir o retorno.
            Address newAddress = viaCepService.consultCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        // Inserir Cliente, vinculado o Endereço (novo ou existente)
        clientRepository.save(client);
    }
}
