package com.nttdata.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.usuario.service.model.Coche;

@FeignClient(name = "coche-service",url = "http://localhost:8002")
@RequestMapping("/coche")
public interface CocheFeignClient {

	@PostMapping()
	public Coche save(@RequestBody Coche coche);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Coche> getCoches(@PathVariable("usuarioId") int usuarioId);
}