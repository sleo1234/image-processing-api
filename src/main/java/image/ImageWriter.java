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
	Random rand = new Random();

	ImageReader imRead = new ImageReader();

	public static int combine(int r, int b, int g) {
		return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	public int[][] recombineIntoRGB(Integer r[][], Integer b[][], Integer g[][]) {

		rgb = new int[1000][1000];
		Integer[][] RGB = new Integer[1000][1000];
		for (int i = 0; i < r.length; i++) {

			for (int j = 0; j < r[i].length; j++) {
				RGB[i][j] = Integer.valueOf(rgb[i][j]);

				rgb[i][j] = combine(r[i][j], b[i][j], g[i][j]);
				System.out.println(rgb[i][j]);
			}
		}
		// imRead.printImageMatrix(RGB);
		return rgb;
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
