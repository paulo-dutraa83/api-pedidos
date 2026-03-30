package br.com.cotiinformatica.api_pedidos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "produtos")
public class Produto {

    @Id
    private String   id;
    private String nome;
    private Double preco;

}
