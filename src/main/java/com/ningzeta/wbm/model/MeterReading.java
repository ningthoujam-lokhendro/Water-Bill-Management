package com.ningzeta.wbm.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "METER_READING")
public class MeterReading implements Serializable{

	private static final long serialVersionUID = -5402403961897344977L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "METER_READING_SEQ")
	@SequenceGenerator(name = "METER_READING_SEQ", sequenceName = "METER_READING_SEQ")
	Long id;
	
	@Column(name = "READING_DATE", nullable = false)
	Date readingDate;
	
	@Column(name = "UNIT", nullable = false)
	int unit;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "METER_ID", nullable = false, referencedColumnName = "METER_ID",
			foreignKey = @ForeignKey(name = "FK_METER_ID"))
	Meter meter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATTA_NUMBER", nullable = false, referencedColumnName="PATTA_NUMBER",
			foreignKey = @ForeignKey(name = "FK_PATTA_NUMBER_MR"))
	private Customer customer;

	@Override
	public String toString() {
		return "MeterReading [id=" + id + ", readingDate=" + readingDate + ", value=" + unit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((meter == null) ? 0 : meter.hashCode());
		result = prime * result + ((readingDate == null) ? 0 : readingDate.hashCode());
		result = prime * result + unit;
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
		MeterReading other = (MeterReading) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meter == null) {
			if (other.meter != null)
				return false;
		} else if (!meter.equals(other.meter))
			return false;
		if (readingDate == null) {
			if (other.readingDate != null)
				return false;
		} else if (!readingDate.equals(other.readingDate))
			return false;
		if (unit != other.unit)
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public Meter getMeter() {
		return meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
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
