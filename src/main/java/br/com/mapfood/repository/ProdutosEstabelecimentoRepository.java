package br.com.mapfood.repository;

import br.com.mapfood.domain.ProdutosEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosEstabelecimentoRepository extends JpaRepository<ProdutosEstabelecimento, Long> {

}
