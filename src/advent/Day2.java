package advent;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day2.txt";
	
	public static void main (String[] args) {
		System.out.println("Answer to 2a is: " + solveAdvent2a());
		System.out.println("Answer to 2b is: " + solveAdvent2b());
	}
	
	private static int solveAdvent2a() {
		List<Integer> osuneet = ir.getStringStream(FN)
								.map(e -> passwordCheck(e))
								.collect(Collectors.toList());
		return osuneet.stream().reduce(0, (a, b) -> a + b);
	}
	
	private static int solveAdvent2b() {
		List<Integer> osuneet = ir.getStringStream(FN)
								.map(e -> passwordCheck2(e))
								.collect(Collectors.toList());
		return osuneet.stream().reduce(0, (a, b) -> a + b);
	}

	private static int passwordCheck(String password) {
		//9-11 p: pppppppppxblp
		String[] pass = password.split(" ");
		int min = Integer.parseInt(pass[0].split("-")[0]);
		int max = Integer.parseInt(pass[0].split("-")[1]);
		char rule = pass[1].charAt(0);
		String pw = pass[2];

		long count = pw.chars().filter(ch -> ch == rule).count();
		if(min <= count && max >= count) {
			return 1;
		}
		return 0;
	}

	private static int passwordCheck2(String password) {
		//9-11 p: pppppppppxblp
		String[] pass = password.split(" ");
		int first = Integer.parseInt(pass[0].split("-")[0]);
		int second =  Integer.parseInt(pass[0].split("-")[1]);
		char rule = pass[1].charAt(0);
		String pw = pass[2];
		char aPwd = pw.charAt(first-1);
		char bPwd = pw.charAt(second-1);

		if(aPwd == rule && bPwd == rule) {
			return 0;
		}
		if(aPwd != rule && bPwd != rule) {
			return 0;
		}
		return 1;
	}
}
