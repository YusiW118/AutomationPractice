package automation.driver;

import automation.utilities.Constants;
public class DriverStrategyImplementer {
	public static int valor=0;
	
	public static DriverStrategy chooseStrategy(String strategy) {
		
		if(strategy.equalsIgnoreCase(Constants.CHROME))
		    valor=1;

		switch(valor) {
		case 1:
			return new Chrome();
		default:
			return null;
		
		}
	}

}
