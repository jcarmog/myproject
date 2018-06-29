/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.util;

/**
 *
 * @author jcarmo
 */
public final class Util {
    
    public static String removerMascara(String valor){
        return valor.replaceAll("\\D", "");
    }
    public static void main(String[] args) {
        System.out.println(">>>"+Util.removerMascara("(61)9999-99999"));
    }
}
