package com.gabriel.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Cliente;
import com.gabriel.os.domain.OS;
import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.domain.enums.Prioridade;
import com.gabriel.os.domain.enums.Status;
import com.gabriel.os.repositories.ClienteRepository;
import com.gabriel.os.repositories.OSRepository;
import com.gabriel.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Rafael Santos", "171.788.080-04", "(61) 98271-4835");
		Tecnico t2 = new Tecnico(null, "Franscisca Teodoro", "583.764.390-73", "(61) 98492-9317");
		Cliente c1 = new Cliente(null, "André Santana", "613.037.750-93", "(61) 93274-06372");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);
		OS os2 = new OS(null, Prioridade.BAIXA, "@@@@@@@@@@@", Status.ABERTO, t2, null);

		t1.getList().add(os1);
		c1.getList().add(os1);
		t2.getList().add(os2);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1, os2));
	}
}
