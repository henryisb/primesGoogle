package hisb.example.primegoogle.business;

import hisb.examples.primegoogle.business.GoogleQueryServiceImpl;
import hisb.examples.primegoogle.business.model.GoogleQueryInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class GoogleQueryTest {

  private GoogleQueryServiceImpl googleQueryService;

  @Before
  public void setup() {
    googleQueryService = new GoogleQueryServiceImpl();
  }

  @Test
  public void testQuerySuccessSingleElement() {
    String query = "blah";
    GoogleQueryInput googleQueryInput = new GoogleQueryInput();
    googleQueryInput.setQuery(query);
    String result = googleQueryService.googleQuery(googleQueryInput).getResponse();

    assert (result != null);

  }

  @Test
  public void testQuerySuccessMultiple() {
    String query = "what is this";
    GoogleQueryInput googleQueryInput = new GoogleQueryInput();
    googleQueryInput.setQuery(query);
    String result = googleQueryService.googleQuery(googleQueryInput).getResponse();

    assert (result != null);

  }

}
