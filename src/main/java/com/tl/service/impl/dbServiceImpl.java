package com.tl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tl.dao.dbDao;
import com.tl.service.dbService;

@Service
@Configurable
public class dbServiceImpl implements dbService{
	private static Logger log =LoggerFactory.getLogger(dbServiceImpl.class);
	@Autowired private dbDao dbDao = null;
	
	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> getDataByRequisitionNo(String requisitionNo) {
		return dbDao.getDataByRequisitionNo(requisitionNo);
	}

	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> getApprovalEndPointByRequisitionNo(String requisitionNo) {
		return dbDao.getApprovalEndPointByRequisitionNo(requisitionNo);
	}
	
	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> getPODetailsByRequestNo(String requestNo) {
		return dbDao.getPODetailsByRequestNo(requestNo);
	}
	
	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> enableSignature(JSONObject sigData) {
		return dbDao.enableSignature(sigData);
	}
	
	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> getPOResultkeyByPONo(String pONo) {
		return dbDao.getPOResultkeyByPONo(pONo);
	}
	
	@Override
	@Transactional(value="transactionManager",readOnly=true)
	public Map<String, Object> getDataByPONo(String pONo) {
		return dbDao.getDataByPONo(pONo);
	}
}
