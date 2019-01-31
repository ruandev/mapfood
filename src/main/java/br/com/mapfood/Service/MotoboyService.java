package br.com.mapfood.Service;

import java.util.List;
import java.util.Optional;

import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoboyService {

    @Autowired
    private MotoboyRepository motoboyRepository;

    public List<Motoboy> findAll(){
        return motoboyRepository.findAll();
    }

    public Motoboy findById(String id) {
        Optional<Motoboy> obj = motoboyRepository.findById(id);
        return obj.orElse(null);
    }

}
