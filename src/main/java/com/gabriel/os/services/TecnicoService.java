package com.gabriel.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Pessoa;
import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.dtos.TecnicoDTO;
import com.gabriel.os.repositories.PessoaRepository;
import com.gabriel.os.repositories.TecnicoRepository;
import com.gabriel.os.services.exceptions.DataIntegratyViolationException;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;


	/*
	 * Busca Tecnico pelo ID
	 */
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	/*
	 * Busca todos os Tecnicos da base de dados
	 */
	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	/*
	 * Cria um Tecnico
	 */
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		Tecnico newTec = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());


		return repository.save(newTec);
	}

	/*
	 * Atualiza um Tecnico
	 */
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}

	/*
	 * Deleta um Tecnico pelo ID
	 */
	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}

		repository.deleteById(id);
	}

	/*
	 * Busca Tecnico pelo CPF
	 */
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}
}
