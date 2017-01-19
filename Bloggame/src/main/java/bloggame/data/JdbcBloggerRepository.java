package bloggame.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bloggame.Blogger;

@Repository
public class JdbcBloggerRepository implements BloggerRepository {
  
  private JdbcOperations jdbc;

  @Autowired
  public JdbcBloggerRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public Blogger save(Blogger blogger) {
    jdbc.update(
        "insert into Blogger (username, password, first_name, last_name, email)" +
        " values (?, ?, ?, ?, ?)",
        blogger.getUsername(),
        blogger.getPassword(),
        blogger.getFirstName(),
        blogger.getLastName(),
        blogger.getEmail());
    return blogger; // TODO: Determine value for id
  }

  public Blogger findByUsername(String username) {
    return jdbc.queryForObject(
        "select id, username, null, first_name, last_name, email from Blogger where username=?", 
        new BloggerRowMapper(), 
        username);
  }
  
  private static class BloggerRowMapper implements RowMapper<Blogger> {
    public Blogger mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Blogger(
          rs.getLong("id"),
          rs.getString("username"),
          null,
          rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getString("email"));
    }
  }

}
