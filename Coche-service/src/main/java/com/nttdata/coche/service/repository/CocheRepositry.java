package com.nttdata.coche.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.coche.service.entity.Coche;

@Repository
public interface CocheRepositry extends JpaRepository<Coche, Integer> {
	List<Coche> findByUsuarioId(int usuarioId);
}
