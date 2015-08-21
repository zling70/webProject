package com.zl.pojo;

public class Address {
	private String uid;//与用户关联的id
	private String addr;//用户地址
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Address [uid=" + uid + ", address=" + addr + "]";
	}
	
}
