package pl.sda.intermediate.shop.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    @Transient
    private static long serialVersionUID = 123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String eMail;
    @Column(name = "password")
    private String passwordHash;
    @Column(name = "birth_date")
    private String birthDate;
    private String pesel;
    private String phone;
    @Column(name = "prefer_emails")
    private Boolean preferEmails;

    @OneToOne
    @JoinColumn(name = "address_id")
    private UserAddress address;

}
