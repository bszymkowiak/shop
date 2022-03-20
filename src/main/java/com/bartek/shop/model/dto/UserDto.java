package com.bartek.shop.model.dto;

import com.bartek.shop.validator.PasswordValid;
import com.bartek.shop.validator.group.Create;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //Jeśli jakieś pole w obiekcie będzie NULL to wtedy nie doda się w Jasonie w odpowiedzi dla klienta
@PasswordValid(groups = Create.class)
public class UserDto {

//    @NotNull //do wsystkich innych obiektow
    @Null
    private Long id;
    @NotBlank(message = "Nie możesz podać żadnej wartości") //tylko dla stringów. sprawdza czy po usunieciu wszystkich spacji, długość będzie większa od 0
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String login;
    @NotBlank
    @Length(min = 6)
//    @Pattern // jakbysmy chcieli wynagac od usera w haśle znaku specjalnego i liczby musimy stworzyć wlasnego regexa
    private String password;
    @NotBlank
    private String confirmedPassword;
    @NotBlank
    @Email
    private String email;
}