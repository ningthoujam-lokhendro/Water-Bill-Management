package com.ningzeta.wbm.util;

public enum PaymentTypeCode {
	BALANCE {
		@Override
		public String toString() {
			return "BALANCE";
		}
	}, ADVANCE {
		@Override
		public String toString() {
			return "ADVANCE";
		}
	}
}
