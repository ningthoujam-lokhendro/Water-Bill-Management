package com.ningzeta.wbm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "WATER_BILL")
public class WaterBill implements Serializable{
	
	private static final long serialVersionUID = 891513480820069032L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "WATER_BILL_SEQ")
	@SequenceGenerator(name = "WATER_BILL_SEQ", sequenceName = "WATER_BILL_SEQ")
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PERIOD_FROM", nullable = false)
	private Date periodFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PERIOD_TO", nullable = false)
	private Date periodTo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", nullable = false)
	private Date paymentDue;
	
	@Column(name = "AMOUNT_DUE", nullable = false)
	private int amountDue;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REF_BILL_STATUS", nullable = false, referencedColumnName = "STATUS",
		foreignKey = @ForeignKey(name = "FK_BILL_STATUS"))
	private BillStatus status;
	
	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public Set<Payment> getPayment() {
		return payment;
	}

	public void setPayment(Set<Payment> payment) {
		this.payment = payment;
	}

	public MeterReading getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(MeterReading meterReading) {
		this.meterReading = meterReading;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "waterBill")
	private Set<Payment> payment = new HashSet<Payment>(0);
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REF_METER_READING", nullable = false, 
		foreignKey = @ForeignKey(name = "FK_METER_READING"))
	MeterReading meterReading;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountDue;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paymentDue == null) ? 0 : paymentDue.hashCode());
		result = prime * result + ((periodFrom == null) ? 0 : periodFrom.hashCode());
		result = prime * result + ((periodTo == null) ? 0 : periodTo.hashCode());
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
		WaterBill other = (WaterBill) obj;
		if (amountDue != other.amountDue)
			return false;
		if (id != other.id)
			return false;
		if (paymentDue == null) {
			if (other.paymentDue != null)
				return false;
		} else if (!paymentDue.equals(other.paymentDue))
			return false;
		if (periodFrom == null) {
			if (other.periodFrom != null)
				return false;
		} else if (!periodFrom.equals(other.periodFrom))
			return false;
		if (periodTo == null) {
			if (other.periodTo != null)
				return false;
		} else if (!periodTo.equals(other.periodTo))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public Date getPaymentDue() {
		return paymentDue;
	}

	public void setPaymentDue(Date paymentDue) {
		this.paymentDue = paymentDue;
	}

	public int getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	@Override
	public String toString() {
		return "WaterBill [id=" + id + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", paymentDue="
				+ paymentDue + ", amountDue=" + amountDue + "]";
	}

}
