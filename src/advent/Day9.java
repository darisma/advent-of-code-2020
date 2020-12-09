package advent;

import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day9.txt";
	
	public static void main (String[] args) {
		Day9 day9 = new Day9();
		System.out.println("Answer to 9a is: " + day9.solveAdvent9a());
		System.out.println("Answer to 9b is: " + day9.solveAdvent9b());
	}

	private long solveAdvent9a() {
		List<Long> rivit = ir.getStringStream(FN)
						.map(Long::parseLong)
						.collect(Collectors.toList());
		
		for (int i=25; i < rivit.size(); i++) {
			if(!loytyykoTekijat(rivit.subList(i-25, i), rivit.get(i)))
				return rivit.get(i);
		}
		
		return 0;
	}
	
	boolean loytyykoTekijat(List<Long> previous25, Long next) {
        return previous25.stream()
				.flatMap(a -> previous25.stream()
				.filter(b -> a + b == next && !a.equals(b)))
				.findAny().isPresent();
 	}
	
	private long solveAdvent9b() {

		Long sum = 133015568L;
		List<Long> rivit = ir.getStringStream(FN)
				.map(Long::parseLong)
				.collect(Collectors.toList());
		
		for(int i=0; i < rivit.size(); i++) {
			Long acc = 0L;
			for(int j = i; j < rivit.size(); j++) {
				acc = acc + rivit.get(j);
				if( acc.equals(sum)) {
					long pienin = rivit.subList(i, j).stream().min(Long::compare).get();
					long suurin = rivit.subList(i, j).stream().max(Long::compare).get();
					return pienin + suurin;
				}
				if( acc > sum) {
					break;
				}
			}
		}
		
		return 0L;
	}
}