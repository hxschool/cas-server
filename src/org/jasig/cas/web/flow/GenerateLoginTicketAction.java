package org.jasig.cas.web.flow;

import javax.validation.constraints.NotNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.ticket.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.execution.RequestContext;

public class GenerateLoginTicketAction
{
  private static final String PREFIX = "LT";
  private final Log logger = LogFactory.getLog(getClass());
  @NotNull
  private UniqueTicketIdGenerator ticketIdGenerator;
  
  public final String generate(RequestContext context)
  {
    String loginTicket = this.ticketIdGenerator.getNewTicketId("LT");
    this.logger.debug("Generated login ticket " + loginTicket);
    
    WebUtils.putTicketGrantingTicketInScopes(context, loginTicket);
    return "generated";
  }
  
  public void setTicketIdGenerator(UniqueTicketIdGenerator generator)
  {
    this.ticketIdGenerator = generator;
  }
}
