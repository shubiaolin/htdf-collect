package com.xxl.job.executor.service.jobhandler.cimiss.htdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.executor.util.CimissConfig;
import com.xxl.job.executor.util.CimissUtil;

/**
 * 国家站小时实况数据
 * @author ck
 *
 */
@JobHander(value="nationRealTimeHourData")
@Service
public class NationRealTimeHourData  extends IJobHandler {
	
	@Autowired
	private CimissConfig cimissConfig;
	
	private static final Logger Logger = LoggerFactory.getLogger(NationRealTimeHourData.class);
 
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		
		//例子 -- 参数
		Map<String,Object> map = new HashMap();
		map.put("userId", cimissConfig.getUsername());
		map.put("pwd", cimissConfig.getPassword());
		map.put("interfaceId", "getStaInfoByNetCodes");
		map.put("elements", "Station_Id_C,Station_Name,City,Station_levl");
		map.put("dataCode", "STA_INFO_CHN");
		map.put("netCodes", "05");
		map.put("dataFormat", "json");
		//获取数据
		List<Map<String,Object>> li =  CimissUtil.getCimissData(map, cimissConfig.getWsdl(), cimissConfig.getTargetNamespace(), cimissConfig.getTimeoutInMilliSeconds());
		//循环获取的数据
		for(Map<String,Object> map1:li) {
			Set<String> m = map1.keySet();
			for(String s:m){
				System.out.println(s+":"+map1.get(s));
			}
		}
		return null;
	}
}
