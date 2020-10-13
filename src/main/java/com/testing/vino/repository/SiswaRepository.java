package com.testing.vino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testing.vino.entity.Siswa;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, String>{

	void save(List<Siswa> studentList);
	
}
