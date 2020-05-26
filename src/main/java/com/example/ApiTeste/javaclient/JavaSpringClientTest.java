package com.example.ApiTeste.javaclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.ApiTeste.model.Produto;

import antlr.collections.List;

public class JavaSpringClientTest {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://localhost:8080/v1/protected/produto")
				.basicAuthentication("rara", "lucas").build();
		Produto produto = restTemplate.getForObject("/{id}", Produto.class, 49);
		ResponseEntity<Produto> forEntity = restTemplate.getForEntity("/{id}", Produto.class, 49);
		
		System.out.println(produto);
		System.out.println(forEntity.getBody());
		
	}
}
