package org.jasig.services.persondir.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jasig.services.persondir.IPersonAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("attributeRepository")
public class AccoutAttributeDao
  extends StubPersonAttributeDao
{
  private JdbcTemplate jdbcTemplate;
  
  public IPersonAttributes getPerson(String username)
  {
    String sql = "SELECT id, company_id, office_id, clazz_id,dorm_id,login_name, password, no, name, email, phone, mobile, user_type, photo, login_ip, login_date, remarks, del_flag from sys_user where  (login_name =? or no =? or phone =? or email =?)";
    Map values = this.jdbcTemplate.queryForMap("SELECT id, company_id, office_id, clazz_id,dorm_id,login_name, password, no, name, email, phone, mobile, user_type, photo, login_ip, login_date, remarks, del_flag from sys_user where  (login_name =? or no =? or phone =? or email =?)", new Object[] { username, username, username, username });
    String roleQuery = "select role_id from sys_user_role where user_id=?";
    List<String> list = this.jdbcTemplate.queryForList("select role_id from sys_user_role where user_id=?", String.class, new Object[] { values.get("id") });
    
    Map<String, List<Object>> attributes = new HashMap();
    attributes.put("id", Collections.singletonList(values.get("id")));
    attributes.put("no", Collections.singletonList(values.get("no")));
    attributes.put("idCard", Collections.singletonList(values.get("login_name")));
    attributes.put("login_name", Collections.singletonList(values.get("login_name")));
    attributes.put("userType", Collections.singletonList(values.get("user_type")));
    attributes.put("name", Collections.singletonList(values.get("name")));
    attributes.put("companyId", Collections.singletonList(values.get("company_id")));
    attributes.put("officeId", Collections.singletonList(values.get("office_id")));
    attributes.put("clazzId", Collections.singletonList(values.get("clazz_id")));
    attributes.put("dormId", Collections.singletonList(values.get("dorm_id")));
    attributes.put("phone", Collections.singletonList(values.get("phone")));
    attributes.put("email", Collections.singletonList(values.get("email")));
    attributes.put("mobile", Collections.singletonList(values.get("mobile")));
    attributes.put("loginIp", Collections.singletonList(values.get("login_ip")));
    attributes.put("loginDate", Collections.singletonList(values.get("login_date")));
    attributes.put("remarks", Collections.singletonList(values.get("remarks")));
    attributes.put("roleId", Collections.singletonList(list));
    
    return new AttributeNamedPersonImpl(attributes);
  }
  
  @Autowired(required=false)
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }
}
