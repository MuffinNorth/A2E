package sample;

import controller.A2JConverter;
import controller.FileController;
import controller.J2EConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Class;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    private final FileChooser fileChooser = new FileChooser();

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button buttonOpen;

    @FXML
    public void openFile() {
        try{
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Axon idea file (*.XON)", "*.xon");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file == null){
                return;
            }
            FileController controller = new FileController(file);
            A2JConverter a2JConverter = new A2JConverter(controller.getStrings());
            Class[] classes = a2JConverter.convertClasses();
            J2EConverter converter = new J2EConverter("База знаний");
            String xml = converter.convertFromJavaClass(classes);
            fileChooser.getExtensionFilters().remove(filter);
            filter = new FileChooser.ExtensionFilter("Personal knowledge base designer (*.EKB)", "*.ekb");
            fileChooser.getExtensionFilters().add(filter);
            File out = fileChooser.showSaveDialog(primaryStage);
            FileWriter writer = new FileWriter(out);
            writer.write(xml);
            writer.close();
            primaryStage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Произошла ошибка!", ButtonType.PREVIOUS);
            alert.showAndWait();
        }

    }

}
