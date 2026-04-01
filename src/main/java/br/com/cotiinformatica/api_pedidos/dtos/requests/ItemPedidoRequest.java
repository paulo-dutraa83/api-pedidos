package br.com.cotiinformatica.api_pedidos.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoRequest {

    private String produtoId;
    private Integer quantidade;

}
