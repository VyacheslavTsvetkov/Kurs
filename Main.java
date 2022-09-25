package kurs;

import java.io.IOException;
import java.util.Map;

public class Main {
    static String inputText = "src/kurs/inText.txt";
    static String inputVocabulary = "src/kurs/inVocabulary.txt";


    public static void main(String[] args) throws IOException {
        Map<String, String> createMap = kurs.Processing.PutFileToMap(inputVocabulary);
        kurs.Processing.readText(inputText);
        kurs.Processing.translation();
        kurs.Processing.printResult();
    }

}
