package unidad_7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio2 {
	static boolean compra_acabada = false;
	static List<String> nombre_producto = new ArrayList<>();
	static List<Float> precio = new ArrayList<>();
	static List<Integer> iva = new ArrayList<>();
	static List<Integer> carro = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		inicializar_productos();

		while (!compra_acabada) {
			System.out.println("Introduce el número del producto que quieres comprar.");
			muestra_productos();

			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= nombre_producto.size()) {
					añadir_producto(opcion);
				} else if (opcion == 0) {
					compra_acabada = true;
				} else {
					System.out.println("Articulo no valido");
				}
			} else {
				System.out.print("Introduce número válido.");
				sc.next();
			}
		}
		System.out.println(suma_carrito_detalles());
		boolean ha_pagado = false;
		while (!ha_pagado) {
			System.out.println("Introduce la cantidad con que va a pagar.");
			if (sc.hasNextInt()) {
				int pago = sc.nextInt();
				if (pago < suma_carrito()) {
				 System.out.println("Por favor, introduzca dinero suficiente.");
				}else {
					System.out.println("Cambio :"+(pago-suma_carrito()+"€"));
					ha_pagado = true;
				}
				
			} else {
				System.out.println("Introduzca un numero valido");
			}
		}

		System.out.println("Vuelva otro dia.");
		sc.close();
	}

	public static void muestra_productos() {
		for (int i = 0; i < nombre_producto.size(); i++) {
			String nombre = nombre_producto.get(i);
			float precio_producto = precio.get(i);
			int iva_producto = iva.get(i);
			System.out.println("Opcion -----> " + (i + 1) + " Nombre: " + nombre + ", Precio: " + precio_producto
					+ "€, IVA: " + iva_producto + "%");
		}
		System.out.println("Pulsa 0 acabar la compra.");
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

	public static String suma_carrito_detalles() {
		float sum_base = 0;
		float sum_iva = 0;
		float precio_final = 0;
		for (int i = 0; i < carro.size(); i++) {
			float precio_producto = precio.get(carro.get(i));
			int iva_producto = iva.get(carro.get(i));
			float subtotal = precio_producto + ((precio_producto / 100) * iva_producto);
			precio_final += subtotal;
			sum_base += precio_producto;
			sum_iva += (precio_producto / 100) * iva_producto;
		}
		return "Precio final:" + precio_final + "€ ,Precio base:" + sum_base + "€ , Total iva:" + sum_iva + "€";
	}

	private static void inicializar_productos() {
		nombre_producto.add("Aguacate");
		nombre_producto.add("Leche");
		nombre_producto.add("Barra de pan");
		nombre_producto.add("Chocolatina");

		precio.add(2f);
		precio.add(1.3f);
		precio.add(0.85f);
		precio.add(0.90f);

		iva.add(21);
		iva.add(4);
		iva.add(4);
		iva.add(21);
	}

}
