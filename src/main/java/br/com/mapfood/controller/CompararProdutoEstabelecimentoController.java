package br.com.mapfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.processors.CompararProdutoEstabelecimento;

@RestController
@RequestMapping(value = "/compara")
public class CompararProdutoEstabelecimentoController {

	@Autowired
	private CompararProdutoEstabelecimento comparaProduto;
	
	 @GetMapping
	    public ResponseEntity<List<ProdutosEstabelecimento>> listar(){
		 List<ProdutosEstabelecimento> lista = comparaProduto.compara();
		 return ResponseEntity.ok().body(lista);
	 }
}
