package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Controller {

    @FXML
    public Button button;

    @FXML
    public Label finalvalues;
    public TextField day;
    public TextField year;
    public TextField month;

    @FXML
    void initialize() {
        finalvalues.setWrapText(true);
        button.setOnAction(event -> {
            if(!day.getText().isEmpty()&&!month.getText().isEmpty()&&!year.getText().isEmpty()&&Integer.parseInt(month.getText())<=12){
                try {
                    LocalDate date2 = LocalDate.of(Integer.parseInt(year.getText()), 1, 1);
                    LocalDate date3 = LocalDate.of(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                    long weeks = ChronoUnit.WEEKS.between(date2, date3);
                    day.setText("");
                    year.setText("");
                    month.setText("");
                    finalvalues.setText(String.valueOf(weeks));
                }catch (Exception e){
                    day.setText("");
                    year.setText("");
                    month.setText("");
                    finalvalues.setText("Не правильно введена дата:(");
                }
            }
        });
    }

}
