package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;

public class StationMarker extends CommonMarker {
	
	public static int DIA = 10;  // The size of the triangle marker
	

	public StationMarker(Location location) {
		super(location);
		// TODO Auto-generated constructor stub
	}
	
	public StationMarker(Feature station) {
		super(((PointFeature)station).getLocation(), station.getProperties());
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		 final int RED = pg.color(255, 0, 0);
		 final int GRN = pg.color(0, 255, 0);
		 final int BLU = pg.color(0, 0, 255);
		 final int YLW = pg.color(255, 255, 0);
		 final int SLV = pg.color(235, 235, 235);
		 final int ORG = pg.color(255, 166, 0);
		
		//System.out.println("Drawing a metro station");
		// Save previous drawing style
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		String lineColor = this.getPrimaryLine();
		int fillColor = 0;
		if(lineColor.equals("RD")){
			fillColor = RED;
		}
		else if(lineColor.equals("BL")){
			fillColor = BLU;
		}
		else if(lineColor.equals("GR")){
			fillColor = GRN;
		}
		else if(lineColor.equals("YL")){
			fillColor = YLW;
		}
		else if(lineColor.equals("SV")){
			fillColor = SLV;
		}
		else if(lineColor.equals("OR")){
			fillColor = ORG;
		}
		pg.fill(fillColor);
		pg.ellipse(x, y, DIA, DIA);
		
		// Restore previous drawing style
		pg.popStyle();
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		String name = getName();
		String lineCodes = getLineCodes();
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-DIA-39, Math.max(pg.textWidth(name), pg.textWidth(lineCodes)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-DIA-33);
		pg.text(lineCodes, x+3, y - DIA -18);
		
		pg.popStyle();
		
	}
	
	private String getName(){
		return "Station: " + getStringProperty("Name");
	}
	
	private String getLineCodes(){
		String fullCode = "Line: ";
		for(int i = 0; i < 4; i++){
			String thisCode = getStringProperty("LineCode" + Integer.toString(i+1));
			if(thisCode != null && thisCode != ""){
				if(i != 0)
					fullCode += ", ";
				fullCode += thisCode;
			}
			else{
				break;
			}
		}
		return fullCode;
	}
	
	private String getPrimaryLine(){
		return getStringProperty("LineCode1");
	}

}
