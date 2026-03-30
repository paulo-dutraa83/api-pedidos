package br.com.cotiinformatica.api_pedidos.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ClienteResponse {

    private String id;
    private String  nome;
    private String  email;

}
