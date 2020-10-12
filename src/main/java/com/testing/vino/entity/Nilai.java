package com.testing.vino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.testing.vino.entity.composite.NilaiId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(NilaiId.class)
@Table(name = "NILAI")
public class Nilai {

	@Id
	@Column(name = "nomor_induk")
	private String nomorInduk;
	
	@MapsId
	@ManyToOne
	@JoinColumn(name="nomor_induk")
	private Siswa siswa;
	
	@Column(name = "mata_pelajaran")
	private String mataPelajaran;
	
	@Column(name = "nilai")
	private Double nilai;
}
