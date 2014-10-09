package com.github.jknack.css.types;

import com.github.jknack.css.Expression;
import com.github.jknack.css.expression.FunctionExpression;
import com.github.jknack.css.expression.HexColorExpression;
import com.github.jknack.css.expression.NumberExpression;

public class CSSColorType {
	// color may be of the format rgb(255,255,255);
	// or 
	// #ff0000; -red
	// #00ff00; -green
	// #0000ff; -blue
	public HexColorExpression colorHex = null;
	public NumberExpression colorR = null;
	public NumberExpression colorG = null;
	public NumberExpression colorB = null;
	
	private int debugLevel = 0;
	
	public CSSColorType(Expression expression) {
		
		if (expression instanceof HexColorExpression) {
			this.colorHex = ((HexColorExpression) expression);
		} else if (expression instanceof FunctionExpression) {
			FunctionExpression colorFuncExp = ((FunctionExpression) expression);
			if (debugLevel > 0) System.out.println("\t\t\t colorFuncExp: " + colorFuncExp);
			if (colorFuncExp.getName().equalsIgnoreCase("rgb")) {
				for (int subExprIndex = 0; subExprIndex < colorFuncExp.paramenters().size(); subExprIndex++) {
					Expression colorSubExp = colorFuncExp.paramenters().get(subExprIndex);
					if (subExprIndex == 0) {
						this.colorR = ((NumberExpression) colorSubExp);
					} else if (subExprIndex == 1) {
						this.colorG = ((NumberExpression) colorSubExp);
					} else if (subExprIndex == 2) {
						this.colorB = ((NumberExpression) colorSubExp);
					}
				}
				if (debugLevel > 0) System.out.println("\t\t\t color: rgb " + colorR.toString() + "," +
						colorG.toString() + "," + colorB.toString());
			} else {
				if (debugLevel > 0) System.out.println("\t\t\t colorFuncExp: " + colorFuncExp.toString() + " isn't rgb");
			}
		} else {
			if (debugLevel > 0) System.out.println("\t\t\t unknown color expression: " + expression.toString());
		}		
		
		
	}
	
	public String toString() {		
		return toString("");
	}
	
	public String toString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		if (colorHex != null) {
	    	buffer.append(prefix).append("hexcolor:");
	    	buffer.append(colorHex.toString());
	    } 
	    
	    if ((colorR != null) && (colorG != null) && (colorB != null)) {
	    	buffer.append(prefix).append("rgb:");
	    	buffer.append(colorR.number().intValue() + "," + colorG.number().intValue() + "," + colorB.number().intValue());
	    }	
	    
	    return buffer.toString();		    
	}    
}
