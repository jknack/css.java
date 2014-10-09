package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSFontSizeEnumType {
	MEDIUM("medium"),
	XX_SMALL("xx-small"),
	X_SMALL("x-small"),
	SMALL("small"),
	LARGE("large"),
	X_LARGE("x-large"),
	XX_LARGE("xx-large"),
	SMALLER("smaller"),
	LARGER("larger"),
	// length
	// %
	INITIAL("initial"),
	INHERIT("inherit")
	;
	
    private String param;

    private CSSFontSizeEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSFontSizeEnumType of(final String sunit) {
      for (CSSFontSizeEnumType param : values()) {
        if (param.param.equalsIgnoreCase(sunit)) {
          return param;
        }
      }
      return MEDIUM;
    }

    @Override
    public String toString() {
      return param;
    }		
}