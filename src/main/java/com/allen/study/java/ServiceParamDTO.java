package com.allen.study.java;

import java.util.List;

public class ServiceParamDTO {
	private String fundProductId;
	private String serviceNo;
	private String paramKey;
	private String paramDataType;
	private List<ParamCheckRuleDTO> paramCheckRules;
	private List<ServiceParamDTO> subParams;

	public String getFundProductId() {
		return fundProductId;
	}

	public void setFundProductId(String fundProductId) {
		this.fundProductId = fundProductId;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamDataType() {
		return paramDataType;
	}

	public void setParamDataType(String paramDataType) {
		this.paramDataType = paramDataType;
	}

	public List<ParamCheckRuleDTO> getParamCheckRules() {
		return paramCheckRules;
	}

	public void setParamCheckRules(List<ParamCheckRuleDTO> paramCheckRules) {
		this.paramCheckRules = paramCheckRules;
	}

	public List<ServiceParamDTO> getSubParams() {
		return subParams;
	}

	public void setSubParams(List<ServiceParamDTO> subParams) {
		this.subParams = subParams;
	}

}
