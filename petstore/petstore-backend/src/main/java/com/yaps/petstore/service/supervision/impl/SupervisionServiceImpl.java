package com.yaps.petstore.service.supervision.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.service.supervision.SupervisionService;

@Service
@Transactional
public class SupervisionServiceImpl implements SupervisionService {

	
	public static final String VERSION_INVALID_ID = "Invalid id";
	
	public static final String UNCORRECT_VERSIONS = "More than one line in version table";
	
	
	
	@Autowired
	protected Mapper mapper;
	
	

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}	
	
	

	
	
}
