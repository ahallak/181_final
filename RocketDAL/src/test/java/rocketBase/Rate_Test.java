package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	// TODO - RocketDAL rate_test
	// Check to see if a known credit score returns a known interest rate

	@Test
	public void test(){

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println("Rates size: " + rates.size());
		
		assertEquals(rates.size(), 5);

		int indexer = 0;
		for (int i = 800; i >= 600; i = i - 50){
			assertEquals(rates.get(indexer).getiMinCreditScore(), i);
			indexer += 1;
		}
		assertTrue(rates.get(0).getdInterestRate() == 3.5);
		assertTrue(rates.get(1).getdInterestRate() == 3.75);
		assertTrue(rates.get(2).getdInterestRate() == 4);
		assertTrue(rates.get(3).getdInterestRate() == 4.5);
		assertTrue(rates.get(4).getdInterestRate() == 5);

	}

}