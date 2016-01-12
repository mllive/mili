package com.robot.stock.model;

public class StockModel {
	String name;
	String code;
	Double nowprice;
	Double openprice;
	Double closeprice;
	Double maxprice;
	Double minprice;
	String date;
	String time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getNowprice() {
		return nowprice;
	}

	public void setNowprice(Double nowprice) {
		this.nowprice = nowprice;
	}

	public Double getOpenprice() {
		return openprice;
	}

	public void setOpenprice(Double openprice) {
		this.openprice = openprice;
	}

	public Double getCloseprice() {
		return closeprice;
	}

	public void setCloseprice(Double closeprice) {
		this.closeprice = closeprice;
	}

	public Double getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}

	public Double getMinprice() {
		return minprice;
	}

	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
