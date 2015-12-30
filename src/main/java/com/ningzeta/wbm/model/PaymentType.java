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

import com.ningzeta.wbm.util.PaymentTypeCode;

@Entity
@Table(name = "PAYMENT_TYPE")
public class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_TYPE_SEQ")
	@SequenceGenerator(name = "PAYMENT_TYPE_SEQ", sequenceName = "PAYMENT_TYPE_SEQ")
	long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaymentTypeCode getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeCode paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "DESCRIPTION")
	@Enumerated(EnumType.STRING)
	PaymentTypeCode paymentType;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "paymentType")
	Set<Payment> payments = new HashSet<Payment>(0);

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
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
		PaymentType other = (PaymentType) obj;
		if (id != other.id)
			return false;
		if (paymentType != other.paymentType)
			return false;
		return true;
	}
}
