package br.com.cotiinformatica.api_pedidos.controllers;

import br.com.cotiinformatica.api_pedidos.dtos.requests.PedidoRequest;
import br.com.cotiinformatica.api_pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody PedidoRequest request) {
        var response = pedidoService.criar(request);
        return ResponseEntity.status(201).body(response);

    }

}
