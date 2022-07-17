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

@RestController
public class FileUploadController {

	 ImageReader imRead = new ImageReader();
	ImageWriter imWrite = new ImageWriter();
    MatrixOperations mat = new MatrixOperations();
    LocalDate date = LocalDate.now(); 
	
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
		
		
		
		 BufferedImage imageToWrite =imWrite.returnImage(file, mat, -5);
		 
	       File f = new File(path+fileName.substring(0,fileName.length()-5)+ date + ".jpg");
	      // BufferedImage imageToWrite = imWrite.returnImage(file, mat);
	       boolean isSaved = ImageIO.write(imageToWrite, "jpg", f);
	       System.out.println(isSaved);
		response.setSize(size);
		response.setDonwloadUri("/downloadFile");
		
		
	return new ResponseEntity<FileUploadResponse>(response, HttpStatus.OK);
	
				
	}
	

	

}
