package br.com.cotiinformatica.api_pedidos.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoResponse {

    private String id;
    private String nome;
    private Double preco;

}
