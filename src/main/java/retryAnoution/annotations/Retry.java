package retryAnoution.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    int attempts() default 1;

    long delay() default 0;

    Class<? extends Throwable>[] retryOn()
            default {Exception.class};

}
