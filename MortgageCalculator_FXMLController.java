import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class MortgageCalculator_FXMLController implements Initializable {

    @FXML private TextField purchasePriceField;
    @FXML private TextField downPaymentAmmountField;
    @FXML private TextField interestRateField;
    @FXML private Slider loanDuraionSlider;
    @FXML private TextField loanDurationField;
    @FXML private Button calculateButton;
    @FXML private TextField loanAmmountField;
    @FXML private TextField monthlyPaymentField;

    private double purchasePrice = 0;
    private double downPaymentAmmount = 0;
    private double annualInterestRate = 0;
    private double loanYears = 0;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void sliderAction()
    {
        loanDuraionSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                loanYears = newValue.doubleValue();
                loanDurationField.setText(loanYears + "");

            }
        });
    }

    @FXML
    public void calculateButtonAction()
    {
        if(purchasePriceField.getText().equals("") || downPaymentAmmountField.getText().equals("")
                || interestRateField.getText().equals("") || loanDurationField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "All fields are mandatory!", "Field(s) Missing Alert", JOptionPane.OK_OPTION);
        }
        else
        {
            purchasePrice = Double.parseDouble(purchasePriceField.getText().trim());
            downPaymentAmmount = Double.parseDouble(downPaymentAmmountField.getText().trim());
            annualInterestRate = Double.parseDouble(interestRateField.getText().trim());
            loanYears = Double.parseDouble(loanDurationField.getText().trim());
            calculateFunction(purchasePrice, downPaymentAmmount, annualInterestRate, loanYears);
        }
    }

    public void calculateFunction(double pruchasePrice, double downPaymentAmmount
            , double annualInterestRate, double loanYears)
    {
        double loanAmount = (pruchasePrice - downPaymentAmmount);

        annualInterestRate = (annualInterestRate / 100);
        double monthlyInterestRate = (annualInterestRate / 12.00);

        double loanMonths = (loanYears * 12);

        double monthlyPayment = (loanAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanMonths)))
                / (Math.pow((1 + monthlyInterestRate), loanMonths) - 1);

        loanAmmountField.setText("$ " + String.format("%.2f", loanAmount));
        monthlyPaymentField.setText("$ " + String.format("%.2f", monthlyPayment));
    }
}
