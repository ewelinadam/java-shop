package pl.sda.intermediate.shop.categories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Integer id;
    private String name;
    private Integer parentId;
    private CategoryState state;

    public void makAsOpened() {

        state.setOpened(true);

    }

    public void makAsSelected() {

        state.setSelected(true);

    }

    public String getParent() {

        return parentId == null? "#" : String.valueOf(parentId);

    }

    public String getText() {
        return name;
    }

}