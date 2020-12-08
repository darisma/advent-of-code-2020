package advent;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day3.txt";
	
	public static void main (String[] args) {
		System.out.println("Answer to 3a is: " + solveAdvent3a(3, 1));
		System.out.println("Answer to 3b is: " + solveAdvent3a(1, 1) *
				solveAdvent3a(3, 1) * solveAdvent3a(5, 1) *
				solveAdvent3a(7, 1) * solveAdvent3a(1, 2));
	}
	
	private static long solveAdvent3a(int right, int down) {
		List<char[]> karttaRivit = ir.getStringStream(FN).
				map(e -> e.toCharArray())
				.collect(Collectors.toList());
		int width = karttaRivit.get(0).length;
		int height = karttaRivit.size();
		int laskuri= 0;
		
		// loop for each row
		for(int i = 0; i < height; i=i+down) {
			int j = (i * right/down)%width;
			if(karttaRivit.get(i)[j] == "#".charAt(0)) {
				laskuri ++;
			}
		}
		
		return laskuri;
	}
	
}
