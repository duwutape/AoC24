package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day09 {
    public static void main(String[] args) {
        new Day09();
    }

    public Day09() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day09/input.txt");
        Scanner scanner = new Scanner(input);

        boolean odd = true;
        int id = 0;

        ArrayList<Integer> disc = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < Integer.parseInt(str.charAt(i) + ""); j++) {
                    if (odd) {
                        disc.add(id);
                    } else {
                        disc.add(null);
                    }
                }
                if (odd) {
                    id++;
                }
                odd = !odd;
            }
        }

        System.out.println(checksum(defrac(disc)));
        System.out.println(checksum(defrac2(disc)));

    }

    private ArrayList<Integer> defrac(ArrayList<Integer> disc) {
        ArrayList<Integer> newDisc = new ArrayList<>();
        int last = disc.size() - 1;

        for (int i = 0; i < disc.size(); i++) {
            if (i <= last) {
                if (disc.get(i) != null) {
                    newDisc.add(disc.get(i));
                } else {
                    while (disc.get(last) == null) {
                        last--;
                    }
                    if (disc.get(last) != 0) {
                        newDisc.add(disc.get(last));
                        last--;
                    }
                }
            }
        }
        return newDisc;
    }

    private ArrayList<Integer> defrac2(ArrayList<Integer> disc) {
        int id = -1;
        int countId = 0;

        for (int i = disc.size() - 1; i > 0; i--) {
            if (disc.get(i) != null && id < 0) {
                countId++;
                id = disc.get(i);
            } else if (disc.get(i) != null && disc.get(i) == id) {
                countId++;
            } else if (disc.get(i) == null || disc.get(i) != id) {
                disc = searchFree(disc, countId, i+1);
                if (disc.get(i)==null){
                    id = -1;
                    countId = 0;
                } else {
                    id = disc.get(i);
                    countId = 1;
                }
            }
        }

        return disc;
    }

    private long checksum(ArrayList<Integer> data) {
        long check = 0;

        for (int i = 0; i < data.size(); i++) {
            long temp;
            if (data.get(i) != null){
                temp = (long) i * data.get(i);
                check += temp;
            }
        }
        return check;
    }

    private ArrayList<Integer> searchFree(ArrayList<Integer> disc, int amount, int index) {
        int countFree = 0;
        for (int i = 0; i < index; i++) {
            if(disc.get(i) == null) {
                countFree++;
            } else {
                countFree = 0;
            }
            if (countFree >= amount){
                return move(disc, amount, i-countFree+1, index);
            }
        }
        return disc;
    }

    private ArrayList<Integer> move(ArrayList<Integer> disc, int amount, int indexTo, int indexFrom){
        for (int i = 0; i < amount; i++) {
            disc.set(indexTo+i,disc.get(indexFrom+i));
            disc.set(indexFrom+i,null);
        }
        return disc;
    }
}
