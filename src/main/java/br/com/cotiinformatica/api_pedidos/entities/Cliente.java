package br.com.cotiinformatica.api_pedidos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private String nome;
    private String email;

}
