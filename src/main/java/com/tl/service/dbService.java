package com.tl.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.json.JSONObject;

public interface dbService {
	Map<String, Object> getDataByRequisitionNo(String requisitionNo) throws Exception;	
	Map<String, Object> getApprovalEndPointByRequisitionNo(String requisitionNo) throws Exception;
	Map<String, Object> getPODetailsByRequestNo(String requestNo) throws Exception;
	Map<String, Object> enableSignature(JSONObject sigData) throws Exception;
	Map<String, Object> getPOResultkeyByPONo(String pONo) throws Exception;
	Map<String, Object> getDataByPONo(String pONo) throws Exception;
}
