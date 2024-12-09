package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {
    public static void main(String[] args) {
        new Day08();
    }

    public Day08() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {
        File input = new File("src/day08/input.txt");
        Scanner scanner = new Scanner(input);

        ArrayList<char[]> map = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = new char[line.length()];
            for (int i = 0; i < line.length(); i++) {
                chars[i] = line.charAt(i);
            }
            map.add(chars);
        }

        ArrayList<Character> frequencies = getFrequencies(map);
        ArrayList<ArrayList<int[]>> coords = getCoords(map, frequencies);

        System.out.println(createAntinodes(copy(map), coords, false));
        System.out.println(createAntinodes(copy(map), coords, true));
    }

    private ArrayList<Character> getFrequencies(ArrayList<char[]> map) {
        ArrayList<Character> frequencies = new ArrayList<>();
        for (char[] row : map) {
            for (char c : row) {
                if (c != '.' && !frequencies.contains(c)) {
                    frequencies.add(c);
                }
            }
        }
        return frequencies;
    }

    private ArrayList<ArrayList<int[]>> getCoords(ArrayList<char[]> map, ArrayList<Character> frequencies) {
        ArrayList<ArrayList<int[]>> allCords = new ArrayList<>();

        for (char frequency : frequencies) {
            ArrayList<int[]> coords = new ArrayList<>();
            for (int y = 0; y < map.size(); y++) {
                for (int x = 0; x < map.get(y).length; x++) {
                    if (map.get(y)[x] == frequency) {
                        coords.add(new int[]{x, y});
                    }
                }
            }
            allCords.add(coords);
        }
        return allCords;
    }

    private int createAntinodes(ArrayList<char[]> map, ArrayList<ArrayList<int[]>> allCoords, boolean resonance) {
        int count = 0;

        for (ArrayList<int[]> coords : allCoords) {
            for (int i = 0; i < coords.size() - 1; i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    int x1 = coords.get(i)[0];
                    int y1 = coords.get(i)[1];
                    int x2 = coords.get(j)[0];
                    int y2 = coords.get(j)[1];

                    int diffX = calcDiff(x1, x2);
                    int diffY = calcDiff(y1, y2);
                    if(resonance){
                        if (map.get(y1)[x1] != '#'){
                            map.get(y1)[x1] = '#';
                            count++;
                        }
                        if (map.get(y2)[x2] != '#'){
                            map.get(y2)[x2] = '#';
                            count++;
                        }

                        while (x1 - diffX >= 0 && x1 - diffX < map.get(i).length && y1 - diffY >= 0 && y1 - diffY < map.size()){
                            if( map.get(y1-diffY)[x1-diffX] != '#'){
                                map.get(y1 - diffY)[x1 - diffX] = '#';
                                count++;
                            }
                            x1 = x1-diffX;
                            y1 = y1-diffY;
                        }
                        while (x2 + diffX >= 0 && x2 + diffX < map.get(i).length && y2 + diffY >= 0 && y2 + diffY < map.size()) {
                            if(map.get(y2+diffY)[x2+diffX] != '#'){
                                map.get(y2 + diffY)[x2 + diffX] = '#';
                                count++;
                            }
                            x2 = x2+diffX;
                            y2 = y2+diffY;
                        }
                    } else {
                        if (x1 - diffX >= 0 && x1 - diffX < map.get(i).length && y1 - diffY >= 0 && y1 - diffY < map.size() && map.get(y1-diffY)[x1-diffX] != '#') {
                            map.get(y1 - diffY)[x1 - diffX] = '#';
                            count++;
                        }
                        if (x2 + diffX >= 0 && x2 + diffX < map.get(i).length && y2 + diffY >= 0 && y2 + diffY < map.size() && map.get(y2+diffY)[x2+diffX] != '#') {
                            map.get(y2 + diffY)[x2 + diffX] = '#';
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private int calcDiff(int coord1, int coord2) {
        return coord2 - coord1;
    }

    private ArrayList<char[]> copy(ArrayList<char[]> input) {
        ArrayList<char[]> output = new ArrayList<>();
        for (char[] row : input) {
            char[] newRow = new char[row.length];
            for (int i = 0; i < row.length; i++) {
                newRow[i] = row[i];
            }
            output.add(newRow);
        }
        return output;
    }
}
