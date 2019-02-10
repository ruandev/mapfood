package br.com.mapfood;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.repository.ClienteRepository;
import br.com.mapfood.resources.ClienteResource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteResourceTest {

    @Autowired
    private ClienteResource clienteResource;

    @Mock
    private Cliente cliente;

    @InjectMocks
    private ClienteRepository clienteRepository;
}
