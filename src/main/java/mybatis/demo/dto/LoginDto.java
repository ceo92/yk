package mybatis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
  @NotBlank
  private String loginEmail;

  @NotBlank
  private String password;




}
