package hisb.examples.primegoogle.business;

import hisb.examples.primegoogle.business.model.PrimeOutput;

public interface PrimeService {

  PrimeOutput primeCalculation(Integer number, String algorithm);
}
