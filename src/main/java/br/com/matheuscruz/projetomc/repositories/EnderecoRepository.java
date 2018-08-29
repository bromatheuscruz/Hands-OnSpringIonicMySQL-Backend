package br.com.matheuscruz.projetomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheuscruz.projetomc.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
