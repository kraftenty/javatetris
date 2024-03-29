module org.nl.javatetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens org.nl.javatetris to javafx.fxml;
    exports org.nl.javatetris;
}