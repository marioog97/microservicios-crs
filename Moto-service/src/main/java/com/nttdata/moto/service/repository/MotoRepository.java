package com.nttdata.moto.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.moto.service.entity.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
	List<Moto> findByUsuarioId(int usuarioId);
}
