package edu.bsu.cs222;

class BaseConverter {

    private final int MAX_BINARY_LENGTH = 20;
    private final int BINARY_BASE = 2;
    private final int DECIMAL_BASE = 10;
    private final int HEX_BASE = 16;

    public int parseFromBinary(String binary) {
        if (binary.length() > MAX_BINARY_LENGTH) {
            return -1;
        }
        if (binary.matches("[01]+")){
            int hold = Integer.parseInt(binary, BINARY_BASE);
            hold = Integer.valueOf(Integer.toString(hold, DECIMAL_BASE));
            return hold;
        }
        return 0;
    }

    public int parseFromHex(String hex) {
        final int BYTE_LENGTH = 4;
        if (hex.length() > BYTE_LENGTH) {
            return -1;
        }
        hex = hex.toLowerCase();
        if (hex.matches("[a-f]+")) {
            int hold = Integer.parseInt(hex, HEX_BASE);
            hold = Integer.valueOf(Integer.toString(hold, DECIMAL_BASE));
            return hold;
        }
        if (hex.matches("[0-9]+")) {
            int hold = Integer.parseInt(hex, HEX_BASE);
            hold = Integer.valueOf(Integer.toString(hold, DECIMAL_BASE));
            return hold;
        }
        if (hex.matches("^(?=.*[a-f])(?=.*[0-9])[a-f0-9]+$")) {
            int hold = Integer.parseInt(hex, HEX_BASE);
            hold = Integer.valueOf(Integer.toString(hold, DECIMAL_BASE));
            return hold;
        } else {
            return 0;
        }
    }

    public int parseFromDecimal(String decimal) {
        final int TWO_BYTE_LENGTH = 8;
        if (decimal.length() > TWO_BYTE_LENGTH) {
            return -1;
        }
        if (decimal.matches("[0-9]+")) {
            return Integer.valueOf(decimal);
        } else {
            return 0;
        }
    }

    public String convertToString(int decimalValue, int base) {
        return Integer.toString(decimalValue,base);
    }
}
