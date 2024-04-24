module com.dashfleet.selinaLibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.net.http;
    requires jackson.databind;
    requires java.logging;
    requires java.base;
    requires java.sql;
    requires com.google.gson;
    requires okhttp3;
    requires retrofit2;
    requires retrofit2.converter.scalars;
    requires retrofit2.converter.gson;
    requires kotlinx.coroutines.core;
    requires kotlinx.coroutines.javafx;
    requires org.hibernate.orm.core;

    requires java.persistence;
    requires java.naming;
    requires net.bytebuddy;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;

//    requires java.transaction;
//    requires java.activation;
    requires lombok;



    opens com.dashfleet.selinaLibrary to javafx.fxml;
    exports com.dashfleet.selinaLibrary;

    opens com.dashfleet.selinaLibrary.data.database.entities;
}