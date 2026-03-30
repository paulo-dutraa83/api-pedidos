package br.com.cotiinformatica.api_pedidos.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoRequest {

    private String nome;
    private Double preco;
}
