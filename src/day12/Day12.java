package day12;

import java.io.FileNotFoundException;

public class Day12 {
    public static void main(String[] args) {
        new Day12();
    }

    public Day12() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
