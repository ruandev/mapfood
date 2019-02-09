package br.com.mapfood.service;

import br.com.mapfood.apimaps.FindRotasAndTimeAPI;
import br.com.mapfood.domain.*;
import br.com.mapfood.processors.PedidoProcessor;
import br.com.mapfood.repository.*;
import br.com.mapfood.util.DistanciaEmKm;
import com.google.maps.model.DirectionsStep;
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

	public List<DirectionsStep> gerarRota(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		Estabelecimento estabelecimento = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get();
		Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).get();

		String cordenadasOrigem = estabelecimento.getLongitude()  + ", " + estabelecimento.getLatitude();;
		String cordenadasDestino = cliente.getLongitude()+", "+cliente.getLatitude();

		return FindRotasAndTimeAPI.buscarRotas(cordenadasOrigem,cordenadasDestino);
	}


	public Pedido selecionarMotoBoy(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();

		List<Motoboy> listaTodosMotoBoy = motoBoyRepository.findAll();
		List<Motoboy> motoboysComDistancia = new ArrayList();
		List<Motoboy> motoboyRotasGoogle = new ArrayList();

		String cordenadasOrigem;
		String cordenadasDestino;

		Estabelecimento estabelecimento = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get();

		for (Motoboy motoboy : listaTodosMotoBoy) {

			Double distanciaMotoBoy = DistanciaEmKm.calcularDistancia(
					Double.parseDouble(estabelecimento.getLatitude()),
					Double.parseDouble(estabelecimento.getLongitude()),
					Double.parseDouble(motoboy.getLatitude()),
					Double.parseDouble(motoboy.getLongitude()));

			motoboysComDistancia.add(new Motoboy().builder()
					.id(motoboy.getId())
					.distanciaParaEstabelecimento(distanciaMotoBoy)
					.latitude(motoboy.getLatitude())
					.longitude(motoboy.getLongitude()).build());
		}

		List<Motoboy> motoboysProximos = motoboysComDistancia.stream()
				.sorted(Comparator.comparing(Motoboy::getDistanciaParaEstabelecimento))
				.collect(Collectors.toList())
				.subList(0, 5);

		cordenadasOrigem = estabelecimento.getLongitude() + ", " + estabelecimento.getLatitude();

		for (Motoboy motoboy : motoboysProximos) {

			cordenadasDestino = motoboy.getLongitude() + "," + motoboy.getLatitude();

			Rotas rota = FindRotasAndTimeAPI.buscarDistanciaTempo(cordenadasOrigem, cordenadasDestino);

			motoboy.setDistanciaParaEstabelecimento(rota.getDistanciaMetros());

			motoboyRotasGoogle.add(motoboy);
		}

		//ordena a lista pela distancia
		Motoboy motoboyMaisPerto = motoboyRotasGoogle.stream()
				.min(Comparator.comparing(Motoboy::getDistanciaParaEstabelecimento))
				.get();

		pedido.setIdMotoboy(motoboyMaisPerto.getId());
		pedido = pedidoRepository.save(pedido);

		return pedido;
	}

	public Long expectativaEntrega(Long idPedido){
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		Motoboy motoboy = motoBoyRepository.findById(pedido.getIdMotoboy()).get();
		Estabelecimento estabelecimento = estabelecimentoRespository.findById(pedido.getIdEstabelecimento()).get();
		Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).get();

		Long tempoSegundoMotoboyEstabelecimento = FindRotasAndTimeAPI.buscarDistanciaTempo(motoboy.getLongitude(), motoboy.getLatitude(), estabelecimento.getLongitude(), estabelecimento.getLatitude()).getTempoSegundos();
		Long tempoMinutosMotoboyEstabelecimento = tempoSegundoMotoboyEstabelecimento/60;

		Long tempoSegundoEstabelecimentoCliente = FindRotasAndTimeAPI.buscarDistanciaTempo(estabelecimento.getLongitude(), estabelecimento.getLatitude(), cliente.getLongitude(), cliente.getLatitude()).getTempoSegundos();
		Long tempoMinutosEstabelecimentoCliente = tempoSegundoEstabelecimentoCliente/60;

		Long expectativa;

		if(tempoMinutosMotoboyEstabelecimento > 10){
			expectativa = tempoMinutosMotoboyEstabelecimento+tempoMinutosEstabelecimentoCliente;
		} else {
			expectativa =  10+tempoMinutosEstabelecimentoCliente;
		}

		pedido.setTempoExpectativaEntrega(expectativa);

		pedidoRepository.save(pedido);

		return expectativa;
	}
}
