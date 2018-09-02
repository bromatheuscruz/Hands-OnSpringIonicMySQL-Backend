package br.com.matheuscruz.projetomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.domain.Produto;
import br.com.matheuscruz.projetomc.repositories.CategoriaRepository;
import br.com.matheuscruz.projetomc.repositories.ProdutoRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id: " + id + " não foi encontrado."));

	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

	}
}
