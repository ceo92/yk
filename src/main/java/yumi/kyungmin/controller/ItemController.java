package yumi.kyungmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yumi.kyungmin.domain.Item;
import yumi.kyungmin.dto.ItemDto;
import yumi.kyungmin.service.ItemService;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("items")
  public String getItems(Model model){
    model.addAttribute("items", itemService.findAll());
    return "item/items";
  }

  @GetMapping("items/save")
  public String saveItemForm(Model model){
    model.addAttribute("item" , new Item());
    return "item/saveItem";
  }

  @PostMapping("items/save")
  public String saveItemPost(@Validated @ModelAttribute("item") ItemDto itemDto , BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      return "item/saveItem";
    }
    itemService.register(itemDto);
    return "redirect:/items";
  }

}
