package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

// generic-font-family (serif, sans-serif, monospace)
public enum CSSGenericFontFamilyEnumType {
	SERIF("serif"),
	SANS_SERIF("sans-serif"),
	MONOSPACE("monospace"),
	INITIAL("initial"),
	INHERIT("inherit"),
	UNKNOWN("")
	;
	
    private String param;

    private CSSGenericFontFamilyEnumType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSGenericFontFamilyEnumType of(final String sunit) {
      for (CSSGenericFontFamilyEnumType param : values()) {
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