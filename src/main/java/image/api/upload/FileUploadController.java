package image.api.upload;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import image.ImageReader;
import image.ImageWriter;
import image.MatrixOperations;
import net.bytebuddy.utility.RandomString;

@RestController
public class FileUploadController {

	 ImageReader imRead = new ImageReader();
	ImageWriter imWrite = new ImageWriter();
    MatrixOperations mat = new MatrixOperations();
    LocalDate date = LocalDate.now(); 
	String generatedString = RandomString.make(4);
	
	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam ("file") MultipartFile multipartFile ) throws IOException{
	
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		long size = multipartFile.getSize();
		FileUploadUtil.saveFile(fileName, multipartFile);
		System.out.println(Paths.get("Files-Upload"));
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName); 
		String path = "C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/";
		File file = new File(path+fileName);
		// Color matrix [][] = imRead.getColors(file);
		//System.out.println(imRead.getColors(file));
		
		System.out.println(" ==============" + generatedString);
		
		 //BufferedImage imageToWrite =imWrite.divideByCoeff(file, mat, 2);
		BufferedImage imageToWrite =imWrite.getGreen(file, mat);
		
	       File f = new File(path+fileName.substring(0,fileName.length()-4)+ generatedString + ".jpg");
	      // BufferedImage imageToWrite = imWrite.returnImage(file, mat);
	       boolean isSaved = ImageIO.write(imageToWrite, "jpg", f);
	       System.out.println(isSaved);
		response.setSize(size);
		response.setDonwloadUri("/downloadFile");
		
		
	return new ResponseEntity<FileUploadResponse>(response, HttpStatus.OK);
	
				
	}
	

	
	@PostMapping("/green")
	public ResponseEntity<FileUploadResponse> getGreenImage(@RequestParam ("file") MultipartFile multipartFile ) throws IOException{
	
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		long size = multipartFile.getSize();
		FileUploadUtil.saveFile(fileName, multipartFile);
		System.out.println(Paths.get("Files-Upload"));
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName); 
		String path = "C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/";
		File file = new File(path+fileName);
	
		BufferedImage imageToWrite =imWrite.getGreen(file, mat);
	       File f = new File(path+fileName.substring(0,fileName.length()-4)+ generatedString + ".jpg");
	     
	       boolean isSaved = ImageIO.write(imageToWrite, "jpg", f);
	       System.out.println(isSaved);
		response.setSize(size);
		response.setDonwloadUri("/downloadFile");
		
		
	return new ResponseEntity<FileUploadResponse>(response, HttpStatus.OK);
	
				
	}
	
	@PostMapping("/add")
	public ResponseEntity<FileUploadResponse> getGreenImage(@RequestParam ("file1") MultipartFile multipartFile1, 
			@RequestParam ("file2") MultipartFile multipartFile2) throws IOException{
		
		String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
		
		long size1 = multipartFile1.getSize();
		long size2 = multipartFile2.getSize();
		
		FileUploadUtil.saveFile(fileName1, multipartFile1);
		FileUploadUtil.saveFile(fileName2, multipartFile2);
		System.out.println(Paths.get("Files-Upload"));
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName1); 
		String path = "C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/";
		File file1 = new File(path+fileName1);
		File file2 = new File(path+fileName2);
		BufferedImage imageToWrite =imWrite.addTwoImages(file1, file2, mat);
	       File f = new File(path+fileName1.substring(0,fileName1.length()-4)+ generatedString + ".jpg");
	     
	       boolean isSaved = ImageIO.write(imageToWrite, "jpg", f);
	       System.out.println(isSaved);
		response.setSize(size1);
		response.setDonwloadUri("/downloadFile");
		
		
	return new ResponseEntity<FileUploadResponse>(response, HttpStatus.OK);
	
				
	}
	
	
	

}
