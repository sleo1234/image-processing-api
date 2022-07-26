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
	@Test
	void contextLoads() {
	}

	
	@Test
	
	public void testImageScaling() throws IOException {
		
		File f = new File("C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/im6g.jpg");
		
		int matrix [][] = imWrite.getResizedPixels(f, 2);
		System.out.println("======Width: " + matrix.length);
		System.out.println("======Height: " + matrix[0].length);
		
	    BufferedImage im = imWrite.resizeImage(f, 2);
	    File file = new File("C:/Users/Leo/eclipse-workspace/image-process/Files-Upload/image-resized2.jpg");
	       ImageIO.write(im, "jpg", file);
		//
		
		
	}
}
