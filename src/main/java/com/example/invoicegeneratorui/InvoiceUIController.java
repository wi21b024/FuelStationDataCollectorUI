package com.example.invoicegeneratorui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import javafx.scene.web.WebEngine;

public class InvoiceUIController {
    @FXML
    public TextField info;
   // @FXML
    //protected WebView webviewBrowser;
    @FXML
    public TextField customerID;

    @FXML
    public void btnGenerate_Clicked(ActionEvent actionEvent) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/invoices/" + customerID.getText()))
                    .POST(HttpRequest.BodyPublishers.noBody()).build();
            var response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Failed to call REST api: \n" + e.toString());
            alert.showAndWait();
        }
    }

    public void btnInfo_Clicked(ActionEvent actionEvent) {
        try {


            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/invoices/" + customerID.getText()))
                    .GET().build();
            var response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                info.setText(response.body());
            } else if (response.statusCode() == 404) {
                info.setText("404 PDF nicht gefunden");
            } else {
                info.setText("Fehler beim Aufrufen der REST-API. Statuscode: " + response.statusCode());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Fehler beim Aufrufen der REST-API: \n" + e.toString());
            alert.showAndWait();
        }
    }


    /*@FXML
    public void btnInfo_Clicked(ActionEvent actionEvent) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/invoices/"))
                    .GET().build();
            var response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            info.setText(response.body());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Failed to call REST api: \n" + e.toString());
            alert.showAndWait();
        }
    }*/

    public void btnExit_Clicked(ActionEvent actionEvent) {
        System.exit(0);
    }
}