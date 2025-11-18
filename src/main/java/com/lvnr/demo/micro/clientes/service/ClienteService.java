package com.lvnr.demo.micro.clientes.service;

import java.util.List;

import com.lvnr.demo.micro.clientes.dto.ClienteDto;

public interface ClienteService {

	public ClienteDto crearCliente(ClienteDto clienteDto);

	public List<ClienteDto> consultarClientes();

	public ClienteDto consultarClientePorCodigo(Integer codigoCliente);

	public ClienteDto actualizarCliente(ClienteDto clienteDto, Integer codigoCliente);

	public String eliminarCliente(Integer codigoCliente);
}
