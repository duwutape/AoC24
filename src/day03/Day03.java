package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {
        new Day03();
    }

    public Day03() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

        int resMult = mult(removeInstructions(getMatches("mul\\([0-9]{1,3},[0-9]{1,3}\\)")));
        int resMultDo = mult(removeInstructions(getMatches("(mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\))")));


        System.out.println(resMult);
        System.out.println(resMultDo);
    }

    private ArrayList<String> getMatches(String regex) throws FileNotFoundException {
        File input = new File("src/day03/input.txt");
        Scanner scanner = new Scanner(input);

        Pattern pattern = Pattern.compile(regex);

        ArrayList<String> matches = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }

    private ArrayList<int[]> removeInstructions(ArrayList<String> data) {
        String strDo = "do()";
        String strDont = "don't()";

        ArrayList<int[]> newData = new ArrayList<>();
        boolean copy = true;
        for (String string : data) {
            if (string.equals(strDont)) {
                copy = false;
            } else if (string.equals(strDo)) {
                copy = true;
            } else if (copy) {
                StringBuilder stringBuilder = new StringBuilder(string);
                stringBuilder.delete(0, 4);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                String strData = stringBuilder.toString();
                String[] strNumbers = strData.split(",");
                newData.add(new int[]{Integer.parseInt(strNumbers[0]), Integer.parseInt(strNumbers[1])});
            }
        }

        return newData;
    }

    private int mult(ArrayList<int[]> data) {
        int res = 0;
        for (int[] pair : data) {
            res += pair[0] * pair[1];
        }
        return res;
    }
}
