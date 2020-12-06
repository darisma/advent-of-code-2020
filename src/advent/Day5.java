package advent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

	static AdventInputReader ir = new AdventInputReader();
	static final String FN = "day5.txt";
	
	public static void main (String[] args) {
		System.out.println("Highest seat is: " + maxSeatId());
		System.out.println("Your seat is: " + yourSeatId());
	}

	private static int maxSeatId(){
		int max = ir.getStringStream(FN)
				.map(line -> solveSeat(line))
				.max(Comparator.naturalOrder())
				.get();
		return max;
	}
	
	private static int yourSeatId() {
		List <Integer> seatIds = ir.getStringStream(FN)
				.map(line -> solveSeat(line))
				.sorted()
				.collect(Collectors.toList());
		// löydä se, missä nousee 2:lla
		int seatId = ir.getStringStream(FN)
				.map(line -> solveSeat(line))
				.max(Comparator.reverseOrder())
				.get();

		for(Integer sId:seatIds) {
			if(sId>seatId+1) {
				// skipped one, return one less
				return sId-1;
			}else {
				seatId = sId;
			}
		}
		
		return 0;
	}
	
	private static int solveSeat(String line) {
		//FBFBBFFRLR
		int minRow = 0;
		int maxRow = 127;
		int minCol = 0;
		int maxCol = 7;
		
		char[] rowData = line.substring(0,7).toCharArray();
		char[] colData = line.substring(7,line.length()).toCharArray();
		
		for(char c:rowData) {
			if(c=="F".charAt(0)) {
				maxRow = getRow(maxRow, minRow, true);
			}
			if(c=="B".charAt(0)) {
				minRow = getRow(maxRow, minRow, false);
			}
		}
		
		for(char d: colData) {
			if(d=="L".charAt(0)) {
				maxCol = getRow(maxCol, minCol, true);
			}
			if(d=="R".charAt(0)) {
				minCol = getRow(maxCol, minCol, false);
			}
		}
		
		int[] result = new int[3];
		result[0] = minRow;
		result[1] = minCol;
		result[2] = getSeatId(minRow, minCol);

		return getSeatId(minRow, minCol);
	}
	
	private static int getSeatId(int row, int col) {
		return row*8+col;
	}
	
	private static int getRow(int maxRow, int minRow, boolean front) {
		if(front) {
			return maxRow-(maxRow-minRow)/2-(maxRow-minRow)%2;
		}
		return minRow=minRow+(maxRow-minRow)/2+(maxRow-minRow)%2;
	}
}
