package br.com.mapfood.resources;

import java.util.List;

import br.com.mapfood.Service.ProdutosEstabelecimentoService;
import br.com.mapfood.domain.ProdutosEstabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosEstabelecimentoResource {

    @Autowired
    private ProdutosEstabelecimentoService produtosEstabelecimentoService;

    @GetMapping
    public ResponseEntity<List<ProdutosEstabelecimento>> listar(){
        List<ProdutosEstabelecimento> produtosEstabelecimentos= produtosEstabelecimentoService.findAll();
        return ResponseEntity.ok().body(produtosEstabelecimentos);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ProdutosEstabelecimento> findById(@PathVariable Long id){
        ProdutosEstabelecimento produtosEstabelecimento= produtosEstabelecimentoService.findById(id);
        return ResponseEntity.ok().body(produtosEstabelecimento);
    }

}
