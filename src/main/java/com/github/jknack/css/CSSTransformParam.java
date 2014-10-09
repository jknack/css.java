package com.github.jknack.css;

import com.github.jknack.css.expression.ExpressionList;
import com.github.jknack.css.expression.FunctionExpression;
import com.github.jknack.css.expression.NumberExpression;
import com.github.jknack.css.types.CSSTransformEnumType;

// transform parameters
	public class CSSTransformParam {
		/**
		 * 
		 */
		private final RuleCSSParameters ruleCSSParameters;

		/**
		 * @param ruleCSSParameters
		 */
		CSSTransformParam(RuleCSSParameters ruleCSSParameters) {
			this.ruleCSSParameters = ruleCSSParameters;
		}

		// css3 2d transform parameter values
		public NumberExpression rotate = null;
		
		public NumberExpression translateX = null;
		public NumberExpression translateY = null;
		
		public NumberExpression scaleX = null;
		public NumberExpression scaleY = null;
		
		public NumberExpression skewX = null;
		public NumberExpression skewY = null;		
		
		// css3 3d transform parameter values
		public NumberExpression translate3dX = null;
		public NumberExpression translate3dY = null;
		public NumberExpression translate3dZ = null;

		public NumberExpression scale3dX = null;
		public NumberExpression scale3dY = null;
		public NumberExpression scale3dZ = null;
		
		// rotate around vector formed by vector x,y,z
		public NumberExpression rotate3dX = null;
		public NumberExpression rotate3dY = null;
		public NumberExpression rotate3dZ = null;
		public NumberExpression rotate3dAngle = null;
		
		public NumberExpression perspective3d = null;
		
		public CSSTransformEnumType transformType = CSSTransformEnumType.NONE;
		
		// for debugging
		public FunctionExpression transformExpression = null;
		
		public void parseExpression(Expression expression) {
			
			CSSTransformParam transform = this;
			
			
			
			transform.transformExpression = ((FunctionExpression) expression);
			String transformNameStr = transform.transformExpression.getName();
			transform.transformType = CSSTransformEnumType.of(transformNameStr);
			
			if (this.ruleCSSParameters.debugLevel > 0) System.out.println("\t\t\t CSSTransformParam: parseExpression: name: " + transformNameStr);
			
			ExpressionList parameters = transform.transformExpression.paramenters();

			switch (transform.transformType) {
			
				// css3 2d transform parameters
				case TRANSLATE:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.translateX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.translateY = ((NumberExpression) parameter);
				    	}
				    }			
					break;
					
				case TRANSLATE_X:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.translateX = ((NumberExpression) parameter);
				    }			
					break;

				case TRANSLATE_Y:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
			    		transform.translateY = ((NumberExpression) parameter);
				    }			
					break;

				case SCALE:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.scaleX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.scaleY = ((NumberExpression) parameter);
				    	}
				    }			
					break;
					
				case SCALE_X:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.scaleX = ((NumberExpression) parameter);
				    }			
					break;

				case SCALE_Y:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
			    		transform.scaleY = ((NumberExpression) parameter);
				    }			
					break;				
					
				case SKEW:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.skewX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.skewY = ((NumberExpression) parameter);
				    	}
				    }			
					break;
					
				case SKEW_X:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.skewX = ((NumberExpression) parameter);
				    }			
					break;

				case SKEW_Y:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
			    		transform.skewY = ((NumberExpression) parameter);
				    }			
					break;					
					
				case ROTATE:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.rotate = ((NumberExpression) parameter);
				    }			
				    break;
				
				 // css3 3d transform parameters				    
				case TRANSLATE_3D:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.translate3dX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.translate3dY = ((NumberExpression) parameter);
				    	} else if (paramIndex == 2) {
				    		transform.translate3dZ = ((NumberExpression) parameter);
				    	}
				    }			
					break;
					
				case TRANSLATE_Z:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.translate3dZ = ((NumberExpression) parameter);
				    }			
					break;

				case ROTATE_3D:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.rotate3dX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.rotate3dY = ((NumberExpression) parameter);
				    	} else if (paramIndex == 2) {
				    		transform.rotate3dZ = ((NumberExpression) parameter);
				    	} else if (paramIndex == 3) {
				    		transform.rotate3dAngle = ((NumberExpression) parameter);
				    	}
				    }			
					break;				
					
				case ROTATE_X:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.rotate3dX = ((NumberExpression) parameter);
				    }			
					break;					

				case ROTATE_Y:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.rotate3dY = ((NumberExpression) parameter);
				    }			
//				    if (debugLevel > 0) System.out.println("transform: rotateY: " + transform.rotate3dY.toString());
					break;					

				case ROTATE_Z:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	transform.rotate3dZ = ((NumberExpression) parameter);
				    }			
					break;					
					
				case SCALE_3D:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
				    	if (paramIndex == 0) {
				    		transform.scale3dX = ((NumberExpression) parameter);
				    	} else if (paramIndex == 1) {
				    		transform.scale3dY = ((NumberExpression) parameter);
				    	} else if (paramIndex == 2) {
				    		transform.scale3dZ = ((NumberExpression) parameter);
				    	}
				    }			
					break;			
					
				case SCALE_Z:
				    for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
			    		transform.scale3dZ = ((NumberExpression) parameter);
				    }			
					break;						
					
				case PERSPECTIVE:
					for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
				    	Expression parameter = parameters.get(paramIndex);
			    		transform.perspective3d = ((NumberExpression) parameter);
				    }			
					break;
				    
				case NONE:
					break;
			}			
		}
		
		public String toString(String prefix) {
			StringBuilder buffer = new StringBuilder();
			
			CSSTransformParam transform = this;
			
			if (transform.rotate != null) {
	    		buffer.append("rotate: " + transform.rotate.toString() + " ");
	    	}

	    	if (transform.translateX != null) {
	    		buffer.append("translateX: " + transform.translateX.toString() + " ");
	    	}
	    	
	    	if (transform.translateY != null) {
	    		buffer.append("translateY: " + transform.translateY.toString() + " ");			    		
	    	}
	    	
	    	if (transform.scaleX != null) {
	    		buffer.append("scaleX: " + transform.scaleX.toString() + " ");
	    	}
	    	
	    	if (transform.scaleY != null) {
	    		buffer.append("scaleY: " + transform.scaleY.toString() + " ");			    		
	    	}			    	
	    	
	    	if (transform.skewX != null) {
	    		buffer.append("skewX: " + transform.skewX.toString() + " ");
	    	}
	    	
	    	if (transform.skewY != null) {
	    		buffer.append("skewY: " + transform.skewY.toString() + " ");			    		
	    	}			

	    	// all 3d transforms together
	    	if ((transform.translate3dX != null) &&
	    			(transform.translate3dY != null) &&
	    			(transform.translate3dZ != null) )
	    	{
	    		buffer.append("translate3d: " + transform.translate3dX.toString() + " "
	    				+ transform.translate3dY.toString() + " "
	    				+ transform.translate3dZ.toString() + " "
	    				);			    		
	    	} else {
	    		// separate 3d transforms
	    		if (transform.translate3dX != null) {
	    			buffer.append("translate3dX: " + transform.translate3dX.toString() + " ");
	    		}
	    		if (transform.translate3dY != null) {
	    			buffer.append("translate3dY: " + transform.translate3dY.toString() + " ");
	    		}
	    		if (transform.translate3dZ != null) {
	    			buffer.append("translate3dZ: " + transform.translate3dZ.toString() + " ");
	    		}
	    		
	    	}
	    	
	    	if ((transform.rotate3dX != null) &&
	    			(transform.rotate3dY != null) &&
	    			(transform.rotate3dZ != null) &&
	    			(transform.rotate3dAngle != null)) 
	    	{
	    		buffer.append("rotate3d: " + transform.rotate3dX.toString() + " "
	    				+ transform.rotate3dY.toString() + " "
	    				+ transform.rotate3dZ.toString() + " "
	    				+ transform.rotate3dAngle.toString());
	    	} else {
	    		if (transform.rotate3dX != null) {
	    			buffer.append("rotate3dX: " + transform.rotate3dX.toString() + " ");
	    		}
	    		if (transform.rotate3dY != null) {
	    			buffer.append("rotate3dY: " + transform.rotate3dY.toString() + " ");
	    		}
	    		if (transform.rotate3dZ != null) {
	    			buffer.append("rotate3dZ: " + transform.rotate3dZ.toString() + " ");
	    		}
	    		if (transform.rotate3dAngle != null) {
	    			buffer.append("rotate3dAngle: " + transform.rotate3dAngle.toString() + " ");
	    		}
	    	}
	    	
	    	if ((transform.scale3dX != null) &&
	    			(transform.scale3dY != null) &&
	    			(transform.scale3dZ != null) )
	    	{
	    		buffer.append("scale3d: " + transform.scale3dX.toString() + " "
	    				+ transform.scale3dY.toString() + " "
	    				+ transform.scale3dZ.toString() + " "
	    				);			    		
	    	} else {
	    		// separate 3d transforms
	    		if (transform.scale3dX != null) {
	    			buffer.append("scale3dX: " + transform.scale3dX.toString() + " ");
	    		}
	    		if (transform.scale3dY != null) {
	    			buffer.append("scale3dY: " + transform.scale3dY.toString() + " ");
	    		}
	    		if (transform.scale3dZ != null) {
	    			buffer.append("scale3dZ: " + transform.scale3dZ.toString() + " ");
	    		}
	    		
	    	}	    	
	    	
	    	if (transform.perspective3d != null) {
	    		buffer.append("perspective: " + transform.perspective3d.toString() + " ");			    		
	    	}		    	
	    	
	    	return buffer.toString();
		}
	}