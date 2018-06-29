/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jcarmo
 */
public final class Util {

    public static String removerMascara(String valor) {
        return valor.replaceAll("\\D", "");
    }

    public static void main(String[] args) {
        System.out.println(">>>" + Util.formatarCPFNPJ("01014672147"));
    }

    public static String formatarCPFNPJ(String valor) {
        if (valor.length() == 14) {
            Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
            Matcher matcher = pattern.matcher(valor);
            if (matcher.find()) {
                return matcher.replaceAll("$1.$2.$3/$4-$5");
            }
            return valor;
        } else if (valor.length() == 11) {
            Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
            Matcher matcher = pattern.matcher(valor);
            if (matcher.find()) {
                return matcher.replaceAll("$1.$2.$3-$4");
            }
            return valor;
        } else {
            return "";
        }
    }
}
