package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class ImageReader {

	public static int imageHeight;
	public static int imageWidth;

	List<Integer> bluePixels = new ArrayList<Integer>();

	Color colors[][];

	Integer blue[][];
	Integer red[][];
	Integer green[][];

	public void readImage() throws IOException {

		String pathname = "C:/Users/Leo/Desktop/ShopmeAdminlo2.png";

		File file = new File(pathname);
		System.out.println(file);
		BufferedImage image = ImageIO.read(file);

		imageHeight = image.getHeight();
		imageWidth = image.getWidth();

		// Color[][] colors = new Color[imageHeight][imageWidth];

		System.out.println("height: " + imageHeight + " and width: " + imageWidth);

		Color pixel = new Color(image.getRGB(10, 10));

		System.out.println((int) pixel.getBlue());
		

	}
	
	

	public Color[][] getColors(File file) throws IOException {

		BufferedImage image = ImageIO.read(file);
		colors = new Color[image.getWidth()][image.getHeight()];
		
		
		
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				// System.out.println(i + " " +j);
				colors[i][j] = new Color(image.getRGB(i, j));
				// colors.add(new Color(image.getRGB(i, j)));
				// colors.add(new Color(image.getRGB(i, j)));
			}
		}
		
		return colors;
	}

	public static void printImageMatrix(Integer [][] matrix) {
		for (int i = 0; i < matrix.length; i++) { // this equals to the row in our matrix.
			for (int j = 0; j < matrix[i].length; j++) { // this equals to the column in each row.
				System.out.print(matrix[i][j] + " ");

			}
			System.out.println(); // change line on console as row comes to end in the matrix.
		}
   
	}

	public Integer[][] getPixels(File file, Color matrix[][], String color) throws IOException {

		BufferedImage image = ImageIO.read(file);
		blue = new Integer[image.getWidth()][image.getHeight()];
		red = new Integer[image.getWidth()][image.getHeight()];
		green = new Integer[image.getWidth()][image.getHeight()];

		if (color.equals("blue")) {
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					blue[i][j] = matrix[i][j].getBlue();

				}
			}
			return blue;
		} else if (color.equals("red")) {
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					red[i][j] = matrix[i][j].getRed();

				}
			}
			return red;
		}

		else {
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					green[i][j] = matrix[i][j].getGreen();

				}
			}
			return green;
		}

	}
	
	

	
	

}
