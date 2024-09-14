package mybatis.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mybatis.demo.domain.Item;
import mybatis.demo.domain.ItemType;
import mybatis.demo.dto.ItemDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

  Map<Long, User> store = new HashMap<>();

  private static Long sequence = 0L;

  @ModelAttribute("regions") //얜 이름을 필수로 지정해줘야됨
  public Map<String , String> regions(){
    //return regionsService.findRegions();
    Map<String, String> regions = new LinkedHashMap<>();
    regions.put("SEOUL" , "서울");
    regions.put("ANYANG" , "안양");
    regions.put("DAEGU" , "대구");
    regions.put("SUWON" , "수원");
    regions.put("CHEONAN" , "천안");
    return regions;
  }

  @ModelAttribute("itemTypes")
  public ItemType[] itemTypes(){
    return ItemType.values();
  }

  //추후 연관관계로 빼자
  @ModelAttribute("deliveryCompanies") //이게 그냥 매 호출마다 모델로 넘어감
  public List<String> deliveryCompanies(){
    List<String> deliveryCompanies = new ArrayList<>();
    deliveryCompanies.add("한화 택배");
    deliveryCompanies.add("롯데 택배");
    deliveryCompanies.add("한진 택배");
    deliveryCompanies.add("CJ 대한통운");
    deliveryCompanies.add("CU 편의점택배");
    return deliveryCompanies;
  }

  @GetMapping("/v4")
  public String dd(Model model){
    model.addAttribute("user" , new User());
    return "test/helloV4";
  }

  @PostMapping("/v4")
  public String kdDddDkd(@ModelAttribute User user){
    log.info("name = {}", user.getName());
    log.info("age = {}", user.getAge());
    log.info("isSale = {}" , user.getSale());
    List<String> regions = user.getRegions();
    for (String region : regions) {
      log.info("region = {}" , region);
    }

    return "test/success";
  }



  @GetMapping
  public String ccc(){
    return "test/hello";
  }

  @PostMapping
  public String vmjcx(@ModelAttribute User user){
    log.info("name = {}", user.getName());
    log.info("age = {}", user.getAge());
    log.info("isSale = {}" , user.getSale());
    return "test/success";
  }

  @GetMapping("/v2")
  public String dkjdd(Model model){
    model.addAttribute("user" , new User());
    return "test/helloV2";
  }

  @PostMapping("/v2")
  public String kdkd(@ModelAttribute User user){
    log.info("name = {}", user.getName());
    log.info("age = {}", user.getAge());
    log.info("isSale = {}" , user.getSale());
    return "test/success";
  }



  @GetMapping("/v3")
  public String dkjDDdd(Model model){
    model.addAttribute("user" , new User());
    return "test/helloV3";
  }

  @PostMapping("/v3")
  public String kdDDkd(@ModelAttribute User user , RedirectAttributes redirectAttributes){
    store.put(++sequence , user);
    redirectAttributes.addAttribute("id", sequence);
    return "redirect:/test/{id}";
  }

  @GetMapping("/{id}")
  public String sfkmscklm(@PathVariable("id") Long id , Model model){
    model.addAttribute("user" , store.get(id));
    return "test/member";
  }


  @Data
  static class User{
    private String name;
    private Integer age;
    private Boolean sale;
    private List<String> regions;
    private ItemType itemType;
    private String deliveryCompany;
  }





}
