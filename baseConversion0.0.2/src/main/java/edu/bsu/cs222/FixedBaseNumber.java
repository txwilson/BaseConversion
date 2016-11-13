package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.regex.Pattern;

class FixedBaseNumber {

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
    }

    private int decimalValue;
    private questionToBase currentBases;
    private ArrayList<Observer> observers = new ArrayList<>();
    private static BaseConverter calculator = new BaseConverter();

    public enum questionToBase {
        BINARY_TO_DECIMAL(2,10),
        DECIMAL_TO_BINARY(10,2),
        BINARY_TO_HEX(2,16),
        HEX_TO_BINARY(16,2),
        HEX_TO_DECIMAL(16,10),
        DECIMAL_TO_HEX(10,16);
        public final int START_BASE;
        public final int END_BASE;

        questionToBase(int startBase, int endBase){
            this.START_BASE = startBase;
            this.END_BASE = endBase;
        }
    }

    public FixedBaseNumber(int decimalValue) {
        this.decimalValue = decimalValue;
    }

    public static FixedBaseNumber parseBinary(String binary) {
        FixedBaseNumber calculatedNumber;
        if(Pattern.matches("[a-zA-Z]+",binary)){
            calculatedNumber= new FixedBaseNumber(0);
        }
        else{
            int convertedDecimal=calculator.parseFromBinary(binary);
            calculatedNumber= new FixedBaseNumber(convertedDecimal);
        }
        return calculatedNumber;
    }

    public static FixedBaseNumber parseHex(String hex) {
        int convertedDecimal=calculator.parseFromHex(hex);
        FixedBaseNumber calculatedNumber= new FixedBaseNumber(convertedDecimal);
        return calculatedNumber;
    }

    public static FixedBaseNumber parseDecimal(String decimal) {
        int convertedDecimal = calculator.parseFromDecimal(decimal);
        FixedBaseNumber calculatedNumber = new FixedBaseNumber(convertedDecimal);
        return calculatedNumber;
    }

    public String toNumberString(int base) {
        return formatString(base);
    }

    private String formatString(int base){
        String formattedString=calculator.convertToString(decimalValue, base);
        switch (base){
            case 2:{
                if (decimalValue>15){
                    formattedString=addZerosEight(formattedString);
                }
                else{
                    formattedString=addZerosFour(formattedString);
                }
                break;
            }
            case 16:{
                formattedString= "0x"+formattedString;
                break;
            }
        }
        return formattedString;
    }

    public int getDecimalValue() {
        return decimalValue;
    }

    private String addZerosEight(String fullDigits) {
        final int TWO_BYTE_LENGTH = 8;
        int tall = TWO_BYTE_LENGTH -(fullDigits.length());
        String correctDigits="";
        while(tall>0){
            correctDigits=correctDigits.concat("0");
            tall--;
        }
        return correctDigits.concat(fullDigits);
    }

    private String addZerosFour(String binaryNum){
        final int BYTE_LENGTH = 4;
        int tall = BYTE_LENGTH -(binaryNum.length());
        String correctDigits="";
        while(tall>0){
            correctDigits=correctDigits.concat("0");
            tall--;
        }
        return correctDigits.concat(binaryNum);
    }

    public void setCurrentBases(questionToBase currentBase) {
        this.currentBases = currentBase;
        notifyObservers();
    }

    public String getStartBase() {
        int baseNum = currentBases.START_BASE;
        String base=toBaseString(baseNum);
        return base;
    }

    public String getEndBase() {
        int baseNum = currentBases.END_BASE;
        String base=toBaseString(baseNum);
        return base;
    }

    private String toBaseString(int baseNum) {
        switch(baseNum){
            case 2: return "Binary";
            case 10: return "Decimal";
            case 16:
                return "Hexadecimal";
        }
        return "Decimal";
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer ob : observers) {
            ob.update(FixedBaseNumber.this);
        }
    }
}
