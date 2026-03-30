package br.com.cotiinformatica.api_pedidos.controllers;

import br.com.cotiinformatica.api_pedidos.dtos.requests.ClienteRequest;
import br.com.cotiinformatica.api_pedidos.dtos.requests.ProdutoRequest;
import br.com.cotiinformatica.api_pedidos.services.ClienteService;
import br.com.cotiinformatica.api_pedidos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //HTTP POST /api/v1/clientes/criar
    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody ProdutoRequest request) {
        var response = produtoService.criar(request);
        return ResponseEntity.status(201).body(response);
    }

    //HTTP GET /api/v1/clientes/criar
    @GetMapping("consultar")
    public ResponseEntity<?> consultar() {
        var response = produtoService.consultar();
        return ResponseEntity.status(200).body(response);
    }


}
