package net.siriussoft.aleservice.crawler.controller;

import net.siriussoft.aleservice.crawler.service.CrawlingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CrawlingDataController {

    @Autowired
    CrawlingDataService crawlingDataService;

    @RequestMapping(value="/crawling")
    public String makeApartmentTransactionData() {
        String result = "hello";
        System.out.println("crawling");
        try {
            crawlingDataService.makeApartmentTransactionData();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
