package org.wecancodeit.reviews;

import org.springframework.test.annotation.DirtiesContext;
import org.wecancodeit.reviews.models.Category;
import org.wecancodeit.reviews.models.Laptop;
import org.wecancodeit.reviews.models.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.wecancodeit.reviews.storage.repos.CategoryRepository;
import org.wecancodeit.reviews.storage.repos.LaptopRepository;
import org.wecancodeit.reviews.storage.repos.ReviewRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DirtiesContext
@ComponentScan
@DataJpaTest
public class JPAWiringTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void categoryShouldHaveListOfLaptops() {
        Category testCategory = new Category("testBrand");
        Review testReview = new Review();
        Laptop testLaptop = new Laptop(testCategory, "testName", "testModel");


        categoryRepository.save(testCategory);
        laptopRepository.save(testLaptop);
        reviewRepository.save(testReview);
        entityManager.flush();
        entityManager.clear();

        Optional<Category> retrievedCategoriesOptional = categoryRepository.findById(testCategory.getId());
        Category retrievedCategory = retrievedCategoriesOptional.get();

        Optional<Laptop> retrievedLaptopOptional = laptopRepository.findByName(testLaptop.getName());
        Laptop retrievedLaptop = retrievedLaptopOptional.get();

        assertThat(retrievedCategory.getLaptops()).contains(testLaptop);
        assertThat(retrievedLaptop.getReviews().contains(testReview));
    }
}
