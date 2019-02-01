package br.com.mapfood;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import br.com.mapfood.ApiMatrixGoogleMaps.FindRotasAndTimeAPI;
import br.com.mapfood.ApiMatrixGoogleMaps.ObJectRotas;
import br.com.mapfood.RecordCSV.LerCSV;
import br.com.mapfood.Service.ClienteService;
import br.com.mapfood.Service.MotoboyService;
import br.com.mapfood.domain.Cliente;
import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.repository.ClienteRepository;
import br.com.mapfood.repository.MotoboyRepository;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapfoodApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
    private ClienteService clienteService;

    @Autowired
    private MotoboyService motoboyService;

	@Autowired
	private MotoboyRepository motoboyRepository;

	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

/***
 * Salvando Motoboys no banco de dados
 */
		System.out.println("----- SALVANDO MOTOBOYS -----");
		LerCSV lerCSVMotoboy = new LerCSV();
		List<Motoboy> listMotoboys = new ArrayList<>();
		Stream<CSVRecord> csvRecordStreamMotoboy = StreamSupport.stream(lerCSVMotoboy.buscaInformacoesArquivoMotoboy().spliterator(), false);
		listMotoboys = csvRecordStreamMotoboy
				.skip(1)
				.map(linhaMotoboy ->  new Motoboy( linhaMotoboy.toMap()))
				.collect(Collectors.toList());
		motoboyRepository.saveAll(listMotoboys);


//		// Salvando Motoboys no banco de dados
		System.out.println("----- SALVANDO Clientes -----");
		LerCSV lerCSV = new LerCSV();
		List<Cliente> listClientes = new ArrayList<>();
		Stream<CSVRecord> csvRecordStreamClientes= StreamSupport.stream(lerCSV.buscaInformacoesArquivoClientes().spliterator(), false);
		listClientes  = csvRecordStreamClientes.skip(1).map(l ->  new Cliente( l.toMap())).collect(Collectors.toList());

		listClientes.forEach(System.out::println);
		clienteRepository.saveAll(listClientes);


            FindRotasAndTimeAPI findRotasAndTimeAPI = new FindRotasAndTimeAPI();

            // Liste as coordenadas de latitude antes das coordenadas de longitude.

            //Implementar a primeiro a busca por id e retornar a latitude e longitude e passar elas como parametro

            //Buscando no repotorio das coordenadas do cliente
             Cliente cliente =  clienteService.findById("1");
             String CORDENADAS_TESTE_ORIGEM = cliente.getLatitude() +","+ cliente.getLongitude();

            //Buscando no repositorio das coordenadas do motoboy
            Motoboy motoboy =  motoboyService.findById("1");
            String CORDENADAS_TESTE_DESTINO = motoboy.getLatitude() +","+ motoboy.getLongitude();

            ObJectRotas testeRotaUM = new ObJectRotas();
            testeRotaUM = findRotasAndTimeAPI.Api(CORDENADAS_TESTE_ORIGEM, CORDENADAS_TESTE_DESTINO);

            System.out.println(testeRotaUM);



    }
}

