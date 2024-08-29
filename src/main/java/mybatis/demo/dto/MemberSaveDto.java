package mybatis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberSaveDto {
  @NotBlank
  private String loginEmail;

  @NotBlank
  private String password;

  @NotBlank
  private String rePassword;

  @NotBlank
  private String memberName;

  @NotBlank
  private String businessName;

  @NotBlank
  private String businessNum;
}
