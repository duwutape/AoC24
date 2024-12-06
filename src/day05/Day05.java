package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day05 {

    private int count = 1177;

    public Day05() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day05/input.txt");
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

        int resCorrect = 0;
        int resIncorrect = 0;
        for (ArrayList<Integer> line : data) {
            count++;
            if (checkInstructions(instructions, line)) {
                resCorrect += line.get(line.size() / 2);
            } else {
                if (count == 1178) {
                    count++;
                    count--;
                }
                ArrayList<Integer> rearrangedLine = new ArrayList<>();
                rearrangedLine = rearrange(instructions, line);
                resIncorrect += rearrangedLine.get(rearrangedLine.size() / 2);
            }
        }

        System.out.println(resCorrect);
        System.out.println(resIncorrect);
    }


    private boolean checkInstructions(HashMap<Integer, ArrayList<Integer>> instructions, ArrayList<Integer> line) {
        for (int num : line) {
            if (instructions.containsKey(num)) {
                for (int value : instructions.get(num)) {
                    if (line.contains(value) && line.indexOf(value) < line.indexOf(num)) {

                    } else if (line.contains(value) && line.indexOf(value) > line.indexOf(num)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> rearrange(HashMap<Integer, ArrayList<Integer>> instructions, ArrayList<Integer> data) {
        ArrayList<Integer> newData = new ArrayList<>();

        for (int num : data) {
            if (!instructions.containsKey(num)) {
                newData.add(0, num);
            } else {
                int minIndex = -1;
                int maxIndex = newData.size();
                for (int instruction : instructions.get(num)) {
                    if (newData.contains(instruction)) {
                        minIndex = Math.max(minIndex, newData.indexOf(instruction));
                    }
                }
                for (int newNum : newData) {
                    if (instructions.get(newNum).contains(num)) {
                        maxIndex += Math.min(maxIndex, newData.indexOf(newNum));
                    }
                }
                if (minIndex + 1 <= newData.size() && minIndex+1 <= maxIndex) {
                    newData.add(minIndex + 1, num);
                } else if (maxIndex < newData.size()){
                    newData.add(num,maxIndex);
                } else {
                    newData.add(num);
                }
            }
        }

        return newData;
    }
}
