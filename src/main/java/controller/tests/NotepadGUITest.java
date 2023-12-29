package controller.tests;

import controller.View;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotepadGUITest {

    public View view = new View();

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testNewFile() {
        View.textField.setText("проверка");
        // Найти кнопку "New" и выполнить на нее клик
        View.iNew.doClick();

        // Проверить, что текстовое поле пусто
        assertEquals("", View.textField.getText());
    }
    @Test
    public void testCopy() {

        View.Copy copyActionListener = new View.Copy();
        View.textField.setText("проверка");
        // Найти кнопку "copy" и выполнить на нее клик
        View.copy.doClick();

        // Проверить, что буфер обмена заполнен
        assertEquals("проверка", copyActionListener.getClipboardContents());

    }
    @Test
    public void testFont() {

        View.textField.setText("проверка");
        // Найти кнопку "italic" и выполнить на нее клик
        View.setFont.doClick(1000);
        View.italic.doClick(1000);

        assertEquals("Arial Курсив", View.textField.getFont().getFontName());
    }
    @Test
    public void testPaste1() {
        View.textField.setText("проверка");
        // Найти кнопку "copy" и выполнить на нее клик
        View.copy.doClick();
        View.textField.setText("");
        // Найти кнопку "New" и выполнить на нее клик
        // Симулируем клик на кнопку
        View.paste.doClick();
        // Проверить, что текстовое поле пусто
        assertEquals("проверка", View.textField.getText());
    }
    @Test
    public void testPaste2() {
        View.Copy copyActionListener = new View.Copy();
        View.textField.setText("проверка");
        // Найти кнопку "copy" и выполнить на нее клик
        View.copy.doClick();

        // Найти кнопку "New" и выполнить на нее клик
        // Симулируем клик на кнопку
        View.paste.doClick();
        // Проверить, что текстовое поле пусто
        assertEquals("проверкапроверка", View.textField.getText());
    }

    @AfterEach
    public void tearDown() {
    }
}
