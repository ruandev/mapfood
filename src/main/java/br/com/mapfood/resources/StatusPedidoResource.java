package br.com.mapfood.resources;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.domain.Pedido;
import br.com.mapfood.domain.enums.EstadoDoPedido;
import br.com.mapfood.service.ClienteService;
import br.com.mapfood.service.EstabelecimentoService;
import br.com.mapfood.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/status-pedidos")
public class StatusPedidoResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private PedidoService pedidoService;

    @ApiOperation(value = "Atualizar status do pedido")
    @PutMapping(value = "/{idPedido}/atualizar/{status}")
    public ResponseEntity<?> atualizarStatusPedido(){

        return null;
    }

    @ApiOperation(value = "Buscar status do Pedido")
    @GetMapping(value = "/pedido/{idPedido}")
    public ResponseEntity<Optional<Pedido>> localizarStatusPorPedido(@PathVariable("idPedido") Long idPedido){

        return pedidoService.statusPedido(idPedido);
    }


    @ApiOperation(value = "Listar todos os pedidos de cada status por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/status/{status}")
    public List<Pedido> listarPedidosDeCadaStatusPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Buscar status do Pedido por Cliente")
    @GetMapping(value = "/cliente/{idCliente}/restaurante/{idRestaurante}")
    public ResponseEntity<Optional<Pedido>> localizarStatusPedidoPorCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }
}
