package mybatis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
