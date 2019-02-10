package br.com.mapfood;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.processors.ClienteProcessor;
import br.com.mapfood.repository.ClienteRepository;
import br.com.mapfood.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService service;

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteProcessor processor;

    @InjectMocks
    private Cliente cliente;

    @Test
    public void findAllNaoPodeSerVazio(){
        List<Cliente> result = service.findAll();
        assertThat(result).isNotNull();
    }

    @Test
    public void findAllRetornaTodosClientes(){
        List<Cliente> result = service.findAll();
        assertThat(result.size()).isEqualTo(1005);
    }

    @Test
    public void findByIdTest(){
        Cliente result = service.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getLatitude()).isEqualTo(-30.03742831);
    }

}
