package br.com.firmino.livraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.firmino.livraria.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :id_categoria ORDER BY titulo")
	List<Livro> findByIdCategoria(@Param(value = "id_categoria") Long id_categoria);
}
