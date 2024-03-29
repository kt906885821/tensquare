package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
    查询全部数据
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }
    /*
    根据ID查询
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    /*
    增加
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }
    /*
    修改
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.setId(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /*
    修改
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /*
    根据上级ID查询分页列表
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageList = spitService.findParentid(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(),pageList.getContent()));
    }
    /*
    点赞
     */
    @RequestMapping(value = "thumbup/{id}",method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        //判断用户是否点赞
        String userid="1234";
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + id)!=null){
            return new Result(false,StatusCode.REMOTE_ERROR,"你已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }



}
