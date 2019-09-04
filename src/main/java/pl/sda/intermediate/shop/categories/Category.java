package pl.sda.intermediate.shop.categories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "parent_id")
    private Integer parentId;
    private Integer level;


}
