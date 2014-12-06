/*
 * File:GrayImage.java
 * -------------------
 * Creates grayscale version of an image
 * and flipHorizontal version of it 
 */

import acm.program.*;
import acm.graphics.*;

public class GrayImage extends GraphicsProgram {
	
	public void run(){
		GImage image = new GImage("IMG_2958.JPG");
		GImage grayImage = createGrayscaleImage(image);
		GImage flipHorizontalImage = flipHorizontal(image);
		
		image.scale(0.5);
		add(image,10,20);
		
		grayImage.scale(0.5);
		add(grayImage,image.getWidth()+50,20);
		
		flipHorizontalImage.scale(0.5);
		add(flipHorizontalImage,2*(image.getWidth()+50),20);
	}
	
	/*Creates a grayscale version of the original image*/
	private GImage createGrayscaleImage(GImage image){
		//Gets copy of pixel array from image
		int[][] array = image.getPixelArray();
		
		int height = array.length;     //numbers of rows in grid
		int width  = array[0].length;  //numbers of columns in a row
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				int pixel = array[i][j];
				
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				
				int xx = computeLuminosity(r,g,b);
				
				array[i][j] = GImage.createRGBPixel(xx, xx, xx);
			}
		}
		return new GImage(array);
	}
	
	/*Calculates the luminosity of a pixel using NTSC formula*/
	private int computeLuminosity(int r, int g, int b){
		return GMath.round(0.299*r + 0.587*g + 0.114*b);
	}
	
	/*Create a flipHorizontal version of an image*/
	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		
		for (int row = 0; row < height; row++) {
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
				int temp = array[row][p1];
				array[row][p1] = array[row][p2];
				array[row][p2] = temp;
			}
		}
		return new GImage(array);
	}

}
