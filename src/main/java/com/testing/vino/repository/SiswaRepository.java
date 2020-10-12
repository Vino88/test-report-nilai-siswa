package com.testing.vino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.testing.vino.entity.Siswa;

public interface SiswaRepository extends PagingAndSortingRepository<Siswa, String>, JpaSpecificationExecutor<Siswa>{

}
