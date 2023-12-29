package controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class File_Functions {

    controller.View view;
    String fileName = null, fileAdress = null;
    public File_Functions(View view){
        this.view = view;
    }
    public void newFile(){
        view.textField.setText("");
        view.window.setTitle("New");
    }
    public void openFile(){
        FileDialog fd = new FileDialog(view.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile() != null){
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            view.window.setTitle(fileName);
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileAdress + fileName));
            view.textField.setText("");
            String line = null;
            while((line = br.readLine())!=null){
                view.textField.append(line + "\n");
            }
            br.close();
        }catch(Exception e){
            System.out.println("File not opened.");
        }
    }
    public void saveFile(){
        FileDialog fd = new FileDialog(view.window, "Open", FileDialog.SAVE);
        fd.setVisible(true);

        if(fileName == null){
            saveAsFile();
        }
        try {
            FileWriter fw = new FileWriter(fileAdress + fileName);
            fw.write(view.textField.getText());
            view.window.setTitle(fileName);
            fw.close();
        }catch ( Exception e) {
            System.out.println("something wrong");
        }
    }
    public void saveAsFile(){
        FileDialog fd = new FileDialog(view.window, "Open", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            view.window.setTitle(fileName);
        }
        try {
            FileWriter fw = new FileWriter(fileAdress + fileName);
            fw.write(view.textField.getText());
            fw.close();
        }catch ( Exception e) {
            System.out.println("something wrong");
        }
    }
}
