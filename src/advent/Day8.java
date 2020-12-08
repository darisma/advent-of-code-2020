package advent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day8.txt";
	public Long accumulator = 0L;
	public Integer index = 0;
	public Integer prevIndex = 0;
	
	public static void main (String[] args) {
		Day8 day8 = new Day8();
		System.out.println("Answer to 8a is: " + day8.solveAdvent8a());
		System.out.println("Answer to 8b is: " + day8.solveAdvent8b());
	}

	private long solveAdvent8a() {
		List<String[]> rivit = ir.getStringStream(FN)
						.map(s -> s.split(" "))
						.collect(Collectors.toList());
		
		ArrayList<Rule> rules = new ArrayList<>();

		for (String[] s : rivit) {
			Rule rule = new Rule(s[0], Integer.parseInt(s[1]));
			rules.add(rule);
		}

		boolean infiniteTriggered = false;

		while(!infiniteTriggered) {
			infiniteTriggered = processLineItem(rules);
		}
		
		return accumulator;
	
	}
	
	private boolean processLineItem(List<Rule> rules) {
		Rule rule = rules.get(index);
		if (rule.isHasRun()) {
			return true;
		}
		
		if(rule.getOperation().equals("nop")) {
			index = index + 1;
		}
		if(rule.getOperation().equals("acc")) {
			accumulator = accumulator + rule.getValue();
			index = index + 1;
		}
		if(rule.getOperation().equals("jmp")) {
			index = index + rule.getValue();
		}
		
		rule.setHasRun(true);
		
		return false;
	}
	
	private long solveAdvent8b() {
		index = 0;
		accumulator = 0L;
		List<String[]> rivit = ir.getStringStream(FN)
				.map(s -> s.split(" "))
				.collect(Collectors.toList());

		ArrayList<Rule> rules = new ArrayList<>();
		
		for (String[] s : rivit) {
			Rule rule = new Rule(s[0], Integer.parseInt(s[1]));
			rules.add(rule);
		}
		
		List<Integer> nopJumps = new ArrayList<>();
		
		for (int i = 0; i< rules.size(); i++) {
			if(rules.get(i).getOperation().equalsIgnoreCase("jmp") || rules.get(i).getOperation().equalsIgnoreCase("nop")) {
				nopJumps.add(i);
			}
		}

		boolean infiniteTriggered = false;
		
		while(index < rules.size()) {

			for(Integer i : nopJumps) {
				rules = new ArrayList<>();		
				for (String[] s : rivit) {
					Rule rule = new Rule(s[0], Integer.parseInt(s[1]));
					rules.add(rule);
				}
				
				rules.get(i).switchOp();
				while(!infiniteTriggered && index < rules.size() ) {
					infiniteTriggered = processLineItem(rules);
				}
				rules.get(i).switchOp();
				if(infiniteTriggered) {
					index = 0;
					accumulator = 0L;
					infiniteTriggered = false;
				}
			}
		}
		
		return accumulator;
	}



}