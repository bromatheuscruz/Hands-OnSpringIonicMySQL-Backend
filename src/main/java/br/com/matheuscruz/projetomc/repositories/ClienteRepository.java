package br.com.matheuscruz.projetomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheuscruz.projetomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
