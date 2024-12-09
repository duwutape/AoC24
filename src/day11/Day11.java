package day11;

import java.io.FileNotFoundException;

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

    }
}
