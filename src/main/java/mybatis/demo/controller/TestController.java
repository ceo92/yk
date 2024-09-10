package mybatis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {


  @GetMapping
  public String ccc(){
    return "test/hello";
  }

  @PostMapping
  public String cxcv(){
    return
  }


}
