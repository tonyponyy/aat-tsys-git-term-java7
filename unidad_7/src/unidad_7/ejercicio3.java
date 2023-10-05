package unidad_7;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class ejercicio3 {
	static boolean transaccion_acabada = false;
	static Hashtable<Integer, String> nombre_producto = new Hashtable<Integer, String>();
	static Hashtable<Integer, Float> precio = new Hashtable<Integer, Float>();
	static Hashtable<Integer, Integer> iva = new Hashtable<Integer, Integer>();
	static Hashtable<Integer, Integer> unidad = new Hashtable<Integer, Integer>();
	static List<Integer> carro = new ArrayList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		inicializar_productos();

		while (!transaccion_acabada) {
			System.out.println("Opciones :");
			System.out.println("1.-Mostrar producto");
			System.out.println("2.-Mostrar todos los productos");
			System.out.println("3.-Añadir producto :");
			System.out.println("0.-Salir :");

			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				switch (opcion) {
				case 0:
					transaccion_acabada = true;
					break;
				case 1:
					muestra_producto();
					break;
				case 2:
					muestra_productos();
					break;
				case 3:
					añadir_producto();
					break;

				default:
					System.out.println("Introduce opción valida.");
					sc.next();
				}

			} else {
				System.out.println("Introduce número válido.");
				sc.next();
			}
		}

		System.out.println("Vuelve otro día.");
		sc.close();
	}
	
	public static void añadir_producto(int producto) {
		carro.add(producto - 1);
		System.out.println("producto " + nombre_producto.get(producto - 1) + " añadido a la cesta de la compra. Total :"
				+ suma_carrito() + "€");

	}
	
	public static float suma_carrito() {
		float sum = 0;
		for (int i = 0; i < carro.size(); i++) {
			float precio_producto = precio.get(carro.get(i));
			int iva_producto = iva.get(carro.get(i));
			float subtotal = precio_producto + ((precio_producto / 100) * iva_producto);
			sum += subtotal;
		}
		return sum;
	}
	

	public static void muestra_productos() {
		for (int i = 1; i < nombre_producto.size() + 1; i++) {
			String nombre = nombre_producto.get(i);
			float precio_producto = precio.get(i);
			int iva_producto = iva.get(i);
			int unidad_producto = unidad.get(i);
			System.out.println("Opción -----> " + (i) + " Nombre: " + nombre + ", Precio: " + precio_producto
					+ "€, IVA: " + iva_producto + "%" + "Unidades:" + unidad_producto);
		}
	}
	
	public static void añadir_producto() {
	    Scanner sc = new Scanner(System.in);
	    boolean mostrar_producto_menu_activo = true;

	    int codigo = nombre_producto.size()+1;
	    System.out.println("Introduce el precio:");
	    if (sc.hasNextFloat()) {
	        float precio_producto = sc.nextFloat();
	        System.out.println("Introduce el IVA:");
	        int iva_producto = 0;
	        if (sc.hasNextInt()) {
	            iva_producto = sc.nextInt();
	        } else {
	            System.out.println("Error al introducir el IVA, vuelva a intentarlo.");
	            return; 
	        }
	        System.out.println("Introduce la cantidad:");
	        if (sc.hasNextInt()) {
	            int cantidad = sc.nextInt();  
	            System.out.println("Introduce el nombre:");
	            String nuevo_nombre_producto = sc.next();
	            nombre_producto.put(codigo, nuevo_nombre_producto);
	            precio.put(codigo, precio_producto);
	            iva.put(codigo, iva_producto);
	            unidad.put(codigo, cantidad);
	            
	            System.out.println("Artiiculo agregado correctamente.");
	            mostrar_producto_menu_activo = false;
	        } else {
	            System.out.println("Error al introducir la cantidad, vuelva a intentarlo.");
	        }
	    } else {
	        System.out.println("Error al introducir el precio, vuelva a intentarlo.");
	    }
	}
	
	

	public static void muestra_producto() {
		Scanner scp = new Scanner(System.in);
		boolean mostrar_producto_menu_activo = true;
		while (mostrar_producto_menu_activo) {
		System.out.println("introduce codigo del articulo.");
		if (scp.hasNextInt()) {
			int opcion = scp.nextInt();
			if (opcion > 0 && opcion <= nombre_producto.size()) {
				String nombre = nombre_producto.get(opcion);
				float precio_producto = precio.get(opcion);
				int iva_producto = iva.get(opcion);
				int unidad_producto = unidad.get(opcion);
				System.out.println("Opción -----> " + (opcion) + " Nombre: " + nombre + ", Precio: " + precio_producto
						+ "€, IVA: " + iva_producto + "%" + "Unidades:" + unidad_producto);
				mostrar_producto_menu_activo = false;
			}else {
				System.out.println("Articulo no encontrado.");
			}
		
		}else {
			System.out.println("introduce codigo valido.");
		}
		
	}}


	private static void inicializar_productos() {
		nombre_producto.put(1, "Aguacate");
		nombre_producto.put(2, "Leche");
		nombre_producto.put(3, "Barra de pan");
		nombre_producto.put(4, "Chocolatina");
		nombre_producto.put(5, "Plátano");
		nombre_producto.put(6, "Patata");
		nombre_producto.put(7, "Champú");
		nombre_producto.put(8, "Kilo Cereza");
		nombre_producto.put(9, "Donut");
		nombre_producto.put(10, "Cebolla");

		precio.put(1, 2f);
		precio.put(2, 1.3f);
		precio.put(3, 0.85f);
		precio.put(4, 0.90f);
		precio.put(5, 0.60f);
		precio.put(6, 0.40f);
		precio.put(7, 3f);
		precio.put(8, 6f);
		precio.put(9, 1f);
		precio.put(10, 0.40f);

		iva.put(1, 21);
		iva.put(2, 4);
		iva.put(3, 4);
		iva.put(4, 21);
		iva.put(5, 4);
		iva.put(6, 4);
		iva.put(7, 21);
		iva.put(8, 21);
		iva.put(9, 21);
		iva.put(10, 21);

		unidad.put(1, 1);
		unidad.put(2, 2);
		unidad.put(3, 4);
		unidad.put(4, 5);
		unidad.put(5, 1);
		unidad.put(6, 2);
		unidad.put(7, 3);
		unidad.put(8, 6);
		unidad.put(9, 2);
		unidad.put(10, 4);

	}
}