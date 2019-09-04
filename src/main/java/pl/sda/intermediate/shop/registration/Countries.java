package pl.sda.intermediate.shop.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Countries {

    POLAND("Polska", "PL"),
    SPAIN("Hiszpania", "SP"),
    GERMANY("Niemcy", "DE"),
    PORTUGAL("Portugalia", "P");

    private String name;
    private String symbol;

}
