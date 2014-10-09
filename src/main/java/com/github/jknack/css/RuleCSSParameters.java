package com.github.jknack.css;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.github.jknack.css.expression.ExpressionList;
import com.github.jknack.css.expression.FunctionExpression;
import com.github.jknack.css.expression.NumberExpression;
import com.github.jknack.css.expression.StringExpression;
import com.github.jknack.css.expression.HexColorExpression;
import com.github.jknack.css.expression.IdExpression;
import com.github.jknack.css.expression.URLExpression;
import com.github.jknack.css.types.CSSAnimationDirectionEnumType;
import com.github.jknack.css.types.CSSAnimationTimingFunctionEnumType;
import com.github.jknack.css.types.CSSColorType;
import com.github.jknack.css.types.CSSFloatEnumType;
import com.github.jknack.css.types.CSSFontSizeEnumType;
import com.github.jknack.css.types.CSSFontStyleEnumType;
import com.github.jknack.css.types.CSSFontVariantEnumType;
import com.github.jknack.css.types.CSSFontWeightEnumType;
import com.github.jknack.css.types.CSSGenericFontFamilyEnumType;
import com.github.jknack.css.types.CSSParamType;
import com.github.jknack.css.types.CSSTextAlignEnumType;

public class RuleCSSParameters {

//	int debugLevel = 1;
	int debugLevel = 0;
	  
	public NumberExpression width = null;
	public NumberExpression height = null;
	
	// animation parameter values for position
	// css - positioning
	public NumberExpression left = null;
	public NumberExpression right = null;	
	public NumberExpression top = null;
	public NumberExpression bottom = null;
	
	public CSSFloatEnumType cssFloat = CSSFloatEnumType.NONE;
		
	// box model - padding
	public NumberExpression paddingLeft = null;
	public NumberExpression paddingRight = null;	
	public NumberExpression paddingTop = null;
	public NumberExpression paddingBottom = null;

	// box model - margin
	public NumberExpression marginLeft = null;
	public NumberExpression marginRight = null;	
	public NumberExpression marginTop = null;
	public NumberExpression marginBottom = null;
	
	// box model - border
	public NumberExpression borderWidth = null;
	public CSSColorType borderColor = null;
	public NumberExpression borderRadius = null;	
	
	// font
	public CSSFontStyleEnumType fontStyle = CSSFontStyleEnumType.UNKNOWN;
	public CSSFontWeightEnumType fontWeight = CSSFontWeightEnumType.UNKNOWN;
	//public CSSFontSizeEnumType fontSize = CSSFontSizeEnumType.MEDIUM;	
	public NumberExpression fontSize = null;
	public CSSFontVariantEnumType fontVariant = CSSFontVariantEnumType.UNKNOWN;
	public CSSGenericFontFamilyEnumType genericFontFamily = CSSGenericFontFamilyEnumType.UNKNOWN;
	public IdExpression fontFamily = null;
	public CSSTextAlignEnumType textAlign = CSSTextAlignEnumType.INITIAL;
	
	public CSSColorType color = null;
	
	public NumberExpression opacity = null;
	
	public CSSColorType backgroundColor = null;
	public URLExpression backgroundImage = null;
	public URLExpression webkitMaskImage = null;
	
	public NumberExpression backgroundPositionX = null;
	public NumberExpression backgroundPositionY = null;
	
	public List<CSSAnimationParam> animations = new ArrayList<CSSAnimationParam>();
    
	public List<CSSTransformParam> transforms = new ArrayList<CSSTransformParam>();
	
	public String toString() {
		return toString("");
	}
	
	// change for lootsie keyframes being copied
//	public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
	
	public RuleCSSParameters copy() {
		RuleCSSParameters cssParam = new RuleCSSParameters();
		
		cssParam.width = this.width;
		cssParam.height = this.height;
		
		// animation parameter values for position
		// css - positioning
		cssParam.left = this.left;
		cssParam.right = this.right;
		cssParam.top = this.top;
		cssParam.bottom = this.bottom;
		
		cssParam.cssFloat = this.cssFloat;
			
		// box model - padding
		cssParam.paddingLeft = this.paddingLeft;
		cssParam.paddingRight = this.paddingRight;
		cssParam.paddingTop = this.paddingTop;
		cssParam.paddingBottom = this.paddingBottom;
		
		// box model - margin
		cssParam.marginLeft = this.marginLeft;
		cssParam.marginRight = this.marginRight;
		cssParam.marginTop = this.marginTop;
		cssParam.marginBottom = this.marginBottom;
			
		// box model - border
		cssParam.borderWidth = this.borderWidth;
		cssParam.borderColor = this.borderColor;
		cssParam.borderRadius = this.borderRadius;
				
		// font
		cssParam.fontStyle = this.fontStyle;
		cssParam.fontWeight = this.fontWeight;
		cssParam.fontSize = this.fontSize;
		cssParam.fontVariant = this.fontVariant;
		cssParam.genericFontFamily = this.genericFontFamily;
		cssParam.fontFamily = this.fontFamily;
		cssParam.textAlign = this.textAlign;
		
		cssParam.color = this.color;
		
		cssParam.opacity = this.opacity;
		
		cssParam.backgroundColor = this.backgroundColor;
		cssParam.backgroundImage = this.backgroundImage;
		cssParam.webkitMaskImage = this.webkitMaskImage;
		
		cssParam.backgroundPositionX = this.backgroundPositionX;
		cssParam.backgroundPositionY = this.backgroundPositionY;
		
		cssParam.animations = this.animations;
		
		cssParam.transforms = this.transforms;
		
		return cssParam;
	}
	
	//public void addParameter(final String name, final Expression expression) {
	public void addParameter(final String name, final List<Expression> expressionList) {
		
		
		RuleCSSParameters cssParameters = this;
		
		Expression expression = null;
		if (expressionList.size() > 0) {
			expression = expressionList.get(0);
		}
		
		CSSAnimationParam animation = null;		
		
		if (debugLevel > 0) System.out.println("\t\t RuleCSSParameters: addParameter: name: " + name);
		
		CSSParamType cssParamType = CSSParamType.of(name);
	    switch(cssParamType) {
	    
		case WIDTH:
			cssParameters.width = ((NumberExpression) expression);
			break;
		case HEIGHT:
			cssParameters.height = ((NumberExpression) expression);
			break;	
			
	    case LEFT:
	    	cssParameters.left = ((NumberExpression) expression);
	    	break;
		case BOTTOM:
			cssParameters.bottom = ((NumberExpression) expression);
			break;
		case RIGHT:
			cssParameters.right = ((NumberExpression) expression);
			break;
		case TOP:
			cssParameters.top = ((NumberExpression) expression);
			break;
			
		case FLOAT:
			String floatStr = ((IdExpression) expression).value();
			CSSFloatEnumType cssFloat = CSSFloatEnumType.of(floatStr);
			cssParameters.cssFloat = cssFloat;		
			break;
			
		// box model - padding
		case PADDING:
			cssParameters.paddingLeft = ((NumberExpression) expression);
			cssParameters.paddingRight = ((NumberExpression) expression);
			cssParameters.paddingTop = ((NumberExpression) expression);
			cssParameters.paddingBottom = ((NumberExpression) expression);
			break;
		case PADDING_LEFT:
			cssParameters.paddingLeft = ((NumberExpression) expression);
			break;
		case PADDING_RIGHT:
			cssParameters.paddingRight = ((NumberExpression) expression);
			break;
		case PADDING_TOP:
			cssParameters.paddingTop = ((NumberExpression) expression);
			break;
		case PADDING_BOTTOM:
			cssParameters.paddingBottom = ((NumberExpression) expression);
			break;		
			
		// box model - margin
		case MARGIN:
			cssParameters.marginLeft = ((NumberExpression) expression);
			cssParameters.marginRight = ((NumberExpression) expression);
			cssParameters.marginTop = ((NumberExpression) expression);
			cssParameters.marginBottom = ((NumberExpression) expression);
			break;
		case MARGIN_LEFT:
			cssParameters.marginLeft = ((NumberExpression) expression);
			break;
		case MARGIN_RIGHT:
			cssParameters.marginRight = ((NumberExpression) expression);
			break;
		case MARGIN_TOP:
			cssParameters.marginTop = ((NumberExpression) expression);
			break;
		case MARGIN_BOTTOM:
			cssParameters.marginBottom = ((NumberExpression) expression);
			break;
			
		// box model - border
		// border: border-width border-style border-color|initial|inherit;
		// http://www.w3schools.com/cssref/pr_border.asp
		case BORDER:
			if (expression instanceof ExpressionList) {
				ExpressionList borderExprList = ((ExpressionList) expression);
				for (int exprIndex = 0; exprIndex < borderExprList.size(); exprIndex++) {
					Expression borderExpr = borderExprList.get(exprIndex);			
					if (debugLevel > 0) System.out.println("\t\t\t borderExpr[" + exprIndex + "]: " + borderExpr.toString());
					
					if (exprIndex == 0) {
						if (borderExpr instanceof NumberExpression) {
							cssParameters.borderWidth = ((NumberExpression) borderExpr);
						} else {
							System.out.println("\t\t\t border-width isn't correct type!");
						}
					} else if (exprIndex == 1) {
						// border-style
					} else if (exprIndex == 2) {
						cssParameters.borderColor = new CSSColorType(borderExpr);
					}
					
				}				
			}
//			for (int exprIndex = 0; exprIndex < expressionList.size(); exprIndex++) {
//				Expression borderExpr = expressionList.get(exprIndex);			
//				if (debugLevel > 0) System.out.println("\t\t\t borderExpr[" + exprIndex + "]: " + borderExpr.toString());				
//			}
			break;
			
		case BORDER_WIDTH:
			cssParameters.borderWidth = ((NumberExpression) expression);
			break;
		case BORDER_COLOR:
			cssParameters.borderColor = new CSSColorType(expression);
			break;
		case BORDER_RADIUS:
			cssParameters.borderRadius = ((NumberExpression) expression);
			break;
			
		// font parameters
		// font: font-style font-variant font-weight font-size/line-height|caption|icon|menu|message-box|small-caption|status-bar|initial|inherit;
		//font-style
		//font-variant (unsupported)
		//font-weight
		//font-size
		//font-family		

		// examples:
		// font:15px arial,sans-serif;
		// font:italic bold 12px/30px Georgia, serif;
		// font:italic small-caps bold 12px arial,sans-serif
		// font:17px TrebuchetMS-Bold;
		// font:9px arial,sans-serif;
		case FONT:
			if (expression instanceof ExpressionList) {
				ExpressionList fontExprList = ((ExpressionList) expression);
				for (int exprIndex = 0; exprIndex < fontExprList.size(); exprIndex++) {
					Expression fontExpr = fontExprList.get(exprIndex);			
					if (debugLevel > 0) System.out.println("\t\t\t fontExpr[" + exprIndex + "]: " + fontExpr.toString());
					
					if (fontExpr instanceof IdExpression) {
						// font-style or font-weight or font-variant or font-family
						String testStr = ((IdExpression) fontExpr).value();
						
						// test font-style (normal, italic, oblique, initial, inherit)
						CSSFontStyleEnumType cssFontStyle = CSSFontStyleEnumType.of(testStr);
						if (cssFontStyle != CSSFontStyleEnumType.UNKNOWN) {
							cssParameters.fontStyle = cssFontStyle;
							if (debugLevel > 0) System.out.println("\t\t\t\t fontStyle: " + cssParameters.fontStyle.toString());
						} else {
							// test font-weight (normal, bold, bolder, lighter)
							CSSFontWeightEnumType cssFontWeight = CSSFontWeightEnumType.of(testStr);
							if (cssFontWeight != CSSFontWeightEnumType.UNKNOWN) {
								cssParameters.fontWeight = cssFontWeight;
								if (debugLevel > 0) System.out.println("\t\t\t\t fontWeight: " + cssParameters.fontWeight.toString());
							} else {
								// test font-variant (normal, small-caps, initial, inherit)
								CSSFontVariantEnumType cssFontVariant = CSSFontVariantEnumType.of(testStr);
								if (cssFontVariant != CSSFontVariantEnumType.UNKNOWN) {
									cssParameters.fontVariant = cssFontVariant;
									if (debugLevel > 0) System.out.println("\t\t\t\t fontVariant: " + cssParameters.fontVariant.toString());
								} else {
									// test generic-font-family (serif, sans-serif, monospace)
									CSSGenericFontFamilyEnumType cssGenericFontFamily = CSSGenericFontFamilyEnumType.of(testStr);
									if (cssGenericFontFamily != CSSGenericFontFamilyEnumType.UNKNOWN) {
										cssParameters.genericFontFamily = cssGenericFontFamily;
										if (debugLevel > 0) System.out.println("\t\t\t\t genericFontFamily: " + cssParameters.genericFontFamily.toString());										
									} else {
										// assume font-family
										cssParameters.fontFamily = ((IdExpression) fontExpr);
										if (debugLevel > 0) System.out.println("\t\t\t\t fontFamily: " + cssParameters.fontFamily.toString());
									}
								}
							}
						}
					} else if (fontExpr instanceof NumberExpression) {
						// font-size or line-height?
						cssParameters.fontSize = ((NumberExpression) fontExpr);
						if (debugLevel > 0) System.out.println("\t\t\t\t fontSize: " + cssParameters.fontSize.toString());
					}
					
//					// we cannot assume that font parameters are ordered - some may be missing
//					if (exprIndex == 0) {
//						// font-style
//						if (fontExpr instanceof IdExpression) {
//							String fontStyleStr = ((IdExpression) fontExpr).value();
//							CSSFontStyleEnumType cssFontStyle = CSSFontStyleEnumType.of(fontStyleStr);
//							cssParameters.fontStyle = cssFontStyle;
//						}
//					} else if (exprIndex == 1) {
//						// font-weight not font-variant?
//						if (fontExpr instanceof IdExpression) {
//							String fontWeightStr = ((IdExpression) fontExpr).value();
//							CSSFontWeightEnumType cssFontWeight = CSSFontWeightEnumType.of(fontWeightStr);
//							cssParameters.fontWeight = cssFontWeight;
//						}
//					} else if (exprIndex == 2) {
//						// font-size
//						if (fontExpr instanceof NumberExpression) {
//							cssParameters.fontSize = ((NumberExpression) fontExpr);
//						}
//					} else if (exprIndex == 3) {
//						// line-height - unsupported						
//					} else if (exprIndex == 4) {
//						// font-family			
//						if (fontExpr instanceof IdExpression) {
//							cssParameters.fontFamily = ((IdExpression) fontExpr);
//						}
//					}					
				}				
			}			
			break;
			
		case FONT_STYLE:
			String fontStyleStr = ((IdExpression) expression).value();
			CSSFontStyleEnumType cssFontStyle = CSSFontStyleEnumType.of(fontStyleStr);
			cssParameters.fontStyle = cssFontStyle;		
			break;
		case FONT_WEIGHT:
			String fontWeightStr = ((IdExpression) expression).value();
			CSSFontWeightEnumType cssFontWeight = CSSFontWeightEnumType.of(fontWeightStr);
			cssParameters.fontWeight = cssFontWeight;
			break;
		case FONT_SIZE:
//			if (expression instanceof IdExpression) {
//				String fontSizeStr = ((IdExpression) expression).value();			
//				CSSFontSizeEnumType cssFontSize = CSSFontSizeEnumType.of(fontSizeStr);
//				cssParameters.fontSize = cssFontSize;
//			}
			if (expression instanceof NumberExpression) {
				cssParameters.fontSize = ((NumberExpression) expression);
			}			
			break;
		case FONT_FAMILY:
			cssParameters.fontFamily = ((IdExpression) expression);
			break;
		case TEXT_ALIGN:
			String textAlignStr = ((IdExpression) expression).value();
			CSSTextAlignEnumType cssTextAlign = CSSTextAlignEnumType.of(textAlignStr);		
			cssParameters.textAlign = cssTextAlign;
			break;
			
		case COLOR:
			cssParameters.color = new CSSColorType(expression);
			break;		
			
		case OPACITY:
			cssParameters.opacity = ((NumberExpression) expression);
			break;
			
		case BACKGROUND_COLOR:
			cssParameters.backgroundColor = new CSSColorType(expression);
			break;
			
		case BACKGROUND_IMAGE:
			cssParameters.backgroundImage = ((URLExpression) expression);
			break;
			
		case WEBKIT_MASK_IMAGE:
			cssParameters.webkitMaskImage = ((URLExpression) expression);
			break;
		
		case BACKGROUND_POSITION_X:
			cssParameters.backgroundPositionX = ((NumberExpression) expression);
			break; 
			
		case BACKGROUND_POSITION_Y:
			cssParameters.backgroundPositionY = ((NumberExpression) expression);
			break; 
			
		case WEBKIT_ANIMATION:
			// parse transform values
			//for (Expression animationExpr : expressionList) {
			for (int exprIndex = 0; exprIndex < expressionList.size(); exprIndex++) {
				Expression animationExpr = expressionList.get(exprIndex);
				
				if (debugLevel > 0) System.out.println("\t\t\t animationExpr: " + exprIndex);
				
				if (animationExpr instanceof ExpressionList) {
					ExpressionList animationExprList = (ExpressionList) animationExpr;
					for (int subExprIndex = 0; subExprIndex < animationExprList.size(); subExprIndex++) {
						Expression animationSubExpr = animationExprList.get(subExprIndex);
						
						if (debugLevel > 0) System.out.println("\t\t\t\t animationSubExpr: " + subExprIndex + " : " + animationSubExpr.toString());
						
					    // parse animation list values as follows:
					    // animation: name duration timing-function iteration-count delay direction [fill-mode play-state];
						if (subExprIndex % 6 == 0) {
							animation = new CSSAnimationParam();
							animation.animationName = ((IdExpression) animationSubExpr);					
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationName: " + animation.animationName.toString());
							animations.add(animation);
						} else if (subExprIndex % 6 == 1) {
							animation.animationDurationNum = ((NumberExpression) animationSubExpr);
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDurationNum: " + animation.animationDurationNum.toString());
						} else if (subExprIndex % 6 == 2) {
							// timing-function
							String timingFunctionStr = animationSubExpr.toString();
							FunctionExpression timingFunExp = ((FunctionExpression) animationSubExpr);
							
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: " + timingFunctionStr);
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: name: " + timingFunExp.getName());
							animation.animationTimingFunction = CSSAnimationTimingFunctionEnumType.of(timingFunExp.getName());
							
							if (animation.animationTimingFunction == CSSAnimationTimingFunctionEnumType.STEPS) {
								for (int timingExprIndex = 0; timingExprIndex < timingFunExp.paramenters().size(); timingExprIndex++) {
									Expression timingFunExpParam = timingFunExp.paramenters().get(timingExprIndex);
									if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: param[" + timingExprIndex + "]: "+ timingFunExpParam.toString());		
									
									animation.animationStepsNum = ((NumberExpression) timingFunExpParam);
								}
							}
							
						} else if (subExprIndex % 6 == 3) {
							if (animationSubExpr instanceof NumberExpression) {
								animation.animationIterationCount = ((NumberExpression) animationSubExpr);
								if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount: " + animation.animationIterationCount.toString());
							} else if (animationSubExpr instanceof IdExpression) {
								IdExpression animationIterationCountIdEx = ((IdExpression) animationSubExpr);			
								if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (IdExpression): " + animationIterationCountIdEx.toString());
								if (animationIterationCountIdEx.value().compareToIgnoreCase("infinite") == 0) {
									NumberExpression numberEx = new NumberExpression("-1");
									animation.animationIterationCount = numberEx; // represent inifinite
									if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (NumberExpression): " + numberEx.toString() + " = " + numberEx.number());
								}								
							}
						} else if (subExprIndex % 6 == 4) {
							animation.animationDelay = ((NumberExpression) animationSubExpr);
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDelay: " + animation.animationDelay.toString());
						} else if (subExprIndex % 6 == 5) {
							// direction							
							animation.animationDirection = CSSAnimationDirectionEnumType.of(animationSubExpr.toString());
							if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDirection: " + animation.animationDirection.toString());
						}
					}
					
				}
				


			}
			
			if (debugLevel > 0) System.out.println("\t\t RuleCSSParameters: addParameter: -webkit-animation: " + expressionList.toString());
						
			
			break;
			
		case ANIMATION_NAME:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			animation.animationName = ((IdExpression) expression);
			if (debugLevel > 0) System.out.println("\t\t\t\t\t animationName: " + animation.animationName.toString());
			
			if (animations.size() == 0) {
				animations.add(animation);
			}
			break;
			
		case ANIMATION_DURATION:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			animation.animationDurationNum = ((NumberExpression) expression);
			if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDurationNum: " + animation.animationDurationNum.toString());
			
			if (animations.size() == 0) {
				animations.add(animation);
			}
			break;			

		case ANIMATION_ITERATION_COUNT:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			if (expression instanceof NumberExpression) {
				animation.animationIterationCount = ((NumberExpression) expression);
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (NumberExpression): " + animation.animationIterationCount.toString());
			} else if (expression instanceof StringExpression) {
				StringExpression animationIterationCountStrEx = ((StringExpression) expression);			
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (StringExpression): " + animationIterationCountStrEx.toString());
			} else if (expression instanceof IdExpression) {
				IdExpression animationIterationCountIdEx = ((IdExpression) expression);			
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (IdExpression): " + animationIterationCountIdEx.toString());
				if (animationIterationCountIdEx.value().compareToIgnoreCase("infinite") == 0) {
					NumberExpression numberEx = new NumberExpression("-1");
					animation.animationIterationCount = numberEx; // represent inifinite
					if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (NumberExpression): " + numberEx.toString() + " = " + numberEx.number());
				}
			} else {
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationIterationCount (unknown expression): ");
				
			}
			
			if (animations.size() == 0) {
				animations.add(animation);
			}			
			break;			
			
		case ANIMATION_TIMING_FUNCTION:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			// timing-function
			String timingFunctionStr = expression.toString();
			if (expression instanceof FunctionExpression) {
				FunctionExpression timingFunExp = ((FunctionExpression) expression);
				
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: " + timingFunctionStr);
				if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: name: " + timingFunExp.getName());
				animation.animationTimingFunction = CSSAnimationTimingFunctionEnumType.of(timingFunExp.getName());
				
				if (animation.animationTimingFunction == CSSAnimationTimingFunctionEnumType.STEPS) {
					for (int timingExprIndex = 0; timingExprIndex < timingFunExp.paramenters().size(); timingExprIndex++) {
						Expression timingFunExpParam = timingFunExp.paramenters().get(timingExprIndex);
						if (debugLevel > 0) System.out.println("\t\t\t\t\t animationTimingFunction: param[" + timingExprIndex + "]: "+ timingFunExpParam.toString());		
						
						animation.animationStepsNum = ((NumberExpression) timingFunExpParam);
					}
				}
			}
			
			if (animations.size() == 0) {
				animations.add(animation);
			}			
			break;				
			
		case ANIMATION_DELAY:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			animation.animationDelay = ((NumberExpression) expression);
			if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDelay: " + animation.animationDelay.toString());
			
			if (animations.size() == 0) {
				animations.add(animation);
			}			
			break;
			
		case ANIMATION_DIRECTION:
			if (animations.size() > 0) {
				animation = animations.get(0);
			} else {
				animation = new CSSAnimationParam();
			}
			
			animation.animationDirection = CSSAnimationDirectionEnumType.of(expression.toString());
			if (debugLevel > 0) System.out.println("\t\t\t\t\t animationDirection: " + animation.animationDirection.toString());
			
			if (animations.size() == 0) {
				animations.add(animation);
			}			
			break;			
			
		case ANIMATION_FILL_MODE:
			// ignored
			break;
			
		case ANIMATION_PLAY_STATE:
			// ignored
			break;

		case TRANSFORM:
			// parse transform values
			for (Expression transformExpr : expressionList) {
				CSSTransformParam transform = new CSSTransformParam(this);			
				transform.parseExpression(transformExpr);
				cssParameters.transforms.add(transform);
			}
			
			if (debugLevel > 0) System.out.println("\t\t RuleCSSParameters: addParameter: transforms: \n" + cssParameters.toString("\t\t"));
			
			break;
			
		case NONE:
			break;		
		default:
			break;    
	    }		
	}
	
	private String cssPositioningToString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		// css - positioning
	    if (left != null) {
	    	buffer.append(prefix + "\t").append("left: ");
	    	buffer.append(left.toString());
	    	buffer.append("\n");
	    }
	    
	    if (right != null) {
	    	buffer.append(prefix + "\t").append("right: ");
	    	buffer.append(right.toString());
	    	buffer.append("\n");
	    }
	    
	    if (top != null) {
	    	buffer.append(prefix + "\t").append("top: ");
	    	buffer.append(top.toString());
	    	buffer.append("\n");
	    }
	    
	    if (bottom != null) {
	    	buffer.append(prefix + "\t").append("bottom: ");
	    	buffer.append(bottom.toString());
	    	buffer.append("\n");
	    }
	    
	    return buffer.toString();
	}
	
	private String cssBoxModelPaddingToString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		if (paddingLeft != null) {
	    	buffer.append(prefix + "\t").append("padding-left: ");
	    	buffer.append(paddingLeft.toString());
	    	buffer.append("\n");
	    }
	    
	    if (paddingRight != null)  {
		    buffer.append(prefix + "\t").append("padding-right: ");
		    buffer.append(paddingRight.toString());
		    buffer.append("\n");
	    }
	    
	    if (paddingTop != null) { 
		    buffer.append(prefix + "\t").append("padding-top: ");
		    buffer.append(paddingTop.toString());
		    buffer.append("\n");
	    }
	    
	    if (paddingBottom != null) {
	    	buffer.append(prefix + "\t").append("padding-bottom: ");
	    	buffer.append(paddingBottom.toString());
	    	buffer.append("\n");
	    }		
	    
	    return buffer.toString();
	}
	
	private String cssBoxModelMarginToString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		if (marginLeft != null) {
	    	buffer.append(prefix + "\t").append("margin-left: ");
	    	buffer.append(marginLeft.toString());
	    	buffer.append("\n");
	    }
	    
	    if (marginRight != null) {
	    	buffer.append(prefix + "\t").append("margin-right: ");
	    	buffer.append(marginRight.toString());
	    	buffer.append("\n");
	    }
	    
	    if (marginTop != null) {
		    buffer.append(prefix + "\t").append("margin-top: ");
		    buffer.append(marginTop.toString());
		    buffer.append("\n");
	    }
	    
	    if (marginBottom != null) {
	    	buffer.append(prefix + "\t").append("margin-bottom: ");
	    	buffer.append(marginBottom.toString());
	    	buffer.append("\n");
	    }
	    
	    return buffer.toString();
	}
	
	private String cssBoxModelBorderToString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		 if (borderWidth != null) {
	    	buffer.append(prefix + "\t").append("border-width: ");
	    	buffer.append(borderWidth.toString());
	    	buffer.append("\n");
	    }
	    
	    if (borderColor != null) {
	    	buffer.append(prefix + "\t").append("border-color: ");
	    	buffer.append(borderColor.toString());
	    	buffer.append("\n");
	    }
	    
	    if (borderRadius != null) {
	    	buffer.append(prefix + "\t").append("border-radius: ");
	    	buffer.append(borderRadius.toString());
	    	buffer.append("\n");
	    }		
	    
	    return buffer.toString();		    
	}
	
	private String cssFontToString(String prefix) {
		StringBuilder buffer = new StringBuilder();
		
		if ((fontStyle != null) && (fontStyle != CSSFontStyleEnumType.UNKNOWN)) {
	    	buffer.append(prefix + "\t").append("font-style: ");
	    	buffer.append(fontStyle.toString());
	    	buffer.append("\n");
	    }
	    
	    if ((fontWeight != null) && (fontWeight != CSSFontWeightEnumType.UNKNOWN)) { 
	    	buffer.append(prefix + "\t").append("font-weight: ");
	    	buffer.append(fontWeight.toString());
	    	buffer.append("\n");
	    }
	    
	    if (fontSize != null) {
	    	buffer.append(prefix + "\t").append("font-size: ");
	    	buffer.append(fontSize.toString());
	    	buffer.append("\n");
	    }
	    
	    if (fontFamily != null) {
	    	buffer.append(prefix + "\t").append("font-family: ");
	    	buffer.append(fontFamily.toString());
	    	buffer.append("\n");
	    }
	    
	    if ((textAlign != null) && (textAlign != CSSTextAlignEnumType.INITIAL)) {
		    buffer.append(prefix + "\t").append("text-align: ");
		    buffer.append(textAlign.toString());
		    buffer.append("\n");
	    }
	    
	    // color
	    if (color != null) {
	    	buffer.append(color.toString());
	    	buffer.append("\n");
	    }
	    
	    return buffer.toString();
		
	}
	
	public String toString(String prefix) {
	    StringBuilder buffer = new StringBuilder();
	    buffer.append(prefix + "{\n");
	    
	    // dimensions
	    if (width != null)  {
	    	buffer.append(prefix + "\t").append("width: ");
		    buffer.append(width.toString());
		    buffer.append("\n");
	    }
	    
	    if (height != null) {
	    	buffer.append(prefix + "\t").append("height: ");
	    	buffer.append(height.toString());
		    buffer.append("\n");
	    }
	    
	    // animation parameters
	    buffer.append(cssPositioningToString(prefix));
	    
	    // float parameter
	    if ((cssFloat != null) && (cssFloat != CSSFloatEnumType.NONE)) {
		    buffer.append(prefix + "\t").append("float: ");
		    buffer.append(cssFloat.toString());
		    buffer.append("\n");
	    }
	    
	    // boxmodel - padding parameters
	    buffer.append(cssBoxModelPaddingToString(prefix));
	    
	    // boxmodel - margin parameters
	    buffer.append(cssBoxModelMarginToString(prefix));
	    
	    // boxmodel - border parameters
	    buffer.append(cssBoxModelBorderToString(prefix));
	    
	    // font parameters
	    buffer.append(cssFontToString(prefix));
	    
	    if (opacity != null) {
		    buffer.append(prefix + "\t").append("opacity: ");
		    buffer.append(opacity.toString());
		    buffer.append("\n");	    		    	
	    }
	    
	    if (backgroundImage != null) {
		    buffer.append(prefix + "\t").append("background-image: ");
		    buffer.append(backgroundImage.toString());
		    buffer.append("\n");	    	
	    }
	    
	    if (backgroundColor != null) {
		    buffer.append(prefix + "\t").append("background-color: ");
		    buffer.append(backgroundColor.toString());
		    buffer.append("\n");	    	
	    }	    
	    
	    if (webkitMaskImage != null) {
		    buffer.append(prefix + "\t").append("-webkit-mask-image: ");
		    buffer.append(webkitMaskImage.toString());
		    buffer.append("\n");	    	
	    }	    
	    
	    if (backgroundPositionX != null) {
		    buffer.append(prefix + "\t").append("background-position-x: ");
		    buffer.append(backgroundPositionX.toString());
		    buffer.append("\n");	    	
	    }
	    
	    if (backgroundPositionY != null) {
		    buffer.append(prefix + "\t").append("background-position-y: ");
		    buffer.append(backgroundPositionY.toString());
		    buffer.append("\n");	    	
	    }	 
	    
	    if ((animations != null) && (animations.size() > 0)) {
	    	buffer.append(prefix + "\t").append("animation: \n");
	    	
		    for (int animIndex = 0; animIndex < animations.size(); animIndex++) {
		    	CSSAnimationParam animation = animations.get(animIndex);
		    	buffer.append(animation.toString(prefix) + " ");				    	
		    }
		    buffer.append("\n");	    	
	    }

	    if ((transforms != null) && (transforms.size() > 0)){
		    buffer.append(prefix + "\t").append("transform: ");
		    
		    for (int transformIndex = 0; transformIndex < transforms.size(); transformIndex++) {
		    	CSSTransformParam transform = transforms.get(transformIndex);
		    	buffer.append(transform.toString(prefix) + " ");				    	
		    }
		    buffer.append("\n");
		    
//			    buffer.append(transform.toString());
//			    buffer.append(transform.getName() + " ");
		    
//			    ExpressionList parameters = transform.paramenters();
//			    for (Expression parameter : parameters.expressions()) {
//			    	buffer.append("parameter: " + parameter.toString() + " ");
//			    	buffer.append(parameter.toString() + " ");
//			    }
		    
		    buffer.append("\n");		    	
	    }
	    
	    
	    buffer.append(prefix + "}\n");	    
	    return buffer.toString();
	  }	
}
