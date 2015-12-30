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
import javax.persistence.UniqueConstraint;

import com.ningzeta.wbm.util.ConnType;

@Entity
@Table(name = "CONNECTION_TYPE", uniqueConstraints = {
	@UniqueConstraint(columnNames = "TYPE", name = "TYPE_IDX")
})
public class ConnectionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONNECTION_TYPE_SEQ")
	@SequenceGenerator(name = "CONNECTION_TYPE_SEQ", sequenceName = "CONNECTION_TYPE_SEQ")
	private int id;
	
	@Column(name = "TYPE", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private ConnType type;
	
	@Column(name = "RATE",nullable = false)
	private int rate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "connectionType")
	private Set<Customer> connections = new HashSet<Customer>(0);

	@Override
	public String toString() {
		return "ConnectionType [id=" + id + ", type=" + type + ", rate=" + rate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + id;
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
		ConnectionType other = (ConnectionType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	public ConnType getType() {
		return type;
	}

	public void setType(ConnType type) {
		this.type = type;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Set<Customer> getConnections() {
		return connections;
	}

	public void setConnections(Set<Customer> connections) {
		this.connections = connections;
	}

}