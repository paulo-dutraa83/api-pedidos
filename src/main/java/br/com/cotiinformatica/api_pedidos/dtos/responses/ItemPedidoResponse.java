package br.com.cotiinformatica.api_pedidos.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoResponse {

    private String id;
    private ProdutoResponse produto; //Chamando o DTO, pois DTO se relaciona com DTO
    private Integer quantidade;

}
