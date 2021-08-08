package com.techelevator.services;

import com.techelevator.model.Share;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;


public class WebApiService {
    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private String result;

    public WebApiService() { }

    public Share getPrice(String ticker) {
        Share share = new Share();
        BASE_URL = "https://sandbox.iexapis.com/stable/stock/{ticker}/quote?token=Tpk_f2f602e084b44aa5a811ffd1445bc357";
        try {
            result = restTemplate.getForObject(BASE_URL, String.class, ticker);
            if(result.contains(",")) {
                String[] object = result.split(",");
                BigDecimal latestPrice = new BigDecimal("0");
                for(String item: object) {
                    if(item.contains("latestPrice")){
                        latestPrice = new BigDecimal(item.split(":")[1]);
                    }
                }
                share.setTickerName(ticker);
                share.setPrice(latestPrice);
            }
        } catch(RestClientException e) {
            System.out.println("Server error!" + e.getMessage());
        }
        return share;
    }

}
