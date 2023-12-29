/**package controller.tests.BDDtests;

import abbot.tester.JButtonTester;
import controller.MockClipboard;
import controller.View;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.extensions.abbot.ComponentTestFixture;

import java.awt.event.ActionEvent;

import static controller.View.textField;

public class Tests extends ComponentTestFixture{

    static String buff;
    private View view;
    static View.Paste pasteActionListener;
    private JButtonTester buttonTester;

    @Given("open notepad")
    public void start() {
        view = new View();
    }

    @Given("open notepad for copy")
    public void start2() {
        view = new View();
        MockClipboard mockClipboard = new MockClipboard("чтото");
    }
    @When("enter {string}")
    public void entering(String arg0) throws Exception {
        textField.setText(arg0);
    }

    @Then("app result is {string}")
    public void third(String arg0) {
        assertEquals(arg0, textField.getText());
    }

    @When("click copy")
    public void copyClick() throws Exception {
        View.Copy copyActionListener = new View.Copy();
        // Симулируем клик на кнопку
        copyActionListener.actionPerformed(new ActionEvent(new Object(), 0, ""));
        buff = copyActionListener.getClipboardContents();
    }
    @Then("copy result is {string}")
    public void copyResult(String arg0) {
        // Проверяем содержимое буфера обмена
        assertEquals("тест", "текст", buff);
    }



    @Given("open notepad for new file")
    public void startNew(){
        view = new View();
    }
    @When("click new")
    public void enterin() throws Exception {
        view.iNew.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, ""));
    }


    @Then("result of new file")
    public void thir() {
        assertEquals("", textField.getText());
        assertEquals("new", view.window.getTitle());
    }



}*/