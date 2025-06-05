package org.example.grafos.ejercicio2;

import org.example.grafos.ejercicio1.Grafo;
import org.example.grafos.ejercicio1.NodoColor;

public class Busqueda {
    public static int tiempo;

    public static <T> void dfs(Grafo<T, NodoColor<T>> grafo){
        tiempo = 0;
        // Inicializo todos los nodos como "Blanco"
        for(NodoColor<T> n : grafo.getNodos()){
            n.setColor("Blanco");
        }
        // Recorro todos los nodos y aplico dfsRecursiva a los "Blanco"
        for(NodoColor<T> n : grafo.getNodos()){
            if(n.getColor().equals("Blanco")){
                dfsRecursiva(n);
            }
        }

    }

    private static <T> void dfsRecursiva(NodoColor<T> nodo){
        nodo.setColor("Amarillo");
        tiempo++;
        nodo.setTiempoDescubrimiento(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se dedscubrio a los " + tiempo + " y el color es: " + nodo.getColor());
        for(NodoColor<T> adyacente : nodo.getAdyacentes()){
            if(adyacente.getColor().equals("Blanco")){
                dfsRecursiva(adyacente);
            }
        }
        nodo.setColor("Negro");
        tiempo++;
        nodo.setTiempoFinalizacion(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se dedscubrio a los " + tiempo + " y el color es: " + nodo.getColor());
    }
}
