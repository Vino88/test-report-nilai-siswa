package com.testing.vino.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.testing.vino.util.CsvUploadUtil;

@RestController
@RequestMapping("/rest/api")
public class UploadCsvController {
	
	@Autowired
	private CsvUploadUtil csv;
	

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
		return csv.uploadCsvFile(multiPartFile);
	}
}
