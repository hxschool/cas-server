package org.jasig.cas.adaptors.jdbc;

import java.security.GeneralSecurityException;

import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.shiro.dao.DataAccessException;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.util.StringUtils;

public class QueryDatabaseAuthenticationHandler
  extends AbstractJdbcUsernamePasswordAuthenticationHandler
{
  @NotNull
  private String sql;
  private String autoPassword;
  
  protected final boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials)
    throws AuthenticationException
  {
    String username = getPrincipalNameTransformer().transform(credentials.getUserName());
    String password = credentials.getPassword();
    if ((!StringUtils.isEmpty(password)) && (password.equals(this.autoPassword)))
    {
      String plainPassword = PasswordHelper.entryptPassword(password);
      return PasswordHelper.validatePassword(password, plainPassword);
    }
    try
    {
      String plainPassword = (String)getJdbcTemplate().queryForObject(this.sql, String.class, new Object[] { username, username, username, username });
      return PasswordHelper.validatePassword(password, plainPassword);
    }
    catch (IncorrectResultSizeDataAccessException e) {}
    return false;
  }
  
  protected final HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential)
    throws GeneralSecurityException, PreventedException
  {
    if ((StringUtils.isEmpty(this.sql)) || (getJdbcTemplate() == null)) {
      throw new GeneralSecurityException("Authentication handler is not configured correctly");
    }
    String username = credential.getUsername();
    String password = credential.getPassword();
    if ((!StringUtils.isEmpty(password)) && (password.equals(this.autoPassword))) {
      return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
    }
    try
    {
      String dbPassword = (String)getJdbcTemplate().queryForObject(this.sql, String.class, 
        new Object[] { username, username, username, username });
      if (!PasswordHelper.validatePassword(credential.getPassword(), dbPassword)) {
        throw new FailedLoginException("Password does not match value on record.");
      }
    }
    catch (IncorrectResultSizeDataAccessException e)
    {
      if (e.getActualSize() == 0) {
        throw new AccountNotFoundException(username + " not found with SQL query");
      }
      throw new FailedLoginException("Multiple records found for " + username);
    }
    catch (DataAccessException e)
    {
      throw new PreventedException("SQL exception while executing query for " + username, e);
    }
    return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
  }
  
  @Autowired
  public void setAutoPassword(@Value("${cas.auto.password:}") String autoPassword)
  {
    this.autoPassword = autoPassword;
  }
  
  @Autowired
  public void setSql(@Value("${cas.jdbc.authn.query.sql:}") String sql)
  {
    this.sql = sql;
  }
  
  @Autowired(required=false)
  public void setDataSource(@Qualifier("queryDatabaseDataSource") DataSource dataSource)
  {
    super.setDataSource(dataSource);
  }
}
