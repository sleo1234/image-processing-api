package image;

import org.springframework.stereotype.Component;

@Component
public class MatrixOperations {

	public Integer[][] addMatrix(Integer[][] m, Integer[][] n) {

		Integer[][] k = new Integer[m.length][m[0].length];

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				k[i][j] = 0;
			}
		}

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {

				k[i][j] = Integer.sum(m[i][j], n[i][j]);
				if (k[i][j] > 255) {
					k[i][j] = 255;
				}

			}
		}

		return k;
	}

	public Integer[][] addCoeff(Integer[][] m, Integer coeff) {

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = m[i][j] + coeff;

			}

		}
		return m;
	}
	
	
	public Integer[][] resizeImPixels(Integer[][] mat,int w2,int h2) {
		
		Integer [] pixels = matToArray(mat);
	    Integer[] temp = new Integer[w2*h2] ;
	    int w1 = mat[0].length;
	    int h1 = mat.length;
	    double x_ratio = w1/(double)w2 ;
	    double y_ratio = h1/(double)h2 ;
	    double px, py ; 
	    for (int i=0;i<h2;i++) {
	        for (int j=0;j<w2;j++) {
	            px = Math.floor(j*x_ratio) ;
	            py = Math.floor(i*y_ratio) ;
	            temp[(i*w2)+j] = pixels[(int)((py*w1)+px)] ;
	        }
	    }
	    return arrToMat(temp,w2,h2) ;
	}
	
	
public static Integer [][] arrToMat(Integer [] m, int height, int width){
        
        Integer [][] mat = new Integer[width][height];
        
         int c=0;
            for (int i=0; i< width; i++){
                
               for (int j=0; j< height; j++){
             mat[i][j] = m[c++];
        }   
            }
            
            return mat;
        
    }
    

		public static Integer[] matToArray(Integer [][] m){
		    Integer arr[] = new Integer [m.length * m[0].length];
		    
		    for (int i=0; i< m.length; i++){
		        Integer[] row = m[i];
		        
		        for (int j=0; j< row.length; j++){
		            Integer number = m[i][j];
		              arr[(i*row.length) +j] = m[i][j];
		        }
		    }
		    
		    return arr;
		}

	public Integer[][] divide(Integer[][] m, Integer coeff) {

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = m[i][j] / coeff;

			}

		}
		return m;

	}

	public Integer[][] returnNullMatrix(Integer[][] m) {

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = 0;
			}

		}
		return m;
	}
	
public static void printArray (Integer [] m){
        
        for (int i=0; i< m.length; i++){
             System.out.print(m[i] +" ");
        }
    }


		public static void printMatrix (Integer [][]m){
		    for (int i=0; i< m.length; i++){
		        System.out.println(" ");
		       for (int j=0; j< m[0].length; j++){
		           System.out.print(m[i][j]+" ");
		}
		    }
		}
		
		
		public static void printMatrix (int [][]m){
		    for (int i=0; i< m.length; i++){
		        System.out.println(" ");
		       for (int j=0; j< m[0].length; j++){
		           System.out.print(m[i][j]+" ");
		}
		    }
		}
		
		
}
