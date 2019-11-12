package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuming
 * @date 2019/2/12
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * add
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "添加评论成功");
    }

    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String articleId) {
        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleId(articleId));
    }

    /**
     * delete
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
