// --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml,javafx.web
package geekbrains.lesson4;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

import javafx.beans.value.ChangeListener;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Insets;

public class MainWebView extends Application {

    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Label label;

    public class Bridge {

        public void showTime() {
            System.out.println("Show Time");

            label.setText("Now is: " + df.format(new Date()));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        label = new Label("-");

        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        webEngine.setJavaScriptEnabled(true);

        // A Worker load the page
        Worker<Void> worker = webEngine.getLoadWorker();

        // Listening to the status of worker
        worker.stateProperty().addListener(new ChangeListener<State>() {

            @Override
            public void changed(ObservableValue<? extends State> observable, //
                                State oldValue, State newValue) {

                // When load successed.
                if (newValue == Worker.State.SUCCEEDED) {
                    // Get window object of page.
                    JSObject jsobj = (JSObject) webEngine.executeScript("window");

                    // Set member for 'window' object.
                    // In Javascript access: window.myJavaMember....
                    jsobj.setMember("myJavaMember", new Bridge());
                }
            }
        });

        webEngine.load(getClass().getResource("/messege.html").toExternalForm());

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(label, webView);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
