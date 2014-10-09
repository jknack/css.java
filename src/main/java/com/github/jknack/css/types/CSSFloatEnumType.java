package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSFloatEnumType {
	NONE(""),
	LEFT("left"),
	RIGHT("right"),
	INHERIT("inherit")
	;
	
    private String param;

    private CSSFloatEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSFloatEnumType of(final String sunit) {
      for (CSSFloatEnumType param : values()) {
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