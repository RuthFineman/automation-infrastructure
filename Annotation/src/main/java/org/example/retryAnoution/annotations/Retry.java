package org.example.retryAnoution.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    int attempts() default 1;//כמה פעמים לנסות להריץ מחדש.

    long delay() default 0;//כמה מילי-שניות לחכות בין הרצה אחת לשניה.

    Class<? extends Throwable>[] retryOn()
            default {Exception.class};//על אילו שגיאות לבצע חזרה

}
