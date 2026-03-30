package br.com.cotiinformatica.api_pedidos.repositories;

import br.com.cotiinformatica.api_pedidos.entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {



}
