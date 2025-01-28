package com.api.main.dto;


public class BaciaHidrograficaDTO {

    private Long objectid;
    private String baciaNome;
    private Long baciaCod;
    private String shape;  // This will hold the GeoJSON as a String

    // Getters and Setters
    
    public Long getObjectid() {
        return objectid;
    }

    public BaciaHidrograficaDTO() {
		super();
	}

	public BaciaHidrograficaDTO(Long objectid) {
		super();
		this.objectid = objectid;
	}

	public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public String getBaciaNome() {
        return baciaNome;
    }

    public void setBaciaNome(String baciaNome) {
        this.baciaNome = baciaNome;
    }

    public Long getBaciaCod() {
        return baciaCod;
    }

    public void setBaciaCod(Long baciaCod) {
        this.baciaCod = baciaCod;
    }

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

    
}

