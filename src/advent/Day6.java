package advent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day6.txt";
	
	public static void main (String[] args) {
		System.out.println("Answer to 6a is: " + solveAdvent6a());
		System.out.println("Answer to 6b is: " + solveAdvent6b());
	}
	

	private static long solveAdvent6a() {
		List<String> rivit = ir.getStringStream(FN).collect(Collectors.toList());
		int laskuri = 0;
		
		ArrayList<String> answers = new ArrayList<>();
		for (String rivi: rivit) {
			if(rivi.length()>0) {
				for (char c : rivi.toCharArray()) {
					String s = String.valueOf(c);
					if(!answers.contains(s)) {
						answers.add(s);
					}
				}
			}else {
				laskuri = laskuri + answers.size();
				answers = new ArrayList<String>();
			}
		}
		
		laskuri = laskuri + answers.size();
		return laskuri;
	}
	
	private static long solveAdvent6b() {
		List<String> rivit = ir.getStringStream(FN).collect(Collectors.toList());
		int laskuri = 0;
		
		ArrayList<ArrayList<String>> group = new ArrayList<>();
		ArrayList<String> answers = new ArrayList<>();
		for (String rivi: rivit) {
			if(rivi.length()>0) {
				for (char c : rivi.toCharArray()) {
					String s = String.valueOf(c);
					if(!answers.contains(s)) {
						answers.add(s);
					}
				}
				group.add(answers); // yhden tyypin vastaukset
				answers = new ArrayList<String>();
			}else {
				// katotaan groupista m‰ts‰‰v‰t
				laskuri = laskuri + matsaaGroup(group);
				// nollataan group seuraavaa grouppia varten
				group = new ArrayList<ArrayList<String>>();
			}
		}

		laskuri = laskuri + matsaaGroup(group);
		return laskuri;
	}
	
	private static int matsaaGroup(ArrayList<ArrayList<String>> group) {
		ArrayList<String> lyhin = group.stream()
				.min(Comparator.comparing(ArrayList::size))
				.get();
		
		ArrayList<String> testing = group.stream()
				.reduce(lyhin, (subtotal, element) -> removeNonMatching(subtotal, element) );
		return testing.size();
	}
	
	private static ArrayList<String> removeNonMatching(ArrayList<String> subtotal, ArrayList<String> element) {
		ArrayList<String> stillLeft = new ArrayList<>();
		for (String s : subtotal) {
			if(element.contains(s)) {
				stillLeft.add(s);
			}
		}
		return stillLeft;
	}
}