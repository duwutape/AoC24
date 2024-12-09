package day23;

import java.io.FileNotFoundException;

public class Day23 {
    public static void main(String[] args) {
        new Day23();
    }

    public Day23() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
