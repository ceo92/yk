package yumi.kyungmin.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberDto {

  @NotBlank
  private String memberName;

  @NotBlank
  private String businessName;

  @NotBlank
  private String businessNum;

}
