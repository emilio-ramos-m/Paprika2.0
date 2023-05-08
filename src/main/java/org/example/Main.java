package org.example;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.Local;
import soot.PackManager;
import soot.PatchingChain;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.jimple.AbstractStmtSwitch;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.StringConstant;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        CallgraphGenerator cg = new CallgraphGenerator();

        System.out.println("Bye, world!");
    }

}