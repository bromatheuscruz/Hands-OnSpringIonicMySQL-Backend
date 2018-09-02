package br.com.matheuscruz.projetomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.domain.Produto;
import br.com.matheuscruz.projetomc.dto.ProdutoDTO;
import br.com.matheuscruz.projetomc.resources.utils.URL;
import br.com.matheuscruz.projetomc.services.ProdutoService;

@RequestMapping("/produtos")
@RestController
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto produto = produtoService.find(id);

		if (produto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(produto);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "categorias", defaultValue = "") String categorias,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "4") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {

		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);

		Page<Produto> pagesProduto = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> pagesProdutoDTO = pagesProduto.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(pagesProdutoDTO);

	}

}
