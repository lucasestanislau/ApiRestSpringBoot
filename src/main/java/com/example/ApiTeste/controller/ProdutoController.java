package com.example.ApiTeste.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiTeste.error.CustomErrorType;
import com.example.ApiTeste.error.ResourceNotFoundException;
import com.example.ApiTeste.model.Produto;
import com.example.ApiTeste.repository.ProdutoRepository;

@RestController
@RequestMapping("/v1")
public class ProdutoController {
	/* Paginação http://localhost:8080/produtos - retorna tudo
	 * 			http://localhost:8080/produtos?page=2 - retorna a página específicada
	 * 			http://localhost:8080/produtos?page=2&size=10 - retorna a página específicada - size define o tamanho da página
	 *			http://localhost:8080/produtos?sort=id,desc
	 * */

	@Autowired
	private ProdutoRepository produtoDAO;

	@GetMapping(path = "/protected/produto/{id}")
	public ResponseEntity<?> getProdutoById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println();
		System.out.println(userDetails);
		System.out.println();
		verificaSeProdutoExiste(id);
		Optional<Produto> a = produtoDAO.findById(id);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	@GetMapping(path = "/protected/produtos")
	public ResponseEntity<?> getProdutos(Pageable pageable){
		return new ResponseEntity<>(produtoDAO.findAll(pageable), HttpStatus.OK);
	}
/* Testando transaction
	@PostMapping(path = "/produtos")
	@Transactional se for exection checked @Transactional(value = Exception.class)
	public ResponseEntity<?> postProduto(@RequestBody Produto produto){
		produtoDAO.save(produto);
		produtoDAO.save(produto);
		if(true)
			throw new RuntimeException("Test Transation");
		produtoDAO.save(produto);
		return new ResponseEntity<>(produtoDAO.save(produto), HttpStatus.OK);
	}*/
	
	@PostMapping(path = "/admin/produtos")
	public ResponseEntity<?> postProduto(@Valid @RequestBody Produto produto){
		return new ResponseEntity<>(produtoDAO.save(produto), HttpStatus.OK);
	}

	@DeleteMapping(path = "/admin/produto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id){
		verificaSeProdutoExiste(id);
		produtoDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/admin/produtos")
	public ResponseEntity<?> updateProduto(@RequestBody Produto p){
		verificaSeProdutoExiste(p.getId());
		produtoDAO.save(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/protected/produtos/findByDescricao/{descricao}")
	public ResponseEntity<?> findByDescricao(@PathVariable String descricao){
		return new ResponseEntity<>(produtoDAO.findByDescricaoIgnoreCaseContaining(descricao), HttpStatus.OK);
	}

	public void verificaSeProdutoExiste(Long id) {
		Optional<Produto> a = produtoDAO.findById(id);
		if(!a.isPresent()) {
			throw new ResourceNotFoundException("Student not found for id: "+ id);
		}
	}

}
