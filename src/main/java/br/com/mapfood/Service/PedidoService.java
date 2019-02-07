package br.com.mapfood.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.domain.Pedido;
import br.com.mapfood.processors.PedidoProcessor;
import br.com.mapfood.repository.EstabelecimentoRepository;
import br.com.mapfood.repository.ItemDoPedidoRepository;
import br.com.mapfood.repository.MotoboyRepository;
import br.com.mapfood.repository.PedidoRepository;
import br.com.mapfood.util.DistanciaEmKm;


@Service
public class PedidoService {

	@Autowired
	private MotoboyRepository motoBoyRepository;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRespository;
	
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

	public Pedido selecionarMotoBoy(Pedido p) {
		
		 List<Motoboy> listaTodosMotoBoy = motoBoyRepository.findAll();
		 TreeMap<Double,Long> listaMotoboyOrdenada = new TreeMap<Double,Long>() ;
		 TreeMap<Double,Long> listaMotoboy = new TreeMap<Double,Long>() ;

		 
		 DistanciaEmKm distancia = new DistanciaEmKm();
		 System.out.println(p.getIdCliente());
		 
		 String latitude = estabelecimentoRespository.findById(p.getIdCliente()).get().getLatitude();
		 
		 String longitude = estabelecimentoRespository.findById(p.getIdCliente()).get().getLongitude();
		 Double distanciaMotoBoy ;
		 
		 for(Motoboy motoboy:listaTodosMotoBoy) {
			 
			distanciaMotoBoy =distancia.distanciaEmKM(
					Double.parseDouble(latitude), 
					Double.parseDouble(longitude),
					Double.parseDouble(motoboy.getLatitude()), 
					Double.parseDouble(motoboy.getLongitude()));
			
			listaMotoboyOrdenada.put(distanciaMotoBoy ,motoboy.getId())	;			
			
		 }
		 
		 
		 int indice = 0; //, valor = Integer.MIN_VALUE;
		 
	
		 for (Map.Entry<Double, Long> entry : listaMotoboyOrdenada.entrySet()) {
		     if ( indice <10) {
		         listaMotoboy.put(entry.getKey(), entry.getValue());
		    	 System.out.println(entry.getKey()+" "+entry.getValue());
		    	 System.out.println(listaMotoboy);
		    
		     }
		     if (indice >10) break;
		     indice++;
		 }
		 
		 
		 return null;
	}
}
