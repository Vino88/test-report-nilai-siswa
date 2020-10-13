package com.testing.vino.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testing.vino.entity.Nilai;
import com.testing.vino.entity.Siswa;
import com.testing.vino.repository.NilaiRepository;
import com.testing.vino.repository.SiswaRepository;

@Service
public class CsvUploadUtil {
    
	@Autowired
	private SiswaRepository siswaRepository;
	@Autowired
	private NilaiRepository nilaiRepository;
	
	public String uploadCsvFile(MultipartFile multipartFile) throws IOException{  
		String message = null;
		File file = convertMultiPartToFile(multipartFile);
		
		String line = "";  
		String splitBy = ",";
		
		try   {  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(file.getName()));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{
				while ((line = br.readLine()) != null) {
					String[] s = line.split(splitBy);    // use comma as separator 
					if(file.getName().equals("siswa.csv")) {
						Siswa siswa = new Siswa();
						siswa.setNomorInduk(s[0]);
						siswa.setNama(s[1]);
						siswa.setKelas(s[2]);
						
						siswaRepository.save(siswa);
						
						System.out.println("Siswa [nomor_induk =" + s[0] + ", nama =" + s[1] + ", Kelas =" + s[2]+ "]");
						
						message = "file has been upload successful!";
						
					}else if(file.getName().equals("nilai.csv")) {
						Nilai nilai = new Nilai();
						nilai.setNomorInduk(s[0]);
						nilai.setMataPelajaran(s[1].toString());
						nilai.setNilai(Double.valueOf(s[2]));
						nilai.setSiswa(nilai.getSiswa());
						
						nilaiRepository.save(nilai);
						
						System.out.println("Siswa [nomor_induk =" + s[0] + ", mp =" + s[1] + ", nilai =" + s[2]+ "]");
						
						message = "file has been upload successful!";
						
					}else {
						message = "file not recomended and not used !";
					}
				}  
			}  
		}   
		catch (IOException e)   {  
			e.printStackTrace();  
		}
		return message;  
	}  
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	public List<Siswa> getStudents() {
		return siswaRepository.findAll();
	}
	
	public Iterable<Nilai> getNilai() {
		return nilaiRepository.findAll();
	}
}
