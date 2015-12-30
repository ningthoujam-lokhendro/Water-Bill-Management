package com.ningzeta.wbm.util;

public enum PaymentStatus {

	COMPLETED {
		@Override
		public String toString() {
			return "COMPLETED";
		}
	},
	PARTIAL{
		@Override
		public String toString() {
			return "PARTIAL";
		}
	}
}
