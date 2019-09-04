package pl.sda.intermediate.shop.registration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private String city;
    private String country;
    private String zipCode;
    private String street;
    private String birthDate;
    private String pesel;
    private String phone;
    private Boolean preferEmails;

}