package controller.tests;

import controller.MockClipboard;
import controller.View;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static controller.View.textField;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotepadUnitTest {

    View view = new View();
    @Test
    public void copyTest() {
        MockClipboard mockClipboard = new MockClipboard("чтото");
        View.Copy copyActionListener = new View.Copy();
        textField.setText("текст2");
        // Симулируем клик на кнопку
        copyActionListener.actionPerformed(new ActionEvent(new Object(), 0, ""));
        String buff = copyActionListener.getClipboardContents();
        assertEquals("текст", "текст2", buff);
    }

    @Test
    public void testFont() {

        View.textField.setText("проверка");
        // Найти кнопку "italic" и выполнить на нее клик
        View.italic.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, "1"));

        assertEquals("Arial Курсив", View.textField.getFont().getFontName());
    }

    @Test
    public void testPaste1() {
        View.Copy copyActionListener = new View.Copy();
        View.textField.setText("проверка");
        // Найти кнопку "copy" и выполнить на нее клик
        View.copy.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, "1"));
        View.textField.setText("");
        // Найти кнопку "New" и выполнить на нее клик
        // Симулируем клик на кнопку
        View.paste.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, "1"));
        // Проверить, что текстовое поле пусто
        assertEquals("проверка", View.textField.getText());
    }
    @Test
    public void testPaste2() {
        View.Copy copyActionListener = new View.Copy();
        View.textField.setText("проверка");
        // Найти кнопку "copy" и выполнить на нее клик
        View.copy.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, "1"));
        // Найти кнопку "New" и выполнить на нее клик
        // Симулируем клик на кнопку
        View.paste.getActionListeners()[0].actionPerformed(new ActionEvent(new Object(), 1, "1"));
        // Проверить, что текстовое поле пусто
        assertEquals("проверкапроверка", View.textField.getText());
    }
}
