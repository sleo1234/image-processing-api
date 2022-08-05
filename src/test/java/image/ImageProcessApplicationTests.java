package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageProcessApplicationTests {
	
	ImageWriter imWrite = new ImageWriter();
    ImageReader imRead = new ImageReader();
    MatrixOperations matOp = new MatrixOperations();
    
	@Test
	void contextLoads() {
	}

	
	@Test
	
	public void testImageScaling() throws IOException {
		
		Float fs = 0.5f;
		File f = new File("C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/imr2.jpg");
		
		int matrix [][] = imWrite.getResizedPixels(f, fs);
		System.out.println("======Width: " + matrix.length);
		System.out.println("======Height: " + matrix[0].length);
		
	    BufferedImage im = imWrite.resizeImage(f, fs );
	    File file = new File("C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/image-resized22.jpg");
	       ImageIO.write(im, "jpg", file);
		//
		
		
	}
	
	@Test
	public void testMatrixOp () {
		
		Integer m[][] = {
				
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		
		Integer n[] = {1,2,3,4,5,6,7,8,9,10};
		
		Integer arr[] = matOp.matToArray(m);
		Integer mat[][] = matOp.arrToMat(n,2,5);
		
		//Integer newMat[][] = matOp.resizePixels(m, 2.3f);
		
		Integer newMat2[][] = matOp.resizeImPixels(m, 6,6);
		matOp.printMatrix(newMat2);
		
	
	}
}
