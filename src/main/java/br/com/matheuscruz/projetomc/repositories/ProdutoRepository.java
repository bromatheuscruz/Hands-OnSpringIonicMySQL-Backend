package br.com.matheuscruz.projetomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheuscruz.projetomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
