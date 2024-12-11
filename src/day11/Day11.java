package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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

        HashMap<Long, Long> stones = new HashMap<>();
        while (scanner.hasNextLong()) {
            long num = scanner.nextLong();
            if (stones.containsKey(num)) {
                stones.put(num, stones.get(num) + 1);
            } else {
                stones.put(num, (long) 1);
            }
        }

        System.out.println(countLen(blink(stones, 25)));
        System.out.println(countLen(blink(stones, 75)));
    }

    private HashMap<Long, Long> blink(HashMap<Long, Long> stones, int amount) {
        for (int i = 0; i < amount; i++) {
            HashMap<Long, Long> newStones = new HashMap<>();
            for (long key : stones.keySet()) {
                if (key == (long) 0) {
                    if (newStones.containsKey((long) 1)) {
                        newStones.put((long) 1, newStones.get((long) 1) + stones.get(key));
                    } else {
                        newStones.put((long) 1, stones.get(key));
                    }
                } else if (String.valueOf(key).length() % 2 == 0) {
                    String value = String.valueOf(key);
                    long sub1 = Long.parseLong(value.substring(0, value.length() / 2));
                    long sub2 = Long.parseLong(value.substring(value.length() / 2));

                    if (newStones.containsKey(sub1)) {
                        newStones.put(sub1, newStones.get(sub1) + stones.get(key));
                    } else {
                        newStones.put(sub1, stones.get(key));
                    }
                    if (newStones.containsKey(sub2)) {
                        newStones.put(sub2, newStones.get(sub2) + stones.get(key));
                    } else {
                        newStones.put(sub2, stones.get(key));
                    }
                } else {
                    if (newStones.containsKey(key * 2024)) {
                        newStones.put(key * 2024, newStones.get(key * 2024) + stones.get(key));
                    } else {
                        newStones.put(key * 2024, stones.get(key));
                    }
                }
            }
            stones = newStones;
        }
        return stones;
    }

    private long countLen(HashMap<Long, Long> stones) {
        long count = 0;
        for (long key : stones.keySet()) {
            count += stones.get(key);
        }
        return count;
    }
}
