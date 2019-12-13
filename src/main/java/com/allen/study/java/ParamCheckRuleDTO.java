package com.allen.study.java;

public class ParamCheckRuleDTO {
	private String paramKey;
	private String paramDataType;
	private String ruleBeanName;
	private Integer ruleOrderNo;
	private String ruleAdditionalInfo;

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

	public String getRuleBeanName() {
		return ruleBeanName;
	}

	public void setRuleBeanName(String ruleBeanName) {
		this.ruleBeanName = ruleBeanName;
	}

	public Integer getRuleOrderNo() {
		return ruleOrderNo;
	}

	public void setRuleOrderNo(Integer ruleOrderNo) {
		this.ruleOrderNo = ruleOrderNo;
	}

	public String getRuleAdditionalInfo() {
		return ruleAdditionalInfo;
	}

	public void setRuleAdditionalInfo(String ruleAdditionalInfo) {
		this.ruleAdditionalInfo = ruleAdditionalInfo;
	}

}
