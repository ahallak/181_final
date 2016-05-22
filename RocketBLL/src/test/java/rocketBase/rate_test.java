package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {
///*****NOTE: these tests should work but they fail on "Initial SessionFactory creation" ..Is this a problem?
	//Println statements placed
	
	// TODO - RocketBLL rate_test
	// Check to see if a known credit score returns a known interest rate
	@Test
	public void test() throws Exception {
		ArrayList<RateDomainModel> listRates = RateDAL.getAllRates();
		double rate = 0.00;
		double paymentAmount = 0.00;
		int credScore = 700;
		
		try {
			rate = RateBLL.getRate(credScore) / (100 * 12);
			System.out.println(rate);
		} catch (RateException ex) {
			throw ex;
		}
		
		paymentAmount = rocketBase.RateBLL.getPayment(rate, 360.0, 300000.0, 0.0, false);
		System.out.println(Math.round(paymentAmount * 100.00) / 100.00);
		assertTrue(Math.round(paymentAmount * 100.00) / 100.00 == 1432.25); //testing predicted value
	}

	// TODO - RocketBLL rate_test
	// Check to see if a RateException is thrown if there are no rates for a
	// given credit score
	@Test(expected = RateException.class)
	public void testException() throws Exception {
		ArrayList<RateDomainModel> listRates = RateDAL.getAllRates();
		int creditScore = 585;
		try {
			double rate = RateBLL.getRate(creditScore);
			//return rate
			System.out.println(rate);
		} catch (RateException e) {
			throw e;
		}
	}

}