package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class Us06_ck {
    BookPage bookPage=new BookPage();
    String actual;
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        bookPage.addBook.click();
    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String book) {
    bookPage.bookName.sendKeys(book);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
       bookPage.isbn.sendKeys(isbn);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
       bookPage.year.sendKeys(year);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
       bookPage.author.sendKeys(author);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
      Select bookDropDown= new Select(bookPage.categoryDropdown) ;
      bookDropDown.selectByVisibleText(category);
    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String message) {
        this.actual=message;
        String expected=bookPage.toastMessage.getText();

        Assert.assertEquals(actual,expected);
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String bookActual) {

        List<String> bookName= new ArrayList<>();
        bookName.add(bookActual);

        DB_Util.runQuery("select name from books where name like 'Head First Java'");
        String expected1= DB_Util.getFirstRowFirstColumn();


        DB_Util.runQuery("select name from books where name like 'The Scrum Field Guide'");
        String expected2=DB_Util.getFirstRowFirstColumn();


        Assert.assertTrue(bookName.contains(expected1) || bookName.contains(expected2));
    }
}
