package cn.myapp.model;

import java.util.Date;

public class Store {
    private Integer id;

    private String brand;

    private String model;

    private Date date;

    private Double price;

	private Double sales;

    private Integer count;
    
    private String type;
    
    private String dateString;
    
    public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString == null ? null : dateString.trim();
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }
    public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

    public void setCount(Integer count) {
        this.count = count;
    }
}