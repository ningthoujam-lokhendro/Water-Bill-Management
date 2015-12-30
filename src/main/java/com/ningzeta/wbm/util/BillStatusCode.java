package com.ningzeta.wbm.util;

public enum BillStatusCode {
	DUE {
		@Override
		public String toString() {
			return "DUE";
		}
	}, PAID {
		@Override
		public String toString() {
			return "PAID";
		}
	}
}
