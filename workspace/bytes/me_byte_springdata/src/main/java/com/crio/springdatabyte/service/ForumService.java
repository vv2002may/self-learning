package com.crio.springdatabyte.service;

import com.crio.springdatabyte.dto.Post;
import com.crio.springdatabyte.dto.Stats;
import com.crio.springdatabyte.dto.User;
import com.crio.springdatabyte.entity.UserEntity;
import java.util.List;

public interface ForumService {

  Stats getForumStats();

  List<Post> getPostsByUser(String username);

  List<Post> getMostRecentPostsByUser(String username, int maxCount);

  List<User> getUsersWithPostText(String text);

  List<User> getUsersList();
}
