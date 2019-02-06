package br.com.mapfood.repository;

import br.com.mapfood.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByIdCliente(Long idCliente);

    List<Pedido> findAllByOrderByStatusPedido();
}
