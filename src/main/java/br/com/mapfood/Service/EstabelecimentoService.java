package br.com.mapfood.Service;

import java.util.List;
import java.util.Optional;

import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<Estabelecimento> findAll(){
        return estabelecimentoRepository.findAll();
    }

    public Estabelecimento findById(Long id) {
        Optional<Estabelecimento> obj = estabelecimentoRepository.findById(id);
        return obj.orElse(null);
    }

}
