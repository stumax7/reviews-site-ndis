package org.wecancodeit.reviews;

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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
public void categoryShouldHaveListOfLaptops(){
    Category testCategory = new Category("Tbrand");
    Review testReview = new Review();
   Laptop testLaptop = new Laptop(testCategory, "");


    categoryRepository.save(testCategory);
    laptopRepository.save(testLaptop);
    reviewRepository.save(testReview);

    entityManager.flush();
    entityManager.clear();

    Optional<Category> retreivedCategroeiesOptional = categoryRepository.findById(testCategory.getId());
    Category retreivedCategory = retreivedCategroeiesOptional.get();
    Laptop retreivedLaptop = laptopRepository.findById(testLaptop.getId()).get();

    assertThat(retreivedCategory.getLaptops()).contains(testLaptop);
}
}