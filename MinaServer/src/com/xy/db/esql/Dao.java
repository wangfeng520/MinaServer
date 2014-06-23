package com.xy.db.esql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Dao {
	public static enum DB {
		POSTGRESQL, ORACLE
	};

	DB[] support() default { DB.POSTGRESQL, DB.ORACLE };
}
