module com.example.invoicegeneratorui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;

    opens com.example.invoicegeneratorui to javafx.fxml;
    exports com.example.invoicegeneratorui;
}