package br.com.cotiinformatica.api_pedidos.services;

import br.com.cotiinformatica.api_pedidos.dtos.requests.ClienteRequest;
import br.com.cotiinformatica.api_pedidos.dtos.responses.ClienteResponse;
import br.com.cotiinformatica.api_pedidos.entities.Cliente;
import br.com.cotiinformatica.api_pedidos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Metodo para inserir um cliente no sistema
    public ClienteResponse criar(ClienteRequest request) {

        var cliente = new Cliente();
        cliente.setEmail(request.getEmail());
        cliente.setNome(request.getNome());

        clienteRepository.save(cliente);

        return toResponse(cliente);
    }

    public List<ClienteResponse> consultar() {

        var clientes = clienteRepository.findAll();

        var response = clientes
                .stream()
                .map(this::toResponse)
                .toList();

        return response;
    }

    private ClienteResponse toResponse(Cliente cliente) {

        var response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());

        return response;

    }

}
