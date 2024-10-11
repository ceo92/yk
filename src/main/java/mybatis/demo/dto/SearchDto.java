package mybatis.demo.dto;

import lombok.Data;
import mybatis.demo.domain.Pagination;

@Data
public class SearchDto {

  private int page;    // 현재 페이지 번호
  private int recordSize;       // 페이지당 출력할 데이터 개수
  private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
  private String keyword;       // 검색 키워드
  private String searchType;    // 검색 유형
  private Pagination pagination;

  //현재 페이지를처음 보여지는 페이지
  public SearchDto() {
    this.page = 1; //현재 페이지를 1 , 즉 처음엔 첫 페이지(1페이지)에 접근돼야하니!
    this.recordSize = 10; //한 화면에서 보여지는 데이터 수 10개 기본!
    this.pageSize = 10; //화면 하단에 출력할 페이지 수 지정 , 기본 10개!
  }

  // Pagination 의 limitStart로 시작값을 대신할 것이므로 이제 안 씀
  /*public int getOffset() {
    return (currentPageNum - 1) * printSize;
  }

   */
}
