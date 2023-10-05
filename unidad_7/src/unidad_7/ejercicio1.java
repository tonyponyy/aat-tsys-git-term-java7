package unidad_7;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class ejercicio1 {
	public static void main(String[] args) {
		 int[] n1 = {1,4,6,8,9,5,5,2,7};
		 int[] n2 = {1,4,6,8,9,5,5,2,7};
		 int[] n3 = {1,4,6,8,9,5,5,2,7};
		 int[] n4 = {1,4,6,8,9,5,5,2,7};
		 Hashtable<String,Float > notas = new Hashtable<String,Float >();
		 notas.put("David",suma_array(n1));
		 notas.put("Laura",suma_array(n2));
		 notas.put("Luis",suma_array(n3));
		 notas.put("Marta",suma_array(n4));
		 Enumeration<String> nombres = notas.keys();
		 
		 while (nombres.hasMoreElements()) {
	            String nombre = nombres.nextElement();
	            Float nota = notas.get(nombre);
	            System.out.println("Nombre: " + nombre + ", Nota media: " + nota);
		 }
		 
	}
	
	public static float suma_array(int[] n) {
		float resultado=0;
		for (int i = 0; i < n.length; i++) {
			resultado +=n[i];
		}
		return resultado;
		
	}

}

