package pl.sda.intermediate.shop.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DBCategoryDAO implements CategoryDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Category> getCategoryList() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }
}
