package pl.sda.intermediate.shop.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAddress implements Serializable {
    @Transient
    private static long serialVersionUID = 1223L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String country;
    @Column(name = "zip_code")
    private String zipCode;
    private String street;

}
