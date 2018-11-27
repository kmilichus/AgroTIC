package com.expertobotanico.drools.main;

import com.expertobotanico.drools.controller.RuleRunner;
import com.expertobotanico.drools.model.*;

import java.util.Scanner;
 
public class Main
{
    private static Scanner sc;

	public static void main(String[] args)
    {
        RuleRunner runner = new RuleRunner(); 
        String[] rules = { "reglas.drl" };
        
        int des = 0;
       
     
    do{
    	Object[] facts = { new Planta(des) };        
        runner.runRules(rules,facts);
        sc = new Scanner(System.in);       
        System.out.print("###=>>> Escoja la opción que más se aproxime");
        System.out.print("\n        *Digite -1 para salir");
        System.out.print(":");
        des= sc.nextInt();
    }while (des != -1);
    }
}
