package bloggame.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bloggame.Post;

@Repository
public class JdbcPostRepository implements PostRepository {

  private JdbcOperations jdbc;

  @Autowired
  public JdbcPostRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<Post> findRecentPosts() {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Post" +
        " order by created_at desc limit 20",
        new PostRowMapper());
  }

  public List<Post> findPosts(long max, int count) {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Post" +
        " where id < ?" +
        " order by created_at desc limit 20",
        new PostRowMapper(), max);
  }

  public Post findOne(long id) {
    return jdbc.queryForObject(
        "select id, message, created_at, latitude, longitude" +
        " from Post" +
        " where id = ?",
        new PostRowMapper(), id);
  }

  public void save(Post spittle) {
    jdbc.update(
        "insert into Post (message, created_at, latitude, longitude)" +
        " values (?, ?, ?, ?)",
        spittle.getMessage(),
        spittle.getTime(),
        spittle.getLatitude(),
        spittle.getLongitude());
  }

  private static class PostRowMapper implements RowMapper<Post> {
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Post(
          rs.getLong("id"),
          rs.getString("message"), 
          rs.getDate("created_at"), 
          rs.getDouble("longitude"), 
          rs.getDouble("latitude"));
    }
  }
  
}
