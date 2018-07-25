package cn.myapp.model;

public class Price {
    private Integer id;

    private String brand;

    private String model;

    private Double price;
    
    private Double oldPrice;
    
	private String dateNow;
    
    private String datePass;
    

	private Double sales;
	
	private String type;
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

    
    
    public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

    
    public String getDateNow() {
		return dateNow;
	}

	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}

	public String getDatePass() {
		return datePass;
	}

	public void setDatePass(String datePass) {
		this.datePass = datePass;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}