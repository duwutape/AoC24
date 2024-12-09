package day14;

import java.io.FileNotFoundException;

public class Day14 {
    public static void main(String[] args) {
        new Day14();
    }

    public Day14() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
