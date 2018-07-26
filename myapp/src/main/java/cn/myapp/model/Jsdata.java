package cn.myapp.model;

public class Jsdata {
	
	private Integer month;
	private String year;
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	private double salesMoney;

	public double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(double salesMoney) {
		this.salesMoney = salesMoney;
	}

	private double money;

}
