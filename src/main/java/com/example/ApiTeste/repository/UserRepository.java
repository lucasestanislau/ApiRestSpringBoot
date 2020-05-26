package com.example.ApiTeste.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ApiTeste.model.ApiUser;

public interface UserRepository extends PagingAndSortingRepository<ApiUser, Long>{

	ApiUser findByUsername(String username);
}
