package org.jasig.cas.action;

import javax.servlet.http.HttpServletRequest;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component
public class ProvideLoginTicketAction
  extends AbstractAction
{
  protected Event doExecute(RequestContext context)
    throws Exception
  {
    HttpServletRequest request = WebUtils.getHttpServletRequest(context);
    Service service = WebUtils.getService(context);
    String ticketGrantingTicket = WebUtils.getTicketGrantingTicketId(context);
    if (ticketGrantingTicket != null) {
      return result("newapp");
    }
    if ((request.getParameter("get-lt") != null) && (request.getParameter("get-lt").equalsIgnoreCase("true"))) {
      return result("loginTicketRequested");
    }
    return result("continue");
  }
}
