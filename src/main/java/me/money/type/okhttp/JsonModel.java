package me.money.type.okhttp;

import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

public class JsonModel {
	/**
	 * 框架返回码
	 */
	private int retcode;
	/**
	 * 数据体
	 */
	private Map<String, Object> result;

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

}
