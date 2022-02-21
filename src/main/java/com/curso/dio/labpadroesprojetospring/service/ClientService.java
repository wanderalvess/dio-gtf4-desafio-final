package com.curso.dio.labpadroesprojetospring.service;

import com.curso.dio.labpadroesprojetospring.model.Client;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author wanderalvess
 */

public interface ClientService {

    Iterable<Client> findAll();

    Client findById(Long id);

    void insert(Client client);

    void update(Long id, Client client);

    void delete(Long id);

}
