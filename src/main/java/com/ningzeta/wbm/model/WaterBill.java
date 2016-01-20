package com.ningzeta.wbm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	private Date dueDate;
	
	@Column(name = "AMOUNT_DUE", nullable = false)
	private int amountDue;
	
	@Column(name = "PREVIOUS_DUE")
	private int previousDue;
	
	@Column(name = "NET_BILL_AMOUNT")
	private int netBillAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILL_STATUS", nullable = false, referencedColumnName = "STATUS",
		foreignKey = @ForeignKey(name = "FK_BILL_STATUS"))
	private BillStatus status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "waterBill")
	private Set<Payment> payment = new HashSet<Payment>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "METER_READING", nullable = false, referencedColumnName = "id",
		foreignKey = @ForeignKey(name = "FK_METER_READING"))
	MeterReading meterReading;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, referencedColumnName = "id",
		foreignKey = @ForeignKey(name = "FK_CUSTOMER"))
	Customer customer;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountDue;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
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
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
				+ dueDate + ", amountDue=" + amountDue + "]";
	}

	public int getPreviousDue() {
		return previousDue;
	}

	public void setPreviousDue(int previousDue) {
		this.previousDue = previousDue;
	}

	public int getNetBillAmount() {
		return netBillAmount;
	}

	public void setNetBillAmount(int netBillAmount) {
		this.netBillAmount = netBillAmount;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
