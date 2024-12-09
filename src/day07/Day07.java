package day07;

import java.io.FileNotFoundException;

public class Day07 {
    public static void main(String[] args) {
        new Day07();
    }

    public Day07() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
