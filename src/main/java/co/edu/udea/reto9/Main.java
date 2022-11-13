package co.edu.udea.reto9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Santiago Graciano David - Jonathan David Ardila Gomez
 */
public class Main {

    public static void main(String[] args) {

        String rutaOrigen = "/home/santiago/Descargas/ETH-USD.csv";
        String rutaDestino = "/home/santiago/Descargas/ArchivoCopia.csv";


        Archivo archivo = new Archivo(rutaOrigen,rutaDestino);
        archivo.procesarArcivo();

        System.out.println("Archivo procesado exitosamente \n\n");

        System.out.println();

        //lista que contiene los numeros a los cuales se les sacara la raiz cuadrada
        List<Double> numeros = new ArrayList<>();
        numeros.add(9.0);
        numeros.add(25.0);
        numeros.add(100.0);

        ProgramacionFuncional ejemplo1 = new ProgramacionFuncional();
        ejemplo1.setListaNumeros(numeros);
        List<Double> numerosElevados = ejemplo1.hallarRaizCuadrada();
        System.out.println("----- Raiz Cuadrada ------");
        numerosElevados.stream().forEach(numero -> System.out.println("La raiz cuadrada es: " + numero));

        //Set que contiene las cadenas de caracteres
        Set<String> conjunto = new HashSet<>();
        conjunto.add("Jirafa");
        conjunto.add("Elefante");
        conjunto.add("Mono");
        conjunto.add("Gato");
        conjunto.add("Ballena");
        conjunto.add("Perro");

        ProgramacionFuncional ejemplo2 = new ProgramacionFuncional();
        List<String> conjuntosFilter = ejemplo2.cadenasMayorACinco(conjunto);
        System.out.println("\n\n------ Cadena mayor a 5 caracteres ----------");
        conjuntosFilter.stream().forEach(cadena -> System.out.println("La cadena " +cadena + " tiene mas de 5 caracteres"));

    }

   
}
