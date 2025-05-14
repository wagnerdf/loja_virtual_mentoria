package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.CategoriaProduto;
import jdev.mentoria.lojavirtual.model.dto.CategoriaProdutoDTO;
import jdev.mentoria.lojavirtual.repository.CategoriaProdutoRepository;

@RestController
public class CategoriaProdutoControle {
	
	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@ResponseBody
	@GetMapping(value = "**/buscarPorDescCatgoria/{desc}")
	public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<CategoriaProduto> acesso = categoriaProdutoRepository.buscarCategoriaDes(desc.toUpperCase());
		
		return new ResponseEntity<List<CategoriaProduto>>(acesso,HttpStatus.OK);
	}

	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/deleteCategoria") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody CategoriaProduto categoriaProduto) { /*Recebe o JSON e converte pra Objeto*/
		
		categoriaProdutoRepository.deleteById(categoriaProduto.getId());
		
		return new ResponseEntity("Categoria Removida",HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "**/salvarCategoria")
	public ResponseEntity<CategoriaProdutoDTO> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava{
		
		if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null))  {
			throw new ExceptionMentoriaJava("A empresa deve ser informada.");
		}
		
		if (categoriaProduto.getId() == null && categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc())) {
			throw new ExceptionMentoriaJava("Não pode cadastrar categoria com mesmo nome.");
		}
		
		CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);
		
		CategoriaProdutoDTO categoriaProdutoDTO = new CategoriaProdutoDTO();
		categoriaProdutoDTO.setId(categoriaSalva.getId());
		categoriaProdutoDTO.setNomeDesc(categoriaSalva.getNomeDesc());
		categoriaProdutoDTO.setEmpresa(categoriaSalva.getEmpresa().getId().toString());
		
		return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoDTO, HttpStatus.OK);
	}

}
