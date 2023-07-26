package br.com.doctorplus.gerenciador.model.utils;

import java.util.Random;

public class Util {

    public static Integer randomNumber() {
        Random rand = new Random();
        return rand.nextInt(1000, 9999);
    }
}
