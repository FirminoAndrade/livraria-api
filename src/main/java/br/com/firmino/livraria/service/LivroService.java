package br.com.firmino.livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.firmino.livraria.dto.LivroDto;
import br.com.firmino.livraria.entities.Categoria;
import br.com.firmino.livraria.entities.Livro;
import br.com.firmino.livraria.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private CategoriaService categoriaService;

	// buscar por id
	private Livro findById(Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		return livro.orElseThrow(() -> new ObjectNotFoundException(
				"Livro não encontrado! ID:" + id + " Tipo: " + Livro.class.getName(), null));
	}

	// listar livros por categoria
	public List<Livro> listarLivrosPorCategoria(Long id_categoria) {
		categoriaService.findById(id_categoria);
		return livroRepository.findByIdCategoria(id_categoria);
	}

	// listar
	public static List<LivroDto> listarTodosLivros(List<Livro> livro) {
		return livro.stream().map(LivroDto::new).collect(Collectors.toList());
	}

	// criar
	public Livro criarLivro(Long id_categoria, Livro livro) {
		livro.setId(null);
		Categoria categoria = categoriaService.findById(id_categoria);
		livro.setCategoria(categoria);
		return livroRepository.save(livro);
	}

	// atualizar
	public Livro atualizarLivro(Long id, Livro dados) {
		Livro novoDados = findById(id);
		atualizarDadosDoLivro(novoDados, dados);
		return livroRepository.save(novoDados);
	}

	// atualizar dados do livro
	private void atualizarDadosDoLivro(Livro novoDados, Livro livro) {
		novoDados.setTitulo(livro.getTitulo());
		novoDados.setNome_autor(livro.getNome_autor());
		novoDados.setTexto(livro.getTexto());

	}

	// deletar
	public void delete(Long id) {
		findById(id);
		livroRepository.deleteById(id);
	}
}
