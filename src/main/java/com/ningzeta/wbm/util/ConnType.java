package com.ningzeta.wbm.util;

public enum ConnType {
	
	HOME {
		@Override
		public String toString() {
			return "HOME";
		}
	},
	COMMERCIAL {
		@Override
		public String toString() {
			return "COMMERCIAL";
		}
	},
	NON_PROFIT {
		@Override
		public String toString() {
			return "NON_PROFIT";
		}
	};
	
	private String value;
	
	ConnType() {}
	
	private ConnType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
