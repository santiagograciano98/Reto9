package co.edu.udea.reto9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

public class ProgramacionFuncional {
    private List<Double> listaNumeros;
    private List<String> listaConjuntoCaracteres;

    public ProgramacionFuncional() {
    }

    public List<Double> getListaNumeros() {
        return listaNumeros;
    }

    public void setListaNumeros(List<Double> listaNumeros) {
        this.listaNumeros = listaNumeros;
    }

    public List<String> getListaConjuntoCaracteres() {
        return listaConjuntoCaracteres;
    }

    public void setListaConjuntoCaracteres(List<String> listaConjuntoCaracteres) {
        this.listaConjuntoCaracteres = listaConjuntoCaracteres;
    }
    /**
     * Utiliza el atributo listaNumeros tipo Double de la clase luego hacer stream para 
     * obtener la raiz cuadrada del número y retornar
     * @return
     */
    public  List<Double> hallarRaizCuadrada(){
        return listaNumeros.stream().map(numero -> Math.sqrt(numero)).collect(Collectors.toList());
    }

    /**
     * Recibe Set de cadena para luego hallar los que cumplen la condición de
     * caracteres mayores a 5 y retornar
     * @param SetConjuntos
     * @return
     */
    public  List<String> cadenasMayorACinco(Set<String> SetConjuntos){
        return SetConjuntos.stream().filter(conjunto -> conjunto.length() > 5).collect(Collectors.toList());
    }
    

}
