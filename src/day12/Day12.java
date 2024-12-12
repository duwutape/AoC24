package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) {
        new Day12();
    }

    private ArrayList<int[]> alreadyChecked = new ArrayList<>();

    public Day12() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day12/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<char[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            data.add(chars);
        }

        getPrice(data);
    }

    private void getPrice(ArrayList<char[]> field) {
        int[] price = new int[2];

        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).length; x++) {
                boolean already = false;
                for (int[] coords : alreadyChecked) {
                    if (coords[0] == x && coords[1] == y) {
                        already = true;
                        break;
                    }
                }
                if (!already) {
                    int[] temp = calc(search(field, x, y, field.get(y)[x], null));
                    price[0] += temp[0];
                    price[1] += temp[1];
                }
            }
        }
        System.out.println(price[0]);
        System.out.println(price[1]);
    }

    private int[] search(ArrayList<char[]> field, int x, int y, char currentChar, int[] data) {
        if (data == null) {
            data = new int[3];
        }

        boolean already = false;
        for (int[] coords : alreadyChecked) {
            if (coords[0] == x && coords[1] == y) {
                already = true;
                break;
            }
        }

        if (!already) {
            data[0]++;
            alreadyChecked.add(new int[]{x, y});
            int neighbors = 0;


            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0 -> {
                        if (x - 1 >= 0 && field.get(y)[x - 1] == currentChar) {
                            neighbors++;
                            data = search(field, x - 1, y, currentChar, data);
                        } else {
                            data[1]++;
                        }
                    }
                    case 1 -> {
                        if (x + 1 < field.get(y).length && field.get(y)[x + 1] == currentChar) {
                            neighbors++;
                            data = search(field, x + 1, y, currentChar, data);
                        } else {
                            data[1]++;
                        }
                    }
                    case 2 -> {
                        if (y - 1 >= 0 && field.get(y - 1)[x] == currentChar) {
                            neighbors++;
                            data = search(field, x, y - 1, currentChar, data);
                        } else {
                            data[1]++;
                        }
                    }
                    case 3 -> {
                        if (y + 1 < field.size() && field.get(y + 1)[x] == currentChar) {
                            neighbors++;
                            data = search(field, x, y + 1, currentChar, data);
                        } else {
                            data[1]++;
                        }
                    }
                }
            }

            switch (neighbors) {
                case 0 -> data[2] += 4;
                case 1 -> data[2] += 2;
                case 2 -> {
                    for (int j = 0; j < 4; j++) {
                        int x1 = 0;
                        int x2 = 0;
                        int y1 = 0;
                        int y2 = 0;

                        switch (j) {
                            case 0 -> {
                                x1 = x;
                                y1 = y - 1;
                                x2 = x + 1;
                                y2 = y;
                            }
                            case 1 -> {
                                x1 = x;
                                y1 = y - 1;
                                x2 = x - 1;
                                y2 = y;
                            }
                            case 2 -> {
                                x1 = x;
                                y1 = y + 1;
                                x2 = x - 1;
                                y2 = y;
                            }
                            case 3 -> {
                                x1 = x;
                                y1 = y + 1;
                                x2 = x + 1;
                                y2 = y;
                            }
                        }
                        if (inBounds(x1, y1, field) && inBounds(x2, y2, field) && field.get(y1)[x1] == currentChar && field.get(y2)[x2] == currentChar) {
                            data[2] += 1;
                            if (field.get(y1)[x2] != currentChar) {
                                data[2] += 1;
                            }
                            break;
                        }
                    }
                }
                case 3 -> {
                    for (int j = 0; j < 4; j++) {
                        int x1 = 0;
                        int x2 = 0;
                        int y1 = 0;
                        int y2 = 0;

                        switch (j) {
                            case 0 -> {
                                x1 = x;
                                y1 = y - 1;
                                x2 = x + 1;
                                y2 = y;
                            }
                            case 1 -> {
                                x1 = x;
                                y1 = y - 1;
                                x2 = x - 1;
                                y2 = y;
                            }
                            case 2 -> {
                                x1 = x;
                                y1 = y + 1;
                                x2 = x - 1;
                                y2 = y;
                            }
                            case 3 -> {
                                x1 = x;
                                y1 = y + 1;
                                x2 = x + 1;
                                y2 = y;
                            }
                        }
                        if (inBounds(x1, y1, field) && inBounds(x2, y2, field) && field.get(y1)[x1] == currentChar && field.get(y2)[x2] == currentChar && field.get(y1)[x2] != currentChar) {
                            data[2] += 1;
                        }
                    }
                }
                case 4 -> {
                    if (inBounds(x - 1, y - 1, field) && field.get(y - 1)[x - 1] != currentChar) {
                        data[2] += 1;
                    }
                    if (inBounds(x + 1, y - 1, field) && field.get(y - 1)[x + 1] != currentChar) {
                        data[2] += 1;
                    }
                    if (inBounds(x - 1, y + 1, field) && field.get(y + 1)[x - 1] != currentChar) {
                        data[2] += 1;
                    }
                    if (inBounds(x + 1, y + 1, field) && field.get(y + 1)[x + 1] != currentChar) {
                        data[2] += 1;
                    }
                }
            }
        }
        return data;
    }

    private int[] calc(int[] data) {
        return new int[]{data[0] * data[1], data[0] * data[2]};
    }

    private boolean inBounds(int x, int y, ArrayList<char[]> field) {
        return y >= 0 && y < field.size() && x >= 0 && x < field.get(y).length;
    }
}
