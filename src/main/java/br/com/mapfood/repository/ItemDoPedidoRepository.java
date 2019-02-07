package br.com.mapfood.repository;

<<<<<<< HEAD
import br.com.mapfood.domain.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mapfood.domain.ItemDoPedido;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {

}
>>>>>>> master
