package br.com.mapfood;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.repository.ClienteRepository;
import br.com.mapfood.resources.ClienteResource;
import br.com.mapfood.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(ClienteResource.class)
public class ClienteResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Test
    public void listarTest()throws Exception{
        Cliente c = new Cliente(1L, "longitude", "latitude");
        List<Cliente> cs = Arrays.asList(c);

        given(service.findAll()).willReturn(cs);

        mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest()throws Exception{
        Cliente c = new Cliente(1L, "longitude", "latitude");

        given(service.findById(1L)).willReturn(c);

        mockMvc.perform(get("/clientes/{id}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
