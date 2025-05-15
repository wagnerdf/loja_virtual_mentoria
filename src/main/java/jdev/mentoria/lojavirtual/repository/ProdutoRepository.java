package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jdev.mentoria.lojavirtual.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) > 0 FROM produto WHERE unaccent(UPPER(TRIM(nome))) = unaccent(UPPER(TRIM(?1)))")
	public boolean existeProduto(String nomeProduto);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) > 0 FROM produto WHERE unaccent(UPPER(TRIM(nome))) = unaccent(UPPER(TRIM(?1))) AND empresa_id = ?2")
	public boolean existeProduto(String nomeProduto, Long idEmpresa);

	@Query("SELECT a FROM Produto a WHERE UPPER(TRIM(a.nome)) LIKE %?1%")
	public List<Produto> buscarProdutoNome(String nome);
	
	@Query("SELECT a FROM Produto a WHERE UPPER(TRIM(a.nome)) LIKE %?1% and a.empresa.id = ?2")
	public List<Produto> buscarProdutoNome(String nome, Long idEmpresa);

}
