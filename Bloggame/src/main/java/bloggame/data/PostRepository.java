package bloggame.data;

import java.util.List;

import bloggame.Post;

public interface PostRepository {

  List<Post> findRecentPosts();

  List<Post> findPosts(long max, int count);
  
  Post findOne(long id);

  void save(Post post);

}
