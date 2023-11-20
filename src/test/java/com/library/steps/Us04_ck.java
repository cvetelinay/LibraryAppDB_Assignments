package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class Us04_ck {
    BookPage bookPage=new BookPage();
    String book;
    List<String>expected;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String book) {
        bookPage.search.sendKeys(book);
        this.book=book;

    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(book).click();
        System.out.println(book);
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        Select selectDropDown=new Select(bookPage.categoryDropdown);
        String name=bookPage.bookName.getAttribute("value");
        String isbn2=bookPage.isbn.getAttribute("value");
        String year2=bookPage.year.getAttribute("value");
        String author2=bookPage.author.getAttribute("value");
        String category2=selectDropDown.getFirstSelectedOption().getText();
        String description2=bookPage.description.getAttribute("value");

            expected=Arrays.asList(name,isbn2,year2,author2,category2,description2);


        DB_Util.runQuery("SELECT b.name,b.isbn,b.year,b.author,bc.name,b.description  FROM books b\n" +
                "    join book_categories bc\n" +
                "    on b.book_category_id=bc.id\n" +
                "    where b.name like 'Test2023';");

        List<String> actual=DB_Util.getRowDataAsList(1);
        Assert.assertEquals(actual,expected);
    }
}
