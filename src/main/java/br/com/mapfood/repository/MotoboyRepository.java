package br.com.mapfood.repository;

import br.com.mapfood.domain.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

}
