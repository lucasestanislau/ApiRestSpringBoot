package com.example.ApiTeste.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ApiTeste.model.Produto;


public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>{
	List<Produto> findByDescricaoIgnoreCaseContaining(String descricao);
	
}
