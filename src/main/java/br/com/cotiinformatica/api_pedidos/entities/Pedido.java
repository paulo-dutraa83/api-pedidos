package br.com.cotiinformatica.api_pedidos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private LocalDateTime dataPedido;
    private String valor;
    private String  clienteId;

}
