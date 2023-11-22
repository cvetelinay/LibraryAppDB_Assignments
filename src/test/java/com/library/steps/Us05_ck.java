package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Us05_ck {
    String expected;
    String actual;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select bc.name,count(borrowed_date)\n" +
                "from book_borrow bb\n" +
                "join books b on b.id=bb.book_id\n" +
                "join book_categories bc on bc.id=b.book_category_id\n" +
                "group by book_id\n" +
                "order by count(borrowed_date) desc ;");

      expected= DB_Util.getFirstRowFirstColumn();
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String actual) {
        this.actual=actual;
        Assert.assertTrue(expected.equals(actual));
    }
}
