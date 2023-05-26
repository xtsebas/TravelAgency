package Controller;
import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReadFile {
    public static Igraph normal= null;
    public static Igraph rain= null;
    public static Igraph snow= null;
    public static Igraph storm= null;

    public static void doGraphs(String fpath){
        ArrayList<ArrayList<String>> data= readTextFile(fpath);
        int count = countUniqueCities(data);
        normal= new Normal(createAdjacencyMatrix(matrixDef(data, 0)),createDistanceMatrix(data, count), count);
        normal.CalcularRutas();
        ArrayList<ArrayList<String>> data1= readTextFile(fpath);
        rain= new Rain(createAdjacencyMatrix(matrixDef(data1, 1)),createDistanceMatrix(data, count), count);
        rain.CalcularRutas();
        ArrayList<ArrayList<String>> data2= readTextFile(fpath);
        snow= new Snow(createAdjacencyMatrix(matrixDef(data2, 2)),createDistanceMatrix(data, count), count);
        snow.CalcularRutas();
        ArrayList<ArrayList<String>> data3= readTextFile(fpath);
        storm= new Storm(createAdjacencyMatrix(matrixDef(data3, 3)),createDistanceMatrix(data, count), count);
        storm.CalcularRutas();

    }
    public static ArrayList<ArrayList<String>> readTextFile(String filePath) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split(" ")));
                data.add(values);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public static int countUniqueCities(ArrayList<ArrayList<String>> matrix) {
        int count = 0;
        ArrayList cities=new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (isInteger(String.valueOf(matrix.get(i).get(j)))){

                }else {
                        if (cities.isEmpty()){
                            cities.add(matrix.get(i).get(j));
                            count=+1;
                        } else {
                            if (!cities.contains(matrix.get(i).get(j))){
                                cities.add(matrix.get(i).get(j));
                                count= count +1;
                            }
                        }
                    }
                }
            }
        return count;
    }

    public static String[][] createDistanceMatrix(ArrayList<ArrayList<String>> matrix, int numCities) {
        ArrayList cities=new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (isInteger(String.valueOf(matrix.get(i).get(j)))){

                }else {
                    if (cities.isEmpty()){
                        cities.add(matrix.get(i).get(j));
                    } else {
                        if (!cities.contains(matrix.get(i).get(j))){
                            cities.add(matrix.get(i).get(j));
                        }
                    }
                }
            }
        }
        String[][] matriz = new String[numCities][numCities];

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                matriz[i][j] = cities.get(j).toString();
            }
        }

        return matriz;
    }

    private static ArrayList<ArrayList<Integer>> createArray(ArrayList<ArrayList<String>> matrix, int numCities) {
        ArrayList<ArrayList<Integer>> numbers=new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                    for (ArrayList row : matrix) {
                        ArrayList arrayList = new ArrayList<>();
                        for (Object num : row) {
                            if (isInteger((String) num)){
                                arrayList.add(num);
                            }
                        }
                        if (!numbers.contains(arrayList)){
                            numbers.add(arrayList);
                        }
                    }
            }
        }
        return numbers;
    }
    public static int[][] createAdjacencyMatrix(ArrayList<ArrayList<String>> cityDistances) {
        int size = cityDistances.size();
        int[][] adjacencyMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    String cityA = cityDistances.get(i).get(0);
                    String cityB = cityDistances.get(j).get(0);
                    String distance = getDistance(cityDistances, cityA, cityB);

                    if (distance != null) {
                        adjacencyMatrix[i][j] = Integer.parseInt(distance);
                    } else {
                        adjacencyMatrix[i][j] = 10000;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    private static String getDistance(ArrayList<ArrayList<String>> cityDistances, String cityA, String cityB) {
        for (ArrayList<String> cities : cityDistances) {
            if (cities.get(0).equals(cityA) && cities.contains(cityB)) {
                int index = cities.indexOf(cityB);
                return cities.get(cities.size()-1);
            }
        }
        return null;
    }

    private static ArrayList<ArrayList<String>> matrixDef(ArrayList<ArrayList<String>> matrix, int weather){
        ArrayList<ArrayList<Integer>> numbers= createArray(matrix, countUniqueCities(matrix));
        for (ArrayList<String> row : matrix) {
            for (int i = row.size() - 1; i >= 0; i--) {
                String element = row.get(i);
                if (isNumeric(element)) {
                    row.remove(i);
                }
            }
        }
        switch (weather){
            case 0:
                for (int i = 0; i < matrix.size(); i++) {
                    if (i<numbers.size()){
                        matrix.get(i).add(String.valueOf(numbers.get(i).get(0)));
                    }
                }
                break;
            case 1:
                for (int i = 0; i < matrix.size(); i++) {
                    if (i<numbers.size()){
                        matrix.get(i).add(String.valueOf(numbers.get(i).get(1)));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < matrix.size(); i++) {
                    if (i<numbers.size()){
                        matrix.get(i).add(String.valueOf(numbers.get(i).get(2)));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < matrix.size(); i++) {
                    if (i<numbers.size()){
                        matrix.get(i).add(String.valueOf(numbers.get(i).get(3)));
                    }
                }
                break;
        }
        return matrix;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}