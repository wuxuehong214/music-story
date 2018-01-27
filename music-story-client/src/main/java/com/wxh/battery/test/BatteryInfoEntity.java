package com.wxh.battery.test;

import java.util.Date;
import java.util.Map;


/**
 * �����Ϣʵ��
 * @author Administrator
 *
 */
public class BatteryInfoEntity {
	
	private long id;
	private String serialNum;           //���к�  ��ص�Ψһ��ʶ
	private String manufacturerName;    //��������
	private String batteryName;         //�������
	private String batteryChemID;      //��о�ͺ�
	private Date manufactureDate;      //��������
	private Map<String,Object> extInfo;   // ������Ϣ1
	private Map<String,Object> sigleCellInfo;  //������Ϣ2
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getBatteryName() {
		return batteryName;
	}
	public void setBatteryName(String batteryName) {
		this.batteryName = batteryName;
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
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getBatteryChemID() {
		return batteryChemID;
	}
	public void setBatteryChemID(String batteryChemID) {
		this.batteryChemID = batteryChemID;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

//	public static BatteryInfoEntity toBatteryEntity(Document d){
//		BatteryInfoEntity battery = new BatteryInfoEntity();
//		
//		battery.setBatteryChemId(d.getString("batteryChemId"));
//		battery.setBatteryName(d.getString("batteryName"));
//		battery.setManuFactureName(d.getString("manuFactureName"));
//		battery.setSerialNum(d.getString("serialNum"));
//		battery.setManuFactureDate(new Date(d.getLong("manuFactureDate")));
//		battery.setExtInfo((Map<String,Object>)d.get("extInfo"));
//		battery.setSigleCellInfo((Map<String,Object>)d.get("sigleCellInfo"));
//		return battery;
//	}
	
}
