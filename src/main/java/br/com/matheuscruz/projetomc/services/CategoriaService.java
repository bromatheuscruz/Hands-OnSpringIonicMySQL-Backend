package br.com.matheuscruz.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.repositories.CategoriaRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id " + id + " não foi encontrado."));
	}

	public Categoria insert(Categoria categoria) {

		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {

		find(categoria.getId());
		return categoriaRepository.save(categoria);

	}

}
