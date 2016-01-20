package com.ningzeta.wbm.model;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "PAYMENT")
public class Payment implements Serializable {

	private static final long serialVersionUID = 5406359443456833473L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_SEQ")
	@SequenceGenerator(name = "PAYMENT_SEQ", sequenceName = "PAYMENT_SEQ")
	long id;

	@Column(name = "DATE_OF_PAYMENT", nullable = false)
	Date dateOfPayment;
	
	@Column(name = "PAID_AMOUNT", nullable = false)
	int paidAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYEMENT_STATUS", nullable = false, referencedColumnName = "DESCRIPTION",
			foreignKey = @ForeignKey(name = "FK_PAYEMENT_STATUS_CODE"))
	PaymentStatusCode paymentStatusCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENT_TYPE", nullable = false, referencedColumnName = "DESCRIPTION",
			foreignKey = @ForeignKey(name = "FK_PAYMENT_TYPE"))
	PaymentType paymentType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WATER_BILL", nullable = false,
			foreignKey = @ForeignKey(name = "FK_WATER_BILL"))
	private WaterBill waterBill;

	public PaymentStatusCode getPaymentStatusCode() {
		return paymentStatusCode;
	}

	public void setPaymentStatusCode(PaymentStatusCode paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", dateOfPayment=" + dateOfPayment + ", paidAmount=" + paidAmount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfPayment == null) ? 0 : dateOfPayment.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + paidAmount;
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
		Payment other = (Payment) obj;
		if (dateOfPayment == null) {
			if (other.dateOfPayment != null)
				return false;
		} else if (!dateOfPayment.equals(other.dateOfPayment))
			return false;
		if (id != other.id)
			return false;
		if (paidAmount != other.paidAmount)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public WaterBill getWaterBill() {
		return waterBill;
	}

	public void setWaterBill(WaterBill waterBill) {
		this.waterBill = waterBill;
	}
}
