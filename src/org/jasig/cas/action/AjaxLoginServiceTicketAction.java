package org.jasig.cas.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public final class AjaxLoginServiceTicketAction
  extends AbstractAction
{
  protected static final String J_CALLBACK = "feedBackUrlCallBack";
  
  protected Event doExecute(RequestContext context)
  {
    HttpServletRequest request = WebUtils.getHttpServletRequest(context);
    Event event = context.getCurrentEvent();
    boolean isAjax = BooleanUtils.toBoolean(request.getParameter("isajax"));
    if (!isAjax) {
      return event;
    }
    boolean isLoginSuccess;
    boolean isLoginSuccess;
    if ("success".equals(event.getId()))
    {
      Service service = WebUtils.getService(context);
      String serviceTicket = WebUtils.getServiceTicketFromRequestScope(context);
      if (service != null) {
        request.setAttribute("service", service.getId());
      }
      request.setAttribute("ticket", serviceTicket);
      isLoginSuccess = true;
    }
    else
    {
      isLoginSuccess = false;
    }
    boolean isFrame = BooleanUtils.toBoolean(request.getParameter("isframe"));
    String callback = request.getParameter("callback");
    if (StringUtils.isEmpty(callback)) {
      callback = "feedBackUrlCallBack";
    }
    if (isFrame) {
      callback = "parent.".concat(callback);
    }
    request.setAttribute("isFrame", Boolean.valueOf(isFrame));
    request.setAttribute("callback", callback);
    request.setAttribute("isLogin", Boolean.valueOf(isLoginSuccess));
    
    return new Event(this, "local");
  }
}
