package day20;

import java.io.FileNotFoundException;

public class Day20 {
    public static void main(String[] args){
        new Day20();
    }

    public Day20() {
        try {
            solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void solve() throws FileNotFoundException {

    }
}
