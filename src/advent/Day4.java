package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day4.txt";
	static final List<String> requiredFields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" );
	
	public static void main (String[] args) {
		System.out.println("Answer to 4a is: " + solveAdvent4a());
		System.out.println("Answer to 4b is: " + solveAdvent4b());
	}
	
	private static long solveAdvent4a() {
		List<String> rivit = ir.getStringStream(FN).collect(Collectors.toList());
		List<HashMap<String, String>> passports = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> passport = new HashMap<String, String>();
		for (String rivi : rivit) {
			if(rivi.length()>0) {
				String[] fields = rivi.split(" ");
				for(String field : fields) {
					String[] keyvalues = field.split(":");
					passport.put(keyvalues[0], keyvalues[1]);
				}
			}else {
				passports.add(passport);
				passport = new HashMap<String, String>();
			}
			
		}
		passports.add(passport); 

		return validatePassports(passports);
	}
	
	private static long solveAdvent4b() {
		List<String> rivit = ir.getStringStream(FN).collect(Collectors.toList());
		List<HashMap<String, String>> passports = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> passport = new HashMap<String, String>();
		for (String rivi : rivit) {
			if(rivi.length()>0) {
				String[] fields = rivi.split(" ");
				for(String field : fields) {
					String[] keyvalues = field.split(":");
					passport.put(keyvalues[0], keyvalues[1]);
				}
			}else {
				passports.add(passport);
				passport = new HashMap<String, String>();
			}
			
		}
		passports.add(passport); 

		return validatePassportsB(passports);
	}
	

	public static int validatePassports(List<HashMap<String, String>> passports) {
		int laskuri = 0;
		for (HashMap<String, String> h : passports) {
			if(h.keySet().containsAll(requiredFields)){
				laskuri++;
			}
		}
		
		return laskuri;
	}

	
	public static int validatePassportsB(List<HashMap<String, String>> passports) {
		int laskuri = 0;
		for (HashMap<String, String> h : passports) {
			if(h.keySet().containsAll(requiredFields)){
				if(validateFields(h)) {
					laskuri++;
				}
			}
		}
		
		return laskuri;
	}
	
	public static boolean validateFields(HashMap<String, String> passport) {
		/*
		 *  byr (Birth Year) - four digits; at least 1920 and at most 2002.
			iyr (Issue Year) - four digits; at least 2010 and at most 2020.
			eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
			hgt (Height) - a number followed by either cm or in:
			If cm, the number must be at least 150 and at most 193.
			If in, the number must be at least 59 and at most 76.
			hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
			ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
			pid (Passport ID) - a nine-digit number, including leading zeroes.
			cid (Country ID) - ignored, missing or not.
		 */
		int byr = Integer.parseInt(passport.get("byr"));
		int iyr = Integer.parseInt(passport.get("iyr"));
		int eyr = Integer.parseInt(passport.get("eyr"));
		String hgt = passport.get("hgt");
		String hcl = passport.get("hcl");
		String ecl = passport.get("ecl");
		String pid = passport.get("pid");
		
		List<String> accptedEcls = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"); 
		
		if(byr < 1920 || byr > 2002) {
			return false;
		}
		
		if(iyr < 2010 || iyr > 2020) {
			return false;
		}
		
		if(eyr < 2020 || eyr > 2030) {
			return false;
		}
		
		if(!hgt.endsWith("cm") && !hgt.endsWith("in")) {
			return false;
		}
		
		if(hgt.endsWith("cm")) {
			if(Integer.parseInt(hgt.substring(0, hgt.indexOf("cm"))) < 150 ) {
				return false;
			}
			if(Integer.parseInt(hgt.substring(0, hgt.indexOf("cm"))) > 193) {
				return false;
			}
		}
		
		if(hgt.endsWith("in")) {
			if(Integer.parseInt(hgt.substring(0, hgt.indexOf("in"))) < 59 ) {
				return false;
			}
			if(Integer.parseInt(hgt.substring(0, hgt.indexOf("in"))) > 76) {
				return false;
			}
		}
		
		if(!hcl.startsWith("#")) {
			return false;
		}

		if(!hcl.substring(1, hcl.length()).matches("[a-f0-9]+")) {
			System.out.println(hcl.substring(1, hcl.length()));
			return false;
		}
		
		if(!accptedEcls.contains(ecl)) {
			return false;
		}
		
		if(pid.length() != 9) {
			return false;
		}
		
		if(!pid.matches("[0-9]+")) {
			return false;
		}
		
		return true;
	}
}
