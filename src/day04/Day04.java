package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day04 {

    private int count1 = 0;
    private int count2 = 0;

    public static void main(String[] args) {
        new Day04();
    }

    public Day04() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day04/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<char[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = new char[line.length()];
            for (int i = 0; i < line.length(); i++) {
                chars[i] = line.charAt(i);
            }
            data.add(chars);
        }

        seek1(data);

        System.out.println(count1);
        System.out.println(count2);
    }

    private void seek1(ArrayList<char[]> data) {
        for (char[] chars : data) {
            for (int x = 0; x < chars.length; x++) {
                if (chars[x] == 'X') {
                    int y = data.indexOf(chars);
                    seek1(data, x, y, 'X');
                } else if (chars[x] == 'A') {
                    int y = data.indexOf(chars);
                    seek1(data, x, y, 'A');
                }
            }
        }
    }

    private void seek1(ArrayList<char[]> data, int x, int y, char currentChar) {
        if (currentChar == 'X') {
            for (int i = -1; i <= 1; i++) {
                if (inBounds(y + i, data)) {
                    for (int j = -1; j <= 1; j++) {
                        char[] chars = data.get(y + i);
                        if (inBounds(x + j, chars)) {
                            if (chars[x + j] == 'M') {
                                int newX = x + j;
                                int newY = y + i;

                                int xMod = newX - x;
                                int yMod = newY - y;

                                if (inBounds(newX + xMod, chars) && inBounds(newX + 2 * xMod, chars) && inBounds(newY + yMod, data) && inBounds(newY + 2 * yMod, data)) {
                                    if (data.get(newY + yMod)[newX + xMod] == 'A' && data.get(newY + 2 * yMod)[newX + 2 * xMod] == 'S') {
                                        count1++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (inBounds(x - 1, data.get(y)) && inBounds(x + 1, data.get(y)) && inBounds(y - 1, data) && inBounds(y + 1, data)) {
                if (((data.get(y - 1)[x - 1] == 'M' && data.get(y + 1)[x + 1] == 'S') || (data.get(y - 1)[x - 1] == 'S' && data.get(y + 1)[x + 1] == 'M')) &&
                        ((data.get(y - 1)[x + 1] == 'M' && data.get(y + 1)[x - 1] == 'S') || (data.get(y - 1)[x + 1] == 'S' && data.get(y + 1)[x - 1] == 'M'))) {
                    count2++;
                }
            }
        }
    }

    private boolean inBounds(int index, char[] chars) {
        if (index >= 0 && index < chars.length) {
            return true;
        } else {
            return false;
        }
    }

    private boolean inBounds(int index, ArrayList<char[]> data) {
        return index >= 0 && index < data.size();
    }
}
