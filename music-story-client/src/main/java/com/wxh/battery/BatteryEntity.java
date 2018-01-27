package com.wxh.battery;

import java.util.Date;
import java.util.Map;

/**
 * �����Ϣʵ��
 * @author Administrator
 *
 */
public class BatteryEntity {
	
	private String serialNum;           //���к�  ��ص�Ψһ��ʶ
	private String manuFactureName;    //��������
	private String batteryName;         //�������
	private String batteryChemId;      //��о�ͺ�
	private Date manuFactureDate;      //��������
	private Map<String,Object> extInfo;   // ������Ϣ1
	private Map<String,Object> sigleCellInfo;  //������Ϣ2
	
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
