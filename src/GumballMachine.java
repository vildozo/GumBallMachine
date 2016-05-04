
public class GumballMachine {
	
	//private static final int SOLD_OUT = 0;
	//private static final int NO_QUARTER = 1;
	//private static final int HAS_QUARTER = 2;
	//private static final int SOLD = 3;
	MachineState state = new SoldOutState();
	
	//private int state = SOLD_OUT;
	private int count;

	public GumballMachine(int count) {
		this.count = count;
		if (count > 0)
			//state = NO_QUARTER;
			state  = new NoQuarterState();
	}

	public void insertQuarter() {
		if (state.getState() == 1) {
			//state = HAS_QUARTER;
			state = new HasQuarterState();
			//System.out.println(Messages.INSERT_SUCCESSFULLY);
			state.ShowMessage(count, "insert");
		}
		else if (state.getState() == 2) {
			//System.out.println(Messages.INSERT_TWICE);
			state.ShowMessage(count+1, "insert");
		}
		else if (state.getState() == 0) {
			state.ShowMessage(count, "insert");
			//System.out.println(Messages.INSERT_WHEN_SOLDOUT);
		}
		else if (state.getState() == 3) {
			//System.out.println(Messages.INSERT_WHEN_SOLD);
			state.ShowMessage(count, "insert");
		}
			
	}

	public void ejectQuarter() {
		if (state.getState() == 2) {
			//state = NO_QUARTER;
			state = new NoQuarterState();
			//System.out.println(Messages.EJECT_SUCCESSFULLY);
			state.ShowMessage(count, "eject");
		}
		else if (state.getState() == 1)
			//System.out.println(Messages.EJECT_WHEN_NO_QUARTER);
			state.ShowMessage(count-1, "eject");
		else if (state.getState() == 0)
			//System.out.println(Messages.EJECT_WHEN_SOLD_OUT);
			state.ShowMessage(count, "eject");
		else if (state.getState() == 3)
			//System.out.println(Messages.EJECT_WHEN_SOLD);
			state.ShowMessage(count, "eject");
	}

	public void turnCrank() {
		if (state.getState() == 0)
			//System.out.println(Messages.TURN_WHEN_SOLD_OUT);
			state.ShowMessage(count, "turnCrank");
		else if (state.getState() == 1)
			//System.out.println(Messages.TURN_WHEN_NO_QUARTER);
			state.ShowMessage(count, "turnCrank");
		else if (state.getState() == 2) {
			//state = SOLD;
			state = new SoldState();
			//System.out.println(Messages.TURN_SUCCESSFULLY);
			state.ShowMessage(count, "turnCrank");
			dispense();
		}
		else if (state.getState() == 3)
			System.out.println(Messages.TURN_TWICE);
	}

	public void dispense() {
		if (state.getState() == 3) {
			//System.out.println(Messages.DISPENSE_SUCCESSFULLY);
			state.ShowMessage(count, "dispense");
			count = count - 1;
			if (count == 0) {
				//state = SOLD_OUT;
				state = new SoldOutState();
				//System.out.println(Messages.DISPENSE_LAST_GUMBALL);
				state.ShowMessage(count, "dispenseLast");
			}
		}
		else if (state.getState() == 0)
			//System.out.println(Messages.DISPENSE_WHEN_SOLD_OUT);
			state.ShowMessage(count, "dispense");
		else if (state.getState() == 1)
			//System.out.println(Messages.DISPENSE_WHEN_NO_QUARTER);
			state.ShowMessage(count, "dispense");
		else if (state.getState() == 2)
			//System.out.println(Messages.DISPENSE_WHEN_HAS_QUARTER);
			state.ShowMessage(count+1, "dispense");
	}

}