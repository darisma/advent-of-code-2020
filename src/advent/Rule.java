package advent;

public class Rule {

	private String operation;
	private int value;
	private int previousOp;
	private boolean hasRun = false;
	private boolean changed = false;
	
	public Rule (String op, int val) {
		this.operation = op;
		this.value = val;

	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isHasRun() {
		return hasRun;
	}
	public void setHasRun(boolean hasRun) {
		this.hasRun = hasRun;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public int getPreviousOp() {
		return previousOp;
	}

	public void setPreviousOp(int previousOp) {
		this.previousOp = previousOp;
	}

	public void switchOp() {
		if(this.operation.equalsIgnoreCase("nop")) {
			this.setOperation("jmp");
		}else if(this.operation.equalsIgnoreCase("jmp")) {
			this.setOperation("nop");
		}
		
	}
	
	
}
