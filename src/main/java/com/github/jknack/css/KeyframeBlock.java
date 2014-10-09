package com.github.jknack.css;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

public class KeyframeBlock {

	public String name = "";
	public final ArrayList<Keyframe> keyframes = new ArrayList<Keyframe>();
	
	public void add(final Keyframe keyframe) {
		keyframes.add(keyframe);
	}	
	
	  @Override
	  public String toString() {
	    StringBuilder buffer = new StringBuilder();
//	    buffer.append("name: " + name);	
	    buffer.append("@keyframes " + name);	
	    buffer.append(" {\n");
	    for (Keyframe keyframe : keyframes) {
	      buffer.append(keyframe.toString());
	    }
	    buffer.append("}\n");	    
	    return buffer.toString();
	  }	
}
