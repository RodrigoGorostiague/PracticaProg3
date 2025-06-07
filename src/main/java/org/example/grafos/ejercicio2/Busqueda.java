package org.example.grafos.ejercicio2;

import org.example.grafos.ejercicio1.Grafo;
import org.example.grafos.ejercicio1.NodoColor;

import java.util.LinkedList;
import java.util.Queue;

public class Busqueda {

    public static <T> void dfs(Grafo<T, NodoColor<T>> grafo){
        int tiempo = 0;
        // Inicializo todos los nodos como "Blanco"
        for(NodoColor<T> n : grafo.getNodos()){
            n.setColor("Blanco");
        }
        // Recorro todos los nodos y aplico dfsRecursiva a los "Blanco"
        for(NodoColor<T> n : grafo.getNodos()){
            if(n.getColor().equals("Blanco")){
                dfsRecursiva(n, tiempo);
            }
        }
    }

    private static <T> void dfsRecursiva(NodoColor<T> nodo,  int tiempo){
        //Visita
        nodo.setColor("Amarillo");
        tiempo++;
        nodo.setTiempoDescubrimiento(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se dedscubrio a los " + tiempo + " y el color es: " + nodo.getColor());
        for(NodoColor<T> adyacente : nodo.getAdyacentes()){
            if(adyacente.getColor().equals("Blanco")){
                dfsRecursiva(adyacente, tiempo);
            }
        }
        nodo.setColor("Negro");
        tiempo++;
        nodo.setTiempoFinalizacion(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se termino a los " + tiempo + " y el color es: " + nodo.getColor());
    }

    public static <T> void bfs(Grafo<T, NodoColor<T>> grafo){
        Queue<NodoColor<T>> queue = new LinkedList<>();
        for (NodoColor<T> n : grafo.getNodos()){
            n.setColor("No visitado");
        }
        for (NodoColor<T> n : grafo.getNodos()){
            if(n.getColor().equals("No visitado")){
                bfsIterativo(n, queue);
            }
        }
    }

    private static <T> void bfsIterativo(NodoColor<T> n, Queue<NodoColor<T>> queue) {
        n.setColor("Visitado");
        queue.add(n);
        int nivel = 0;
        while (!queue.isEmpty()) {

            System.out.print("Nivel " + nivel + " => ");

            int tamanio = queue.size();
            for (int i = 0; i < tamanio; i++) {
                NodoColor<T> actual = queue.poll();

                System.out.print(actual.getValor() + " ");

                for (NodoColor<T> adyacente : actual.getAdyacentes()) {
                    if (adyacente.getColor().equals("No visitado")) {
                        adyacente.setColor("Visitado");
                        queue.add(adyacente);
                    }
                }
            }

            System.out.println(); // aca se hace el salto  de cada nivel
            nivel++;
        }
    }

}
