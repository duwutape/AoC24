package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day04 {

    private int count;

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
            char[] chars = new char[line.length() - 1];
            for (int i = 0; i < line.length() - 1; i++) {
                chars[i] = line.charAt(i);
            }
            data.add(chars);
        }
        count = 0;
        seek(data);

        System.out.println(count);
    }

    private void seek(ArrayList<char[]> data) {
        for (char[] chars : data) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'X') {
                    int x = i;
                    int y = data.indexOf(chars);
                    seek(data, x, y, 'X');
                }
            }
        }
    }

    private void seek(ArrayList<char[]> data, int x, int y, char currentChar) {
        for (int i = -1; i <= 1; i++) {
            if (y + i >= 0 && y + i <= data.size()-1) {
                for (int j = -1; j <= 1; j++) {
                    char[] chars = data.get(y + i);
                    if (x + j>= 0 && x+ j <= chars.length - 1) {
                        if (currentChar == 'X' && chars[x + j] == 'M') {
                            int newX = x+j;
                            int newY = y+i;

                            if ()
                            seek(data, x + j, y + i, 'M');
                        } else if (currentChar == 'M' && chars[x + j] == 'A') {
                            seek(data, x + j, y + i, 'A');
                        } else if (currentChar == 'A' && chars[x + j] == 'S') {
                            count++;
                        }
                    }
                }
            }
        }
    }
}

