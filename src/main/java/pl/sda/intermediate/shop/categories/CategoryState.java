package pl.sda.intermediate.shop.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryState {

    private Boolean selected;
    private Boolean opened;

}
