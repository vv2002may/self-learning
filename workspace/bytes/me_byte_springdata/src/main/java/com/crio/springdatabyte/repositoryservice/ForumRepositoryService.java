package com.crio.springdatabyte.repositoryservice;

import com.crio.springdatabyte.dto.Post;
import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.dto.User;
import com.crio.springdatabyte.entity.UserEntity;
import java.util.List;

public interface ForumRepositoryService {

  Stats getForumStats();

  List<Post> getPostsByUser(String username);

  List<User> getUsersWithPostText(String text);

  List<User> getUsers();

}
