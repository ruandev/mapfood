package br.com.mapfood.controller;

import java.util.List;

import br.com.mapfood.service.ProdutosEstabelecimentoService;
import br.com.mapfood.domain.ProdutosEstabelecimento;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosEstabelecimentoController {

    @Autowired
    private ProdutosEstabelecimentoService produtosEstabelecimentoService;

    @ApiOperation(value = "Lista todos os produtos por estabelecimento")
    @GetMapping
    public ResponseEntity<List<ProdutosEstabelecimento>> listar(){
        List<ProdutosEstabelecimento> produtosEstabelecimentos= produtosEstabelecimentoService.findAll();
        return ResponseEntity.ok().body(produtosEstabelecimentos);
    }

    @ApiOperation(value = "Busca produto do estabelecimento pelo id")
    @GetMapping(value="/{id}")
    public ResponseEntity<ProdutosEstabelecimento> findById(@PathVariable Long id){
        ProdutosEstabelecimento produtosEstabelecimento= produtosEstabelecimentoService.findById(id);
        return ResponseEntity.ok().body(produtosEstabelecimento);
    }

}
