package com.testing.vino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "NILAI")
public class Nilai {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="nomor_induk")
	private Siswa siswa;
	
	@Column(name = "mata_pelajaran")
	private String mataPelajaran;
	
	@Column(name = "nilai")
	private Double nilai;
}
