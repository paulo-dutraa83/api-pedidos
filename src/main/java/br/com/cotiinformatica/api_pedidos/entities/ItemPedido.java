package br.com.cotiinformatica.api_pedidos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Document(collection = "itens-pedido")
public class ItemPedido {

    @Id
    private String id;
    private String pedidoId;
    private String produtoId;
    private Integer quantidade;

}
