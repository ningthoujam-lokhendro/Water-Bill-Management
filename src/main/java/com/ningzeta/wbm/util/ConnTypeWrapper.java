package com.ningzeta.wbm.util;

public class ConnTypeWrapper {
	
	String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public ConnType getEnumType() {
		
		switch (type) {
		case "HOME":
			return ConnType.HOME;
		case "COMMERCIAL":
			return ConnType.COMMERCIAL;
		case "NON_PROFIT":
			return ConnType.NON_PROFIT;
		default:
			return ConnType.HOME;
		}
	}
}
