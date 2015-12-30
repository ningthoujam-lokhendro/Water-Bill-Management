package com.ningzeta.wbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ningzeta.wbm.model.ConnectionType;
import com.ningzeta.wbm.util.ConnType;

public interface ConnectionTypeRepository extends JpaRepository<ConnectionType, Integer>{
	
	ConnectionType findByType(ConnType type);

}
