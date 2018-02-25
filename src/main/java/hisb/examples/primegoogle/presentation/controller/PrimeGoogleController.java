package hisb.examples.primegoogle.presentation.controller;

import hisb.examples.primegoogle.business.GoogleQueryService;
import hisb.examples.primegoogle.business.PrimeService;
import hisb.examples.primegoogle.business.model.GoogleQueryOutput;
import hisb.examples.primegoogle.business.model.PrimeOutput;
import hisb.examples.primegoogle.business.model.GoogleQueryInput;
import hisb.examples.primegoogle.excpetions.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestController
public class PrimeGoogleController {

  @Autowired
  private PrimeService primeService;

  @Autowired
  private GoogleQueryService googleQueryService;


  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(
      value = "primes/{number}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<PrimeOutput> primeNumbers(
      @PathVariable("number") Integer number,
      @RequestParam(required = false) String algorithm) {

    PrimeOutput listOfPrimes = primeService.primeCalculation(number, algorithm);

    return new ResponseEntity<>(listOfPrimes, HttpStatus.ACCEPTED);

  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(
      value = "querygoogle/",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<GoogleQueryOutput> googleQuery(
      @RequestBody GoogleQueryInput query) {

    if (query.getQuery() == null) {
      throw new NullPointerException("Unprocessable : null input");
    }

    GoogleQueryOutput googleResponse = googleQueryService.googleQuery(query);
    log.info("Controller from reponse : " + googleResponse.getResponse());

    return new ResponseEntity<>(googleResponse, HttpStatus.ACCEPTED);

  }

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(NullPointerException.class)
  public ExceptionResponse invalidInput(NullPointerException ex) {
    return new ExceptionResponse(ex.getMessage(),
        "Unprocessable");
  }
}
