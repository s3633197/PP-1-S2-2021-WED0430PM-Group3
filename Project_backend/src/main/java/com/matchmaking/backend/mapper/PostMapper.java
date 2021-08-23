package com.matchmaking.backend.mapper;

import com.matchmaking.backend.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void createPost(Post post);

    List<Post> getAllPosts();

    void updatePost(Post post);

    void deletePost(int postId);

    List<Post> getPostsByCompanyId(int companyId);

    Post getPost(int postId);
}
