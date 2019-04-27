package org.jasig.cas.adaptors.jdbc;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractJdbcUsernamePasswordAuthenticationHandler
  extends AbstractUsernamePasswordAuthenticationHandler
{
  private JdbcTemplate jdbcTemplate;
  private DataSource dataSource;
  
  public void setDataSource(@NotNull DataSource dataSource)
  {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.dataSource = dataSource;
  }
  
  protected final JdbcTemplate getJdbcTemplate()
  {
    return this.jdbcTemplate;
  }
  
  protected final DataSource getDataSource()
  {
    return this.dataSource;
  }
}
