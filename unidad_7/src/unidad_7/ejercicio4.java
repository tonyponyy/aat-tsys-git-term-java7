package unidad_7;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class ejercicio4 {
	static boolean transaccion_acabada = false;
	static Hashtable<Integer, String> nombre_producto = new Hashtable<Integer, String>();
	static Hashtable<Integer, Float> precio = new Hashtable<Integer, Float>();
	static Hashtable<Integer, Integer> iva = new Hashtable<Integer, Integer>();
	static Hashtable<Integer, Integer> unidad = new Hashtable<Integer, Integer>();
	static List<Integer> carro = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		inicializar_productos();

		while (!transaccion_acabada) {
			menu_operador();
		}

		System.out.println("Vuelve otro día.");
		sc.close();
	}

	public static void muestra_productos() {
		for (int i = 1; i <= nombre_producto.size(); i++) {
			String nombre = nombre_producto.get(i);
			float precio_producto = precio.get(i);
			int iva_producto = iva.get(i);
			int unidad_producto = unidad.get(i);
			System.out.println("Opción -----> " + i + " Nombre: " + nombre + ", Precio: " + precio_producto + "€, IVA: "+ iva_producto + "%" + "Unidades:" + unidad_producto);
		}
	}

	public static void menu_comprar() {
		boolean compra_acabada = false;
		while (!compra_acabada) {
			System.out.println("Introduce el número del producto que quieres comprar.");
			muestra_productos();

			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= nombre_producto.size()) {
					añadir_producto_carro(opcion);
				} else if (opcion == 0) {
					compra_acabada = true;
				} else {
					System.out.println("Articulo no válido");
				}
			} else {
				System.out.print("Introduce numero vaalido.");
				sc.next();
			}
		}
		System.out.println(suma_carrito_detalles());
		boolean ha_pagado = false;
		while (!ha_pagado) {
			System.out.println("Introduce la cantidad con la que va a pagar.");
			if (sc.hasNextInt()) {
				int pago = sc.nextInt();
				if (pago < suma_carrito()) {
					System.out.println("Por favor, introduce dinero suficiente.");
				} else {
					System.out.println("Cambio: " + (pago - suma_carrito()) + "€");
					ha_pagado = true;
				}

			} else {
				System.out.println("Introduzca un número válido.");
			}
		}
	}

	public static String suma_carrito_detalles() {
	    float sum_base = 0;
	    float sum_iva = 0;
	    float precio_final = 0;
	    for (int i = 0; i < carro.size(); i++) {
	        int codigo = carro.get(i);
	        float precio_producto = precio.get(codigo);
	        int iva_producto = iva.get(codigo);
	        float subtotal = precio_producto + ((precio_producto / 100) * iva_producto);
	        precio_final += subtotal;
	        sum_base += precio_producto;
	        sum_iva += (precio_producto / 100) * iva_producto;
	    }
	    return "Precio final:" + precio_final + "€, Precio base:" + sum_base + "€, Total IVA:" + sum_iva + "€";
	}

	public static float suma_carrito() {
	    float sum = 0;
	    for (int i = 0; i < carro.size(); i++) {
	        int codigo = carro.get(i);
	        float precio_producto = precio.get(codigo);
	        int iva_producto = iva.get(codigo);
	        float subtotal = precio_producto + ((precio_producto / 100) * iva_producto);
	        sum += subtotal;
	    }
	    return sum;
	}

	public static void menu_operador() {
		System.out.println("Opciones :");
		System.out.println("1.-Mostrar producto");
		System.out.println("2.-Mostrar todos los productos");
		System.out.println("3.-Añadir producto :");
		System.out.println("4.-Comprar");
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
			case 4:
				menu_comprar();
				break;

			default:
				System.out.println("Introduce opción válida.");
				sc.next();
			}

		} else {
			System.out.println("Introduce número válido.");
			sc.next();
		}
	}

	public static void añadir_producto() {
	
		int codigo = nombre_producto.size() + 1;
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
				System.out.println("Artículo agregado correctamente.");
				
			} else {
				System.out.println("Error al introducir la cantidad, vuelva a intentarlo.");
			}
		} else {
			System.out.println("Error al introducir el precio, vuelva a intentarlo.");
		}
	}

	public static void añadir_producto_carro(int producto) {
	    if (nombre_producto.containsKey(producto)) {
	        carro.add(producto);
	        System.out.println("Producto " + nombre_producto.get(producto) + " añadido a la cesta de la compra. Total: "
	                + suma_carrito() + "€");
	        System.out.println("Pulsa 0 para finalizar la compra.");
	    } else {
	        System.out.println("El producto no existe.");
	    }
	}

	public static void muestra_producto() {
		boolean mostrar_producto_menu_activo = true;
		while (mostrar_producto_menu_activo) {
			System.out.println("Introduce código del artículo.");
			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= nombre_producto.size()) {
					String nombre = nombre_producto.get(opcion);
					float precio_producto = precio.get(opcion);
					int iva_producto = iva.get(opcion);
					int unidad_producto = unidad.get(opcion);
					System.out.println("Opción -----> " + opcion + " Nombre: " + nombre + ", Precio: " + precio_producto
							+ "€, IVA: " + iva_producto + "%" + "Unidades:" + unidad_producto);
					mostrar_producto_menu_activo = false;
				} else {
					System.out.println("Articulo no encontrado.");
				}

			} else {
				System.out.println("Introduce código válido.");
			}
		}
	}

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
