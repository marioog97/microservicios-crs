package com.nttdata.coche.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.coche.service.entity.Coche;
import com.nttdata.coche.service.service.CocheService;

@RestController
@RequestMapping("/coche")
public class CocheController {

	@Autowired
	private CocheService cocheService;

	@GetMapping
	public ResponseEntity<List<Coche>> listarMotos() {
		List<Coche> motos = cocheService.getAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Coche> obtenerMoto(@PathVariable("id") int id) {
		Coche moto = cocheService.getCocheById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Coche> guardarCoche(@RequestBody Coche coche) {
		Coche nuevoCoche = cocheService.save(coche);
		return ResponseEntity.ok(nuevoCoche);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Coche>> listarCochesPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Coche> coches = cocheService.byUsuarioId(id);
		if (coches.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(coches);
	}

}
