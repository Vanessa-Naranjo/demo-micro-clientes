package com.lvnr.demo.micro.clientes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvnr.demo.micro.clientes.dto.ClienteDto;
import com.lvnr.demo.micro.clientes.entity.ClienteEntity;
import com.lvnr.demo.micro.clientes.repository.ClienteRepository;
import com.lvnr.demo.micro.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ClienteDto crearCliente(ClienteDto clienteDto) {
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setNombres(clienteDto.getNombres());
		clienteEntity.setPrimerApellido(clienteDto.getPrimerApellido());
		clienteEntity.setSegundoApellido(clienteDto.getSegundoApellido());
		clienteEntity.setCodigoCliente(clienteDto.getCodigoCliente());
		if (!clienteRepository.existsByCodigoCliente(clienteDto.getCodigoCliente())) {
			clienteRepository.save(clienteEntity);
		} else {
			throw new IllegalArgumentException("El cliente ya existe: " + clienteDto.getCodigoCliente());
		}
		return clienteDto;
	}

	@Override
	public List<ClienteDto> consultarClientes() {
		List<ClienteDto> clientesDto = new ArrayList<>();
		List<ClienteEntity> clientesEntity = this.clienteRepository.findAll();

		for (ClienteEntity clienteEntity : clientesEntity) {
			ClienteDto clienteDto = new ClienteDto();
			clienteDto.setNombres(clienteEntity.getNombres());
			clienteDto.setPrimerApellido(clienteEntity.getPrimerApellido());
			clienteDto.setSegundoApellido(clienteEntity.getSegundoApellido());
			clienteDto.setCodigoCliente(clienteEntity.getCodigoCliente());

			clientesDto.add(clienteDto);
		}
		return clientesDto;
	}

	@Override
	public ClienteDto consultarClientePorCodigo(Integer codigoCliente) {
		ClienteEntity clienteEntity = this.clienteRepository.findByCodigoCliente(codigoCliente);
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setNombres(clienteEntity.getNombres());
		clienteDto.setPrimerApellido(clienteEntity.getPrimerApellido());
		clienteDto.setSegundoApellido(clienteEntity.getSegundoApellido());
		clienteDto.setCodigoCliente(clienteEntity.getCodigoCliente());

		return clienteDto;
	}

	@Override
	public ClienteDto actualizarCliente(ClienteDto clienteDto, Integer codigoCliente) {
		ClienteEntity clienteEntity = this.clienteRepository.findByCodigoCliente(codigoCliente);
		clienteEntity.setNombres(clienteDto.getNombres());
		clienteEntity.setPrimerApellido(clienteDto.getPrimerApellido());
		clienteEntity.setSegundoApellido(clienteDto.getSegundoApellido());
		clienteEntity.setCodigoCliente(clienteDto.getCodigoCliente());
		if (codigoCliente != clienteDto.getCodigoCliente()) {
			if (this.clienteRepository.existsByCodigoCliente(clienteDto.getCodigoCliente())) {
				throw new IllegalArgumentException("El codigo del cliente ya existe: " + clienteDto.getCodigoCliente());
			}
		}
		clienteRepository.save(clienteEntity);

		return clienteDto;
	}

	@Override
	@Transactional
	public String eliminarCliente(Integer codigoCliente) {
		if (this.clienteRepository.existsByCodigoCliente(codigoCliente)) {
			this.clienteRepository.deleteByCodigoCliente(codigoCliente);
			return "El cliente fue eliminado: " + codigoCliente;
		}
		return "El cliente no existe: " + codigoCliente;
	}

}
