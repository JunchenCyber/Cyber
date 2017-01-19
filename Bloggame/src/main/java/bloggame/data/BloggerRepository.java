package bloggame.data;

import bloggame.Blogger;

public interface BloggerRepository {

  Blogger save(Blogger blogger);
  
  Blogger findByUsername(String username);

}
