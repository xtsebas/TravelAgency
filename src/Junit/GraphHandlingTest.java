package Junit;

import Controller.GraphHandling;
import Controller.ReadFile;
import Model.Igraph;
import Model.Normal;

import java.util.ArrayList;

import static Controller.ReadFile.normal;
import static org.junit.jupiter.api.Assertions.*;

class GraphHandlingTest {
    static String  filePath = "C:\\Users\\Sebastian\\projects\\Proyectos en grupos\\Travel\\logistica.txt";


    int [][] dis= {{0,10,10000,10000,10000,10000,10000},
            {10000,0,8,10000,10000,10000,10000},
            {10000,10000,0,14,10000,10,10000},
            {10000,13,10000,0,6,10000,10000},
            {10000,10000,10000,10000,0,11,10000},
            {10,10000,10000,10000,10000,0,9},
            {7,10000,10000,10000,10000,10000,0}};

    @org.junit.jupiter.api.Test
    void showPath() {
        ReadFile.doGraphs(filePath);
        ArrayList Path= new ArrayList<>();
        Path.add("London");
        Path.add("SaoPaulo");
        Path.add("BuenosAires");
        assertEquals(GraphHandling.showPath("Normal", "BuenosAires", "London"), Path);

    }

    @org.junit.jupiter.api.Test
    void editMatrix() {
        ReadFile.doGraphs(filePath);
        int [][] dis= {{0,10,10000,10000,10000,10000,10000},
                {10000,0,8,10000,10000,10000,10000},
                {10000,10000,0,14,10000,10,10000},
                {10000,13,10000,0,6,10000,10000},
                {10000,10000,10000,10000,0,11,10000},
                {10,10000,10000,10000,10000,0,9},
                {7,10000,10000,10000,10000,10000,0}};

        GraphHandling.editMatrix("Normal", "BuenosAires", "SaoPaulo");
        assertEquals(normal.getDistancias(), dis);
    }
}