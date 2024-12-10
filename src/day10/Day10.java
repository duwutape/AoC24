package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        new Day10();
    }

    public Day10() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day10/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<int[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int[] ints = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                ints[i] = Integer.parseInt(line.charAt(i) + "");
            }
            data.add(ints);
        }

        int count = 0;
        int countP2 = 0;

        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.get(y).length; x++) {
                if (data.get(y)[x] == 0) {
                    count += (search(data, x, y, 0, 0, null, 1));
                    countP2 += (search(data, x, y, 0, 0, null, 2));
                }
            }
        }
        System.out.println(count);
        System.out.println(countP2);

    }

    private int search(ArrayList<int[]> data, int x, int y, int current, int count, ArrayList<int[]> coords, int part) {
        if (coords == null) {
            coords = new ArrayList<>();
        }

        if (current == 9) {
            if (part == 1) {
                boolean alreadyReached = false;
                for (int[] coord : coords) {
                    if (coord[0] == x && coord[1] == y) {
                        alreadyReached = true;
                        break;
                    }
                }
                if (!alreadyReached) {
                    count++;
                    coords.add(new int[]{x, y});
                }

            } else {
                count++;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0 -> {
                        if (x - 1 >= 0 && data.get(y)[x - 1] == current + 1) {
                            count = search(data, x - 1, y, current + 1, count, coords, part);
                        }
                    }
                    case 1 -> {
                        if (x + 1 < data.get(y).length && data.get(y)[x + 1] == current + 1) {
                            count = search(data, x + 1, y, current + 1, count, coords, part);
                        }
                    }
                    case 2 -> {
                        if (y - 1 >= 0 && data.get(y - 1)[x] == current + 1) {
                            count = search(data, x, y - 1, current + 1, count, coords, part);
                        }
                    }
                    case 3 -> {
                        if (y + 1 < data.size() && data.get(y + 1)[x] == current + 1) {
                            count = search(data, x, y + 1, current + 1, count, coords, part);
                        }
                    }
                }
            }
        }
        return count;
    }
}
