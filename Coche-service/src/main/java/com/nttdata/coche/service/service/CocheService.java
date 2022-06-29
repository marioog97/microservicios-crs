package com.nttdata.coche.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.coche.service.entity.Coche;
import com.nttdata.coche.service.repository.CocheRepositry;

@Service
public class CocheService {
	@Autowired
	private CocheRepositry cocheRepository;
	
	public List<Coche> getAll(){
		return cocheRepository.findAll();

	}
	
	public Coche getCocheById(int id) {
		return cocheRepository.findById(id).orElse(null);
	}
	
	public Coche save(Coche coche) {
		Coche cochenuevo = cocheRepository.save(coche);
		return cochenuevo;
	}
	
	public List<Coche> byUsuarioId(int usuarioId){
		return cocheRepository.findByUsuarioId(usuarioId);
	}
}
