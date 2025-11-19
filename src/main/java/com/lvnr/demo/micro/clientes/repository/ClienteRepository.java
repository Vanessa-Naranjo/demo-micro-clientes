package com.lvnr.demo.micro.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lvnr.demo.micro.clientes.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

	ClienteEntity findByCodigoCliente(Integer CodigoCliente);

	boolean existsByCodigoCliente(Integer CodigoCliente);

	void deleteByCodigoCliente(Integer CodigoCliente);
	
}
