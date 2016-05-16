package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateDomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	// * DONE *
	private RateDomainModel rateDomainModel = null;

	public RateException(RateDomainModel rateDomainModel) {
		this.rateDomainModel = rateDomainModel;
	}

	public RateDomainModel getRateDomainModel() {
		return rateDomainModel;
	}

}