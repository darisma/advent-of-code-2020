package advent;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day1.txt";
	
	public static void main (String[] args) {
		System.out.println("Answer to 1a is: " + solveAdvent1a());
		System.out.println("Answer to 1b is: " + solveAdvent1b());
	}
	
	private static int solveAdvent1a() {
		List<Integer> tavarat = ir.getStringStream(FN)
									.map(Integer::parseInt)
									.collect(Collectors.toList());
        return tavarat.stream()
        				.flatMap(a -> tavarat.stream()
        				.filter(b -> a + b == 2020L)
        				.map(b -> a * b))
        				.findAny()
        				.get();
	}
	
	private static int solveAdvent1b()  {
		List<Integer> tavarat = ir.getStringStream(FN)
									.map(Integer::parseInt)
									.collect(Collectors.toList());

        	for (int a : tavarat) {
        		for (int b : tavarat) {
        			for (int c : tavarat) {
        				if (a != b && b != c && a + b + c == 2020) {
        					return a*b*c;
        				}
        			}
        		}
        	}

        return 0;
	}
}
