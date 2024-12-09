package day18;

import java.io.FileNotFoundException;

public class Day18 {
    public static void main(String[] args) {
        new Day18();
    }

    public Day18() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
