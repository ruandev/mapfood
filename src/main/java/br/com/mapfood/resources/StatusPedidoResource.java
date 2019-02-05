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

    @ApiOperation(value = "Buscar status do Pedido por Cliente")
    @GetMapping(value = "/cliente/{idCliente}")
    public ResponseEntity<Optional<Pedido>> localizarStatusPedidoPorCliente(@PathVariable("idCliente") Long idCliente){

       return pedidoService.pedidoPorCliente(idCliente);
    }

    @ApiOperation(value = "Listar todos os pedidos por status")
    @GetMapping
    public List<Pedido> listar(){

        return pedidoService.findAllOrderByStatus();
    }

    @ApiOperation(value = "Listar todos os pedidos com status aceito")
    @GetMapping(value = "/aceito")
    public List<Pedido> listarPedidosStatusAceito(){
        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status preparando")
    @GetMapping(value = "/preparando")
    public List<Pedido> listarPedidosStatusPreparando(){
        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status pronto")
    @GetMapping(value = "/pronto")
    public List<Pedido> listarPedidosStatusPronto(){
        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status em deslocamento")
    @GetMapping(value = "/em-deslocamento")
    public List<Pedido> listarPedidosStatusEmDeslocamento(){
        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status entregue")
    @GetMapping(value = "/entregue")
    public List<Pedido> listarPedidosStatusEntregue(){
        return null;
    }

    //Por Restaurante:

    @ApiOperation(value = "Listar todos os pedidos por status por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}")
    public List<Pedido> listarPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status aceito por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/aceito")
    public List<Pedido> listarPedidosStatusAceitoPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status preparando por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/preparando")
    public List<Pedido> listarPedidosStatusPreparandoPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status pronto por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/pronto")
    public List<Pedido> listarPedidosStatusProntoPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status em deslocamento por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/em-deslocamento")
    public List<Pedido> listarPedidosStatusEmDeslocamentoPorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }

    @ApiOperation(value = "Listar todos os pedidos com status entregue por restaurante")
    @GetMapping(value = "/restaurante/{idRestaurante}/entregue")
    public List<Pedido> listarPedidosStatusEntreguePorRestaurante(@PathVariable("idRestaurante") Long idRestaurante){

        return null;
    }
}
