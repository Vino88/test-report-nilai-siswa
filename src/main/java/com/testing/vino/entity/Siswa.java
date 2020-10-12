package com.testing.vino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SISWA")
public class Siswa {

	@Id
	@Column(name = "nomor_induk")
	private String nomorInduk;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "kelas")
	private String kelas;
}
