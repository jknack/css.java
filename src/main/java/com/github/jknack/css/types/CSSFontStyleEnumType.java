package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSFontStyleEnumType {
	NORMAL("normal"),
	ITALIC("italic"),
	OBLIQUE("oblique"),
	INITIAL("initial"),
	INHERIT("inherit"),
	UNKNOWN("")
	;
	
    private String param;

    private CSSFontStyleEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSFontStyleEnumType of(final String sunit) {
      for (CSSFontStyleEnumType param : values()) {
        if (param.param.equalsIgnoreCase(sunit)) {
          return param;
        }
      }
      return UNKNOWN;
    }

    @Override
    public String toString() {
      return param;
    }		
}