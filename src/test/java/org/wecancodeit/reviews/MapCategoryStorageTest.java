package org.wecancodeit.reviews;

import org.wecancodeit.reviews.models.Category;
import org.junit.jupiter.api.Test;
import org.wecancodeit.reviews.storage.CategoriesStorage;
import org.wecancodeit.reviews.storage.MapCategoriesStorage;

import static org.assertj.core.api.Assertions.assertThat;

public class MapCategoryStorageTest {
    @Test
    public void shouldStoreCategoriesInMap() {
        Category testCategory = new Category("MSI", "names");
        CategoriesStorage underTest = new MapCategoriesStorage();
        underTest.store(testCategory);
        assertThat(underTest.findAllCategories().contains(testCategory));
    }

    @Test
    public void shouldRetrieveSingleCategory() {
        Category testCategory = new Category("Test", "names");
        Category testCategory2 = new Category("Msi","namesfgh");
        MapCategoriesStorage underTest = new MapCategoriesStorage();
        underTest.store(testCategory);
        underTest.store(testCategory2);
        Category retrieveCategory = underTest.findCategoryByBrand("Test");
        assertThat(retrieveCategory).isEqualTo(testCategory);
    }
}