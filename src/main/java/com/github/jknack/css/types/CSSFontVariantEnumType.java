package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

// font-variant (normal, small-caps, initial, inherit)
public enum CSSFontVariantEnumType {
	NORMAL("normal"),
	SMALL_CAPS("small-caps"),
	INITIAL("initial"),
	INHERIT("inherit"),
	UNKNOWN("")
	;
	
    private String param;

    private CSSFontVariantEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSFontVariantEnumType of(final String sunit) {
      for (CSSFontVariantEnumType param : values()) {
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