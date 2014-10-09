package com.github.jknack.css;

import com.github.jknack.css.expression.IdExpression;
import com.github.jknack.css.expression.NumberExpression;
import com.github.jknack.css.expression.StringExpression;
import com.github.jknack.css.types.CSSAnimationDirectionEnumType;
import com.github.jknack.css.types.CSSAnimationTimingFunctionEnumType;

public class CSSAnimationParam {
	
	// input:
    // playv 1s steps(4) 1 2s normal
    // -webkit-animation:  playv 0.25s steps(4) 1 2s normal, playh .0625s steps(4) 4 2s normal;
	
    // parse animation list values as follows:
    // animation: name duration timing-function iteration-count delay direction [fill-mode play-state];
    
    // Chrome, Safari, Opera
    //    -webkit-animation-name: myfirst;
    //    -webkit-animation-duration: 5s;
    //    -webkit-animation-timing-function: linear;
    //    -webkit-animation-delay: 2s;
    //    -webkit-animation-iteration-count: infinite;
    //    -webkit-animation-direction: alternate;
    //    -webkit-animation-play-state: running;
    
    // Standard syntax
    //    animation-name: myfirst;
    //    animation-duration: 5s;
    //    animation-timing-function: linear;
    //    animation-delay: 2s;
    //    animation-iteration-count: infinite;
    //    animation-direction: alternate;
    //    animation-play-state: running;		

	public IdExpression animationName = null;
	public NumberExpression animationDelay = null;
	public NumberExpression animationIterationCount = null;	
	public CSSAnimationTimingFunctionEnumType animationTimingFunction = null;
	public NumberExpression animationDurationNum = null;
	public NumberExpression animationStepsNum = null;
	public CSSAnimationDirectionEnumType animationDirection = null;
	
	public String toString(String prefix) {
		StringBuilder buffer = new StringBuilder();
			
		// animation parameters
	    if (animationName != null) {
	    	buffer.append(prefix + "\t").append("animation-name: ");
	    	buffer.append(animationName.toString());
			buffer.append("\n");
	    }
	    
	    if (animationDurationNum != null) {
		    buffer.append(prefix + "\t\t").append("animation-duration: ");
		    buffer.append(animationDurationNum.toString());
		    buffer.append("\n");
	    }	
	    
	    if (animationTimingFunction != null) {
		    buffer.append(prefix + "\t\t").append("animation-timing-function: ");
		    buffer.append(animationTimingFunction.toString());
		    
		    if (animationTimingFunction == CSSAnimationTimingFunctionEnumType.STEPS) {
		    	buffer.append(" " + animationStepsNum.toString());
		    }
		    
		    buffer.append("\n");
	    }		    

	    if (animationIterationCount != null) {
		    buffer.append(prefix + "\t\t").append("animation-iteration-count: ");
		    buffer.append(animationIterationCount.toString());
		    buffer.append("\n");
	    }		    
	    
	    if (animationDelay != null) {
		    buffer.append(prefix + "\t\t").append("animation-delay ");
		    buffer.append(animationDelay.toString());
		    buffer.append("\n");
	    }	  
	    
	    if (animationDirection != null) {
		    buffer.append(prefix + "\t\t").append("animation-direction ");
		    buffer.append(animationDirection.toString());
		    buffer.append("\n");
	    }
	    
	    return buffer.toString();
	}
}
