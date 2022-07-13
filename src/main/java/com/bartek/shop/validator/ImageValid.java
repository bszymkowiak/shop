package com.bartek.shop.validator;

import com.bartek.shop.validator.impl.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageValidator.class)
public @interface ImageValid {

    String message() default "Image must be .jpg or .png"; //komunikat, ktory pojawia siew momencie błędu walidacji

    Class<?>[] groups() default {}; // Flagi - kiedy ma być adnotacja a kiedy nie

    Class<? extends Payload>[] payload() default {}; //Mówi nam o tym, że rozszerzamy błąd dla validatora ktory dziedziczy po validatorze
}
