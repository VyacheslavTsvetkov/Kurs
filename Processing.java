package kurs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Processing {
    private static final HashMap<String, String> dictionary = new HashMap<>();
    private static String text = "";
    private static String tmp = "";

    public static Map<String, String> PutFileToMap(String path) throws IOException {

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("-", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                dictionary.put(key, value);
            }
        }
        reader.close();
        return dictionary;
    }

    public static void readText(String path) {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> tmp += line + "\n");
            text = tmp.toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void translation() {

        StringBuilder source = new StringBuilder();
        List<String> listWords = new ArrayList<>();

        Stream.of(text).map(line -> line.split("\\s")).forEach(line -> {
            for (String str : line)
                listWords.add(str.replaceAll("\\p{Punct}", "").toLowerCase());
        });

            for (String str : listWords) {

            source.append(str);

            String tmp = checkKey (source.toString());
            String translation = dictionary.get(tmp);

            boolean boolCheck = dictionary.get(tmp) != null;
            if (boolCheck) {
                Pattern pattern = Pattern.compile("\\b" + tmp + "\\b");
                Matcher matcher = pattern.matcher(text);
                text = matcher.replaceFirst(translation);
            }
                source.delete(0, source.length());
        }
    }

        private static String checkKey (String sourceWord){
            String result = sourceWord;
                for (String line : sourceWord.split(" ")) {
                    result = line;
                    if (dictionary.containsKey(result))
                        break;
                }
                return result;
        }
        public static void printResult () {
            System.out.println("Source text" + "\n" + tmp + "\n" + "translated text" + "\n" + text);
        }

    }




