package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) {
        new Day07();
    }

    public Day07() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Long> results = new ArrayList<>();
    ArrayList<long[]> allOperants = new ArrayList<>();

    private void solve() throws FileNotFoundException {
        File input = new File("src/day07/input.txt");
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(":");
            results.add(Long.parseLong(splitLine[0]));

            String[] splitLineOperants = splitLine[1].split(" ");
            long[] operants = new long[splitLineOperants.length - 1];

            for (int i = 0; i < operants.length; i++) {
                operants[i] = Integer.parseInt(splitLineOperants[i + 1]);
            }
            allOperants.add(operants);
        }

        int baseP1 = 2;
        int baseP2 = 3;

        System.out.println(checkPossible(results, allOperants,baseP1));
        System.out.println(checkPossible(results, allOperants,baseP2));
    }

    private long checkPossible(ArrayList<Long> results, ArrayList<long[]> allOperants, int base) {
        long res = 0;

        for (int i = 0; i < results.size(); i++) {
            ArrayList<ArrayList<Integer>> possibilities = createBin(allOperants.get(i),base);
            for (ArrayList<Integer> possibility : possibilities) {
                if (results.get(i).equals(calc(allOperants.get(i), possibility))) {
                    res += results.get(i);
                    break;
                }
            }
        }
        return res;
    }

    private ArrayList<ArrayList<Integer>> createBin(long[] operants, int base) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();

        for (int i = 0; i < Math.pow(base, operants.length - 1); i++) {
            int temp = i;
            ArrayList<Integer> binary = new ArrayList<>();
            while (temp > 0) {
                int remainder = temp % base;
                binary.add(0, remainder);
                temp = temp / base;
            }
            if (binary.size() == 0) {
                binary.add(0);
            }
            while (binary.size() < operants.length - 1) {
                binary.add(0, 0);
            }
            out.add(binary);
        }
        return out;
    }

    private long calc(long[] operants, ArrayList<Integer> possibility) {
        long res = operants[0];

        for (int i = 0; i < possibility.size(); i++) {
            switch (possibility.get(i)) {
                case 0 -> res = res * operants[i + 1];
                case 1 -> res += operants[i + 1];
                case 2 -> res = Long.parseLong("" + res + operants[i + 1]);
            }
        }
        return res;
    }
}
