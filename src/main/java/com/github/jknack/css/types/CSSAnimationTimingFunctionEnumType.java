package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSAnimationTimingFunctionEnumType {
	NONE(""),
	LINEAR("linear"),
	EASE("ease"),
	EASE_IN("ease-in"),
	EASE_OUT("ease-out"),
	EASE_IN_OUT("ease-in-out"),
	CUBIC_BEZIER("cubic-bezier"),
	STEP_START("step-start"),
	STEP_END("step-end"),
	STEPS("steps")
	;
	
	//	linear	The animation has the same speed from start to end	Play it »
	//	ease	Default value. The animation has a slow start, then fast, before it ends slowly	Play it »
	//	ease-in	The animation has a slow start	Play it »
	//	ease-out	The animation has a slow end	Play it »
	//	ease-in-out	The animation has both a slow start and a slow end	Play it »
	//	cubic-bezier(n,n,n,n)
	
    private String param;

    private CSSAnimationTimingFunctionEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSAnimationTimingFunctionEnumType of(final String sunit) {
      for (CSSAnimationTimingFunctionEnumType param : values()) {
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
