package br.com.firmino.livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.firmino.livraria.dto.CategoriaDto;
import br.com.firmino.livraria.entities.Categoria;
import br.com.firmino.livraria.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	// buscar por id
	public Categoria findById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! ID:" + id + " Tipo: " + Categoria.class.getName(), null));
	}

	// listar
	public static List<CategoriaDto> listaCategoria(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}

	// criar
	public Categoria criarCategoria(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	// atualizar
	public Categoria atualizarCategoria(Long id, CategoriaDto categoriaDto) {
		Categoria categoria = findById(id);
		categoria.setNome(categoriaDto.getNome());
		categoria.setDescricao(categoriaDto.getDescricao());

		return categoriaRepository.save(categoria);
	}

	// deletar
	public void delete(Long id) {
		findById(id);
		categoriaRepository.deleteById(id);
	}
}
