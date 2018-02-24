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

  @Override
  public PrimeOutput primeCalculation(Integer number, String algorithm) {
    log.info("you've hit the prime thingy");
    PrimeOutput primeOutput = new PrimeOutput();
    List<Integer> primes;


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
