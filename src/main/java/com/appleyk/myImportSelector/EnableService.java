package com.appleyk.myImportSelector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableService {
}
