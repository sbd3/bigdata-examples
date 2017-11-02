package geeksforgeeks.basic;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.Reader;

class SolutionIter implements Iterable<Integer> {

    private BufferedReader buffReader;

    public SolutionIter(Reader inp) {
        buffReader = new BufferedReader(inp);
    }

    public Iterator<Integer> iterator() {
        return buffReader.lines().filter(line -> {
            try {
                line = line.trim();
                if(line.startsWith("+"))
                    line = line.substring(1, line.length() -1);
                int val = Integer.parseInt(line);
                if(val != Long.parseLong(line)) {
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }).map(line -> Integer.parseInt(line.trim())).iterator();
    }
}
