package day08;

import java.io.FileNotFoundException;

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

    }
}
