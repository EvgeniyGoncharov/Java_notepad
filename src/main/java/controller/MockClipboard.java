package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MockClipboard extends Clipboard implements ClipboardOwner {

    private String clipboardContents;

    public MockClipboard(String name) {
        super(name);
    }

    public void setClipboardContents(String contents) {
        this.clipboardContents = contents;
    }

    public String getClipboardContents() {
        return clipboardContents;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        if (contents != null) {
            // Присвоить текст textField
            try {
                View.textField.setText((String) contents.getTransferData(DataFlavor.stringFlavor));
            } catch (UnsupportedFlavorException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Метод для установки содержимого с использованием Transferable
    public void setContentsWithTransferable(Transferable transferable) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, this);
    }

    // Метод для получения Transferable на основе текущего содержимого
    public Transferable getContents() {
        return new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                // Возвращаем все доступные форматы данных в буфере обмена
                return new DataFlavor[]{DataFlavor.stringFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                // Поддерживается ли указанный формат данных
                return Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable(flavor)) {
                    // Извлекаем данные для указанного формата
                    return Toolkit.getDefaultToolkit().getSystemClipboard().getData(flavor);
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }
        };
    }
}
