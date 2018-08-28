package br.com.matheuscruz.projetomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria categoria = categoriaService.find(id);

		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(categoria);

	}

}
