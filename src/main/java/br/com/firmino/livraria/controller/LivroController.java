package br.com.firmino.livraria.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.firmino.livraria.dto.LivroDto;
import br.com.firmino.livraria.entities.Livro;
import br.com.firmino.livraria.repositories.LivroRepository;
import br.com.firmino.livraria.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private LivroService livroService;

	@GetMapping("/{id}")
	@Operation(summary = "Buscar Livro")
	public ResponseEntity<Optional<Livro>> buscarLivro(@PathVariable Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		return ResponseEntity.ok().body(livro);
	}

	@GetMapping("/todos")
	@Operation(summary = "Listar Todos os Livros")
	public List<LivroDto> listarLivros() {
		List<Livro> livro = livroRepository.findAll();
		return LivroService.listarTodosLivros(livro);
	}

	@GetMapping
	// localhost:8085/livros?categoria=1
	@Operation(summary = "Listar Livros Por Categoria")
	public ResponseEntity<List<LivroDto>> listarLivrosPorCategoria(
			@RequestParam(value = "categoria", defaultValue = "0") Long id_categoria) {
		List<Livro> listaLivros = livroService.listarLivrosPorCategoria(id_categoria);
		List<LivroDto> listaLivrosDto = listaLivros.stream().map(LivroDto::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaLivrosDto);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Livro")
	public ResponseEntity<Livro> cadastrarLivro(
			@RequestParam(value = "categoria", defaultValue = "0") Long id_categoria, @Valid @RequestBody Livro livro) {
		Livro novoLivro = livroService.criarLivro(id_categoria, livro);
		return ResponseEntity.ok().body(novoLivro);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Livro")
	public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @Valid @RequestBody Livro dados) {
		Livro novoDados = livroService.atualizarLivro(id, dados);
		return ResponseEntity.ok().body(novoDados);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Atualizar Livro Patch")
	public ResponseEntity<Livro> atualizarLivroPatch(@PathVariable Long id, @Valid @RequestBody Livro dados) {
		Livro novoDados = livroService.atualizarLivro(id, dados);
		return ResponseEntity.ok().body(novoDados);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Livro")
	public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
		livroService.delete(id);
		return ResponseEntity.ok().build();

	}
}
