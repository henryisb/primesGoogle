package hisb.examples.primegoogle.business;

import hisb.examples.primegoogle.business.model.PrimeOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Service
@Slf4j
public class PrimeServiceImpl implements PrimeService {

  /**
   * Prime calculation service, takes in the algorithm and the max value
   * @param number is max value for calculation
   * @param algorithm to use to calculate
   * @return the prime output which contains the list of primes
   */
  @Override
  public PrimeOutput primeCalculation(Integer number, String algorithm) {
    PrimeOutput primeOutput = new PrimeOutput();
    List<Integer> primes;

    // Choose which algorithm or default
    if (algorithm == null) {
      primes = algorithmFor(number);
    } else if (algorithm.equals("for")) {
      primes = algorithmFor(number);
    } else if (algorithm.equals("stream")) {
      primes = algorithmStream(number);
    } else {
      primes = algorithmFor(number);
    }

    primeOutput.setPrimeList(primes);
    return primeOutput;
  }

  /**
   * Algorithm using for looks to calculate the prime number up to number submitted
   * @param max value is used as the limit
   * @return list of prime numbers
   */
  private List<Integer> algorithmFor(Integer max) {
    List<Integer> primes = new ArrayList<>();
    for(int i=1; i<= max; i++) {
      int count = 0;
      for (int j = i; j>=1; j--) {
        if (i%j == 0) {
          count++;
        }
      }
      if (count == 2) {
        primes.add(i);
      }
    }
    log.info("prime numbers : " + primes);
    return primes;
  }

  /**
   * Algorithm using streaming to calculate the prime numbers up to the number submitted
   * @param max value is used as th limit
   * @return list of prime numbers
   */
  private List<Integer> algorithmStream(Integer max) {
    List<Integer> primes = new ArrayList<>();

    IntStream.rangeClosed(2, max)
        .filter(i -> IntStream.rangeClosed(2, (int)Math.sqrt(i))
            .allMatch(j -> i%j != 0))
        .forEach(n -> {
          primes.add(n);
        });
    log.info("primes numbers : " + primes);
    return primes;
  }
}
