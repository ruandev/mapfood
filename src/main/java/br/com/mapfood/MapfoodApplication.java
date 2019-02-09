package br.com.mapfood;

import br.com.mapfood.service.ClienteService;
import br.com.mapfood.service.EstabelecimentoService;
import br.com.mapfood.service.MotoboyService;
import br.com.mapfood.service.PedidoService;
import br.com.mapfood.service.ProdutosEstabelecimentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapfoodApplication implements CommandLineRunner {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private MotoboyService motoboyService;

	@Autowired
	private ProdutosEstabelecimentoService produtosEstabelecimentoService;
	
	@Autowired
	private PedidoService pedidoService;
	

	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		clienteService.processarFileCliente();

		estabelecimentoService.processarFileEstabelecimento();

		motoboyService.processarFileMotoboy();

		produtosEstabelecimentoService.processarFileProdutosEstabelecimento();
		
		pedidoService.criarDados();
		
	}
}

