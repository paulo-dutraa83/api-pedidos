package br.com.cotiinformatica.api_pedidos.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class PedidoResponse {

    private String id;
    private ClienteResponse cliente;
    private LocalDateTime   dataHora;
    private Double  valor;
    private List<ItemPedidoResponse> itensPedido;

}
