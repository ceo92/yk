package yumi.kyungmin.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberDto {

  private String memberName;

  private String businessName;

  private String businessNum;

}
