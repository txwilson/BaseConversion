package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class BaseConverterTest {

    private BaseConverter testCalculator = new BaseConverter();

    @Test
    public void parseFromBinaryTest(){
        int hold =testCalculator.parseFromBinary("0");
        Assert.assertEquals(0,hold);
        hold =testCalculator.parseFromBinary("01");
        Assert.assertEquals(1,hold);
        hold =testCalculator.parseFromBinary("111");
        Assert.assertEquals(7,hold);
        hold =testCalculator.parseFromBinary("000001");
        Assert.assertEquals(1,hold);
        hold =testCalculator.parseFromBinary("1100");
        Assert.assertEquals(12,hold);
    }

    @Test
    public void parseFromBinaryTestIncorrectValues() {
        int hold = testCalculator.parseFromBinary("D");
        Assert.assertEquals(0,hold);
        hold =testCalculator.parseFromBinary("z1");
        Assert.assertEquals(0,hold);
        hold = testCalculator.parseFromBinary("-5646");
        Assert.assertEquals(0,hold);
        hold = testCalculator.parseFromBinary("102");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromBinary("FX");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromBinary("-10100111");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromBinary("-101");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromBinary("1100111111111111111111111111111111111");
        Assert.assertEquals(-1, hold);
    }

    @Test
    public void parseFromHexTest(){
        int hold=testCalculator.parseFromHex("0");
        Assert.assertEquals(0,hold);
        hold=testCalculator.parseFromHex("f");
        Assert.assertEquals(15,hold);
        hold=testCalculator.parseFromHex("3D");
        Assert.assertEquals(61,hold);
        hold=testCalculator.parseFromHex("f7");
        Assert.assertEquals(247,hold);
        hold = testCalculator.parseFromHex("00F");
        Assert.assertEquals(15,hold);
        hold = testCalculator.parseFromHex("10");
        Assert.assertEquals(16, hold);
        hold = testCalculator.parseFromHex("ff");
        Assert.assertEquals(255, hold);
    }

    @Test
    public void parseFromHexTestIncorrectValues() {
        int hold = testCalculator.parseFromHex("abq");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromHex("12v");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromHex("Z<");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromHex("12/9h");
        Assert.assertEquals(-1, hold);
        hold = testCalculator.parseFromHex("01UI");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromHex("-101");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromHex("0000000000000");
        Assert.assertEquals(-1, hold);
    }

    @Test
    public void parseFromDecimalTest() {
        int hold = testCalculator.parseFromDecimal("0");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("365");
        Assert.assertEquals(365, hold);
        hold = testCalculator.parseFromDecimal("78900");
        Assert.assertEquals(78900, hold);
        hold = testCalculator.parseFromDecimal("1920");
        Assert.assertEquals(1920, hold);
        hold = testCalculator.parseFromDecimal("01");
        Assert.assertEquals(01, hold);
    }

    @Test
    public void parseFromDecimalTestIncorrectValues() {
        int hold = testCalculator.parseFromDecimal("a");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("z?/");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("+");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("GHv,>");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("....");
        Assert.assertEquals(0, hold);
        hold = testCalculator.parseFromDecimal("0123456789");
        Assert.assertEquals(-1, hold);
    }

    @Test
    public void convertToStringTest() {
        String hold=testCalculator.convertToString(10,2);
        Assert.assertEquals("1010",hold);
        hold=testCalculator.convertToString(0,2);
        Assert.assertEquals("0",hold);
        hold=testCalculator.convertToString(27,16);
        Assert.assertEquals("1b",hold);
        hold=testCalculator.convertToString(98,16);
        Assert.assertEquals("62",hold);
        hold=testCalculator.convertToString(22,10);
        Assert.assertEquals("22",hold);
    }
}