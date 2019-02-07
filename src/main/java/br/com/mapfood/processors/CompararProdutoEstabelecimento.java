package br.com.mapfood.processors;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

>>>>>>> master
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.repository.EstabelecimentoRepository;
import br.com.mapfood.repository.ProdutosEstabelecimentoRepository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompararProdutoEstabelecimento {

    @Autowired
    ProdutosEstabelecimentoRepository repoProduto;

    @Autowired
    EstabelecimentoRepository repoEstabelecimento;

    public List<ProdutosEstabelecimento> compara(){
        List<ProdutosEstabelecimento> listProduto;
        List<ProdutosEstabelecimento> novoListProduto = new ArrayList<>();

        listProduto = repoProduto.findAll();

        Estabelecimento produto = null;

        for( ProdutosEstabelecimento prod:listProduto) {
            produto =repoEstabelecimento.findBycodigoRestaurante(prod.getRestaurantId());
            if(produto!=null) {
                novoListProduto.add(prod);
            }
        }

        return (List<ProdutosEstabelecimento>) novoListProduto;
    }
=======

@Service
public class CompararProdutoEstabelecimento {
	
	@Autowired
	private ProdutosEstabelecimentoRepository repoProduto;
	@Autowired
	private EstabelecimentoRepository repoEstabelecimento;
	
	public List<ProdutosEstabelecimento> compara() {
		
		List<ProdutosEstabelecimento> listProduto = new ArrayList<>();
		List<ProdutosEstabelecimento> novoListProduto = new ArrayList<>();
		
		listProduto = repoProduto.findAll();	    
	
		Estabelecimento produto = null;
	    
		for( ProdutosEstabelecimento prod:listProduto) {
			produto =repoEstabelecimento.findBycodigoRestaurante(prod.getRestaurantId());
			if(produto!=null) {				
				novoListProduto.add(prod);
			}
		}
	
		return (List<ProdutosEstabelecimento>) novoListProduto;
	}
	
>>>>>>> master
}
