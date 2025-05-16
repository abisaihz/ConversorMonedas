package com.alura.conversor.principal;

import com.alura.conversor.metodos.Conversion;
import com.alura.conversor.metodos.ListaConversion;
import com.alura.conversor.metodos.ValorConversion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Empezamos con el scanner para capturar informacion
        Scanner lectura = new Scanner(System.in);
        // Creo una lista para ver el historial
        List<ValorConversion> operaciones = new ArrayList<>();

        System.out.println("Bienvenido al CONVERSOR DE MONEDAS");
        System.out.println("Puede escribir 'salir' en cualquier momento para terminar el programa.");
        System.out.println("Por favor capture cualquiera de las tasas disponibles: ");
        // Creamos la lista de monedas disponibles
        ListaConversion lista = new ListaConversion();
        for (int i = 0; i < 8; i++){

            System.out.println(i + ": --- " + lista.getMonedas(i).toString());
        }


        while (true) {

            try {
                System.out.println("Capture que tasa desea convertir: ");
                String entrada = lectura.nextLine();
                if (entrada.equalsIgnoreCase("salir")) break;

                int moneda1 = Integer.parseInt(entrada);
                if (moneda1 < 0 || moneda1 > 7) {
                    System.out.println("Por favor capture un número entre 0 y 7.");
                    continue;
                }

                System.out.println("Capture la nueva tasa a la que desea convertir: ");
                entrada = lectura.nextLine();
                if (entrada.equalsIgnoreCase("salir")) break;

                int moneda2 = Integer.parseInt(entrada);
                if (moneda2 < 0 || moneda2 > 7) {
                    System.out.println("Por favor capture un número entre 0 y 7.");
                    continue;
                }

                System.out.println("Capture el monto deseado a convertir: ");
                entrada = lectura.nextLine();
                if (entrada.equalsIgnoreCase("salir")) break;

                double montoDeseado = Double.parseDouble(entrada);

                Conversion usuarioConversion = new Conversion(moneda1, moneda2);
                ConversorAPI llamadaApi = new ConversorAPI(usuarioConversion);
                ValorConversion operacionRealizada = new ValorConversion(moneda1, moneda2, llamadaApi.getFactorConversion(), montoDeseado);

                System.out.println("Resultado de la conversión:");
                System.out.println(operacionRealizada);

                operaciones.add(operacionRealizada);

                System.out.println("¿Desea continuar? (Escriba 'salir' para terminar, " +
                        "escriba 'historico' para ver su actividad reciente " +
                        "o presione Enter para continuar): ");
                entrada = lectura.nextLine();
                if (entrada.equalsIgnoreCase("salir")) break;
                if (entrada.equalsIgnoreCase("historico")) {
                    System.out.println("Esta es la lista de operaciones realizadas:");
                    for (ValorConversion opRealizada : operaciones) {
                        System.out.println(opRealizada);
                    }

                }
                System.out.println("Puede escribir 'salir' en cualquier momento para cerrar el programa.");



            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente nuevamente usando solo números.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }

        lectura.close();
        System.out.println("Programa finalizado.");

    }
}
    
