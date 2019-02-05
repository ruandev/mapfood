package br.com.mapfood.service;

import br.com.mapfood.domain.Pedido;
import br.com.mapfood.domain.enums.EstadoDoPedido;
import br.com.mapfood.processors.PedidoProcessor;
import br.com.mapfood.repository.ItemDoPedidoRepository;
import br.com.mapfood.repository.MotoboyRepository;
import br.com.mapfood.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoProcessor pedidoProcessor;

    @Autowired
    ItemDoPedidoRepository itemDoPedidoRepository;

    @Autowired
    MotoboyRepository motoboyRepository;

    public void criarDados() {
        List<Pedido> listaPedido = pedidoProcessor.criarPedidos();
        pedidoRepository.saveAll(listaPedido);

        for (Pedido pedido : listaPedido) {
            itemDoPedidoRepository.saveAll(pedido.getItens());
        }
    }

    public List<Pedido> findAll(){
            return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
            Optional<Pedido> obj = pedidoRepository.findById(id);
            return obj.orElse(null);
    }

    public List<Pedido> findAllOrderByStatus() {
        List<Pedido> pedidos = pedidoRepository.findAllByOrderByPedido();
        return pedidos;
    }

    public void validaPedido(Pedido pedido) {

    }

    public ResponseEntity<Optional<Pedido>> statusPedido(Long idPedido){
        Optional<Pedido> statusPedido = pedidoRepository.findById(idPedido);

        return ResponseEntity.ok().body(statusPedido);
    }

    public ResponseEntity<Optional<Pedido>> pedidoPorCliente(Long idCliente){
        Optional<Pedido> cliente = pedidoRepository.findByIdCliente(idCliente);
        return ResponseEntity.ok().body(cliente);
    }

}

