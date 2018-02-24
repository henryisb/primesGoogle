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

  @Override
  public GoogleQueryOutput googleQuery(GoogleQueryInput query) {
    GoogleQueryOutput googleQueryOutput = new GoogleQueryOutput();
    String queryWithoutSpaces = query.getQuery().replace(" ", "+");
    String searchUrl = "http://www.google.com/search?q=" + queryWithoutSpaces + "&num=2";

    log.info("search url : " + searchUrl);
    try {
      Document doc = Jsoup.connect(searchUrl)
          .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
          .get();

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
