package org.example.grafos.ejercicio2;

import org.example.grafos.ejercicio1.Grafo;
import org.example.grafos.ejercicio1.GrafoDirigido;
import org.example.grafos.ejercicio1.NodoColor;

import java.util.*;

public class Busqueda {
    private static int ciclos;
    private static int tiempo;
    public enum Criterio { SIMPLE, CICLO, CAMINO_MAS_LARGO, PATH_TO, BETWEEN }
    public static <T> void dfs(Grafo<T, NodoColor<T>> grafo, Criterio criterio){
        tiempo = 0;
        ciclos = 0;

        // Inicializo todos los nodos como "Blanco"
        for(NodoColor<T> n : grafo.getNodos()){
            n.setColor("Blanco");
        }
        switch (criterio){
            case SIMPLE: {
                // Recorro todos los nodos y aplico dfsRecursiva a los "Blanco"
                for (NodoColor<T> n : grafo.getNodos()) {
                    if (n.getColor().equals("Blanco")) {
                        dfsRecursiva(n);
                    }
                }
                break;
            }
            case CICLO: {
                for (NodoColor<T> n : grafo.getNodos()) {
                    if (n.getColor().equals("Blanco")) {
                        dfsCiclica(n);
                    }
                }
                System.out.println("El grafo tiene " + ciclos + " ciclos.");
                break;
            }
            case CAMINO_MAS_LARGO: {
                Stack<NodoColor<T>> pila = new Stack<>();
                for (NodoColor<T> n : grafo.getNodos()) {
                    if (n.getColor().equals("Blanco")) {
                        dfsCML(n, pila);
                    }
                }

                List<NodoColor<T>> resultado = new ArrayList<>();
                while (!pila.isEmpty()) {
                    resultado.add(pila.pop());
                }
                System.out.println(resultado);
                break;
            }
        }

    }

    private static <T> void dfsRecursiva(NodoColor<T> nodo){
        //Visita
        nodo.setColor("Amarillo");
        tiempo++;
        nodo.setTiempoDescubrimiento(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se dedscubrio a los " + tiempo + " y el color es: " + nodo.getColor());
        for(NodoColor<T> adyacente : nodo.getAdyacentes()){
            String color = adyacente.getColor();
            if(color.equals("Blanco")){
                dfsRecursiva(adyacente);
            } else if (color.equals("Amarillo")) {
                System.out.println("Ciclo mas mas");
                ciclos++;
            }
        }
        nodo.setColor("Negro");
        tiempo++;
        nodo.setTiempoFinalizacion(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se termino a los " + tiempo + " y el color es: " + nodo.getColor());

    }

    private static <T> void dfsCiclica(NodoColor<T> nodo){
        //Visita
        nodo.setColor("Amarillo");
        tiempo++;
        nodo.setTiempoDescubrimiento(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se dedscubrio a los " + tiempo + " y el color es: " + nodo.getColor());
        for(NodoColor<T> adyacente : nodo.getAdyacentes()){
            String color = adyacente.getColor();
            if(color.equals("Blanco")){
                dfsRecursiva(adyacente);
            } else if (color.equals("Amarillo")) {
                System.out.println("Ciclo mas mas");
                ciclos++;
            }
        }
        nodo.setColor("Negro");
        tiempo++;
        nodo.setTiempoFinalizacion(tiempo);
        System.out.println("El nodo "+ nodo.getValor() + " se termino a los " + tiempo + " y el color es: " + nodo.getColor());

    }
    private static <T> void dfsCML(NodoColor<T> nodo,  Stack<NodoColor<T>> pila){
        nodo.setColor("Amarillo");
        for (NodoColor<T> adyacente : nodo.getAdyacentes()) {
            if(adyacente.getColor().equals("Blanco")){
                dfsCML(adyacente, pila);
            }else if (adyacente.getColor().equals("Amarillo")) {
                System.out.println("ERROR: Ciclo encontrado!!!!!");
                throw new RuntimeException("ERROR: Ciclo encontrado la concha de la lora!!!!!");
            }
        }
        nodo.setColor("Negro");
        pila.push(nodo);//Aca lo meto en una pila
    }

    public static <T> void bfs(Grafo<T, NodoColor<T>> grafo, Criterio criterio){
        Queue<NodoColor<T>> queue = new LinkedList<>();
        for (NodoColor<T> n : grafo.getNodos()){
            n.setColor("No visitado");
        }
        switch (criterio){
            case SIMPLE: {
                for (NodoColor<T> n : grafo.getNodos()){
                    if(n.getColor().equals("No visitado")){
                        bfsIterativo(n, queue);
                    }
                }
            }
            break;
        }

    }

    public static <T> void bfs(Grafo<T, NodoColor<T>> grafo, Criterio criterio, NodoColor<T> nodoDestino){
        Queue<NodoColor<T>> queue = new LinkedList<>();
        for (NodoColor<T> n : grafo.getNodos()){
            n.setColor("No visitado");
        }
        switch (criterio){
            case SIMPLE: {
                for (NodoColor<T> n : grafo.getNodos()){
                    if(n.getColor().equals("No visitado")){
                        bfsIterativo(n, queue);
                    }
                }
                break;
            }
            case PATH_TO: {
                List<NodoColor<T>> resultado = new ArrayList<>();

                Grafo<T, NodoColor<T>> invertido = invertirGrafo(grafo); // llamá al método correcto
                NodoColor<T> nodoDestinoInvertido = invertido.getNodo(nodoDestino.getValor()); // <-- esta línea es la que fallaba

                bdfPathTo(nodoDestinoInvertido, resultado);

                System.out.println("Nodos que tienen camino hacia el destino:");
                for (NodoColor<T> n : resultado) {
                    System.out.println(n.getValor());
                }
                System.out.println("El camino es: ");
                for (NodoColor<T> n : resultado) {
                    System.out.print(n.getValor() + " => ");
                }
                break;
            }

        }
    }
    public static <T> void bfs(Grafo<T, NodoColor<T>> grafo, Criterio criterio, NodoColor<T> nodoOrigen, NodoColor<T> nodoDestino){
        Queue<NodoColor<T>> queue = new LinkedList<>();
        for (NodoColor<T> n : grafo.getNodos()){
            n.setColor("No visitado");
        }
        switch (criterio){
            case SIMPLE: {
                for (NodoColor<T> n : grafo.getNodos()){
                    if(n.getColor().equals("No visitado")){
                        bfsIterativo(n, queue);
                    }
                }
                break;
            }
            case PATH_TO: {
                List<NodoColor<T>> resultado = new ArrayList<>();

                Grafo<T, NodoColor<T>> invertido = invertirGrafo(grafo); // llamá al método correcto
                NodoColor<T> nodoDestinoInvertido = invertido.getNodo(nodoDestino.getValor()); // <-- esta línea es la que fallaba

                bdfPathTo(nodoDestinoInvertido, resultado);

                System.out.println("Nodos que tienen camino hacia el destino:");
                for (NodoColor<T> n : resultado) {
                    System.out.println(n.getValor());
                }
                System.out.println("El camino es: ");
                for (NodoColor<T> n : resultado) {
                    System.out.print(n.getValor() + " => ");
                }
                break;
            }
            case BETWEEN: {
                List<NodoColor<T>> resultado = new ArrayList<>();
                NodoColor<T> nodoA =  grafo.getNodo(nodoOrigen.getValor());
                bfsBetween(nodoA,nodoDestino, resultado);
            }

        }
    }

    private static <T> void bfsIterativo(NodoColor<T> n, Queue<NodoColor<T>> queue) {
        n.setColor("Visitado");
        queue.add(n);
        int nivel = 0;
        while (!queue.isEmpty()) {

            //System.out.print("Nivel " + nivel + " => ");

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

            //System.out.println(); // aca se hace el salto  de cada nivel
            nivel++;
        }
    }

    private static <T> void bdfPathTo(NodoColor<T> n, List<NodoColor<T>> resultado) {
        n.setColor("Visitado");
        resultado.add(n);
        for (NodoColor<T> adyacente : n.getAdyacentes()) {
            if (adyacente.getColor().equals("No visitado")) {
                bdfPathTo(adyacente, resultado);
            }
        }
    }

    private static <T> Grafo<T, NodoColor<T>> invertirGrafo(Grafo<T, NodoColor<T>> grafoOriginal) {
        Grafo<T, NodoColor<T>> invertido = new GrafoDirigido<>();

        // Primero clonar los nodos
        for (NodoColor<T> nodo : grafoOriginal.getNodos()) {
            invertido.addNodo(new NodoColor<>(nodo.getValor()));
        }

        // Luego agregar aristas invertidas
        for (NodoColor<T> nodo : grafoOriginal.getNodos()) {
            for (NodoColor<T> ady : nodo.getAdyacentes()) {
                NodoColor<T> origen = invertido.getNodo(ady.getValor());
                NodoColor<T> destino = invertido.getNodo(nodo.getValor());
                invertido.agregarArista(origen, destino);
            }
        }
        return invertido;
    }

    private static <T> void bfsBetween(NodoColor<T> nodoOrigen, NodoColor<T> nodoDestino, List<NodoColor<T>> resultado) {
        Queue<NodoColor<T>> queue = new LinkedList<>(); //Armo la queue
        Map<NodoColor<T>, NodoColor<T>> padre = new HashMap<>(); //Armo la lista de antcestros

        nodoOrigen.setColor("Visitado");
        queue.add(nodoOrigen);

        boolean encontrado = false;

        while (!queue.isEmpty() && !encontrado) { // mientras no haya recxorrrido todo y no encutre el nodoDestino continuo profundizando niveles
            NodoColor<T> actual = queue.poll();

            for (NodoColor<T> adyacente : actual.getAdyacentes()) {
                if (adyacente.getColor().equals("No visitado")) {
                    adyacente.setColor("Visitado");
                    padre.put(adyacente, actual); // clave: el hijo, valor: el padre, asi creo los ancestros
                    queue.add(adyacente);//agrego a la queue para poder seguir buscando

                    if (adyacente.equals(nodoDestino)) {//si encontramos el destino
                        encontrado = true;//encontramos
                        break;//y cortamos el for
                    }
                }
            }
        }

        // Reconstruir el camino si se encontró
        if (encontrado) {
            Stack<NodoColor<T>> camino = new Stack<>();
            NodoColor<T> actual = nodoDestino;
            while (actual != null) {
                camino.push(actual);
                actual = padre.get(actual);
            }

            // Extraer camino en orden correcto
            while (!camino.isEmpty()) {
                resultado.add(camino.pop());
            }

            System.out.println("Camino más corto de " + nodoOrigen.getValor() + " a " + nodoDestino.getValor() + ":");
            for (NodoColor<T> n : resultado) {
                System.out.print(n.getValor() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No hay camino entre " + nodoOrigen.getValor() + " y " + nodoDestino.getValor());
        }
    }


}
