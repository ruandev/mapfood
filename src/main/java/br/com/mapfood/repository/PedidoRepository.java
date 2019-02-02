package br.com.mapfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mapfood.domain.Pedido;


@Repository
public interface  PedidoRepository extends JpaRepository<Pedido, Long> {

}