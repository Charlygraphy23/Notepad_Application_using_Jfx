package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sun.font.FontFamily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FontController {
    @FXML
    private JFXListView<String> fontList;

    @FXML
    private JFXTextField textfield;

    @FXML
    private JFXListView<String> listViewStyle;

    @FXML
    private JFXTextField textfieldStyle;

    @FXML
    private JFXListView<String> listViewSize;

    @FXML
    private JFXTextField textfieldSize;

    @FXML
    private Label previewFont;
    @FXML
    private JFXButton okButton;

    @FXML
    private JFXButton canclebutton;


    private List<String> list;
    private List<String> listStle;
    private List<String> listSize;

    private ObservableList<String> fontfamilyList;
    private ObservableList<String> observableListStyle;
    private ObservableList<String> observableListSize;

    private String [] s={"Regular","Bold","Extra_Bold"};
    private String [] size={"8","9","10","11","12","13","14","16","18","20","22","24","26","28","36"};
    private Font font;

    private String name=null;
    private int si=12;

    @FXML
    void initialize() {

        textfieldSize.setText("12");
        textfield.setText(Font.getFamilies().get(0));
        textfieldStyle.setText(s[0]);

        list= Font.getFamilies();
        listStle=new ArrayList<String>(Arrays.asList(s));
        listSize=new ArrayList<String>(Arrays.asList(size));

        fontfamilyList= FXCollections.observableList(list);
        observableListStyle=FXCollections.observableList(listStle);
        observableListSize=FXCollections.observableList(listSize);

        listViewSize.setItems(observableListSize);
        fontList.setItems(fontfamilyList);
        listViewStyle.setItems(observableListStyle);

        fontList.setOnMouseClicked(e->{
            textfield.setText(fontList.getSelectionModel().getSelectedItem());
            font=Font.font(textfield.getText(),FontWeight.findByName(textfieldStyle.getText()),Integer.parseInt(textfieldSize.getText()));
            previewFont.setFont(font);
        });

        listViewSize.setOnMouseClicked(e->{
            textfieldSize.setText(listViewSize.getSelectionModel().getSelectedItem());
            font=Font.font(textfield.getText(),FontWeight.findByName(textfieldStyle.getText()),Integer.parseInt(textfieldSize.getText()));
            previewFont.setFont(font);
        });

       listViewStyle.setOnMouseClicked(e->{
           textfieldStyle.setText(listViewStyle.getSelectionModel().getSelectedItem());
            font=Font.font(textfield.getText(),FontWeight.findByName(textfieldStyle.getText()),Integer.parseInt(textfieldSize.getText()));
            previewFont.setFont(font);

       });



       okButton.setOnAction(e->{

            font=Font.font(textfield.getText(),FontWeight.findByName(textfieldStyle.getText()),Integer.parseInt(textfieldSize.getText()));
           okButton.getScene().getWindow().hide();
           FXMLLoader loader=new FXMLLoader();
           loader.setLocation(getClass().getResource("/sample/view/notepad_view.fxml"));
           try {
               loader.load();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
           Notepad_Controller controller=loader.getController();
           try {
               controller.initFont(font);
           } catch (IOException ex) {
               ex.printStackTrace();
           }


       });

       canclebutton.setOnAction(e->{
           canclebutton.getScene().getWindow().hide();
       });


    }
}
