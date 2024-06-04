package com.api.main.dto;

import com.vividsolutions.jts.geom.Geometry;

public class InterferenciaDTO {

	private Double interLatitude;

	private Double interLongitude;

	private Geometry intGeometry;

	public Double getInterLatitude() {
		return interLatitude;
	}

	public void setInterLatitude(Double interLatitude) {
		this.interLatitude = interLatitude;
	}

	public Double getInterLongitude() {
		return interLongitude;
	}

	public void setInterLongitude(Double interLongitude) {
		this.interLongitude = interLongitude;
	}

	public Geometry getIntGeometry() {
		return intGeometry;
	}

	public void setIntGeometry(Geometry intGeometry) {
		this.intGeometry = intGeometry;
	}

}
