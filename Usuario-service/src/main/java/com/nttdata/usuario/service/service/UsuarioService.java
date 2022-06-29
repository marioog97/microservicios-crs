package com.nttdata.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nttdata.usuario.service.entity.Usuario;
import com.nttdata.usuario.service.feignclients.CocheFeignClient;
import com.nttdata.usuario.service.feignclients.MotoFeignClient;
import com.nttdata.usuario.service.model.Coche;
import com.nttdata.usuario.service.model.Moto;
import com.nttdata.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CocheFeignClient cocheFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();

	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevousuario = usuarioRepository.save(usuario);
		return nuevousuario;
	}

	public List<Coche> getCoches(int usuarioId) {
		List<Coche> coches = restTemplate.getForObject("http://localhost:8002/coche/usuario/" + usuarioId, List.class);
		return coches;
	}

	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

	public Coche saveCoche(int usuarioId, Coche coche) {
		coche.setUsuarioId(usuarioId);
		Coche nuevoCoche = cocheFeignClient.save(coche);
		return nuevoCoche;
	}

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}

	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		resultado.put("Mensaje", usuario);
		List<Coche> coches = cocheFeignClient.getCoches(usuarioId);
		if (coches.isEmpty()) {
			resultado.put("Coches", "El ususario no tiene coches");
		} else {
			resultado.put("Coches", coches);
		}

		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos.isEmpty()) {
			resultado.put("Motos", "El ususario no tiene motos");
		} else {
			resultado.put("Motos", motos);
		}
		return resultado;

	}
}
