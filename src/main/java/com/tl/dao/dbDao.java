package com.tl.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.json.JSONObject;

public interface dbDao {
	Map<String, Object> getDataByRequisitionNo(String requisitionNo);
	Map<String, Object> getApprovalEndPointByRequisitionNo(String requisitionNo);
	Map<String, Object> getPODetailsByRequestNo(String requestNo);
	Map<String, Object> enableSignature(JSONObject sigData);
	Map<String, Object> getPOResultkeyByPONo(String pONo);
	Map<String, Object> getDataByPONo(String pONo);
}
