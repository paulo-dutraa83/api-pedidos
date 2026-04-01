package br.com.cotiinformatica.api_pedidos.dtos.requests;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PedidoRequest {

    private String clienteId;
    private List<ItemPedidoRequest> itensPedido;

}
