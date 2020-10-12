package com.testing.vino.controller;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testing.vino.entity.Nilai;
import com.testing.vino.entity.Siswa;
import com.testing.vino.repository.NilaiRepository;
import com.testing.vino.repository.SiswaRepository;

@RestController
@RequestMapping("/rest/api")
public class UploadExcellController {
	
	@Autowired
	private SiswaRepository siswaRepository;
	@Autowired
	private NilaiRepository nilaiRepository;

	@RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<String> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        String message = null;
        
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        if(files.getOriginalFilename().equals("siswa.csv")) {
	        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
	            if (index > 0) {
	                Siswa siswa = new Siswa();
	
	                XSSFRow row = worksheet.getRow(index);
	
	                siswa.setNomorInduk(row.getCell(0).getStringCellValue());
	                siswa.setNama(row.getCell(1).getStringCellValue());
	                siswa.setKelas(row.getCell(2).getStringCellValue());
	                
	                siswaRepository.save(siswa);
	                
	                message = "Upload file siswa has been successful!";
	                
	            }
	        }
		}else if(files.getOriginalFilename().equals("nilai.csv")) {
			for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
	            if (index > 0) {
	                Nilai nilai = new Nilai();
	
	                XSSFRow row = worksheet.getRow(index);
	
	                nilai.setNomorInduk(row.getCell(0).getStringCellValue());
	                nilai.setMataPelajaran(row.getCell(1).getStringCellValue());
	                nilai.setNilai(row.getCell(2).getNumericCellValue());
	                
	                nilaiRepository.save(nilai);
	                
	                message = "Upload file nilai has been successful!";
	                
	            }
	        }
		}else {
			message = "please check file name or file format";
		}

        return new ResponseEntity<>(message, status);
    }
}
