package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        new Day01();
    }

    public Day01() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day01/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" {3}");
            left.add(Integer.parseInt(data[0]));
            right.add(Integer.parseInt(data[1]));

        }

        Collections.sort(left);
        Collections.sort(right);

        int sum_abs = 0;
        int sum_sim = 0;
        for (int numLeft : left) {
            sum_abs += Math.abs((numLeft - right.get(left.indexOf(numLeft))));

            int count = 0;
            for (int numRight : right) {
                if (numLeft == numRight) {
                    count++;
                }
            }
            sum_sim += numLeft * count;
        }

        System.out.println(sum_abs);
        System.out.println(sum_sim);

    }
}



