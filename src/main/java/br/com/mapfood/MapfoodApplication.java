package br.com.mapfood;

import java.util.Arrays;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.repository.ClienteRepository;
import br.com.mapfood.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapfoodApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;


	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Cliente c1 = new Cliente(1l,"-51.228496", "-30.03742831");
        Cliente c2 = new Cliente(2l,"-51.136677", "-30.07824631");

        clienteRepository.saveAll(Arrays.asList(c1,c2));

		Estabelecimento r1 =new Estabelecimento(1L,"5640f4538237d9c4aaf3b751c4d11769b3fc1e3165ed3b912c508768f7fc15fdl",
				"Monteiro's Lanches","SAO PAULO", "-46.640973","-23.603658","Lanches");
		Estabelecimento r2 =new Estabelecimento(2L,"a660f6374ab1a743468f576cb89e3a8cd9b9776a363ada7002a5db6066a8b572",
				"True Food",
						"SAO PAULO","-46.657908","-23.554423","Comida Saudável");
		Estabelecimento r3=	new Estabelecimento(3L,"07fc65555705243de378cc34222909fe9f44ad055b855ec8b7b9b8df86d70874",
				"Red Galeteria","SAO PAULO","-46.688782","-23.600529","Comida Variada");

		estabelecimentoRepository.saveAll(Arrays.asList(r1,r2,r3));



	}
}

