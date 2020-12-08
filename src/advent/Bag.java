package advent;


import java.util.ArrayList;
import java.util.List;

public class Bag {

	private String bagName;
	private ArrayList<Bag> bags;
	
	public Bag(String bagName) {
		this.bagName = bagName;
		this.bags = new ArrayList<Bag>();
	}
	
	public Bag(String bagName, List<String> rule) {
		this.bagName = bagName;
		this.bags = new ArrayList<Bag>();
		for(String r: rule) {
			if(!r.contains("no other bag")) {
				int amount = 1;
				if(Character.isDigit(r.charAt(0))){
					amount = Integer.parseInt(r.substring(0,r.indexOf(" ")));
				}
				for(int i = 0; i<amount;i++) {
					bags.add(new Bag(trimAmounts(r)));
				}
			}
		}
	}

	public String getBagName() {
		return bagName;
	}
	public void setBagName(String bagName) {
		this.bagName = bagName;
	}
	public List<Bag> getBags() {
		return bags;
	}
	public void setBags(ArrayList<Bag> bags) {
		this.bags = bags;
	}

	public boolean containsBagName(String bagName) {
		return bags.stream().anyMatch(b -> b.getBagName().equalsIgnoreCase(bagName));
	}
	
	@Override
	public String toString() {
		return "Bag [bagName=" + bagName + ", bags=" + bags + "]";
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
}
