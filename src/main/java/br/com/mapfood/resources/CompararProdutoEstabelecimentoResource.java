package br.com.mapfood.resources;

import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.processors.CompararProdutoEstabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/compara")
public class CompararProdutoEstabelecimentoResource {

    @Autowired
    CompararProdutoEstabelecimento comparaProduto;

    @GetMapping
    public ResponseEntity<List<ProdutosEstabelecimento>> listar(){
        List<ProdutosEstabelecimento> lista = comparaProduto.compara();
        return ResponseEntity.ok().body(lista);
    }
}
