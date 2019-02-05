package br.com.mapfood.repository;

import br.com.mapfood.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByIdCliente(Long idCliente);

    List<Pedido> findAllByOrderByPedido();
}
