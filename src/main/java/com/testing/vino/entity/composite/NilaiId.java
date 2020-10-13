package com.testing.vino.entity.composite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NilaiId implements Serializable{
	private static final long serialVersionUID = 9143871345500093042L;
	private String nomorInduk;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomorInduk == null) ? 0 : nomorInduk.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NilaiId other = (NilaiId) obj;
		if (nomorInduk == null) {
			if (other.nomorInduk != null)
				return false;
		} else if (!nomorInduk.equals(other.nomorInduk))
			return false;
		return true;
	}
}
