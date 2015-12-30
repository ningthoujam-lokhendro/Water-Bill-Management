package com.ningzeta.wbm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningzeta.wbm.model.ConnectionType;
import com.ningzeta.wbm.repository.ConnectionTypeRepository;
import com.ningzeta.wbm.util.ConnType;

@Service
@Transactional
public class ConnectionTypeService {
	
	@Autowired
	ConnectionTypeRepository connTypeRepo;
	
	// Only Elevated user should save this
	public void save(ConnectionType conType) {
		connTypeRepo.save(conType);
	}
	
	public ConnectionType findByType(ConnType type) {
		
		ConnectionType connType =  null;		
		connType = connTypeRepo.findByType(type);
		
		return connType;
	}

}
