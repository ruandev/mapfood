package br.com.mapfood.service;

import br.com.mapfood.apimaps.FindRotasAndTimeAPI;
import br.com.mapfood.domain.*;
import br.com.mapfood.processors.PedidoProcessor;
import br.com.mapfood.repository.*;
import com.google.maps.model.DirectionsStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PedidoService {

	private static final long TEMPO_MAXIMO = 30l;

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

	@Autowired
	private MotoboyService motoboyService;
	
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

	public List<DirectionsStep> gerarRota(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		Estabelecimento estabelecimento = pedido.getEstabelecimento();
		Cliente cliente = pedido.getCliente();

		String cordenadasOrigem = estabelecimento.getLongitude()  + ", " + estabelecimento.getLatitude();;
		String cordenadasDestino = cliente.getLongitude()+", "+cliente.getLatitude();

		return FindRotasAndTimeAPI.buscarRotas(cordenadasOrigem,cordenadasDestino);
	}


	public Pedido selecionarMotoBoy(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido).get();

		Motoboy motoboyMaisPerto = motoboyService.definirMotoboyPedido(pedido);

		pedido.setMotoboy(motoboyMaisPerto);
		pedido = pedidoRepository.save(pedido);

		return pedido;
	}

	public Long previsaoDeEntrega(Long idPedido){
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		Motoboy motoboy = pedido.getMotoboy();
		Estabelecimento estabelecimento = pedido.getEstabelecimento();
		Cliente cliente = pedido.getCliente();

		Long expectativa = calcularPrevisao(motoboy, estabelecimento, cliente);

		pedido.setTempoExpectativaEntrega(expectativa);

		pedidoRepository.save(pedido);

		return expectativa;
	}

	public List<Pedido> montarRoteiro(List<Long> listIdsPedidos){
		List<Pedido> pedidos = pedidoRepository.findAllById(listIdsPedidos);
		List<Cliente> listCliente = pedidos.stream().map(Pedido::getCliente).collect(Collectors.toList());
		Estabelecimento estabelecimento = pedidos.get(0).getEstabelecimento();

		for(Cliente cliente : listCliente){
			Rotas rota = FindRotasAndTimeAPI.buscarDistanciaTempo(estabelecimento.getLongitude(), estabelecimento.getLatitude(), cliente.getLongitude(), cliente.getLatitude());

			pedidos.stream()
					.filter(p -> p.getCliente().equals(cliente))
					.findFirst().get()
					.setTempoEstabelecimentoCliente(rota.getTempoSegundos()/60);
		}

		pedidos = pedidos.stream()
				.sorted(Comparator.comparing(Pedido::getTempoEstabelecimentoCliente))
				.collect(Collectors.toList());

		do {
			Pedido pedidoMaisProximoSemMotoboy = pedidos.stream()
														.filter(p -> p.getMotoboy() == null)
														.findFirst().get();

			Motoboy motoboy = motoboyService.definirMotoboyPedido(pedidoMaisProximoSemMotoboy);

			pedidos.stream()
					.filter(p -> p.getIdPedido().equals(pedidoMaisProximoSemMotoboy.getIdPedido()))
					.findFirst().get()
					.setMotoboy(motoboy);

			Long previsaoEntrega = calcularPrevisao(motoboy, pedidoMaisProximoSemMotoboy.getEstabelecimento(), pedidoMaisProximoSemMotoboy.getCliente());

            pedidos.stream()
                    .filter(p -> p.getIdPedido().equals(pedidoMaisProximoSemMotoboy.getIdPedido()))
                    .findFirst().get()
                    .setTempoExpectativaEntrega(previsaoEntrega);

			List<Pedido> pedidosSemMotoboy = pedidos.stream()
													.filter(p -> p.getMotoboy() == null)
													.collect(Collectors.toList());

			for (int i = 0; i < pedidosSemMotoboy.size(); i++) {
				if (previsaoEntrega < TEMPO_MAXIMO) {
					Long tempoEntreClientes = calcularTempoCliente(pedidoMaisProximoSemMotoboy.getCliente(),
																	pedidosSemMotoboy.get(i).getCliente());

					if (previsaoEntrega + tempoEntreClientes <= TEMPO_MAXIMO) {
						previsaoEntrega = previsaoEntrega + tempoEntreClientes;
						int finalI = i;
						pedidos.stream()
								.filter(p -> p.getIdPedido() == pedidosSemMotoboy.get(finalI).getIdPedido())
								.findFirst().get()
								.setMotoboy(motoboy);
                        pedidos.stream()
                                .filter(p -> p.getIdPedido() == pedidosSemMotoboy.get(finalI).getIdPedido())
                                .findFirst().get()
                                .setTempoExpectativaEntrega(previsaoEntrega);
					} else {
						motoboy.setDisponivel(false);
						motoBoyRepository.save(motoboy);
						break;
					}
				}
			}

		} while(pedidos.get(pedidos.size()-1).getMotoboy() == null);


		return pedidoRepository.saveAll(pedidos);
	}



	private Long calcularPrevisao(Motoboy motoboy, Estabelecimento estabelecimento, Cliente cliente) {
		Long tempoSegundoMotoboyEstabelecimento = FindRotasAndTimeAPI.buscarDistanciaTempo(motoboy.getLongitude(), motoboy.getLatitude(), estabelecimento.getLongitude(), estabelecimento.getLatitude()).getTempoSegundos();
		Long tempoMinutosMotoboyEstabelecimento = tempoSegundoMotoboyEstabelecimento/60;

		Long tempoSegundoEstabelecimentoCliente = FindRotasAndTimeAPI.buscarDistanciaTempo(estabelecimento.getLongitude(), estabelecimento.getLatitude(), cliente.getLongitude(), cliente.getLatitude()).getTempoSegundos();
		Long tempoMinutosEstabelecimentoCliente = tempoSegundoEstabelecimentoCliente/60;

		Long expectativa;

		if(tempoMinutosMotoboyEstabelecimento > 10){
			expectativa = tempoMinutosMotoboyEstabelecimento + tempoMinutosEstabelecimentoCliente;
		} else {
			expectativa =  10 + tempoMinutosEstabelecimentoCliente;
		}
		return expectativa;
	}

	private Long calcularTempoCliente(Cliente clienteOrigem, Cliente proximoCliente) {
		Long tempoSegundo = FindRotasAndTimeAPI.buscarDistanciaTempo(clienteOrigem.getLongitude(), clienteOrigem.getLatitude(), proximoCliente.getLongitude(), proximoCliente.getLatitude()).getTempoSegundos();

		return tempoSegundo/60;
	}
}
