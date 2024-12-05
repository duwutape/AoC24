package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day05 {
    public Day05() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day05/test.txt");
        Scanner scanner = new Scanner(input);
        boolean readInstructions = true;

        HashMap<Integer, ArrayList<Integer>> instructions = new HashMap<>();
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (readInstructions) {
                if (line.isEmpty()) {
                    readInstructions = false;
                } else {
                    String[] nums = line.split("\\|");
                    int key = Integer.parseInt(nums[1]);
                    int value = Integer.parseInt(nums[0]);

                    ArrayList<Integer> arrayList = new ArrayList<>();
                    if (instructions.containsKey(key)) {
                        arrayList.addAll(instructions.get(key));
                    }
                    arrayList.add(value);
                    instructions.put(key, arrayList);
                }
            } else {

                String[] strNumbers = line.split(",");
                ArrayList<Integer> nums = new ArrayList<>();
                for (String string : strNumbers) {
                    nums.add(Integer.parseInt(string));
                }
                data.add(nums);


            }
        }

        int count = 0;
        for (ArrayList<Integer> line : data) {
            if (checkInstructions(instructions, line)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private boolean checkInstructions(HashMap<Integer, ArrayList<Integer>> instructions, ArrayList<Integer> line) {
        for (int num : line) {
            if (!instructions.containsKey(num)) {
                return true;
            } else {
                ArrayList<Integer> values = instructions.get(num);
                for (int value : values){
                    if (!line.contains(value)){
                        return true;
                    } else {
                        checkInstructions()
                    }
                }
                if ()


                ArrayList<Integer> subLine = new ArrayList<>();
                for (int i = 0; i < line.indexOf(num); i++) {
                    subLine.add(line.get(i));
                }
                ArrayList<Integer> checkKeys = instructions.get(num);
                return checkInstructions(instructions,checkKeys, subLine);
            }
        }
        return false;
    }

    private boolean checkInstructions(HashMap<Integer, ArrayList<Integer>> instructions, ArrayList<Integer> keys, ArrayList<Integer> line) {
        for (int key : keys) {
            if(line.contains(key)){
                ArrayList<Integer> subLine = new ArrayList<>();
                for (int i = 0; i < line.indexOf(key); i++) {
                    subLine.add(line.get(i));
                }
                ArrayList<Integer> checkKeys = instructions.get(key);
                return checkInstructions(instructions,checkKeys, subLine);
            } else {
                return false;
            }
        }
        return false;
    }
}
