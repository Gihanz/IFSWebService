package com.tl.ws;

import java.util.Map;

import org.apache.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tl.service.exceptions.BSLException;
import com.tl.service.impl.dbServiceImpl;

@RestController
public class WsServiceImpl extends BaseWebServiceEndPointImpl{
	
	private static Logger log =LoggerFactory.getLogger(WsServiceImpl.class);	
	
	@RequestMapping(value="/getDataByRequisitionNo", method = RequestMethod.GET, produces="application/json")
	public Map<String, Object> getDataByRequisitionNo(@RequestParam("requisitionNo") String requisitionNo)
	{
		try
		{
			return dbService.getDataByRequisitionNo(requisitionNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
	
	@RequestMapping(value="/getApprovalEndPointByRequisitionNo", method = RequestMethod.GET, produces="application/json")
	public Map<String, Object> getApprovalEndPointByRequisitionNo(@RequestParam("requisitionNo") String requisitionNo)
	{
		try
		{
			return dbService.getApprovalEndPointByRequisitionNo(requisitionNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
	
	@RequestMapping(value="/getPODetailsByRequestNo", method = RequestMethod.GET, produces="application/json")
	public Map<String, Object> getPODetailsByRequestNo(@RequestParam("requestNo") String requestNo)
	{
		try
		{
			return dbService.getPODetailsByRequestNo(requestNo);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/enableSignature", method = RequestMethod.POST, produces = "application/json", consumes="application/json")
	public Map<String, Object> enableSignature(@RequestBody JSONObject sigData) 
	{
		try
		{
			return dbService.enableSignature(sigData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/getPOResultkeyByPONo", method = RequestMethod.GET, produces="application/json")
	public Map<String, Object> getPOResultkeyByPONo(@RequestParam("pONo") String pONo)
	{
		try
		{
			return dbService.getPOResultkeyByPONo(pONo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/getDataByPONo", method = RequestMethod.GET, produces="application/json")
	public Map<String, Object> getDataByPONo(@RequestParam("pONo") String pONo)
	{
		try
		{
			return dbService.getDataByPONo(pONo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.fillInStackTrace()+""+e.getStackTrace()+e.getLocalizedMessage());
			throw new BSLException(getExcptnMesProperty(e.getMessage()));
		}
	}
}

