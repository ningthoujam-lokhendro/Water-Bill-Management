package com.ningzeta.wbm.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "METER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "METER_ID", name = "METER_ID_IDX")
})
public class Meter implements Serializable{
	
	private static final long serialVersionUID = 8824857822189005238L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "METER_SEQ")
	@SequenceGenerator(name = "METER_SEQ", sequenceName = "METER_SEQ")
	private Long id;
	
	@Column(name = "METER_ID")
	private String meterId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATTA_NUMBER", nullable = false, referencedColumnName="PATTA_NUMBER",
			foreignKey = @ForeignKey(name = "FK_PATTA_NUMBER"))
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meter", cascade=CascadeType.ALL)
	private Set<MeterReading> meterReading = new HashSet<MeterReading>(0);

	@Override
	public String toString() {
		return "Meters [id=" + id + ", meterId=" + meterId + "]";
	}

	public Set<MeterReading> getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(Set<MeterReading> meterReading) {
		this.meterReading = meterReading;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((meterId == null) ? 0 : meterId.hashCode());
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
		Meter other = (Meter) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meterId == null) {
			if (other.meterId != null)
				return false;
		} else if (!meterId.equals(other.meterId))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
