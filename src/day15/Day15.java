package day15;

import java.io.FileNotFoundException;

public class Day15 {
    public static void main(String[] args) {
        new Day15();
    }

    public Day15() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
