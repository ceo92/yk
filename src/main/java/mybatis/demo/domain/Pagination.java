package mybatis.demo.domain;

import lombok.Getter;
import mybatis.demo.dto.SearchDto;

@Getter
public class Pagination {

  private int totalRecordCount;     // 전체 데이터 수
  private int totalPageCount;       // 전체 페이지 수
  private int startPage;            // 첫 페이지 번호
  private int endPage;              // 끝 페이지 번호
  private int limitStart;           // LIMIT 시작 위치 , LIMIT 2,3 하면 2를 뜻함 , SearchDto의 offset 역할 , 분할을 시작하는 번호
  private boolean existPrevPage;    // 이전 페이지 존재 여부
  private boolean existNextPage;    // 다음 페이지 존재 여부

  public Pagination(int totalRecordCount, SearchDto params) {
    if (totalRecordCount > 0) {
      this.totalRecordCount = totalRecordCount;
      calculation(params);
    }
  }

  private void calculation(SearchDto params) {

    // 전체 페이지 수 계산
    totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;

    // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
    if (params.getPage() > totalPageCount) {
      params.setPage(totalPageCount);
    }

    // 첫 페이지 번호 계산
    startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

    // 끝 페이지 번호 계산
    endPage = startPage + params.getPageSize() - 1;

    // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
    if (endPage > totalPageCount) {
      endPage = totalPageCount;
    }

    // LIMIT 시작 위치 계산
    limitStart = (params.getPage() - 1) * params.getRecordSize();

    // 이전 페이지 존재 여부 확인 , 1만 아니면 전부 이전 페이지가 존재하므로
    existPrevPage = startPage != 1;

    // 다음 페이지 존재 여부 확인 , 현재 데이터가 전체 데이터 수보다 작을 때만 다음 페이지가 존재
    existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
  }

}
