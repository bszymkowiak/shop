package com.bartek.shop.validator;

import com.bartek.shop.validator.impl.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //informacyjna ze ta adnotacja bedzie adnotacja
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValid {

    String message() default "Passwords are not the same."; //komunikat, ktory pojawia siew momencie błędu walidacji

    Class<?>[] groups() default {}; // Flagi - kiedy ma być adnotacja a kiedy nie

    Class<? extends Payload>[] payload() default {}; //Mówi nam o tym, że rozszerzamy błąd dla validatora ktory dziedziczy po validatorze
}