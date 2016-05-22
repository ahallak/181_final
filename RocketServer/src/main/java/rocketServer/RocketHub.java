package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;

public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");

		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;
			int cscore = lq.getiCreditScore();
			double r = 0;
			double n = 0;
			double p = 0;
			double f = 0;
			boolean t = false;
			try {
				lq.setdRate(RateBLL.getRate(cscore));
			} catch (RateException e) {
				sendToAll(e);
			}
			r = lq.getdRate() / (100*12);
			p = lq.getdAmount();
			n = (double) lq.getiTerm()*12;
			double payment = RateBLL.getPayment(r, n, p, f, t);
			lq.setdPayment(payment);
			
			sendToAll(lq);
		}
	}
}