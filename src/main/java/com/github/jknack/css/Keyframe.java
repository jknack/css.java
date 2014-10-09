package com.github.jknack.css;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Lootsie
public class Keyframe {

	public int percentage = 0;
	
	// declaration matches ident to expression
	//public Map<String, Expression> properties = new LinkedHashMap<String, Expression>();
	public Map<String, List<Expression>> properties = new LinkedHashMap<String, List<Expression>>();
		
	public RuleCSSParameters cssParameters = new RuleCSSParameters();
	
	public Keyframe() {		
	    return;
	}
	
//	public void addProperty(final String name, final Expression expression) {
//	    properties.put(name, expression);	    
//	    cssParameters.addParameter(name, expression);
//	}

	public void addProperty(final String name, final List<Expression> expressionList) {
	    properties.put(name, expressionList);	    
	    cssParameters.addParameter(name, expressionList);
	}
	
	
//	public Expression property(final String name) {
//		return properties.get(name);
//	}	

	public List<Expression> property(final String name) {
		return properties.get(name);
	}	
	
	  @Override
	  public String toString() {
	    StringBuilder buffer = new StringBuilder();
//	    buffer.append("percentage: " + percentage);
	    buffer.append(percentage + "%");	    
	    buffer.append(" {\n");
//	    for (Entry<String, Expression> entry : properties.entrySet()) {
//	      buffer.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(";\n");
//	    }
	    for (Entry<String, List<Expression>> entry : properties.entrySet()) {
		      buffer.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(";\n");
		    }	    
	    buffer.append("}\n");	    
	    return buffer.toString();
	  }
	
	
}
