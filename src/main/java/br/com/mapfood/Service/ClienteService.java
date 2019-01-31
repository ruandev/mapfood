package br.com.mapfood.Service;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.processors.ClienteProcessor;
import br.com.mapfood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteProcessor clienteProcessor;

    public void processarFileCliente(){
        List<Cliente> listCliente = clienteProcessor.lerArquivo();

        clienteRepository.saveAll(listCliente);
    }


    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElse(null);
    }

}
