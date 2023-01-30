package br.com.firmino.livraria.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firmino.livraria.dto.CategoriaDto;
import br.com.firmino.livraria.entities.Categoria;
import br.com.firmino.livraria.repositories.CategoriaRepository;
import br.com.firmino.livraria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	@Operation(summary = "Buscar Categoria")
	public ResponseEntity<Optional<Categoria>> buscarCategoria(@PathVariable Long id) {
		Optional<Categoria> categorias = categoriaRepository.findById(id);
		return ResponseEntity.ok().body(categorias);
	}

	@GetMapping
	@Operation(summary = "Listar Categorias")
	public List<CategoriaDto> listarCategorioas() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return CategoriaService.listaCategoria(categorias);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Categoria")
	public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.criarCategoria(categoria);
		return ResponseEntity.ok().body(categoria);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Categoria")
	public ResponseEntity<CategoriaDto> atualizarCategoria(@Valid @PathVariable Long id,
			@RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaService.atualizarCategoria(id, categoriaDto);
		return ResponseEntity.ok(new CategoriaDto(categoria));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Categoria")
	public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
