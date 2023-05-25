package View;

import Controller.GraphHandling;
import Model.Igraph;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
        option();
    }

    static void menu(){
        System.out.println("Bienvenido a la Agencia de Busqueda, primero se necesita el txt para ingresar su tiempo de viaje");
        String fpath= in.nextLine();
        //Readfile option
    }

    static void option(){
        boolean op= true;
        while (op){
            System.out.println("Que desea hacer:\n1. Tiempo y camino de destino\n2. Centro de mi grafo\n3. TRAFICO");
            int option=in.nextInt();
            in.nextLine();
            switch (option){
                case 1:
                    System.out.println("Cual clima (escribirlo) \n Normal\n Lluvia\n Nieve\n Tormenta");
                    String weather= in.nextLine();
                    System.out.println("En cual ciudad desea inicar su viaje (escribirlo):");
                    String city1 = in.nextLine();
                    System.out.println("En cual ciudad desea terminar su viaje (escribirlo):");
                    String city2 = in.nextLine();
                    int distance= GraphHandling.findDistance(weather, city1, city2);
                    ArrayList path= GraphHandling.showPath(weather, city1, city2);
                    System.out.println("Su distancia de ir de " + city1 + " a " + city2 + " es: " + distance);
                    System.out.println("Su camino de ir de " + city1 + " a " + city2 + " es: ");
                    show(path);
                    break;
                case 2:
                    System.out.println("Cual clima (escribirlo) \n Normal\n Lluvia\n Nieve\n Tormenta");
                    weather= in.nextLine();
                    Igraph igraph= GraphHandling.findGraph(weather);
                    String center= GraphHandling.FindCenter(weather);
                    System.out.println("El centro del grafo es la ciudad " + center);
                    break;
                case 3:
                    System.out.println("Cual clima (escribirlo) \n Normal\n Lluvia\n Nieve\n Tormenta");
                     weather= in.nextLine();
                    System.out.println("En cual ciudad desea que no haya conexion (escribirlo):");
                     city1 = in.nextLine();
                    System.out.println("En cual ciudad ya no tiene conexion con la primera (escribirlo):");
                     city2 = in.nextLine();
                    GraphHandling.editMatrix(weather,city1,city2);
                    System.out.println("Realizado");
                    break;
                default:
                    System.out.println("No ingreso ninguna de nuestras opciones");
                    break;
            }
            System.out.println("Desea realizar otra opcion: \n1.Si\n2.No");
            option=in.nextInt();
            if (option==2){op=false;}
        }
    }
    static void show(ArrayList arrayList){
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
    }
}