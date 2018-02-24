package hisb.example.primegoogle.business;

import hisb.examples.primegoogle.business.PrimeServiceImpl;
import hisb.examples.primegoogle.business.model.PrimeOutput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PrimesTest {

  private PrimeServiceImpl primeService;

  @Before
  public void setup() {
    primeService = new PrimeServiceImpl();
  }

  @Test
  public void calculatePrimesForSuccessful() {
    int i = 10;
    String algorithm = "for";
    PrimeOutput primeOutput = primeService.primeCalculation(i, algorithm);
    assert (primeOutput.getPrimeList().size() == 4);

  }

  @Test
  public void calculatePrimesStreamingSuccessful() {
    int i = 15;
    String algorithm = "stream";
    PrimeOutput primeOutput = primeService.primeCalculation(i, algorithm);
    assert (primeOutput.getPrimeList().size() == 6);
  }

  @Test
  public void calculatePrimesNullSuccessful() {
    int i = 20;
    PrimeOutput primeOutput = primeService.primeCalculation(i, null);
    assert (primeOutput.getPrimeList().size() == 8);
  }

  @Test
  public void calculatePrimesStringSuccessful() {
    int i = 30;
    String algorithm = "blah";
    PrimeOutput primeOutput = primeService.primeCalculation(i, algorithm);
    assert (primeOutput.getPrimeList().size() == 10);
  }


}
