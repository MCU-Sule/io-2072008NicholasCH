package com.example.pt07_2072008;

import com.example.pt07_2072008.model.modelKomen;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloController {
    public ListView lvKomen;
    public TextField txtUser;
    public TextArea txtKomen;
    public Button btnAdd;
    public Button btnSave;
    public Button btnLoad;
    public Button btnSave2;
    public Button btnLoad2;
    @FXML
    private Label welcomeText;
    private ObservableList<modelKomen> kList;
    Alert alert;
    Gson gson;
    FileChooser chooser;
    Path path;

    public void initialize(){
        kList = FXCollections.observableArrayList();
        lvKomen.setItems(kList);
    }

    public void reset(){
        txtKomen.clear();
        txtUser.clear();
    }

    public void addKomen(ActionEvent actionEvent) {
        if((txtUser.getText()).equals("")  || (txtKomen.getText()).equals("")){
            alert = new Alert(Alert.AlertType.ERROR, "Please Fill the Field Properly!", ButtonType.OK);
            alert.showAndWait();
        } else {
            kList.add(new modelKomen(txtUser.getText(), txtKomen.getText()));
            reset();
        }
    }

    public void saveKomen(ActionEvent actionEvent) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("data/komentar.txt"));
            gson = new Gson();
            String temp = gson.toJson(lvKomen.getItems());
            writer.write(temp);
            writer.close();
            alert = new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadKomen(ActionEvent actionEvent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/komentar.txt"));
            String temp = "";
            String komen;
            while ((komen = reader.readLine()) != null){
                temp += komen;
            }
            gson = new Gson();
            modelKomen [] komentar = gson.fromJson(temp, modelKomen[].class);
            kList = FXCollections.observableArrayList(komentar);
            lvKomen.setItems(kList);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveKomen2(ActionEvent actionEvent) {
        chooser = new FileChooser();
        FileChooser.ExtensionFilter extFil = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extFil);
        chooser.setSelectedExtensionFilter(extFil);
        File file = chooser.showSaveDialog(txtKomen.getScene().getWindow());
        if(file != null){
            path = Paths.get(file.toURI());
            gson = new Gson();
            String temp = gson.toJson(lvKomen.getItems());
            try {
                Files.write(path, temp.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadKomen2(ActionEvent actionEvent) {
        chooser = new FileChooser();
        FileChooser.ExtensionFilter extFil = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extFil);
        chooser.setSelectedExtensionFilter(extFil);
        File file = chooser.showOpenDialog(txtKomen.getScene().getWindow());
        if(file != null){
            path = Paths.get(file.toURI());
            try {
                String komen = Files.readString(path);
                gson = new Gson();
                modelKomen [] komentar = gson.fromJson(komen, modelKomen[].class);
                kList = FXCollections.observableArrayList(komentar);
                lvKomen.setItems(kList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}