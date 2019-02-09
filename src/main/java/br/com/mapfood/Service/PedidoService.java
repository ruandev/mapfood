package br.com.mapfood.Service;

import br.com.mapfood.APIMaps.FindRotasAndTimeAPI;
import br.com.mapfood.ApiMatrixGoogleMaps.FindRotasSteps;
import br.com.mapfood.ApiMatrixGoogleMaps.MotoboyRotas;
import br.com.mapfood.ApiMatrixGoogleMaps.ObjectRotasSteps;
import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.domain.Pedido;
import br.com.mapfood.domain.Rotas;
import br.com.mapfood.processors.PedidoProcessor;
import br.com.mapfood.repository.*;
import br.com.mapfood.util.DistanciaEmKm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PedidoService {

	@Autowired
	private MotoboyRepository motoBoyRepository;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRespository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoProcessor  pedidoProcessor;
	
	@Autowired
	private ItemDoPedidoRepository itemDoPedidoRepository;
	
	public void criarDados() {
		List<Pedido> listaPedido =pedidoProcessor.criarPedidos();
		pedidoRepository.saveAll(listaPedido);
		
		for(Pedido pedido: listaPedido) {
			itemDoPedidoRepository.saveAll( pedido.getItens());
		
		}
		
	}
	
	public List<Pedido> findAll(){
		
	    return pedidoRepository.findAll();
	}

	public Pedido findById(Long id) {
	    Optional<Pedido> obj = pedidoRepository.findById(id);
	    return obj.orElse(null);
	}
	
	public void validaPedido(Pedido pedido) {		
	
		
	}

	public Pedido gerarRota(Pedido pedido) {
		
		 String latitudeEstabelecimento = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get().getLatitude();		 
		 String longitudeEstabelecimento = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get().getLongitude();
		 String latitudeCliente = clienteRepository.findById(pedido.getIdCliente()).get().getLatitude();
		 String longitudeCliente = clienteRepository.findById(pedido.getIdCliente()).get().getLongitude();
		 String cordenadasOrigem;
		 String cordenadasDestino;
		 
		 System.out.println(latitudeEstabelecimento+" "+longitudeEstabelecimento);
		 System.out.println(latitudeCliente+" "+longitudeCliente);
		 
		 FindRotasSteps rotasSteps = new FindRotasSteps();
		 
		 cordenadasOrigem =longitudeEstabelecimento  + ", " + latitudeEstabelecimento;
		 cordenadasDestino = longitudeCliente+", "+latitudeCliente;

		 ObjectRotasSteps objectSteps= rotasSteps.Api(cordenadasOrigem,cordenadasDestino);
		 
		return null;
	}
	
	
	public Pedido selecionarMotoBoy(Pedido pedido) {
		
		 List<Motoboy> listaTodosMotoBoy = motoBoyRepository.findAll();
		 List<MotoboyRotas> motoboyRotas = new ArrayList();
		 List<MotoboyRotas> motoboyRotasGoogle = new ArrayList();
	 
		 String cordenadasOrigem;
		 String cordenadasDestino;
		 
		 DistanciaEmKm distancia = new DistanciaEmKm();
		 
		 String latitude = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get().getLatitude();		 
		 String longitude = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get().getLongitude();
		 
		 Double distanciaMotoBoy ;		 
		 
		 for(Motoboy motoboy:listaTodosMotoBoy) {
			 
			distanciaMotoBoy =distancia.distanciaEmKM(
					Double.parseDouble(latitude), 
					Double.parseDouble(longitude),
					Double.parseDouble(motoboy.getLatitude()), 
					Double.parseDouble(motoboy.getLongitude()));
			
			MotoboyRotas m = new MotoboyRotas();
			m.setId(motoboy.getId());
			m.setDistancia(distanciaMotoBoy);			
			motoboyRotas.add(m);
		 }		 
		 	  
	     List<MotoboyRotas> listagenOrdenadasPorDistancia = motoboyRotas.stream()
	    		    .sorted(Comparator.comparing(MotoboyRotas::getDistancia))
	    		    .collect(Collectors.toList());		 

		 cordenadasOrigem = longitude + ", " + latitude;

		 FindRotasAndTimeAPI findRotasAndTimeAPI =  new FindRotasAndTimeAPI ();
		 		 
		 Rotas testeRotaUM;
		 
		 for(int i =0; i<5;i++) {
			 MotoboyRotas motoboyRotas2 = new MotoboyRotas();

			 Optional<Motoboy> motoboy = motoBoyRepository.findById(listagenOrdenadasPorDistancia.get(i).getId());
			 
			 cordenadasDestino=motoboy.get().getLongitude()+","+motoboy.get().getLatitude();
             testeRotaUM =  findRotasAndTimeAPI.ApiBuscaRota(cordenadasOrigem , cordenadasDestino);
             
             motoboyRotas2.setId(motoboy.get().getId());
             motoboyRotas2.setDistancia(testeRotaUM.getDistanciaMetros());
             motoboyRotasGoogle.add(motoboyRotas2);
             System.out.println("motoboyRotas 2 = "+motoboy.get().getId()+" "+testeRotaUM.getDistanciaMetros());
		 }
		 
		 //ordena a lista pela distancia
		 List<MotoboyRotas> listagemGoogleOrdenada = motoboyRotasGoogle.stream()
	    		    .sorted(Comparator.comparing(MotoboyRotas::getDistancia))
	    		    .collect(Collectors.toList());	
		 
	     Long idMotoboy = listagemGoogleOrdenada.get(0).getId();
	     
	     pedido.setIdMotoboy(idMotoboy);
	     pedidoRepository.save(pedido);
	     
		 return pedido;
	}
}
