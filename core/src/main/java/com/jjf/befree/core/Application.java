package com.jjf.befree.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jijianfeng on 2017/9/20.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/")
public class Application {

  @RequestMapping(method = RequestMethod.GET ,path = "start")
  @ResponseBody
  String start(@RequestParam String name) {
    return "hello" + name;
  }

  public static void main( String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
