package com.example.realestate.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService{
	public static String uploadDir=System.getProperty("user.dir")+("/src/main/images");
	
	
	 public String saveFile(MultipartFile file) throws IOException {
	        if (file.isEmpty()) {
	            return null;
	        }
			
			  String fileName = file.getOriginalFilename();
			  Path filePath = Paths.get(uploadDir , fileName);
			   Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			  
			  //Files.write(filePath, file.getBytes());
			 
	        
	    
	        return fileName;
	    }

}
