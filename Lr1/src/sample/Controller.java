package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;

public class Controller extends Window {
    public TextField key;
    File file, Outfile;

    public void openFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(this);
    }

    private void code (String keyIn){
        try{
            FileInputStream fileIn = new FileInputStream(file);
            FileOutputStream fileOut = new FileOutputStream(Outfile);

            int fileSize = fileIn.available(); //размер файла в байтах
            byte[] buffer = new byte[keyIn.length()]; //размер ключа в байтах
            byte[] keyByte = keyIn.getBytes(); //перевод строки в байты
            int blockCount = (int)fileSize/buffer.length; //количество ключей в тексте

            for(int i = 0; i < blockCount; i++){
                fileIn.read(buffer, 0, buffer.length); //считывание буфера
                for(int k = 0; k < buffer.length; k++)
                    buffer[k] = (byte)(buffer[k]^keyByte[k]);
                fileOut.write(buffer);
            }
            if(fileSize%blockCount != 0){
                int rez = fileSize-(blockCount*keyIn.length());
                buffer = new byte[rez];
                fileIn.read(buffer, 0, rez);
                for(int k = 0; k < buffer.length; k++)
                    buffer[k] = (byte)(buffer[k]^keyByte[k]);
                fileOut.write(buffer);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void encode(ActionEvent actionEvent) {
        Outfile = new File (file.getPath().replace("txt","k"));
        if(key.getLength() == 0)
            code("abcdef");
        else
            code(key.getText());
        System.exit(0);
    }

    public void decode(ActionEvent actionEvent) {
        Outfile = new File (file.getPath().replace("k","txt"));
        if(key.getLength() == 0)
            code("abcdef");
        else
            code(key.getText());
        System.exit(0);
    }
}
