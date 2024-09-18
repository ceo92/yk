package mybatis.demo.domain;

public enum ItemType {


  FOOD("음식") , CLOTHES("옷") , COSMETICS("화장품");

  private String description; //enum은 name값 즉 FOOD , CLOTHES , COSMETICS만 있음 , description은 내가 클래스마냥 내부에서 지정한 필드명임 ㅇㅇ

  ItemType(String description){
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
