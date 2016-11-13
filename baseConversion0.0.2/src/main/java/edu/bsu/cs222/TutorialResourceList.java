package edu.bsu.cs222;

import javafx.scene.image.Image;

import java.util.ArrayList;

class TutorialResourceList {

    public  ArrayList<Image> binaryToDecimalImages = new ArrayList<>();
    public ArrayList<Image> binaryToHexadecimalImages = new ArrayList<>();
    public ArrayList<Image> decimalToHexadecimalImages = new ArrayList<>();
    private Image currentResource;

    public ArrayList<Image> addImagesToBinaryToDecimalArray(){
        currentResource = (new Image(getClass().getResourceAsStream("/binaryToDecimalPage1.png")));
        binaryToDecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/binaryToDecimalExamplesPage2.png"));
        binaryToDecimalImages.add(currentResource);
        return binaryToDecimalImages;
    }

    public ArrayList<Image> addImagesToBinaryToHexadecimalArray(){
        currentResource = new Image(getClass().getResourceAsStream("/binaryToHexadecimalPage1.png"));
        binaryToHexadecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/binaryToHexadecimalExamplesPage2.png"));
        binaryToHexadecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/binaryToHexadecimalExamplesPage3.png"));
        binaryToHexadecimalImages.add(currentResource);
        return binaryToHexadecimalImages;
    }

    public ArrayList<Image> addImagesToDecimalToHexadecimalArray(){
        currentResource = new Image(getClass().getResourceAsStream("/decimalToHexadecimalPage1.png"));
        decimalToHexadecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/decimalToHexadecimalTablePage2.png"));
        decimalToHexadecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/decimalToHexadecimalExamplesPage3.png"));
        decimalToHexadecimalImages.add(currentResource);
        currentResource = new Image(getClass().getResourceAsStream("/decimalToHexadecimalReverseExamplesPage4.png"));
        decimalToHexadecimalImages.add(currentResource);
        return decimalToHexadecimalImages;
    }
}
