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
}
