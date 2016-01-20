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

import com.ningzeta.wbm.util.PaymentStatus;

@Entity
@Table(name = "PAYMENT_STATUS_CODE")
public class PaymentStatusCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_STATUS_CODE_SEQ")
	@SequenceGenerator(name = "PAYMENT_STATUS_CODE_SEQ", sequenceName = "PAYMENT_STATUS_CODE_SEQ")
	private long id;
	
	@Column(name = "DESCRIPTION", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "PaymentStatusCode [id=" + id + ", paymentStatus=" + paymentStatus + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentStatusCode other = (PaymentStatusCode) obj;
		if (id != other.id)
			return false;
		if (paymentStatus != other.paymentStatus)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
