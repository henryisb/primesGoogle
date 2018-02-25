package hisb.examples.primegoogle.business;

import hisb.examples.primegoogle.business.model.GoogleQueryInput;
import hisb.examples.primegoogle.business.model.GoogleQueryOutput;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GoogleQueryServiceImpl implements GoogleQueryService {

  /**
   * Service for searching google to return teh second field returned
   * @param query is used to construct the url
   * @return googleQueryInput which contains the url of the second
   */
  @Override
  public GoogleQueryOutput googleQuery(GoogleQueryInput query) {
    GoogleQueryOutput googleQueryOutput = new GoogleQueryOutput();
    // Remove the spaces from the query
    String queryWithoutSpaces = query.getQuery().replace(" ", "+");

    // Create query url and return two
    String searchUrl = "http://www.google.com/search?q=" + queryWithoutSpaces + "&num=2";

    log.info("search url : " + searchUrl);
    try {
      // Construct query with compatible agent
      Document doc = Jsoup.connect(searchUrl)
          .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
          .get();

      // Trim the response to just include the url for the second value
      String secondResponse = doc.select("h3.r > a").last().attr("href").replace("/url?q=", "");
      log.info("Second reponse : " + searchUrl);
      googleQueryOutput.setResponse(secondResponse);

      return googleQueryOutput;
    } catch (Exception e) {
      log.info(e.getMessage());
    }

    return googleQueryOutput;
  }
}
