package com.tensquare.qa.controller;

import com.tensquare.qa.client.LabelClient;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private LabelClient labelClient;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        Date now = new Date();
        problem.setCreatetime(now);
        problem.setUpdatetime(now);
        problemService.add(problem);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problem.setUpdatetime(new Date());
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据ID查询最新问题
     *
     * @param label label id
     * @param page  page
     * @param size  size
     */
    @RequestMapping(value = "/newlist/{label}/{page}/{size}", method = RequestMethod.GET)
    public Result latest(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> latest = problemService.findLatestByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult<>(latest.getTotalElements(), latest.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据ID查询热门问题
     *
     * @param label label id
     * @param page  page
     * @param size  size
     */
    @RequestMapping(value = "/hotlist/{label}/{page}/{size}", method = RequestMethod.GET)
    public Result hottest(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> latest = problemService.findHottestByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult<>(latest.getTotalElements(), latest.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据ID查询等待回答的问题
     *
     * @param label label id
     * @param page  page
     * @param size  size
     */
    @RequestMapping(value = "/waitlist/{label}/{page}/{size}", method = RequestMethod.GET)
    public Result waited(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
        Page<Problem> latest = problemService.findHottestByLabelId(label, page, size);
        PageResult<Problem> pageResult = new PageResult<>(latest.getTotalElements(), latest.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 通过label client调用
     *
     * @param labelid
     * @return
     */
    @RequestMapping(value = "/label/{labelid}", method = RequestMethod.GET)
    public Result findLabelById(@PathVariable String labelid) {
        return labelClient.findById(labelid);
    }


    /*
    根据标签ID查询最新问题列表
     */
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> listByLabelId = problemService.findNewListByLabelId(labelid, page, size);
        PageResult<Problem> pageResult = new PageResult<Problem>(listByLabelId.getTotalElements(), listByLabelId.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据标签ID查询热门问题列表
     */
    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findHotListByLabelId(@PathVariable String labelid,@PathVariable int page,@PathVariable int size){
        Page<Problem> hotListByLabelId = problemService.findHotListByLabelId(labelid, page, size);
        PageResult<Problem> problemPageResult = new PageResult<>(hotListByLabelId.getTotalElements(), hotListByLabelId.getContent());
        return new Result(true,StatusCode.OK,"查询成功",problemPageResult);
    }

    /**
     * 根据标签ID查询等待回答问题列表
     */
    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findWaitListByLabelId(@PathVariable String labelid,@PathVariable int page,@PathVariable int size){
        Page<Problem> waitListByLabelId = problemService.findWaitListByLabelId(labelid, page, size);
        PageResult<Problem> problemPageResult = new PageResult<>(waitListByLabelId.getTotalElements(), waitListByLabelId.getContent());
        return new Result(true,StatusCode.OK,"查询成功",problemPageResult);
    }

}