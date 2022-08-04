package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class ImageWriter {

	int rgb[][];
	int newPixels[];
	Random rand = new Random();

	ImageReader imRead = new ImageReader();

	public static int combine(int r, int b, int g) {
		return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}
	
	
	public int[][] getResizedPixels(File file, float sf) throws IOException {
		
		Color matrix[][] = imRead.getColors(file);
		BufferedImage image = ImageIO.read(file);
		
		MatrixOperations matOp = new MatrixOperations();
		
		int oldWidth = image.getWidth();
		int oldHeight = image.getHeight();
		
		Integer oldBlue[][] = imRead.getPixels(file, matrix, "blue");
		Integer oldRed[][] = imRead.getPixels(file, matrix, "red");
		Integer oldGreen[][] = imRead.getPixels(file, matrix, "green");
		

		
		int newWidth = Math.round(sf*oldWidth);
		System.out.println("cccccccccccccccccccccc: " + newWidth);
		int newHeight = Math.round(sf*oldHeight);
		
		Integer newBlue[][] = matOp.resizeImPixels(oldBlue, (int) (sf*oldHeight),(int) (sf * oldWidth));
		Integer newGreen[][] = matOp.resizeImPixels(oldGreen, (int) (sf*oldHeight),(int) (sf * oldWidth));
		Integer newRed[][] = matOp.resizeImPixels(oldRed, (int) (sf*oldHeight),(int) (sf * oldWidth));

		System.out.println("============-------=======: " + newBlue.length);
		
		
			
		return recombineIntoRGB(newRed, newBlue, newGreen);
	}
	
	
	
	public BufferedImage resizeImage(File file, float sf) throws IOException {
		
		
		
		int newRGB[][] = getResizedPixels(file, sf);
		
		BufferedImage img = new BufferedImage(newRGB.length, newRGB[0].length,
			    BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < newRGB.length; i++) {

			for (int j = 0; j < newRGB[0].length; j++) {
				img.setRGB(i, j, newRGB[i][j]);
			}
		}
		
		return img;
	}

	public int[][] recombineIntoRGB(Integer r[][], Integer b[][], Integer g[][]) {
       System.out.println("------------------------------" + r.length);
       System.out.println("------------------------------" + r[0].length);
		rgb = new int[r.length][r[0].length];
		
		for (int i = 0; i < r.length; i++) {

			for (int j = 0; j < r[i].length; j++) {
				if (r[i][j] == null && g[i][j] == null && b[i][j] == null) {
					r[i][j]=0;
					g[i][j]=0;
					b[i][j]=0;
				}
			}
		}
		
		for (int i = 0; i < r.length; i++) {

			for (int j = 0; j < r[i].length; j++) {
				
				rgb[i][j] = combine(r[i][j], b[i][j], g[i][j]);
				System.out.println(rgb[i][j]);
			}
		}
	
		return rgb;
	}
	
	
	public BufferedImage getGreen(File file, MatrixOperations matOp) throws IOException {
		Color matrix[][] = imRead.getColors(file);
		BufferedImage image = ImageIO.read(file);
		Integer blue[][] = imRead.getPixels(file, matrix, "blue");
		Integer red[][] = imRead.getPixels(file, matrix, "red");
		Integer green[][] = imRead.getPixels(file, matrix, "green");
		
		Integer newMatRed[][] = matOp.returnNullMatrix(red);
		Integer newMatBlue[][] = matOp.returnNullMatrix(blue);
		
		int rgb[][] = recombineIntoRGB(newMatRed, newMatBlue, green);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, rgb[i][j]);
			}
		}
		return image;
	}

	public BufferedImage getRed(File file, MatrixOperations matOp) throws IOException {
		Color matrix[][] = imRead.getColors(file);
		BufferedImage image = ImageIO.read(file);
		Integer blue[][] = imRead.getPixels(file, matrix, "blue");
		Integer red[][] = imRead.getPixels(file, matrix, "red");
		Integer green[][] = imRead.getPixels(file, matrix, "green");
		
		Integer newMatGreen[][] = matOp.returnNullMatrix(green);
		Integer newMatBlue[][] = matOp.returnNullMatrix(blue);
		
		int rgb[][] = recombineIntoRGB(red, newMatBlue, newMatGreen);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, rgb[i][j]);
			}
		}
		return image;
	}

	
	public BufferedImage getBlue(File file, MatrixOperations matOp) throws IOException {
		Color matrix[][] = imRead.getColors(file);
		BufferedImage image = ImageIO.read(file);
		Integer blue[][] = imRead.getPixels(file, matrix, "blue");
		Integer red[][] = imRead.getPixels(file, matrix, "red");
		Integer green[][] = imRead.getPixels(file, matrix, "green");
		
		Integer newMatRed[][] = matOp.returnNullMatrix(red);
		Integer newMatGreen[][] = matOp.returnNullMatrix(green);
		
		int rgb[][] = recombineIntoRGB(newMatRed, blue, newMatGreen);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, rgb[i][j]);
			}
		}
		return image;
	}

	
	public BufferedImage returnImage(File file, MatrixOperations matOp, Integer coeff) throws IOException {
		BufferedImage image = ImageIO.read(file);

		Color matrix[][] = imRead.getColors(file);

		Integer blue[][] = imRead.getPixels(file, matrix, "blue");
		Integer red[][] = imRead.getPixels(file, matrix, "red");
		Integer green[][] = imRead.getPixels(file, matrix, "green");

		Integer newMatRed[][] = matOp.addCoeff(red, coeff);
		Integer newMatBlue[][] = matOp.addCoeff(blue, coeff);
		Integer newMatGreen[][] = matOp.addCoeff(green, coeff);

		int rgb[][] = recombineIntoRGB(newMatRed, newMatBlue, newMatGreen);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, rgb[i][j]);
			}
		}
		return image;
	}
	
	
	
	public BufferedImage divideByCoeff(File file, MatrixOperations matOp, Integer coeff) throws IOException {
		
		
		BufferedImage image = ImageIO.read(file);

		Color matrix[][] = imRead.getColors(file);

		Integer blue[][] = imRead.getPixels(file, matrix, "blue");
		Integer red[][] = imRead.getPixels(file, matrix, "red");
		Integer green[][] = imRead.getPixels(file, matrix, "green");

		Integer newMatRed[][] = matOp.divide(red, coeff);
		Integer newMatBlue[][] = matOp.divide(blue, coeff);
		Integer newMatGreen[][] = matOp.divide(green, coeff);

		int rgb[][] = recombineIntoRGB(newMatRed, newMatBlue, newMatGreen);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, rgb[i][j]);
			}
		}
		return image;
	}

	public Integer [][] pixeledImage (File file, int new_width, int new_height) throws IOException {

		BufferedImage oldImage = ImageIO.read(file);

		int old_width = oldImage.getWidth();
		int old_height = oldImage.getHeight();

		double px,py;
		
		//Integer[][] newBlue = new Integer[new_width][new_height];
		
		
		Color matrix[][] = imRead.getColors(file);
		
		Integer newBlue[][] = imRead.getPixels(file, matrix, "blue");
		Integer oldBlue[][] = imRead.getPixels(file, matrix, "blue");
		Integer oldRed[][] = imRead.getPixels(file, matrix, "red");
		Integer oldGreen[][] = imRead.getPixels(file, matrix, "green");

		double x_ratio = (double) old_width / (double) new_width;
		double y_ratio = (double) old_width / (double) new_width;

		for (int i = 0; i < new_width; i++) {
			for (int j = 0; j < new_height; j++) {
				
				px = Math.floor(j*x_ratio);
				py = Math.floor(i*y_ratio);
				
                 newBlue[i*new_width][j] = oldBlue[(int) (py * old_width)][(int) (px)];
			}
		}

		return newBlue;
	}

	public BufferedImage addTwoImages(File file1, File file2, MatrixOperations matOp) throws IOException {

		BufferedImage image1 = ImageIO.read(file1);
		BufferedImage image2 = ImageIO.read(file2);

		Color matrix1[][] = imRead.getColors(file1);
		Color matrix2[][] = imRead.getColors(file2);

		Integer blue1[][] = imRead.getPixels(file1, matrix1, "blue");
		Integer red1[][] = imRead.getPixels(file1, matrix1, "red");
		Integer green1[][] = imRead.getPixels(file1, matrix1, "green");

		Integer blue2[][] = imRead.getPixels(file2, matrix2, "blue");
		Integer red2[][] = imRead.getPixels(file2, matrix2, "red");
		Integer green2[][] = imRead.getPixels(file2, matrix2, "green");

		Integer newBlue[][] = matOp.addMatrix(blue1, blue2);
		Integer newRed[][] = matOp.addMatrix(red1, red2);
		Integer newGreen[][] = matOp.addMatrix(green1, green2);

		int rgb[][] = recombineIntoRGB(newRed, newBlue, newGreen);

		for (int i = 0; i < image1.getWidth(); i++) {
			for (int j = 0; j < image1.getHeight(); j++) {
				image1.setRGB(i, j, rgb[i][j]);
			}
		}

		return image1;

	}
	
  
	
	
	

}
