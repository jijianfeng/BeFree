package com.jjf.befree.core.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jijianfeng on 2017/9/20.
 */
@Controller
@EnableAutoConfiguration
public class CrawlerController {
  @RequestMapping("/")
  @ResponseBody
  String start() {
    return "start!";
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(CrawlerController.class, args);
  }
}
