package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSTransformEnumType {
		NONE(""),
		
		// css3 2d transform paramteters
		TRANSLATE("translate"),
		TRANSLATE_X("translateX"),
		TRANSLATE_Y("translateY"),
		SCALE("scale"),
		SCALE_X("scaleX"),
		SCALE_Y("scaleY"),
		ROTATE("rotate"),
		SKEW("skew"),
		SKEW_X("skewX"),
		SKEW_Y("skewY"),
		
		TRANSLATE_3D("translate3d"),
//		TRANSLATE_X("translateX"),
//		TRANSLATE_Y("translateY"),
		TRANSLATE_Z("translateZ"),
				
		ROTATE_3D("rotate3d"),
		ROTATE_X("rotateX"),
		ROTATE_Y("rotateY"),
		ROTATE_Z("rotateZ"),
		
		SCALE_3D("scale3d"),
//		SCALE_X("scaleX"),
//		SCALE_Y("scaleY"),
		SCALE_Z("scaleZ"),
		
		PERSPECTIVE("perspective")
		;
		
	    private String param;

	    private CSSTransformEnumType(final String param) {
	      this.param = notNull(param, "The unit is required.");
	    }

	    public String param() {
	      return param;
	    }

	    public static CSSTransformEnumType of(final String sunit) {
	      for (CSSTransformEnumType param : values()) {
	        if (param.param.equalsIgnoreCase(sunit)) {
	          return param;
	        }
	      }
	      return NONE;
	    }

	    @Override
	    public String toString() {
	      return param;
	    }		
	}