package org.callgraph;


import sootup.callgraph.CallGraph;
import sootup.callgraph.CallGraphAlgorithm;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.core.Project;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.signatures.MethodSignature;
import sootup.core.typehierarchy.ViewTypeHierarchy;
import sootup.core.types.ClassType;
import sootup.core.types.VoidType;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.bytecode.inputlocation.PathBasedAnalysisInputLocation;
import sootup.java.core.JavaIdentifierFactory;
import sootup.java.core.JavaProject;
import sootup.java.core.JavaSootClass;
import sootup.java.core.language.JavaLanguage;
import sootup.java.core.views.JavaView;
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class CallgraphGenerator {
    CallgraphGenerator(String path_apk){
        ///////////////////////////////////////////////////////////////////////////////////////
        PathBasedAnalysisInputLocation
                pathBasedNamespace = new PathBasedAnalysisInputLocation(Paths.get(path_apk),null);
        JavaProject project =
                JavaProject.builder(new JavaLanguage(17))
                        .addInputLocation(pathBasedNamespace) // apk
                        .addInputLocation(
                                new JavaClassPathAnalysisInputLocation(
                                        "/usr/lib/jvm/java-17-openjdk-amd64/lib/jrt-fs.jar")
                        )
                        .addInputLocation(
                                new JavaClassPathAnalysisInputLocation("/home/emilio/Android/Sdk/platforms/android-21/android.jar") // change this
                        )
                        .build();
        ///////////////////////////////////////////////////////////////////////////////////////
        //Creating the type Hierarchy
        JavaView view = (JavaView) project.createFullView();
        ViewTypeHierarchy typeHierarchy = new ViewTypeHierarchy(view);
        ///////////////////////////////////////////////////////////////////////////////////////
        //Defining an Entry Method
        ClassType mainClass =
                JavaIdentifierFactory.getInstance().getClassType("com.amaze.filemanager.activities.MainActivity");

        ClassType classTypeA = project.getIdentifierFactory().getClassType("android.os.Bundle");

        MethodSignature entryMethodSignature =
                JavaIdentifierFactory.getInstance()
                        .getMethodSignature(
                                mainClass,
                                JavaIdentifierFactory.getInstance()
                                        .getMethodSubSignature(
                                                "onCreate", VoidType.getInstance(), Collections.singletonList(classTypeA)
                                        )
                        );
        ///////////////////////////////////////////////////////////////////////////////////////
        // Class Hierarchy Analysis
        CallGraphAlgorithm cha =
                new ClassHierarchyAnalysisAlgorithm(view, typeHierarchy);

        CallGraph cg =
                cha.initialize(Collections.singletonList(entryMethodSignature));

        cg.callsFrom(entryMethodSignature).forEach(System.out::println);

        System.out.println(cg.toStringSorted());
        ///////////////////////////////////////////////////////////////////////////////////////

    }
}
