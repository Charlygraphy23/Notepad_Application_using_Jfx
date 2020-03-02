package sample.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.*;

public class Notepad_Controller extends Window {

    @FXML
    private MenuItem newMenu;

    @FXML
    private MenuItem openMenue;

    @FXML
    private MenuItem saveMenu;

    @FXML
    private MenuItem saveasMenu;

    @FXML
    private MenuItem printMenu;

    @FXML
    private MenuItem closemenue;

    @FXML
    private JFXTextArea textarea;

    @FXML
    private MenuItem cut;

    @FXML
    private MenuItem copy;

    @FXML
    private MenuItem past;

    @FXML
    private MenuItem selectall;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuItem getFont;

    @FXML
    private MenuItem changeFont;

    @FXML
    private MenuItem about;

    @FXML
    private Label label;

    private FileChooser fileChooser=null;
    private File temp=null;
    private static Font font;
    private String selectedText=null;
    private String replaceText=null;
    private File tempFile=null;

    @FXML
    void initialize() throws IOException {



        newMenu.setOnAction(e -> {
            newMenuClicked();
        });
        openMenue.setOnAction(e -> {
            openMenuClicked();
        });
        saveMenu.setOnAction(e -> {
            System.out.println(textarea.getText());
            try {
                saveMenuClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveasMenu.setOnAction(e -> {
            try {
                saveAsMenuClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        printMenu.setOnAction(e -> {
            printMenuClicked();
        });

        closemenue.setOnAction(e -> {
            closeMenuClicked();
        });
        cut.setOnAction(e -> {
            cutmenuClicked();
        });
        copy.setOnAction(e -> {
            copymenuClicked();
        });
        past.setOnAction(e -> {
            pastemenuClicked();
        });
        selectall.setOnAction(e -> {
            allemenuClicked();
        });
        delete.setOnAction(e -> {
            deleteemenuClicked();
        });

        getFont.setOnAction(e -> {
            Stage s = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/view/font_manage.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            s.setTitle("Font");
            s.setScene(new Scene(root));
            s.setResizable(false);
            s.show();

        });

        changeFont.setOnAction(e -> {
            if (font != null) {
                textarea.setFont(font);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Pls Select A Font");
                alert.show();
            }
        });

        about.setOnAction(e->{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Notepad Application");
            alert.setContentText(" Version No- 2.029v\n Copyright- Dipta Biswas @2020");
            alert.show();
        });

        textarea.setOnKeyTyped(e->{
          label.setText(String.valueOf(textarea.getText().length()));
        });

    }


    private void deleteemenuClicked() {textarea.deleteText(textarea.getSelection()); }
    private void allemenuClicked() { textarea.selectAll(); }
    private void pastemenuClicked() { textarea.paste(); }
    private void copymenuClicked() { textarea.copy();}
    private void cutmenuClicked() { textarea.cut();}
    private void newMenuClicked() {  textarea.setText(""); }

    private void saveMenuClicked() throws IOException {
       if(temp!=null){
           FileWriter writer=new FileWriter(temp);
           writer.write(textarea.getText());
           writer.close();
       }
       else {
           fileChooser=new FileChooser();
           fileChooser.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("Text Documents (*.txt)","*.txt"),
                   new FileChooser.ExtensionFilter("All Files","*.*")
           );

           File file=fileChooser.showSaveDialog(this);
           tempFile=file;
           if(file!=null){
               FileWriter writer=new FileWriter(file.getAbsoluteFile());
               writer.write(textarea.getText().trim());
               writer.close();
               if(tempFile!=null){
                   Stage stage=(Stage) textarea.getScene().getWindow();
                   stage.setTitle(tempFile.getName());
               }
               else {
                   Stage stage=(Stage) textarea.getScene().getWindow();
                   stage.setTitle("untitled- Notepad.txt");
               }

           }
       }
    }

    private void openMenuClicked() {
        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Documents (*.txt)","*.txt"),
                new FileChooser.ExtensionFilter("All Files","*.*")
        );

        File file = fileChooser.showOpenDialog(this);
        tempFile=file;
        if(file!=null){
            temp=file;
        }
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                String s = null;
                while ((s = reader.readLine()) != null) {
                    textarea.setText(s);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if(tempFile!=null){
                Stage stage=(Stage) textarea.getScene().getWindow();
                stage.setTitle(tempFile.getName());
            }
            else {
                Stage stage=(Stage) textarea.getScene().getWindow();
                stage.setTitle("untitled- Notepad.txt");
            }
        }

    }

    private void saveAsMenuClicked() throws IOException {
        fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Documents (*.txt)","*.txt"),
                new FileChooser.ExtensionFilter("All Files","*.*")
        );

        File file=fileChooser.showSaveDialog(this);
        tempFile=file;
        if(file!=null){
            FileWriter writer=new FileWriter(file.getAbsoluteFile());
            writer.write(textarea.getText().trim());
            writer.close();

            if(tempFile!=null){
                Stage stage=(Stage) textarea.getScene().getWindow();
                stage.setTitle(tempFile.getName());
            }
            else {
                Stage stage=(Stage) textarea.getScene().getWindow();
                stage.setTitle("untitled- Notepad.txt");
            }
        }

    }

    private void printMenuClicked() {
       // Stage s=new Stage();

        PrinterJob job=PrinterJob.createPrinterJob();
        boolean f=job.showPrintDialog(textarea.getScene().getWindow());
        if(f){
            boolean print=job.printPage(textarea);
            if(print)
                job.endJob();
            else
                System.out.println("Error");
        }
    }


    public void initFont( Font f) throws IOException {
        font=f;
        System.out.println("from Init -"+font.getName());
    }

    private void closeMenuClicked() {
        System.exit(0);
    }

}
