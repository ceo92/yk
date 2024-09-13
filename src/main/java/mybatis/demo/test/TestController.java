package mybatis.demo.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mybatis.demo.domain.Item;
import mybatis.demo.domain.ItemType;
import mybatis.demo.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

  @ModelAttribute
  public Map<String , String> regions(){
    Map<String, String> regions = new LinkedHashMap<>();
    regions.put("SEOUL" , "서울");
    regions.put("ANYANG" , "안양");
    regions.put("DAEGU" , "대구");
    regions.put("SUWON" , "수원");
    regions.put("CHEONAN" , "천안");
    return regions;
  }

  @ModelAttribute
  public ItemType[] itemTypes(){
    return ItemType.values();
  }



  //추후 연관관계로 빼자
  @ModelAttribute //이게 그냥 매 호출마다 모델로 넘어감
  public List<String> deliveryCompanies(){
    List<String> deliveryCompanies = new ArrayList<>();
    deliveryCompanies.add("한화 택배");
    deliveryCompanies.add("롯데 택배");
    deliveryCompanies.add("한진 택배");
    deliveryCompanies.add("CJ 대한통운");
    deliveryCompanies.add("CU 편의점택배");
    return deliveryCompanies;
  }



  @GetMapping
  public String ccc(Model model){
    model.addAttribute("item" , new ItemTestDto());
    return "test/hello";
  }

  @PostMapping
  public String vmjcx(){

  }



}
