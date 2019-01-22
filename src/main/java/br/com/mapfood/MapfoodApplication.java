package br.com.mapfood;

import java.util.Arrays;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapfoodApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Cliente c1 = new Cliente(1l,"-51.228496", "-30.03742831");
        Cliente c2 = new Cliente(2l,"-51.136677", "-30.07824631");

        clienteRepository.saveAll(Arrays.asList(c1, c2));

	}
}

