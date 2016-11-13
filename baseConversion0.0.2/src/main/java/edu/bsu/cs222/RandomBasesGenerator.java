package edu.bsu.cs222;

import java.util.Random;

class RandomBasesGenerator {

    private Random random = new Random();
    private int baseSelection;
    private FixedBaseNumber.questionToBase currentBases;

    public void setRandomBasesOne(FixedBaseNumber fixedNumber){
        boolean baseSelect = random.nextBoolean();
        if(baseSelect){
            currentBases =FixedBaseNumber.questionToBase.BINARY_TO_DECIMAL;
        }
        else{
            currentBases = FixedBaseNumber.questionToBase.DECIMAL_TO_BINARY;
        }
        fixedNumber.setCurrentBases(currentBases);
    }

    public void setRandomBasesThree(FixedBaseNumber fixedNumber){
        final int LEVEL_THREE_OPTIONS = 6;
        baseSelection = random.nextInt(LEVEL_THREE_OPTIONS);
        switch (baseSelection){
            case 0: currentBases =FixedBaseNumber.questionToBase.BINARY_TO_DECIMAL;
                break;
            case 1: currentBases =FixedBaseNumber.questionToBase.DECIMAL_TO_BINARY;
                break;
            case 2: currentBases =FixedBaseNumber.questionToBase.BINARY_TO_HEX;
                break;
            case 3: currentBases =FixedBaseNumber.questionToBase.HEX_TO_BINARY;
                break;
            case 4: currentBases =FixedBaseNumber.questionToBase.HEX_TO_DECIMAL;
                break;
            case 5: currentBases =FixedBaseNumber.questionToBase.DECIMAL_TO_HEX;
                break;
        }
        fixedNumber.setCurrentBases(currentBases);
    }

    public void setRandomBasesTwo(FixedBaseNumber fixedNumber){
        final int LEVEL_TWO_OPTIONS = 3;
        baseSelection = random.nextInt(LEVEL_TWO_OPTIONS);
        switch(baseSelection){
            case 0: currentBases =FixedBaseNumber.questionToBase.BINARY_TO_DECIMAL;
                break;
            case 1: currentBases =FixedBaseNumber.questionToBase.DECIMAL_TO_BINARY;
                break;
            case 2: currentBases =FixedBaseNumber.questionToBase.BINARY_TO_HEX;
                break;
        }
        fixedNumber.setCurrentBases(currentBases);
    }
}
