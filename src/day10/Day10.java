package day10;

import java.io.FileNotFoundException;

public class Day10 {
    public static void main(String[] args) {
        new Day10();
    }

    public Day10() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
