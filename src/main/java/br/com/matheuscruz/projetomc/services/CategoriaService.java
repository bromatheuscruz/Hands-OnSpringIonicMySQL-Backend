package br.com.matheuscruz.projetomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.repositories.CategoriaRepository;
import br.com.matheuscruz.projetomc.services.exceptions.DataViolationException;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {

		return categoriaRepository.findAll();
	}

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

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);

		} catch (DataViolationException e) {

			throw new DataViolationException("Não é possível excluir uma categoria que contém produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return categoriaRepository.findAll(pageRequest);

	}
}
