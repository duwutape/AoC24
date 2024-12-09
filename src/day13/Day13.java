package day13;

import java.io.FileNotFoundException;

public class Day13 {
    public static void main(String[] args) {
        new Day13();
    }

    public Day13() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
