package br.com.elizeustachuka.cliente.utils;

public class Util {
    public static boolean validaCep(String cep) {
        if (!cep.matches("\\d{8}")) {
            return false;
        }

        return true;
    }
}
