package br.com.matheuscruz.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.orElse(null);
	}

}
