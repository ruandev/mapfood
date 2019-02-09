package br.com.mapfood.service;

import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.processors.EstabelecimentoProcessor;
import br.com.mapfood.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoProcessor estabelecimentoProcessor;

    public void processarFileEstabelecimento(){
        List<Estabelecimento> listEstabelecimento = estabelecimentoProcessor.lerArquivo();

        estabelecimentoRepository.saveAll(listEstabelecimento);
    }

    public List<Estabelecimento> findAll(){
        return estabelecimentoRepository.findAll();
    }

    public Estabelecimento findById(Long id) {
        Optional<Estabelecimento> obj = estabelecimentoRepository.findById(id);
        return obj.orElse(null);
    }

}
