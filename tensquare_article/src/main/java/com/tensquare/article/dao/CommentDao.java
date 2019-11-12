package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author yuming
 * @date 2019/2/12
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * 根据文章id查询评论
     * @param articleid
     * @return
     */
    List<Comment> findByArticleid(String articleid);
}
