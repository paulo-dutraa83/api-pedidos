package br.com.cotiinformatica.api_pedidos.services;

import br.com.cotiinformatica.api_pedidos.dtos.requests.PedidoRequest;
import br.com.cotiinformatica.api_pedidos.dtos.responses.ClienteResponse;
import br.com.cotiinformatica.api_pedidos.dtos.responses.ItemPedidoResponse;
import br.com.cotiinformatica.api_pedidos.dtos.responses.PedidoResponse;
import br.com.cotiinformatica.api_pedidos.dtos.responses.ProdutoResponse;
import br.com.cotiinformatica.api_pedidos.entities.ItemPedido;
import br.com.cotiinformatica.api_pedidos.entities.Pedido;
import br.com.cotiinformatica.api_pedidos.repositories.ClienteRepository;
import br.com.cotiinformatica.api_pedidos.repositories.ItemPedidoRepository;
import br.com.cotiinformatica.api_pedidos.repositories.PedidoRepository;
import br.com.cotiinformatica.api_pedidos.repositories.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Queue queue;

    public PedidoResponse criar(PedidoRequest request) {

        //Buscando no banco de dados o cliente informado no pedido
        var cliente = clienteRepository.findById(request.getClienteId())
                        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado")); //Msg caso nao encontre o cliente

        //Criando um pedido
        var pedido = new Pedido();
        pedido.setClienteId(cliente.getId()); //Associando um cliente ao pedido
        pedido.setDataPedido(LocalDateTime.now()); //Data e hora atual
        pedido.setValor(0.0);

        //Capturando os itens do pedido
        var itensPedido = new ArrayList<ItemPedido>();
        for(var item : request.getItensPedido()){

            //Buscando no banco de dados o produto informado no item do pedido
            var produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            var itemPedido = new ItemPedido();
            itemPedido.setPedidoId(pedido.getId());
            itemPedido.setProdutoId(produto.getId());
            itemPedido.setQuantidade(item.getQuantidade());

            //Adicionando o item do pedido na lista
            itensPedido.add(itemPedido);

            //Somando o preço * quantidade no valor do pedido
            var valorPedido = pedido.getValor();
            pedido.setValor(valorPedido + (produto.getPreco() * item.getQuantidade()));
        }

        //Salvando o pedido no banco de dados
        pedidoRepository.save(pedido);

        //Salvando os itens do pedido no banco
        for (var item : itensPedido) {
            itemPedidoRepository.save(item);
        }

        //Retornando os dados do pedido como resposta
        var pedidoResponse = new PedidoResponse();
        pedidoResponse.setId(pedido.getId());
        pedidoResponse.setDataHora(pedido.getDataPedido());
        pedidoResponse.setValor(pedido.getValor());

        pedidoResponse.setCliente(new ClienteResponse());
        pedidoResponse.getCliente().setId(cliente.getId());
        pedidoResponse.getCliente().setNome(cliente.getNome());
        pedidoResponse.getCliente().setEmail(cliente.getEmail());

        pedidoResponse.setItensPedido(new ArrayList<ItemPedidoResponse>());
        for(var item : itensPedido) {
            var itemPedidoResponse = new ItemPedidoResponse();
            itemPedidoResponse.setId(item.getId());
            itemPedidoResponse.setQuantidade(item.getQuantidade());
            itemPedidoResponse.setProduto(new ProdutoResponse());

            var produto = produtoRepository.findById(item.getProdutoId()).get(); //.get() no final eh pq nao quero perguntar se o id esta no banco, sei que esta.
            itemPedidoResponse.getProduto().setId(produto.getId());
            itemPedidoResponse.getProduto().setNome(produto.getNome());
            itemPedidoResponse.getProduto().setPreco(produto.getPreco());

            pedidoResponse.getItensPedido().add(itemPedidoResponse);
        }

        try {
            //Convertendo os dados do pedido em JSON
            var json = objectMapper.writeValueAsString(pedidoResponse);
            //Enviando os dados do pedido para a mensageria
            rabbitTemplate.convertAndSend(queue.getName(), json);

        } catch (Exception e) {
            throw new IllegalArgumentException("Falha ao enviar para a mensageria: " + e.getMessage());
        }

        return pedidoResponse;

    }

}
