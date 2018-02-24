package hisb.example.primegoogle.presentation;

import hisb.examples.primegoogle.business.GoogleQueryServiceImpl;
import hisb.examples.primegoogle.business.PrimeServiceImpl;
import hisb.examples.primegoogle.business.model.GoogleQueryInput;
import hisb.examples.primegoogle.presentation.controller.PrimeGoogleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

  @Mock
  private PrimeServiceImpl primeService;

  @Mock
  private GoogleQueryServiceImpl googleQueryService;

  @InjectMocks
  private PrimeGoogleController primeGoogleController;

  @Before
  public void setup() {
    primeGoogleController = new PrimeGoogleController();
  }

  @Test(expected = NullPointerException.class)
  public void testGoogleControllerFailure() {
    GoogleQueryInput googleQueryInput = new GoogleQueryInput();
    googleQueryInput.setQuery(null);
    primeGoogleController.googleQuery(googleQueryInput);

  }
}
