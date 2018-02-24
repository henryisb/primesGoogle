package hisb.examples.primegoogle.business;

import hisb.examples.primegoogle.business.model.GoogleQueryInput;
import hisb.examples.primegoogle.business.model.GoogleQueryOutput;

public interface GoogleQueryService {
  GoogleQueryOutput googleQuery(GoogleQueryInput query);
}
