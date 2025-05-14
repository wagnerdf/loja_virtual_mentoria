package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jdev.mentoria.lojavirtual.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long>{
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) > 0 FROM categoria_produto WHERE unaccent(UPPER(TRIM(nome_desc))) = unaccent(UPPER(TRIM(?1)))")
	public boolean existeCategoria(String nomeCategoria);

	@Query("SELECT a FROM CategoriaProduto a WHERE UPPER(TRIM(a.nomeDesc)) LIKE %?1%")
	public List<CategoriaProduto> buscarCategoriaDes(String upperCase);

}
