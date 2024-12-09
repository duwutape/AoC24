package day24;

import java.io.FileNotFoundException;

public class Day24 {
    public static void main(String[] args) {
        new Day24();
    }

    public Day24() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
