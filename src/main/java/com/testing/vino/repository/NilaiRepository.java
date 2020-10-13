package com.testing.vino.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.testing.vino.entity.Nilai;
import com.testing.vino.entity.composite.NilaiId;

public interface NilaiRepository extends PagingAndSortingRepository<Nilai, NilaiId>, JpaSpecificationExecutor<Nilai> {
	
	@Query(nativeQuery = true, value="SELECT * FROM nilai a LEFT JOIN siswa b on a.nomor_induk=b.nomor_induk where a.nomor_induk=:nomorInduk AND b.nama=:nama AND "
			+ "a.mata_pelajaran=:mataPelajaran AND a.nilai=:nilai")
	Iterable<Nilai> findByFilter(@Param("nomorInduk") String nomorInduk,
								@Param("nama") String nama,
								@Param("mataPelajaran") String mataPelajaran,
								@Param("nilai") Double nilai);
	
	@Query(nativeQuery = true, value = "select * from nilai where nomor_induk=:nomorInduk")
	Nilai findFirstById(@Param("nomorInduk") String nomorInduk);
}
