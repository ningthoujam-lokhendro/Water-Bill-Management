package com.ningzeta.wbm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ningzeta.wbm.util.BillStatusCode;

@Entity
@Table(name = "BILL_STATUS")
public class BillStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BILL_STATUS_SEQ")
	@SequenceGenerator(name = "BILL_STATUS_SEQ", sequenceName = "BILL_STATUS_SEQ")
	long id;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	BillStatusCode billStatusCode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	Set<WaterBill> waterBill = new HashSet<WaterBill>(0);

	public Set<WaterBill> getWaterBill() {
		return waterBill;
	}

	public void setWaterBill(Set<WaterBill> waterBill) {
		this.waterBill = waterBill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BillStatusCode getBillStatusCode() {
		return billStatusCode;
	}

	public void setBillStatusCode(BillStatusCode billStatusCode) {
		this.billStatusCode = billStatusCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billStatusCode == null) ? 0 : billStatusCode.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillStatus other = (BillStatus) obj;
		if (billStatusCode != other.billStatusCode)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
