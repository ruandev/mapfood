package br.com.mapfood.resources;

<<<<<<< HEAD
import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.processors.CompararProdutoEstabelecimento;
=======
import java.util.List;

>>>>>>> master
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
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
=======
import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.processors.CompararProdutoEstabelecimento;

@RestController
@RequestMapping(value = "/Compara")
public class CompararProdutoEstabelecimentoResource {

	@Autowired
	private CompararProdutoEstabelecimento comparaProduto;
	
	 @GetMapping
	    public ResponseEntity<List<ProdutosEstabelecimento>> listar(){
		 List<ProdutosEstabelecimento> lista = comparaProduto.compara();
		 return ResponseEntity.ok().body(lista);
	 }
>>>>>>> master
}
