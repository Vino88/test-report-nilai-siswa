package com.testing.vino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.testing.vino.entity.Nilai;
import com.testing.vino.entity.composite.NilaiId;

public interface NilaiRepository extends JpaRepository<Nilai, NilaiId>, JpaSpecificationExecutor<Nilai> {

}
