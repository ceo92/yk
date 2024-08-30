package mybatis.demo.interceptor;

import static mybatis.demo.SessionConst.MEMBER_NAME;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute(MEMBER_NAME) == null){
      response.sendRedirect("/"); //지정된 주소로 리다이렉트함
      return false;
    }

    return true;
  }
}
