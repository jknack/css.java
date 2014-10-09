package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSAnimationDirectionEnumType {
	NORMAL("normal"),
	REVERSE("reverse"),
	ALTERNATE("alternate"),
	ALTERNATE_REVERSE("alternate-reverse"),
	INITIAL("initial"),
	INHERIT("inherit")
	;
	
	//	linear	The animation has the same speed from start to end	Play it »
	//	ease	Default value. The animation has a slow start, then fast, before it ends slowly	Play it »
	//	ease-in	The animation has a slow start	Play it »
	//	ease-out	The animation has a slow end	Play it »
	//	ease-in-out	The animation has both a slow start and a slow end	Play it »
	//	cubic-bezier(n,n,n,n)
	
    private String param;

    private CSSAnimationDirectionEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSAnimationDirectionEnumType of(final String sunit) {
      for (CSSAnimationDirectionEnumType param : values()) {
        if (param.param.equalsIgnoreCase(sunit)) {
          return param;
        }
      }
      return NORMAL;
    }

    @Override
    public String toString() {
      return param;
    }	
}
