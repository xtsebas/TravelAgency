package Controller;

import Model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Controller.ReadFile.*;

public class GraphHandling {
    public static int findDistance(String weather, String city1, String city2){
        Igraph igraph = findGraph(weather);
        int indiceOrigen = Arrays.asList(igraph.getVertices()).indexOf(city1);
        int indiceDestino = Arrays.asList(igraph.getVertices()).indexOf(city2);

        return igraph.getDistancias()[indiceOrigen][indiceDestino];
    }

    public static String FindCenter(String weather){
        Igraph igraph = findGraph(weather);

        int[] coordenates = findCenterCalculate(igraph.getDistancias());
        String[][] Matrix= igraph.getRecorridos();
        return Matrix[coordenates[0]][coordenates[1]];
    }

    public static ArrayList showPath(String weather, String city1, String city2){
        Igraph graph=findGraph(weather);
        int one=0;
        int two=0;
        ArrayList Path= null;
        ArrayList cities= new ArrayList<>(List.of(graph.getVertices()));
        for (Object city: cities){
            if (city.equals(city1)){
                one=cities.indexOf(city);
            }
            if (city.equals(city2)){
                two=cities.indexOf(city);
            }
        }
        String prov= graph.getVertices()[two];;
            while (city1!=prov){
                if (Path==null){
                    Path = new ArrayList<>();
                    Path.add(prov);
                }else {
                    for (int j = 0; j < graph.getSIZE(); j++) {
                        for (int k = 0; k < graph.getSIZE(); k++) {
                            if (prov == graph.getVertices()[k] && graph.getRecorridos()[one][k]!= prov){
                                prov = graph.getRecorridos()[one][k];
                                Path.add(prov);
                            }
                            if (prov == graph.getVertices()[k]){
                                prov=city1;
                                if (Path.get(Path.size()-1)!= city1){
                                    Path.add(prov);
                                }
                                break;
                            }
                        }
                    }
                }
            }

        return Path;
    }

    public static void editMatrix(String weather, String city1, String city2){
        Igraph graph=findGraph(weather);
        for (int i = 0; i < graph.getSIZE(); i++) {
            for (int j = 0; j < graph.getSIZE(); j++) {
                if (graph.getRecorridos()[i][0]==city1 && graph.getRecorridos()[0][j]==city2 && graph.getDistancias()[i][j] != 0){
                    graph.getDistancias()[i][j]=10000;
                    String city= graph.getRecorridos()[0][j];
                    graph.getRecorridos()[i][j]=city;
                }
            }
        }
        graph.CalcularRutas();
    }

    public static Igraph findGraph(String weather){
        Igraph igraph= null;
        switch (weather){
            case "Normal":
                igraph= normal;
            break;
            case "Lluvia":
                igraph= rain;
                break;
            case "Nieve":
                igraph= snow;
                break;
            case "Tormenta":
                igraph= storm;
                break;
        }
        return igraph;
    }

    public static int[] findCenterCalculate(int[][] matrizAdyacencia) {
        int n = matrizAdyacencia.length;
        int[] distanciasMaximas = new int[n];

        // Inicializar distancias máximas con cero
        Arrays.fill(distanciasMaximas, 0);

        // Calcular las distancias máximas para cada vértice
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrizAdyacencia[i][j] != 999 && matrizAdyacencia[i][j] > distanciasMaximas[i]) {
                    distanciasMaximas[i] = matrizAdyacencia[i][j];
                }
            }
        }

        // Encontrar el mínimo de las distancias máximas
        int fila = 0;
        int columna = 0;
        int minDistanciaMaxima = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (distanciasMaximas[i] < minDistanciaMaxima) {
                minDistanciaMaxima = distanciasMaximas[i];
                fila = i;
                columna = i;
            }
        }

        return new int[]{fila, columna};
    }
}
