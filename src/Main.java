import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
//import java.nio.charset;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        String text= readUsingScanner(fileName);
        StringBuffer buffer = new StringBuffer();
        String result;

        String removeComment = "(\\\"[^\\'].*?\\\"|print(?:ln|f)?\\(.*\\);)|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
        String removePrintComment = "(\"(?:[^\\\\\"]+|\\\\.)*\")|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";

        Pattern pattern = Pattern.compile(removeComment);
        Matcher matcher = pattern.matcher(text);
        String replacement;

        while (matcher.find()) {

            if (matcher.group(1) != null) {

                replacement = matcher.group().replaceAll(removePrintComment, "$1");
                matcher.appendReplacement(buffer, replacement);

            } else {

                matcher.appendReplacement(buffer, "");
            }
        }

        matcher.appendTail(buffer);
        result = buffer.toString();

        System.out.println(result);
    }

    private static String readUsingScanner(String fileName) throws IOException {   // читаем файл с помощью Scanner
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }
}