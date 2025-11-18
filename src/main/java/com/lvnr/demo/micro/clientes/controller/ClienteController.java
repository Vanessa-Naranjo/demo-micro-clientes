package com.lvnr.demo.micro.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lvnr.demo.micro.clientes.dto.ClienteDto;
import com.lvnr.demo.micro.clientes.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteDto clienteDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(clienteDto));
	}

	@GetMapping
	public ResponseEntity<List<ClienteDto>> consultarClientes() {
		return ResponseEntity.ok(clienteService.consultarClientes());
	}

	@GetMapping("/consultarClienteCodigo/{codigoCliente}")
	public ResponseEntity<ClienteDto> consultarClientePorCodigo(@PathVariable Integer codigoCliente) {
		return ResponseEntity.ok(clienteService.consultarClientePorCodigo(codigoCliente));
	}

	@PutMapping("/{codigoCliente}")
	public ResponseEntity<ClienteDto> actualizarCliente(@RequestBody ClienteDto clienteDto,
			@PathVariable Integer codigoCliente) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clienteService.actualizarCliente(clienteDto, codigoCliente));
	}

	@DeleteMapping("/{codigoCliente}")
	public ResponseEntity<String> eliminarCliente(@PathVariable Integer codigoCliente) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteService.eliminarCliente(codigoCliente));
	}

}