package edu.bsu.cs222;

import java.util.Random;

class RandomNumberGenerator {
    private Random random = new Random();
    private int decimal;

    public int generateLevelOneDecimal(){
        final int LEVEL_ONE_START = 14;
        decimal = random.nextInt(LEVEL_ONE_START) + 1;
        return decimal;
    }

    public int generateLevelTwoDecimal() {
        final int LEVEL_TWO_START = 16;
        final int LEVEL_TWO_END = 62;
        decimal=randomRange(LEVEL_TWO_START, LEVEL_TWO_END);
        return decimal;
    }

    public int generateLevelThreeDecimal() {
        final int LEVEL_THREE_START = 63;
        final int LEVEL_THREE_END = 255;
        decimal=randomRange(LEVEL_THREE_START, LEVEL_THREE_END);
        return decimal;
    }

    private int randomRange(int min, int max){
        int randomNumber= random.nextInt((max-min)+1);
        return randomNumber+min;
    }
}
