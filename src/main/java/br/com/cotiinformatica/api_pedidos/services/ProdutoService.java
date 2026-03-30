package br.com.cotiinformatica.api_pedidos.services;

import br.com.cotiinformatica.api_pedidos.dtos.requests.ProdutoRequest;
import br.com.cotiinformatica.api_pedidos.dtos.responses.ProdutoResponse;
import br.com.cotiinformatica.api_pedidos.entities.Produto;
import br.com.cotiinformatica.api_pedidos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /*
        Método para inserir um produto no sistema
     */
    public ProdutoResponse criar(ProdutoRequest request) {

        var produto = new Produto();

        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public List<ProdutoResponse> consultar() {

        var produtos = produtoRepository.findAll();

        var response = produtos
                .stream()
                .map(this::toResponse)
                .toList();

        return response;
    }

    private ProdutoResponse toResponse(Produto produto) {
        var response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setPreco(produto.getPreco());

        return response;
    }
}
