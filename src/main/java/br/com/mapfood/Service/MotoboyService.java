package br.com.mapfood.Service;

import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.processors.MotoboyProcessor;
import br.com.mapfood.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoboyService {

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private MotoboyProcessor motoboyProcessor;

    public void processarFileMotoboy(){
        List<Motoboy> listMotoboy = motoboyProcessor.lerArquivo();

        motoboyRepository.saveAll(listMotoboy);
    }

    public List<Motoboy> findAll(){
        return motoboyRepository.findAll();
    }

    public Motoboy findById(Long id) {
        Optional<Motoboy> obj = motoboyRepository.findById(id);
        return obj.orElse(null);
    }

}
