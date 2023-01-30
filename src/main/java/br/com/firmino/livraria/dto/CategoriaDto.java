package br.com.firmino.livraria.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.firmino.livraria.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

	private Long id;

	@NotEmpty(message = "Campo NOME é requerido!")
	@Length(min = 3, max = 30, message = "O Campo NOME deve ter entre 3 e 30 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido!")
	@Length(min = 3, max = 100, message = "O Campo DESCRIÇÃO deve ter entre 3 e 100 caracteres.")
	private String descricao;

	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}
}
