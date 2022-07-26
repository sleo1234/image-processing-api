package image;

import org.springframework.stereotype.Component;

@Component
public class MatrixOperations {

	 

	
	
	public Integer[][]  addMatrix(Integer[][] m, Integer [][] n){
		
	 Integer [][] k = new Integer[m.length][m[0].length];
  
	 for (int i = 0; i < m.length; i++) { 
			for (int j = 0; j < m[i].length; j++) { 
		k[i][j]=0;		
			}
	 }
	 
	
		for (int i = 0; i < m.length; i++) { 
			for (int j = 0; j < m[0].length; j++) { 
		
			k[i][j]=Integer.sum(m[i][j], n[i][j]); 
			if(k[i][j] > 255) {
				k[i][j] =255;
			}
					
			 }
			}
		
			 

		return k;
	}
	
	public Integer[][] addCoeff(Integer[][]m, Integer coeff){
		
		for (int i = 0; i < m.length; i++) { 
			for (int j = 0; j < m[i].length; j++) { 
				m[i][j] = m[i][j]+ coeff;

			}
			
		}
		return m;
	}
	
	public Integer [][] divide(Integer[][] m, Integer coeff){
		
		
		for (int i = 0; i < m.length; i++) { 
			for (int j = 0; j < m[i].length; j++) { 
				m[i][j] = m[i][j]/coeff;

			}
			
		}
		return m;
		
	}
	
	public Integer[][] returnNullMatrix (Integer [][] m) {
		
		for (int i = 0; i < m.length; i++) { 
			for (int j = 0; j < m[i].length; j++) { 
				m[i][j] = 0;
			}
			
		}
		return m;
	}
}
