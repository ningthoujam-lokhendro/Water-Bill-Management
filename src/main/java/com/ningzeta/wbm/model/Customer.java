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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSTOMER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "PATTA_NUMBER", name = "PATTA_NUMBER_IDX"),
		@UniqueConstraint(columnNames = "CUSTOMER_ID", name = "CUSTOMER_ID_IDX")
})
public class Customer implements Serializable{

	private static final long serialVersionUID = -965450154805019511L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_SEQ")
	@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ")
	private Long id;
	
	@GenericGenerator(name = "cus_id_seq", strategy = "com.ningzeta.wbm.id.CustomerIdGenerator")
	@GeneratedValue(generator = "cus_id_seq")
	@Column(name = "CUSTOMER_ID", length = 10)
	private String customerId;
	
	@Column(name = "FIRST_NAME",nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "PATTA_NUMBER")
	private String pattaNumber;
	
	@Column(name = "PHONE_LANDLINE", length = 15)
	private String landLineNumber;
	
	@Column(name = "PHONE_MOBILE", length = 15)
	private String mobileNumber;
	
	@Column(name = "WARD_NUMBER", nullable = false)
	private int wardNumber;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE", nullable = false)
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REF_CONNECTION_TYPE", nullable = false,
		foreignKey = @ForeignKey(name = "FK_CONNECTION_TYPE"))
	ConnectionType connectionType;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer" )
	Set<Meter> meter = new HashSet<Meter>(0);

	public Set<Meter> getMeter() {
		return meter;
	}

	public void setMeter(Set<Meter> meter) {
		this.meter = meter;
	}

	public Customer () {}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPattaNumber() {
		return pattaNumber;
	}

	public void setPattaNumber(String pattaNumber) {
		this.pattaNumber = pattaNumber;
	}

	public String getLandLineNumber() {
		return landLineNumber;
	}

	public void setLandLineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(int wardNumber) {
		this.wardNumber = wardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((connectionType == null) ? 0 : connectionType.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((landLineNumber == null) ? 0 : landLineNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((pattaNumber == null) ? 0 : pattaNumber.hashCode());
		result = prime * result + wardNumber;
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (connectionType == null) {
			if (other.connectionType != null)
				return false;
		} else if (!connectionType.equals(other.connectionType))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (customerId != other.customerId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (landLineNumber == null) {
			if (other.landLineNumber != null)
				return false;
		} else if (!landLineNumber.equals(other.landLineNumber))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (pattaNumber == null) {
			if (other.pattaNumber != null)
				return false;
		} else if (!pattaNumber.equals(other.pattaNumber))
			return false;
		if (wardNumber != other.wardNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", pattaNumber=" + pattaNumber + ", landLineNumber=" + landLineNumber + ", mobileNumber="
				+ mobileNumber + ", wardNumber=" + wardNumber + ", address=" + address + ", creationDate="
				+ creationDate + ", connectionType=" + connectionType + "]";
	}
	
}
