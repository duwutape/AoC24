package day16;

import java.io.FileNotFoundException;

public class Day16 {
    public static void main(String[] args) {
        new Day16();
    }

    public Day16() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
