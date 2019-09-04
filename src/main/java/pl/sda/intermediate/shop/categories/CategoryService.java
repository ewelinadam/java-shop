package pl.sda.intermediate.shop.categories;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    Predicate<CategoryDTO> realPredicate = e -> e.getState().getOpened() || e.getState().getSelected();
    Predicate<CategoryDTO> dummyPredicate = e -> true;

    @Autowired
    private CategoryDAO categoryDAO = null;

    public List<CategoryDTO> findCategories(String searchText) {

        // FileCategoryDAO fileCategoryDAO = FileCategoryDAO.getInstance();

        Map<Integer, CategoryDTO> dtosMap = categoryDAO.getCategoryList().stream()
                .map(e -> convertToCategoryDTO(e))
                .collect(Collectors.toMap(e -> e.getId(), e -> e));

        dtosMap.values().stream()
                .filter(e -> e.getName().equals(StringUtils.defaultIfBlank(searchText, "").trim()))
                .forEach(foundCategory -> {
                    foundCategory.makAsSelected();
                    openParent(foundCategory, dtosMap);
                });

        return populateResult(dtosMap, StringUtils.isNotBlank(searchText)? realPredicate : dummyPredicate);

    }

    private List<CategoryDTO> populateResult(Map<Integer, CategoryDTO> dtosMap, Predicate<CategoryDTO> predicate) {

        return dtosMap.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private void openParent(CategoryDTO categoryDTO, Map<Integer, CategoryDTO> dtos) {

        Integer parentId = categoryDTO.getParentId();

        if (parentId == null) {
            return;
        }

        CategoryDTO parent = dtos.get(parentId);
        parent.makAsOpened();
        openParent(parent, dtos);

    }

    private CategoryDTO convertToCategoryDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getCategoryId());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setName(category.getCategoryName());
        categoryDTO.setState(new CategoryState(false, false));
        return categoryDTO;

    }

}
