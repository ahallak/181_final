package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {
/*ready to submit*/
	private MainApp mainApp;

	// TODO - RocketClient.RocketMainController

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtRate;

	@FXML
	private TextField txtHouseCost;

	@FXML
	private TextField txtDownPayment;

	@FXML
	private ComboBox<String> cbTerm;

	@FXML
	private TextField txtMonthlyPayment;

	@FXML
	private Button btnPayment;

	@FXML
	private Label lblCreditScore;

	@FXML
	private Label lblHouseCost;

	@FXML
	private Label lblDownPayment;

	@FXML
	private Label lblRate;

	@FXML
	private Label lblIncome;

	@FXML
	private Label lblExpenses;

	@FXML
	private Label lblTerm;

	@FXML
	private Label lblError;

	@FXML
	private Label lblMortgagePayment;

	@FXML
	private void initialize(){
		cbTerm.getItems().add("15");
		cbTerm.getItems().add("30");
		cbTerm.getSelectionModel().select("30");
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	// TODO - RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		// TODO - RocketClient.RocketMainController
		Action calcPay = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		// TODO - RocketClient.RocketMainController
		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq
		
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		
		try{
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
		} catch(RateException e){
			lq.setdRate(-1);
		}
		lq.setiTerm(Integer.parseInt(cbTerm.getSelectionModel().getSelectedItem().toString()));
		calcPay.setLoanRequest(lq);
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest){
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places
		double rate = lRequest.getdRate();
		double pmt = Math.round(lRequest.getdPayment() * 100.00) / 100.00;
		if (rate == -1) {
			txtRate.setText("N/A");
			txtMonthlyPayment.setText("Bad credit score");
		} else {
			txtRate.setText(Double.toString(rate));
			if ((lRequest.getiTerm() == 30
					&& pmt * 12 * 30 < (lRequest.getIncome() - lRequest.getExpenses() * 12) * 30)
					|| //
					(lRequest.getiTerm() == 15
							&& pmt * 12 * 15 < (lRequest.getIncome() - lRequest.getExpenses() * 12) * 15)) {
				txtMonthlyPayment.setText(Double.toString(pmt));
			} else {
				txtMonthlyPayment.setText("Cost of house too high");
			}
		}

	}

}