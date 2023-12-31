package controller;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.Rectangle;

import javax.swing.JFrame;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import javax.swing.SwingUtilities;

import javax.swing.event.DocumentEvent;

import javax.swing.event.DocumentListener;

/**
 * Класс, отвечающий за логику работы приложения
 */
public class View implements ActionListener{

    JFrame window;
    File_Functions file = new File_Functions(this);
    /**
     * Поле для ввода текста
     */
    public JTextArea textField = new JTextArea();


    /**
     * Для возможности прокручивать текст
     */
    public JScrollPane scrollPane = new JScrollPane(textField);



    /**
     * Меню программы
     */
    private JMenuBar mainMenu = new JMenuBar();
    /**
     * Пункт меню "Выбор шрифта"
     */
    private JMenu setFont = new JMenu("Set Font");

    private JMenu fileBar = new JMenu("File");

    private JMenuItem iNew = new JMenuItem("New"), iOpen = new JMenuItem("Open"), iSave = new JMenuItem("Save"), iSaveAs = new JMenuItem("SaveAs");

    /**
     * Установить шрифт "Arial"
     */
    private JMenuItem arial = new JMenuItem("Arial");
    /**
     * Установить шрифт "Serif"
     */
    private JMenuItem serif = new JMenuItem("Serif");
    /**
     * Установить стиль текста "Plain"
     */
    private JMenuItem plain = new JMenuItem("Plain");
    /**
     * Установить стиль текста "Bold"
     */
    private JMenuItem bold = new JMenuItem("Bold");
    /**
     * Установить стиль текста "Bold"
     */
    private JMenuItem italic = new JMenuItem("Italic");

    /**
     * Пункт меню "Перенос строки"
     */
    private JCheckBox lineBreak = new JCheckBox("Line Break");
    /**
     * Скопировать текст в буфер обмена
     */
    private JButton copy = new JButton("Copy");
    /**
     * Вставить текст из буфера обмена
     */
    private JButton paste = new JButton("Paste");

    private JMenuItem row = new JMenuItem();

    private JMenuItem column = new JMenuItem();
    public View(){

        window = new JFrame();
        // Дефолтные настройки окна приложения
        window.setTitle("Simple Notepad");
        window.setBounds(100,100, 400, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавить меню, текстовое поле с возможностью промотки
        window.setJMenuBar(mainMenu);
        window.add(scrollPane);
        window.revalidate();
        mainMenu.add(fileBar);
        mainMenu.add(setFont);
        mainMenu.add(lineBreak);
        mainMenu.add(copy);
        mainMenu.add(paste);
        mainMenu.add(row);
        mainMenu.add(column);

        //меню файла
        fileBar.add(iNew);
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        fileBar.add(iOpen);
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        fileBar.add(iSave);
        iOpen.addActionListener(this);
        iSave.setActionCommand("Save");
        fileBar.add(iSaveAs);
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");

        //меню шрифта
        setFont.add(arial);
        setFont.add(serif);
        setFont.add(plain);
        setFont.add(bold);
        setFont.add(italic);

        lineBreak.addActionListener(new LineBreakHandler());

        copy.addActionListener(new Copy());
        paste.addActionListener(new Paste());

        textField.setFont(new Font("Arial", Font.PLAIN, 13));

        arial.addActionListener(new setArial());
        serif.addActionListener(new setSerif());
        plain.addActionListener(new setPlain());
        bold.addActionListener(new setBold());
        italic.addActionListener(new setItalic());

        textField.addCaretListener(new CaretHandler());

        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(textField);
        scrollPane.setRowHeaderView(lineNumberingTextArea);

        textField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent documentEvent)
            {
                lineNumberingTextArea.updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent)
            {
                lineNumberingTextArea.updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent)
            {
                lineNumberingTextArea.updateLineNumbers();
            }
        });
    }
    /**
     * метод для работы сохранения, открытия файла
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command){
            case "New":
                file.newFile();
                break;
            case "Open":
                file.openFile();
                break;
            case "Save":
                file.saveFile();
                break;
            case "SaveAs":
                file.saveAsFile();
                break;
        }
    }

    /**
     * Вспомогательный класс для отслеживания позиции курсора
     */
    private class CaretHandler implements CaretListener{
        @Override
        public void caretUpdate(CaretEvent e)
        {
            if(textField.getCaret().getMagicCaretPosition() != null)
            {
                row.setText(String.valueOf((textField.getCaret().getMagicCaretPosition().x )/ 7));
                column.setText(String.valueOf((textField.getCaret().getMagicCaretPosition().y ) / 14));
            }
        }
    }
    /**
     * Вспомогательный класс для обработки нажатия на кнопку "Перенос строки"
     */
    private class LineBreakHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(lineBreak.isSelected())
                textField.setLineWrap(true);
            else if(!lineBreak.isSelected())
                textField.setLineWrap(false);
        }
    }
    /**
     * Вспомогательный класс для реализации копирования текста в буфер обмена через кнопку
     */
    private class Copy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringSelection stringSelection = new StringSelection(textField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
    /**
     * Вспомогательный класс для реализации вставки текста из буфера обмена через кнопку
     */
    private class Paste implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            DataFlavor flavor = DataFlavor.stringFlavor;
            if(clipboard.isDataFlavorAvailable(flavor)) {
                try {
                    textField.setText(clipboard.getData(flavor).toString());
                } catch (UnsupportedFlavorException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    /**
     * Вспомогательный класс для выбора шрифта "Arial"
     */
    private class setArial implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setFont(new Font("Arial", textField.getFont().getStyle(), textField.getFont().getSize()));
        }
    }
    /**
     * Вспомогательный класс для выбора шрифта "Serif"
     */
    private class setSerif implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setFont(new Font("Serif", textField.getFont().getStyle(), textField.getFont().getSize()));
        }
    }
    /**
     * Вспомогательный класс для выбора стиля текста "Plain"
     */
    private class setPlain implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setFont(new Font(textField.getFont().getName(), Font.PLAIN, textField.getFont().getSize()));
        }
    }
    /**
     * Вспомогательный класс для выбора стиля текста "Bold"
     */
    private class setBold implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setFont(new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize()));
        }
    }
    /**
     * Вспомогательный класс для выбора стиля текста "Italic"
     */
    private class setItalic implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setFont(new Font(textField.getFont().getName(), Font.ITALIC, textField.getFont().getSize()));
        }
    }



}