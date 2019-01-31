package br.com.mapfood;

import br.com.mapfood.Service.ClienteService;
import br.com.mapfood.Service.EstabelecimentoService;
import br.com.mapfood.Service.MotoboyService;
import br.com.mapfood.Service.ProdutosEstabelecimentoService;
import br.com.mapfood.domain.ProdutosEstabelecimento;
import br.com.mapfood.repository.ProdutosEstabelecimentoRepository;
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
	private ProdutosEstabelecimentoRepository produtosEstabelecimentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		clienteService.processarFileCliente();

		estabelecimentoService.processarFileEstabelecimento();

		motoboyService.processarFileMotoboy();

		produtosEstabelecimentoService.processarFileProdutosEstabelecimento();

		ProdutosEstabelecimento p1 =  new ProdutosEstabelecimento(1L,"Galinhada executivo","00002d8401f2d47cef5bb54b4e1cea226ec5155f2b5fb5fab4163b71d3eeb281","3848076bc47f22e56d585493e80f8563872b5f6912e7647d346c51096bfb56be","Mineiro Delivery - Boa Viagem","Almoço por até R$ 13,90",12.1d,"RECIFE");

		ProdutosEstabelecimento p2 =  new ProdutosEstabelecimento(2L,"Galinhada executivo",
				"00002d8401f2d47cef5bb54b4e1cea226ec5155f2b5fb5fab4163b71d3eeb281","3848076bc47f22e56d585493e80f8563872b5f6912e7647d346c51096bfb56be","Mineiro Delivery - Boa Viagem","Almoço por até R$ 13,90",12.1d,"RECIFE");

		produtosEstabelecimentoRepository.saveAll(Arrays.asList(p1,p2));
	}
}

