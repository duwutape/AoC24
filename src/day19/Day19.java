package day19;

import java.io.FileNotFoundException;

public class Day19 {
    public static void main(String[] args) {
        new Day19();
    }

    public Day19() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
