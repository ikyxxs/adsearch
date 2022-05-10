package com.ikyxxs.adsearch.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LongField {
}
