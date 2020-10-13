package com.testing.vino.component;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.testing.vino.dto.DetailDto;
import com.testing.vino.dto.Response;
import com.testing.vino.entity.Nilai;
import com.testing.vino.repository.NilaiRepository;

@Component
public class ReportSiswaHelperComponent {

	@Autowired
	private NilaiRepository nilaiRepository;
	
	public Response<List<DetailDto>> view(String nomorInduk, String nama, String mataPelajaran, Double nilai){
		List<DetailDto> content = new ArrayList<DetailDto>();

		Specification<Nilai> spec = new Specification<Nilai>() {
            public Predicate toPredicate(Root<Nilai> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
            	
            	Predicate finalPredicate = null;
            	
        		if(nomorInduk != null && nomorInduk.trim().length() > 0) {
            		Predicate NI = builder.like(builder.lower(root.get("nomorInduk")), "%" + nomorInduk.toLowerCase() + "%");
            		finalPredicate = NI;
        		}
        		
        		if(nama != null && nama.trim().length() > 0) {
            		Predicate name =  builder.like(builder.lower(root.join("siswa", JoinType.LEFT).get("nama")), "%" + nama.toLowerCase() + "%");
            		if (finalPredicate == null) {
            			finalPredicate = name;
					} else {
						finalPredicate = builder.and(name, finalPredicate);
					}
        		}
        		
        		if(mataPelajaran != null && mataPelajaran.trim().length() > 0) {
            		Predicate mp = builder.like(builder.lower(root.get("mataPelajaran")), "%" + mataPelajaran.toLowerCase() + "%");
            		if (finalPredicate == null) {
            			finalPredicate = mp;
					} else {
						finalPredicate = builder.and(mp, finalPredicate);
					}
        		}
        		
        		if(nilai != null && nilai.intValue() > 0) {
            		Predicate score = builder.equal(root.get("nilai"), nilai);
            		if (finalPredicate == null) {
            			finalPredicate = score;
					} else {
						finalPredicate = builder.and(score, finalPredicate);
					}
        		}
            	return finalPredicate;
            }
		};
		
		List<Nilai> listNilai = nilaiRepository.findAll(spec);
		
		for(Nilai n : listNilai) {
			DetailDto dto = new DetailDto();
			dto.setId(n.getId());
			dto.setNomorInduk(n.getSiswa().getNomorInduk());
			dto.setMataPelajaran(n.getMataPelajaran());
			dto.setNilai(n.getNilai());
			dto.setNama(n.getSiswa().getNama());
			content.add(dto);
		}
		
		return new Response<List<DetailDto>>(content);
	}
	
	public void updateNilai(String id, Double score) {
		Nilai nilai = nilaiRepository.findFirstById(id);
		if(nilai != null && nilai.getId() != null) {
			nilai.setNilai(score);
			nilaiRepository.save(nilai);
		}
	}
	
	public void delete(String id) {
		Nilai nilai = nilaiRepository.findFirstById(id);
		if(nilai != null) {
			nilaiRepository.delete(nilai);
		}
	}
}
