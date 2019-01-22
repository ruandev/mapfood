package br.com.mapfood.resources;

import java.util.List;

import br.com.mapfood.Service.EstabelecimentoService;
import br.com.mapfood.domain.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoResource {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> listar(){
        List<Estabelecimento> estabelecimentos = estabelecimentoService.findAll();
        return ResponseEntity.ok().body(estabelecimentos);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Estabelecimento> findById(@PathVariable Long id){
        Estabelecimento estabelecimento= estabelecimentoService.findById(id);
        return ResponseEntity.ok().body(estabelecimento);
    }

}
