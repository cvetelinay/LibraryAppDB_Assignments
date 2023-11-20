package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Us03_ck {
    DashBoardPage dashBoardPage =new DashBoardPage();
    BookPage bookPage=new BookPage();
    List<String> actualBookCategory;
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {
        dashBoardPage.navigateModule(module);


    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        bookPage.mainCategoryElement.click();
    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        Select booksDropDown=new Select(bookPage.mainCategoryElement);

        List<WebElement> bookCategory= booksDropDown.getOptions();

        actualBookCategory=new ArrayList<>();

        for (WebElement each : bookCategory) {
//            bookCategory.removeIf(p-> p.getText().equals("ALL"));
            actualBookCategory.add(each.getText());
        }
        actualBookCategory.remove(0);

        DB_Util.runQuery("SELECT distinct name from book_categories");
        List<String>expectedBookCategory=DB_Util.getColumnDataAsList(1);
        Assert.assertEquals(actualBookCategory,expectedBookCategory);
    }
}
