package br.com.doctorplus.gerenciador.api.annotations;

import br.com.doctorplus.gerenciador.api.validators.CaractereEspecialValidator;
import br.com.doctorplus.gerenciador.api.validators.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
public @interface Cpf {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
