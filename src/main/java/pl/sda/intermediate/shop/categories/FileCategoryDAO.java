package pl.sda.intermediate.shop.categories;

import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileCategoryDAO implements CategoryDAO { // DAO Data Access Object

    private static FileCategoryDAO instance;

    private List<Category> categoryList;

    @Override
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public static FileCategoryDAO getInstance() {

        if (instance == null) {

            synchronized (FileCategoryDAO.class) {
                if (instance == null) {
                    instance = new FileCategoryDAO();
                }
            }

        }

        return instance;

    }

    private FileCategoryDAO() {
        categoryList = initializeCategories();
    }

    private List<Category> initializeCategories() {

        List<String> listFromFile = readLinesFromFile();
        AtomicInteger counter = new AtomicInteger(1);

        List<Category> entryLIst = listFromFile.stream()
                .map(i -> populateCategory(i, counter))
                .collect(Collectors.toList());

        Map<Integer, List<Category>> mapOfLevels = entryLIst.stream()
                .collect(Collectors.groupingBy(i -> i.getLevel()));

        populateParentId(mapOfLevels, 0);

//        return entryLIst;
        return mapOfLevels.values().stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
    }

    private void populateParentId(Map<Integer, List<Category>> mapOfLevels, int lvl) {

        if (!mapOfLevels.containsKey(lvl)) {
            return;
        }

        List<Category> categoriesOnLevel = mapOfLevels.get(lvl);

        for (Category child : categoriesOnLevel) {

            if (lvl == 0) {
                continue;
            }

            List<Category> potentialParent = mapOfLevels.get(lvl - 1);
            Integer parentId = potentialParent.stream()
                    .map(p -> p.getCategoryId())
                    .filter(i -> i < child.getCategoryId())
                    .sorted(Comparator.reverseOrder())
                    .findFirst()
                    .orElse(0); //jezeli optional bedzie pusty to zwroci podaną przez nas wartość (0)

            child.setParentId(parentId);

        }

        populateParentId(mapOfLevels, lvl + 1);

    }

    private Category populateCategory(String line, AtomicInteger counter) {

        Category c = new Category();
        c.setLevel(calculateLevel(line));
        c.setCategoryName(line.trim());
        c.setCategoryId(counter.getAndIncrement());
        return c;

    }

    private Integer calculateLevel(String categoryName) {

        if (categoryName.startsWith(" ")) {
            String[] split = categoryName.split("\\S+");
            return split[0].length();
        }

        return 0;

    }

    private List<String> readLinesFromFile() {

        ClassLoader classLoader = this.getClass().getClassLoader();

        try {
            Path path = Paths.get(classLoader.getResource("kategorie").toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            return new ArrayList<>();
        }

    }

}
