package br.com.mapfood.repository;

import br.com.mapfood.domain.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

<<<<<<< HEAD
    Estabelecimento findBycodigoRestaurante(String restaurantId);
=======
	Estabelecimento findBycodigoRestaurante(String restaurantId);

>>>>>>> master
}
