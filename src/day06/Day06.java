package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day06 {
    public Day06() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day06/input.txt");
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

        ArrayList<char[]> dataPart2 = copy(data);
        int x = 0;
        int y = 0;

        for (char[] line : data) {
            for (int i = 0; i < line.length; i++) {
                if (line[i] == '^') {
                    x = i;
                    y = data.indexOf(line);
                }
            }
        }

        int count = createPath(data, x, y);
        int countObstacles = part2(dataPart2, x, y);

        System.out.println(count);
        System.out.println(countObstacles);

    }

    private ArrayList<char[]> copy(ArrayList<char[]> input) {
        ArrayList<char[]> output = new ArrayList<>();
        for (char[] row : input) {
            char[] newRow = new char[row.length];
            for (int i = 0; i < row.length; i++) {
                newRow[i] = row[i];
            }
            output.add(newRow);
        }
        return output;
    }

    private int createPath(ArrayList<char[]> data, int x, int y) {
        boolean inBounds = true;
        int countX = 0;
        int step = 0;

        while (inBounds) {
            step++;
            if (step >= data.size()*data.get(0).length) {
                return -1;
            }

            char guard = data.get(y)[x];
            char dir = guard;
            boolean turn = false;
            boolean move = false;

            switch (guard) {
                case '^' -> {
                    if (y - 1 >= 0) {
                        if (data.get(y - 1)[x] == '.' || data.get(y - 1)[x] == 'X') {
                            move = true;
                            y -= 1;
                            countX++;
                        } else if (data.get(y - 1)[x] == '#') {
                            turn = true;
                        }
                    } else {
                        data.get(y)[x] = 'X';
                        countX++;
                        inBounds = false;
                    }

                }
                case '>' -> {
                    if (x + 1 < data.get(y).length) {
                        if (data.get(y)[x + 1] == '.' || data.get(y)[x + 1] == 'X') {
                            move = true;
                            x += 1;
                            countX++;
                        } else if (data.get(y)[x + 1] == '#') {
                            turn = true;
                        }
                    } else {
                        data.get(y)[x] = 'X';
                        countX++;
                        inBounds = false;
                    }

                }
                case 'v' -> {
                    if (y + 1 < data.size()) {
                        if (data.get(y + 1)[x] == '.' || data.get(y + 1)[x] == 'X') {
                            move = true;
                            y += 1;
                            countX++;
                        } else if (data.get(y + 1)[x] == '#') {
                            turn = true;
                        }
                    } else {
                        data.get(y)[x] = 'X';
                        countX++;
                        inBounds = false;

                    }
                }
                case '<' -> {
                    if (x - 1 >= 0) {
                        if (data.get(y)[x - 1] == '.' || data.get(y)[x - 1] == 'X') {
                            move = true;
                            x -= 1;
                            countX++;
                        } else if (data.get(y)[x - 1] == '#') {
                            turn = true;
                        }
                    } else {
                        data.get(y)[x] = 'X';
                        countX++;
                        inBounds = false;
                    }
                }

            }

            if (turn) {
                data = turn(data, x, y);
            }
            if (move) {
                data = move(data, x, y, dir);
            }
        }

        return countX;
    }

    private ArrayList<char[]> turn(ArrayList<char[]> data, int x, int y) {
        switch (data.get(y)[x]) {
            case '^' -> data.get(y)[x] = '>';
            case '>' -> data.get(y)[x] = 'v';
            case 'v' -> data.get(y)[x] = '<';
            case '<' -> data.get(y)[x] = '^';
        }
        return data;
    }

    private ArrayList<char[]> move(ArrayList<char[]> data, int x, int y, char dir) {
        data.get(y)[x] = dir;

        switch (dir) {
            case '^' -> {
                data.get(y + 1)[x] = 'X';
            }
            case 'v' -> {
                data.get(y - 1)[x] = 'X';
            }
            case '<' -> {
                data.get(y)[x + 1] = 'X';
            }
            case '>' -> {
                data.get(y)[x - 1] = 'X';
            }
        }

        return data;
    }

    private int part2(ArrayList<char[]> data, int x, int y) throws FileNotFoundException {
        File file = new File("src/day06/output.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<char[]> outputPart1 = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = new char[line.length()];
            for (int i = 0; i < line.length(); i++) {
                chars[i] = line.charAt(i);
            }
            outputPart1.add(chars);
        }

        int count = 0;

        for (int i = 0; i < outputPart1.size(); i++) {
            for (int j = 0; j < outputPart1.get(i).length; j++) {
                if (outputPart1.get(i)[j]=='X' && data.get(i)[j]=='.'){
                    ArrayList<char[]> copy = copy(data);
                    copy.get(i)[j] = '#';
                    if(createPath(copy,x,y) == -1){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
