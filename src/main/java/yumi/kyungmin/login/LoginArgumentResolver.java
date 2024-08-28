package yumi.kyungmin.login;

import static yumi.kyungmin.SessionConst.MEMBER_NAME;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import yumi.kyungmin.domain.Member;

@Slf4j
public class LoginArgumentResolver implements HandlerMethodArgumentResolver { //커스텀

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
    boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
    log.info("supportsParameter = {}" , hasMemberType && hasLoginAnnotation);
    return hasMemberType && hasLoginAnnotation;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    HttpSession session = request.getSession(false);
    log.info("session = {}" , session);
    log.info("resolveArgument");
    if (session == null){
      return null;
    }
    return session.getAttribute(MEMBER_NAME);
  }
}
