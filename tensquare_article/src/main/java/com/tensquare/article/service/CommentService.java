package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author yuming
 * @date 2019/2/12
 */
@Service
public class CommentService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CommentDao commentDao;

    /**
     * add
     * @param comment
     */
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        comment.setPublishdate(new Date());
        commentDao.save(comment);
    }

    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    public List<Comment> findByArticleId(String articleId) {
        return commentDao.findByArticleid(articleId);
    }

    /**
     * delete
     * @param id
     */
    public void deleteById(String id) {
        commentDao.deleteById(id);
    }
}
