package br.com.mapfood.repository;

import br.com.mapfood.domain.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    @Query("select m from Motoboy m where m.disponivel = true")
    List<Motoboy> findMotoboyListDisponivel();

}
