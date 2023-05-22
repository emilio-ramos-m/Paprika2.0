package org.example;

import java.io.File;
import java.util.*;
import soot.*;
import soot.jimple.*;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.options.Options;
import soot.toolkits.graph.*;
import soot.util.*;

public class CallgraphGenerator {
    CallgraphGenerator(){
        // Ruta al archivo APK de tu aplicación Android
        String apkPath = "../apk_validation/com.amaze.filemanager_29.apk";

        // Configurar las opciones de Soot
        Options.v().set_src_prec(Options.src_prec_apk);
        Options.v().set_process_dir(Collections.singletonList(apkPath));
        Options.v().set_android_jars("../android-platforms/"); // Ruta a las bibliotecas de Android

        Options.v().set_whole_program(true);
        Options.v().set_allow_phantom_refs(true);

        // Cargar las clases y sus dependencias
        Scene.v().loadNecessaryClasses();

        // Habilitar la fase de análisis de CHA (Class Hierarchy Analysis)
        PhaseOptions.v().setPhaseOption("cg.cha", "on");

        PackManager.v().runPacks();
        // Obtener el Call Graph
        CallGraph callGraph = Scene.v().getCallGraph();

        // Imprimir el Call Graph
        Iterator<Edge> edgeIterator = callGraph.iterator();
        while (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            SootMethod src = (SootMethod) edge.getSrc();
            SootMethod tgt = (SootMethod) edge.getTgt();

            System.out.println(src + " -> " + tgt);
        }
    }
}
