package day22;

import java.io.FileNotFoundException;

public class Day22 {
    public static void main(String[] args) {
        new Day22();
    }

    public Day22() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
