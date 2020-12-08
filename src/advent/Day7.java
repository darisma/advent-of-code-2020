package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day7.txt";
	
	public static void main (String[] args) {
		System.out.println("Answer to 7a is: " + solveAdvent7a());
		System.out.println("Answer to 7b is: " + solveAdvent7b());
	}

	private static long solveAdvent7a() {
		List<String[]> rivit = ir.getStringStream(FN)
						.map(s -> s.replace("bags", "bag")
						.split("contain"))
						.collect(Collectors.toList());
		ArrayList<Bag> rules = new ArrayList<Bag>();
		ArrayList<String> bagNames = new ArrayList<>();
		for (String[] s : rivit) {
			List<String> rule = Arrays.asList(s[1].split(",")).stream()
					.map(e -> trimAmounts(e.trim()))
					.collect(Collectors.toList());
			Bag bag = new Bag(s[0].trim(), rule);
			rules.add(bag);
			bagNames.add(s[0].trim());
		}
		List<Bag> searchBag = Arrays.asList(new Bag("shiny gold bag"));

		List<String> bigList = new ArrayList<>();
		getParents(bigList, rules, searchBag, 0); 
		bigList = bigList.stream().distinct().collect(Collectors.toList());

		return bigList.size();
	
	}
	
	private static long solveAdvent7b() {
		List<String[]> rivit = ir.getStringStream(FN)
					.map(s -> s.replace("bags", "bag")
						.split("contain"))
					.collect(Collectors.toList());
		ArrayList<String> resultBags = new ArrayList<String>();
		ArrayList<Bag> rules = new ArrayList<Bag>();
		ArrayList<String> bagNames = new ArrayList<String>();
		for (String[] s : rivit) {
			List<String> rule = Arrays.asList(s[1].split(",")).stream()
					.map(e -> e.trim())
					.collect(Collectors.toList());;
			Bag bag = new Bag(s[0].trim(), rule);
			rules.add(bag);
			bagNames.add(s[0].trim());
		}
		//shiny gold bags contain 5 pale brown bags, 2 light red bags, 3 drab lime bags.
		Bag goldbag = rules.stream()
				.filter(b -> b.getBagName().equalsIgnoreCase("shiny gold bag"))
				.findFirst().get();
		
		calculateChildren(resultBags, rules, goldbag);
		return resultBags.size();
	
	}

	private static void calculateChildren(List<String> resultList, List<Bag> fullList, Bag bag) {
		for(Bag b : bag.getBags()) {
			Bag realBag =fullList.stream()
					.filter(f -> f.getBagName().equalsIgnoreCase(b.getBagName()))
					.findFirst().get();
			resultList.add(realBag.getBagName());
			calculateChildren(resultList, fullList, realBag);
		}
	}
	
	private static String trimAmounts(String singleRule) {
		if(singleRule.endsWith(".")) {
			singleRule = singleRule.substring(0,singleRule.length()-1);
		}
		if(Character.isDigit(singleRule.charAt(0))){
			return singleRule.substring(singleRule.indexOf(" ")+1, singleRule.length());
		}
		return singleRule;
	}
	
	private static int getParents(List<String> bigList, List<Bag> fullList, List<Bag> bags, int counter){
		List<Bag> resultBags = new ArrayList<>();
		for(Bag b : fullList) {
			for(Bag r : bags) {
				if(b.containsBagName(r.getBagName())) {
					if(resultBags.stream()
							.noneMatch(c -> c.getBagName().equalsIgnoreCase(b.getBagName()))) {
						resultBags.add(b);
						bigList.add(b.getBagName());
						counter = counter +1;
					}
				}
			}
		}
		if(!resultBags.isEmpty()) {
			getParents(bigList, fullList, resultBags, counter);
		}
		return counter;
	}
}