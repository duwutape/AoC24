package day11;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {
        new Day11();
    }

    public Day11() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day11/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<ArrayList<Long>> stones = new ArrayList<>();
        while (scanner.hasNextInt()){
            ArrayList<Long> stone = new ArrayList<>();
            stone.add((long)scanner.nextInt());
            stones.add(stone);
        }

        System.out.println(countLen(blink(stones,25)));
        System.out.println(countLen(blink(stones,75)));
    }

    private ArrayList<ArrayList<Long>> blink(ArrayList<ArrayList<Long>> allStones, int amount){
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < allStones.size(); j++) {
                ArrayList<Long> newStones = new ArrayList<>();
                ArrayList<Long> stones = allStones.get(j);
                for (long stone : stones) {
                    if (stone == 0) {
                        newStones.add((long) 1);
                    } else if (String.valueOf(stone).length() % 2 == 0) {
                        String value = String.valueOf(stone);
                        newStones.add(Long.parseLong(value.substring(0, value.length() / 2)));
                        newStones.add(Long.parseLong(value.substring(value.length() / 2)));
                    } else {
                        newStones.add((stone * 2024));
                    }
                }
                System.out.println(i);
                allStones.remove(j);
                allStones.add(j,newStones);
            }

        }
        return allStones;
    }

    private int countLen(ArrayList<ArrayList<Long>> allStones){
        int count = 0;
        for (ArrayList<Long> stones : allStones) {
            count += stones.size();
        }
        return count;
    }
}
