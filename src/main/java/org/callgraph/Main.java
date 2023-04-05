package org.callgraph;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CallgraphGenerator cg =
                new CallgraphGenerator("/home/emilio/4to/proyecto_guiado/com.amaze.filemanager_29.apk");

        System.out.println("Bye world!");
    }
}