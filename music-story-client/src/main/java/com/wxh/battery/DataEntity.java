package com.wxh.battery;

import java.util.Date;

/**
 * 电池监控数据实体
 * @author Administrator
 *
 */
public class DataEntity {

	private String id;           //ID
	private String serialNum;    //序列�?  与电池关联的标识
	private int cacheAddress;    //读取到的数据�?��内存地址
	private int dataLength;      //数据长度
	private Date clientReadTime;  //无用！！�?
	private Date serverReadTime;  //无用！！�?
	private byte[] data;          //具体电池监控数据
	private String remark1;
	private String remark2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getCacheAddress() {
		return cacheAddress;
	}

	public void setCacheAddress(int cacheAddress) {
		this.cacheAddress = cacheAddress;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public Date getClientReadTime() {
		return clientReadTime;
	}

	public void setClientReadTime(Date clientReadTime) {
		this.clientReadTime = clientReadTime;
	}

	public Date getServerReadTime() {
		return serverReadTime;
	}

	public void setServerReadTime(Date serverReadTime) {
		this.serverReadTime = serverReadTime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

}
