package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.service.CarroService;
import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/carro")
@CrossOrigin
public class CarroController {
	
	@Autowired
	private CarroService carroService;

	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}

//	
//	@RequestMapping(value = "/teste", method = RequestMethod.GET)
//	public ResponseEntity<Collection<Carro>> getAllDisponiveis() {
//		return new ResponseEntity<Collection<Carro>>(carroService.teste(), HttpStatus.OK);
//	}
	
//	
////  TODOS DISPONIVEIS 
//	
	@RequestMapping(value = "/getAllDisponivel", method = RequestMethod.GET)
	public ResponseEntity<Collection<Carro>> getAllDisponiveis() {
		return new ResponseEntity<Collection<Carro>>(carroService.buscarDisponiveis(), HttpStatus.OK);
	}
	
//	TODOS
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Carro>> getAll() {
		return new ResponseEntity<Collection<Carro>>(carroService.todos(), HttpStatus.OK);
	}
//	
////	NAO DISPONIVEL
//	
	@RequestMapping(value = "/getAllReservados", method = RequestMethod.GET)
	public ResponseEntity<Collection<Carro>> getAllReservados() {
		return new ResponseEntity<Collection<Carro>>(carroService.buscarReservados(), HttpStatus.OK);
	}
	
//	ADICIONAR UM CARRO
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Carro> salvar(@RequestBody Carro carro, UriComponentsBuilder uriComponentsBuilder) {
		carro = carroService.salvar(carro);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + carro.getId()).build().toUri());
		return new ResponseEntity<Carro>(carro, responseHeaders, HttpStatus.CREATED);
	}
	
//	GET CARRO
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Carro>(carroService.buscarPorId(id), HttpStatus.OK);
	}
	
//	DELETAR CARRO
	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Carro> deletePorId(@PathVariable("id") Long id) {
		carroService.excluir(id);
		return new ResponseEntity<Carro>(HttpStatus.OK);
	}
	

}
