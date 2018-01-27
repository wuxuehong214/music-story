package com.wxh.battery;

import java.util.Date;
import java.util.Map;

/**
 * 电池信息实体
 * @author Administrator
 *
 */
public class BatteryEntity {
	
	private String serialNum;           //序列号  电池的唯一标识
	private String manuFactureName;    //厂商名称
	private String batteryName;         //电池名称
	private String batteryChemId;      //电芯型号
	private Date manuFactureDate;      //生产日期
	private Map<String,Object> extInfo;   // 额外信息1
	private Map<String,Object> sigleCellInfo;  //额外信息2
	
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getManuFactureName() {
		return manuFactureName;
	}
	public void setManuFactureName(String manuFactureName) {
		this.manuFactureName = manuFactureName;
	}
	public String getBatteryName() {
		return batteryName;
	}
	public void setBatteryName(String batteryName) {
		this.batteryName = batteryName;
	}
	public String getBatteryChemId() {
		return batteryChemId;
	}
	public void setBatteryChemId(String batteryChemId) {
		this.batteryChemId = batteryChemId;
	}
	public Date getManuFactureDate() {
		return manuFactureDate;
	}
	public void setManuFactureDate(Date manuFactureDate) {
		this.manuFactureDate = manuFactureDate;
	}
	public Map<String, Object> getExtInfo() {
		return extInfo;
	}
	public void setExtInfo(Map<String, Object> extInfo) {
		this.extInfo = extInfo;
	}
	public Map<String, Object> getSigleCellInfo() {
		return sigleCellInfo;
	}
	public void setSigleCellInfo(Map<String, Object> sigleCellInfo) {
		this.sigleCellInfo = sigleCellInfo;
	}

	
}
