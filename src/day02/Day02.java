package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day02 {

    private int countSafe = 0;
    private int countSafeRemove = 0;
    private int failIndex;

    public static void main(String[] args) {
        new Day02();
    }

    public Day02() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day02/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<int[]> data = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] reportData = line.split(" ");
            int[] report = new int[reportData.length];
            for (int i = 0; i < reportData.length; i++) {
                report[i] = Integer.parseInt(reportData[i]);
            }
            data.add(report);
        }


        for (int[] report : data) {
            failIndex = -1;
            boolean safe = check(report);
            if (safe) {
                countSafe++;
                countSafeRemove++;
            } else {
                for (int i = 0; i < report.length; i++) {
                    if (check(remove(report, i))) {
                        countSafeRemove++;
                        break;
                    }
                }
            }
        }

        System.out.println(countSafe);
        System.out.println(countSafeRemove);
    }

    private int[] remove(int[] data, int index) {
        int[] newData = new int[data.length - 1];
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            if (i != index) {
                newData[j] = data[i];
                j++;
            }
        }

        return newData;
    }

    private boolean check(int[] report) {
        boolean increasing;

        if (report[0] < report[1] && Math.abs(report[0] - report[1]) <= 3) {
            increasing = true;
        } else if (report[0] > report[1] && Math.abs(report[0] - report[1]) <= 3) {
            increasing = false;
        } else {
            if (failIndex < 0) {
                failIndex = 0;
            }
            return false;
        }
        for (int i = 1; i < report.length - 1; i++) {
            if (increasing) {
                if (report[i] >= report[i + 1] || Math.abs(report[i] - report[i + 1]) > 3) {
                    if (failIndex < 0) {
                        failIndex = 0;
                    }
                    return false;
                }
            } else {
                if (report[i] <= report[i + 1] || Math.abs(report[i] - report[i + 1]) > 3) {
                    if (failIndex < 0) {
                        failIndex = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
