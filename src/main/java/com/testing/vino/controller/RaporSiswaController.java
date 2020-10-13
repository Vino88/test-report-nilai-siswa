package com.testing.vino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testing.vino.component.ReportSiswaHelperComponent;
import com.testing.vino.dto.DetailDto;
import com.testing.vino.dto.NilaiDto;
import com.testing.vino.dto.Response;
import com.testing.vino.util.MessageUtil;

@RestController
@RequestMapping("/rest/api/report")
public class RaporSiswaController {

	@Autowired
	private ReportSiswaHelperComponent helperComponent;
	
	@GetMapping("/view")
	public Response<List<DetailDto>> getViewReport(@RequestParam(name="nomorInduk", required=false) String nomorInduk,
													@RequestParam(name ="nama", required=false) String nama,
													@RequestParam(name ="mataPelajaran", required=false) String mataPelajaran,
													@RequestParam(name = "nilai", required=false) Double nilai){
		return helperComponent.view(nomorInduk, nama, mataPelajaran, nilai);
	}
	
	@PutMapping("/update/{nomorInduk}")
	public ResponseEntity<MessageUtil> updateNilai(@PathVariable("nomorInduk") String nomorInduk, 
													@RequestBody NilaiDto score){
		
		MessageUtil message = null;
		if(score != null && score.getNilai() != null && score.getNilai().intValue() > 0) {
			helperComponent.updateNilai(nomorInduk, score.getNilai());
			message = new MessageUtil(200, "SUCCESS", "Update Successful!");
		}else {
			message = new MessageUtil(400, "FAILED", "Bad Request!");
		}
		
		return new ResponseEntity<MessageUtil>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{nomorInduk}")
	public ResponseEntity<MessageUtil> delete(@PathVariable String nomorInduk){
		MessageUtil message = null;
		if(nomorInduk != null && nomorInduk.trim().length() > 0) {
			helperComponent.delete(nomorInduk);
			message = new MessageUtil(200, "SUCCESS", "Delete Successful!");
		}else {
			message = new MessageUtil(400, "FAILED", "Bad Request!");
		}
		
		return new ResponseEntity<MessageUtil>(message, HttpStatus.OK);
	}
}
