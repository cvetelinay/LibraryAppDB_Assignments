package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Us07_ck {

    BookPage bookPage=new BookPage();
    BorrowedBooksPage borrowedBooksPage=new BorrowedBooksPage();
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
//        bookPage.borrowBook("Test2023").click();
        bookPage.borrowBook(bookPage.bookName.getText()).click();

    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String borrowingBooksPage) {
        bookPage.navigateModule(borrowingBooksPage);
        List<String>allBooksNameInString=new ArrayList<>();
        for (WebElement each : borrowedBooksPage.allBorrowedBooksName) {
            allBooksNameInString.add(each.getText());
        }
//        Assert.assertTrue(allBooksNameInString.contains("Test2023"));
        Assert.assertTrue(allBooksNameInString.contains(bookPage.bookName));
    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        DB_Util.runQuery("select b.name\n" +
                "from book_borrow  bb\n" +
                "join books b on  bb.book_id=b.id\n" +
                "join users u on u.id=bb.user_id\n" +
                "where full_name like('Test Student 5')");
       List<String> expected=DB_Util.getColumnDataAsList(1);
        Assert.assertTrue(expected.contains("Test2023"));


    }
}
