package br.com.matheuscruz.projetomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheuscruz.projetomc.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
