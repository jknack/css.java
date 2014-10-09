package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSFontWeightEnumType {
	NORMAL("normal"),
	BOLD("bold"),
	BOLDER("bolder"),
	LIGHTER("lighter"),
	NUM_100("100"),
	NUM_200("200"),
	NUM_300("300"),
	NUM_400("400"),
	NUM_500("500"),
	NUM_600("600"),
	NUM_700("700"),
	NUM_800("800"),
	NUM_900("900"),		
	INITIAL("initial"),
	INHERIT("inherit"),
	UNKNOWN("")
	;
	
    private String param;

    private CSSFontWeightEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSFontWeightEnumType of(final String sunit) {
      for (CSSFontWeightEnumType param : values()) {
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