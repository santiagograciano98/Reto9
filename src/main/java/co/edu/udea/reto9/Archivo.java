package co.edu.udea.reto9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class Archivo {

    private String rutaOrigen;
    private String rutaDestino;

    public Archivo() {
    }

    public Archivo(String rutaOrigen, String rutaDestino) {
        this.rutaOrigen = rutaOrigen;
        this.rutaDestino = rutaDestino;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }

    public void procesarArcivo() {
        List<String> fechas = new ArrayList<>();
        List<Double> listaVolumenes = new ArrayList<>();
        List<Double> ListaPreciosCierre = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path pathRutaOrigen = Paths.get(this.rutaOrigen);
        Path pathRutaDestino = Paths.get(this.rutaDestino);
        List<String> lineasArchivo;

        try ( BufferedWriter writer = Files.newBufferedWriter(pathRutaDestino, charset)) {
            lineasArchivo = Files.readAllLines(pathRutaOrigen);
            //se rumueve la linea 1 de los titulos para que el archivo quede con la misma cantidad de caracteres en 
            //cada una de sus linea
            lineasArchivo.remove(0);
            writer.write("Fecha" + "\t" + "Concepto de precio" + "\n");

            for (String linea : lineasArchivo) {
                //vectorDatos tiene el vector resultante luego de hacer split por (,)
                String vectorDatos[] = linea.split(",");

                String textoEnviar = vectorDatos[0] + "\t" + precioApertura(Double.parseDouble(vectorDatos[1])) + "\n";
                writer.write(textoEnviar);
                fechas.add(vectorDatos[0]);
                ListaPreciosCierre.add(Double.valueOf(vectorDatos[4]));
                listaVolumenes.add(Double.valueOf(vectorDatos[6]));
            }

            double promedio = calcularPromedio(ListaPreciosCierre);
            double desviacionEstandar = calcularDesviacionEstandar(ListaPreciosCierre);
            int posicionMax = buscarPosicion(Collections.max(listaVolumenes), listaVolumenes);
            int posicionMin = buscarPosicion(Collections.min(listaVolumenes), listaVolumenes);

            System.out.println("El promedio de los precios de cierre de Ethereum durante el año fue: " + promedio);
            System.out.println("La desviacion estandar  de los precios de cierre de Ethereum durante el año fue:" + desviacionEstandar);
            System.out.println("El volumen transado mas alto es: " + Collections.max(listaVolumenes) + " en la fecha " + fechas.get(posicionMax));
            System.out.println("El volumen transado mas bajo es: " + Collections.min(listaVolumenes) + " en la fecha " + fechas.get(posicionMin));
           

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Se encargara de darle un concepto al precio dependiendo de su valor
     * las categorias son MUY BAJO-BAJO-MEDIO-ALTO-MUY ALTO
     * @param precio
     * @return
     */
    public static String precioApertura(double precio) {
        String conceptoPrecio = "";

        if (precio < 1200) {
            conceptoPrecio = "MUY BAJO";
        } else if (precio >= 1200 && precio < 2100) {
            conceptoPrecio = "BAJO";
        } else if (precio >= 2100 && precio < 3100) {
            conceptoPrecio = "MEDIO";
        } else if (precio >= 3100 && precio < 4600) {
            conceptoPrecio = "ALTO";
        } else if (precio >= 4600) {
            conceptoPrecio = "MUY ALTO";
        } else {
            conceptoPrecio = "El PRECIO ESTA FUERA DEL RANGO";
        }

        return conceptoPrecio;
    }

    /**
     * Recibe  lista de precios para luego recorrer y hacer un promedio
     * @param listaDatos
     * @return
     */
    public static double calcularPromedio(List<Double> listaDatos) {
        double suma = 0;
        for (double dato : listaDatos) {
            suma += dato;
        }

        double promedio = suma / listaDatos.size();

        return promedio;
    }

    /**
     * Recibe  lista de precios para luego recorrer y hacer calculo de desviación estandar
     * @param listaDatos
     * @return
     */
    public static double calcularDesviacionEstandar(List<Double> listaDatos) {
        double promedio = calcularPromedio(listaDatos);
        double suma = 0;
        for (double elem : listaDatos) {
            suma += Math.pow(elem - promedio, 2);
        }

        double desviacionEstandar = Math.sqrt(suma / (listaDatos.size() - 1));

        return desviacionEstandar;
    }

    public static int buscarPosicion(double valor, List<Double> listaDatos) {
        int contador = -1;
        for (double elem : listaDatos) {
            contador++;
            if (elem == valor) {
                return contador;
            }
        }
        return -1;
    }

}
