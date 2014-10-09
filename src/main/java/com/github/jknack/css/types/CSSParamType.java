package com.github.jknack.css.types;

import static org.apache.commons.lang3.Validate.notNull;

public enum CSSParamType {
	NONE(""),
	
	// css - dimension
	// http://www.w3schools.com/css/css_dimension.asp
	WIDTH("width"),
	HEIGHT("height"),
	
	// css - positioning
	// http://www.w3schools.com/css/css_positioning.asp
    LEFT("left"),
    RIGHT("right"),
    TOP("top"),
    BOTTOM("bottom"),
    
    // float positioning algorithm
    // http://www.w3schools.com/css/css_float.asp
    FLOAT("float"),
    
    // box model - padding
    // http://www.w3schools.com/css/css_padding.asp
    PADDING("padding"),
    PADDING_LEFT("padding-left"),
    PADDING_RIGHT("padding-right"),
    PADDING_TOP("padding-top"),
    PADDING_BOTTOM("padding-bottom"),
    
    // box model - margin
    MARGIN("margin"),
    MARGIN_LEFT("margin-left"),
    MARGIN_RIGHT("margin-right"),
    MARGIN_TOP("margin-top"),
    MARGIN_BOTTOM("margin-bottom"),
    
    // box model - border
    BORDER("border"),
    BORDER_WIDTH("border-width"),
    BORDER_COLOR("border-color"),
    BORDER_RADIUS("border-radius"),
    
    // font parameters
    FONT("font"),
    FONT_STYLE("font-style"),
    FONT_WEIGHT("font-weight"),
    FONT_SIZE("font-size"),
    FONT_FAMILY("font-family"),
    TEXT_ALIGN("text-align"),
    
    COLOR("color"),
    
    BACKGROUND_COLOR("background-color"),
    BACKGROUND_IMAGE("background-image"),
    WEBKIT_MASK_IMAGE("-webkit-mask-image"),
    OPACITY("opacity"),
    
    BACKGROUND_POSITION_X("background-position-x"),
    BACKGROUND_POSITION_Y("background-position-y"),
    
    // css - animation parameters
    // http://www.w3schools.com/cssref/css3_pr_animation.asp
    WEBKIT_ANIMATION("-webkit-animation"),
    ANIMATION_NAME("animation-name"),
    ANIMATION_DURATION("animation-duration"),
    ANIMATION_TIMING_FUNCTION("animation-timing-function"),
    ANIMATION_DELAY("animation-delay"),
    ANIMATION_ITERATION_COUNT("animation-iteration-count"),
    ANIMATION_DIRECTION("animation-direction"),
    ANIMATION_FILL_MODE("animation-fill-mode"),
    ANIMATION_PLAY_STATE("animation-play-state"),
    
    TRANSFORM("transform")
    ;
    
    private String param;

    private CSSParamType(final String param) {
      this.param = notNull(param, "The unit is required.");
    }

    public String param() {
      return param;
    }

    public static CSSParamType of(final String sunit) {
      for (CSSParamType param : values()) {
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