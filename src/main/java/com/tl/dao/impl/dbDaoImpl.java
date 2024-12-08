package com.tl.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.jdbc.ReturningWork;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import com.tl.dao.dbDao;
import com.tl.dao.exceptions.DAOException;

@Repository
public class dbDaoImpl extends abstractWFConfigdao implements dbDao{
	
private static Logger log =LoggerFactory.getLogger(dbDaoImpl.class);
	
	public Map<String, Object> getDataByRequisitionNo(final String requisitionNo) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {

					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{call C_TYT_ECM_PRPA_WAP_API.GET_REQUISITION_INFO(?,?,?,?,?,?,?,?,?,?)}");
						call.setString(1, requisitionNo); 			
						call.registerOutParameter(2, java.sql.Types.VARCHAR);
						call.registerOutParameter(3, java.sql.Types.VARCHAR);
						call.registerOutParameter(4, java.sql.Types.VARCHAR);
						call.registerOutParameter(5, java.sql.Types.VARCHAR);
						call.registerOutParameter(6, java.sql.Types.VARCHAR);
						call.registerOutParameter(7, java.sql.Types.VARCHAR);
						call.registerOutParameter(8, java.sql.Types.NUMERIC);
						call.registerOutParameter(9, java.sql.Types.NUMERIC);
						call.registerOutParameter(10, OracleTypes.CURSOR);
						call.execute();
						
						rtnData.put("employee_no", call.getObject(2)); 
						rtnData.put("site", call.getObject(3)); 
						rtnData.put("department", call.getObject(4)); 
						rtnData.put("purchase_type", call.getObject(5)); 
						rtnData.put("purchase_category", call.getObject(6));
						rtnData.put("goods_category", call.getObject(7));
						rtnData.put("total_gross_amt", call.getObject(8)); 
						rtnData.put("total_net_amt", call.getObject(9));
						
						ResultSet resultSet = (ResultSet) call.getObject(10);
						
						List<Map<String, Object>> purReqLineDataList = new ArrayList<Map<String, Object>>();
						while (resultSet.next()) {
							Map<String, Object> purReqLineData = new HashMap<String, Object>();
							purReqLineData.put("line_no", resultSet.getObject(1)); 
							purReqLineData.put("description", resultSet.getObject(2)); 
							purReqLineData.put("quantity", resultSet.getObject(3)); 
							purReqLineData.put("wanted_receipt_date", resultSet.getObject(4)); 
							purReqLineData.put("status", resultSet.getObject(5)); 
							purReqLineDataList.add(purReqLineData);
			            }					
						rtnData.put("line_data", purReqLineDataList);
						
						return null;
					}			
					
				});		
			}
			catch(HibernateException hex){
				log.info("Error in getDataByRequisitionNo");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.getDataByRequisitionNo",hex);
			}	
			return rtnData;
	}
	
	public Map<String, Object> getApprovalEndPointByRequisitionNo(final String requisitionNo) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {

					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{? = call C_TYT_ECM_PRPA_WAP_API.IS_ENDPOINT_APPRIVAL_BHOD(?)}");
						call.setString(2, requisitionNo); 			
						call.registerOutParameter(1, java.sql.Types.VARCHAR);
						call.execute();
						
						if(call.getObject(1).equals("TRUE")){
							rtnData.put("branch_end_point", true);
						}else{
							rtnData.put("branch_end_point", false);
						}	
						
						return null;
					}	
					
				});
			} 
			catch(HibernateException hex){
				log.info("Error in getApprovalEndPointByRequisitionNo");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.getApprovalEndPointByRequisitionNo",hex);
			}	
			return rtnData;
	}
	
	public Map<String, Object> getPODetailsByRequestNo(final String requestNo) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {

					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{call C_TYT_ECM_PRPA_WAP_API.GET_PUR_ORD_INFO(?,?,?,?,?,?,?,?)}");
						call.setString(1, requestNo); 			
						call.registerOutParameter(2, java.sql.Types.VARCHAR);
						call.registerOutParameter(3, java.sql.Types.NUMERIC);
						call.registerOutParameter(4, java.sql.Types.NUMERIC);
						call.registerOutParameter(5, java.sql.Types.VARCHAR);
						call.registerOutParameter(6, java.sql.Types.VARCHAR);
						call.registerOutParameter(7, java.sql.Types.VARCHAR);
						call.registerOutParameter(8, java.sql.Types.VARCHAR);
						call.execute();
						
						rtnData.put("purchase_order_no", call.getObject(2)); 
						rtnData.put("total_gross_amt", call.getObject(3)); 
						rtnData.put("total_net_amt", call.getObject(4));
						rtnData.put("supplier_code", call.getObject(5)); 
						rtnData.put("supplier_name", call.getObject(6)); 
						rtnData.put("vendor_email", call.getObject(7));
						rtnData.put("final_approver", call.getObject(8));
						
						return null;
					}			
					
				});		
			}
			catch(HibernateException hex){
				log.info("Error in getPODetailsByRequestNo");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.getPODetailsByRequestNo",hex);
			}	
			return rtnData;
	}
	
	public Map<String, Object> enableSignature(final JSONObject sigData) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {

					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{call C_TYT_ECM_PRPA_WAP_API.ADD_PUR_ORD_SIGNATURES(?,?,?,?,?,?,?,?,?)}");
						try {
							call.setString(1, sigData.getString("username"));
							call.setString(2, sigData.getString("role"));
							call.setString(3, sigData.getString("step_name"));
							call.setString(4, sigData.getString("po_number"));
							call.setString(5, sigData.getString("purchase_type"));
							call.setDouble(6, sigData.getDouble("total_gross_amt"));
							call.setDouble(7, sigData.getDouble("total_net_amt"));
							call.registerOutParameter(8, java.sql.Types.VARCHAR);
							call.registerOutParameter(9, java.sql.Types.VARCHAR);
							
						} catch (JSONException e) {
							e.printStackTrace();
						} 							
						call.execute();
						
						rtnData.put("response", call.getObject(8)); 
						rtnData.put("response_description", call.getObject(9));
						
						return null;
					}			
					
				});		
			}
			catch(HibernateException hex){
				log.info("Error in enableSignature");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.enableSignature",hex);
			}	
			return rtnData;
	}
	
	public Map<String, Object> getPOResultkeyByPONo(final String pONo) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {
	
					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{call C_TYT_ECM_PRPA_WAP_API.EXECUTE_PUR_ORD_REPORT(?,?,?)}");
						call.setString(1, pONo); 			
						call.registerOutParameter(2, java.sql.Types.NUMERIC);
						call.registerOutParameter(3, java.sql.Types.VARCHAR);
						call.execute();
						
						rtnData.put("result_key", call.getObject(2)); 
						rtnData.put("layout_name", call.getObject(3)); 
						
						return null;
					}			
					
				});		
			}
			catch(HibernateException hex){
				log.info("Error in getPOResultkeyByPONo");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.getPOResultkeyByPONo",hex);
			}	
			return rtnData;
	}
	
	public Map<String, Object> getDataByPONo(final String pONo) {		
		final Map<String, Object> rtnData= new HashMap<String, Object>();

			try{
				session().doReturningWork(new ReturningWork<String>() {

					@Override
					public String execute(Connection conn) throws SQLException {
						
						CallableStatement call = conn.prepareCall("{call C_TYT_ECM_PRPA_WAP_API.GET_PO_MULTI_REQUISITIONS_INFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
						call.setString(1, pONo); 
						call.registerOutParameter(2, OracleTypes.CURSOR);
						call.registerOutParameter(3, java.sql.Types.VARCHAR);
						call.registerOutParameter(4, java.sql.Types.VARCHAR);
						call.registerOutParameter(5, java.sql.Types.VARCHAR);
						call.registerOutParameter(6, java.sql.Types.VARCHAR);
						call.registerOutParameter(7, java.sql.Types.VARCHAR);
						call.registerOutParameter(8, java.sql.Types.NUMERIC);
						call.registerOutParameter(9, java.sql.Types.NUMERIC);
						call.registerOutParameter(10, OracleTypes.CURSOR);
						call.registerOutParameter(11, java.sql.Types.VARCHAR);
						call.registerOutParameter(12, java.sql.Types.VARCHAR);
						call.registerOutParameter(13, java.sql.Types.VARCHAR);
						call.registerOutParameter(14, java.sql.Types.VARCHAR);
						call.registerOutParameter(15, java.sql.Types.VARCHAR);
						call.execute();
								
						ResultSet requisitionNumbersRstSet = (ResultSet) call.getObject(2);
						
						List<Object> mergedReqNumbersLst = new ArrayList<Object>();
						while (requisitionNumbersRstSet.next()) {
							mergedReqNumbersLst.add(requisitionNumbersRstSet.getObject(1));
			            }
						rtnData.put("merged_req_numbers", mergedReqNumbersLst);
						
						rtnData.put("site", call.getObject(3)); 
						rtnData.put("department", call.getObject(4)); 
						rtnData.put("purchase_type", call.getObject(5)); 
						rtnData.put("purchase_category", call.getObject(6));
						rtnData.put("goods_category", call.getObject(7));
						rtnData.put("total_gross_amt", call.getObject(8)); 
						rtnData.put("total_net_amt", call.getObject(9));
						
						ResultSet lineItemsRstSet = (ResultSet) call.getObject(10);
						
						List<Map<String, Object>> purReqLineDataList = new ArrayList<Map<String, Object>>();
						while (lineItemsRstSet.next()) {
							Map<String, Object> purReqLineData = new HashMap<String, Object>();
							purReqLineData.put("requisition_number", lineItemsRstSet.getObject(1));
							purReqLineData.put("line_no", lineItemsRstSet.getObject(2));
							purReqLineData.put("description", lineItemsRstSet.getObject(3));
							purReqLineData.put("quantity", lineItemsRstSet.getObject(4));
							purReqLineData.put("wanted_receipt_date", lineItemsRstSet.getObject(5));
							purReqLineData.put("status", lineItemsRstSet.getObject(6));
							purReqLineDataList.add(purReqLineData);
			            }					
						rtnData.put("line_data", purReqLineDataList);
						
						rtnData.put("supplier_code", call.getObject(11));
						rtnData.put("supplier_name", call.getObject(12));
						rtnData.put("vendor_email", call.getObject(13));
						rtnData.put("final_approver", call.getObject(14));
						rtnData.put("request_number", call.getObject(15));
						
						return null;
					}			
					
				});		
			}
			catch(HibernateException hex){
				log.info("Error in getDataByPONo");
				hex.printStackTrace();
				log.error("Error ", hex.fillInStackTrace());
				log.error(hex.getMessage());
				throw new DAOException("er.db.getDataByPONo",hex);
			}	
			return rtnData;
	}
}
