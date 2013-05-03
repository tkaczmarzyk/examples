package net.kaczmarzyk.example.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(FastTest.class)
@ExcludeCategory(SlowTest.class)
@SuiteClasses({SimpleFastTest.class, SimpleSlowTest.class})
public class FastSuite {

}
