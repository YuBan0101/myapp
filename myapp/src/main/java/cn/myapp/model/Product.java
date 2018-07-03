package cn.myapp.model;

import java.util.Date;

public class Product {
    private Integer id;

    private String brand;

    private String model;

    private Integer count;
    
    private String type;
    
    private String LastDeliverDate;
    
    private String LastStoreDate;


    
    public String getLastDeliverDate() {
		return LastDeliverDate;
	}

	public void setLastDeliverDate(String lastDeliverDate) {
		LastDeliverDate = lastDeliverDate;
	}

	public String getLastStoreDate() {
		return LastStoreDate;
	}

	public void setLastStoreDate(String lastStoreDate) {
		LastStoreDate = lastStoreDate;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}