package br.com.mapfood.Service;

import java.util.List;
import java.util.Optional;

import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.processors.ProdutosEstabelecimentoProcessor;
import br.com.mapfood.repository.ProdutosEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosEstabelecimentoService {

    @Autowired
    private ProdutosEstabelecimentoRepository produtosEstabelecimentoRepository;

    @Autowired
    private ProdutosEstabelecimentoProcessor produtosEstabelecimentoProcessor;

    public void processarFileProdutosEstabelecimento(){
        List<ProdutosEstabelecimento> listProdutosEstabelecimentos = produtosEstabelecimentoProcessor.lerArquivo();

        produtosEstabelecimentoRepository.saveAll(listProdutosEstabelecimentos);
    }

    public List<ProdutosEstabelecimento> findAll(){
        return produtosEstabelecimentoRepository.findAll();
    }

    public ProdutosEstabelecimento findById(Long id) {
        Optional<ProdutosEstabelecimento> obj = produtosEstabelecimentoRepository.findById(id);
        return obj.orElse(null);
    }

}
