module org.nl.javatetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.nl.javatetris to javafx.fxml;
    exports org.nl.javatetris;
}