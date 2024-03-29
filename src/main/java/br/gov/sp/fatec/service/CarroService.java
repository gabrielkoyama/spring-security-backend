package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.model.Carro;

public interface CarroService {
	
	public List<Carro> todos();	
	public Carro salvar(Carro carro);
	public void excluir(Long idCarro);
	public Carro buscarPorId(Long idCarro);
	public List<Carro> buscarDisponiveis();
	public List<Carro> buscarReservados();
	
}
