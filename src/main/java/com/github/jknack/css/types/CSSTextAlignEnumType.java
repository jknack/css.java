package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSTextAlignEnumType {
	LEFT("left"),
	RIGHT("right"),
	CENTER("center"),
	JUSTIFY("justify"),

	INITIAL("initial"),
	INHERIT("inherit")
	;
	
    private String param;

    private CSSTextAlignEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSTextAlignEnumType of(final String sunit) {
      for (CSSTextAlignEnumType param : values()) {
        if (param.param.equalsIgnoreCase(sunit)) {
          return param;
        }
      }
      return INITIAL;
    }

    @Override
    public String toString() {
      return param;
    }		
}