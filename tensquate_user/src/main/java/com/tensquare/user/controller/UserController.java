package com.tensquare.user.controller;


import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@Scope
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

//    /**
//     * 分页+多条件查询
//     *
//     * @param searchMap 查询条件封装
//     * @param page      页码
//     * @param size      页大小
//     * @return 分页结果
//     */
//    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
//    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
//        Page<User> pageList = userService.findSearch(searchMap, page, size);
//        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
//    }
//
//    /**
//     * 根据条件查询
//     *
//     * @param searchMap
//     * @return
//     */
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public Result findSearch(@RequestBody Map searchMap) {
//        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
//    }
//
//    /**
//     * 增加
//     *
//     * @param user
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public Result add(@RequestBody User user) {
//        userService.add(user);
//        return new Result(true, StatusCode.OK, "增加成功");
//    }
//
//    /**
//     * 修改
//     *
//     * @param user
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Result update(@RequestBody User user, @PathVariable String id) {
//        user.setId(id);
//        userService.update(user);
//        return new Result(true, StatusCode.OK, "修改成功");
//    }
//
//    /**
//     * 删除,jtw鉴权,必须是管理员角色才可以删除
//     * 前端请求头添加Authorization 格式为 Bearer+空格+token
//     *
//     * @param id
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result delete(@PathVariable String id) {
//        /*String authorization = httpServletRequest.getHeader("Authorization");
//        // 未设置头信息
//        if (StringUtils.isEmpty(authorization)) {
//            return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
//        }
//
//        // 头信息不正确
//        if (!authorization.startsWith("Bearer ")) {
//            return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
//        }
//
//        String token = authorization.substring(7);
//        Claims claims = jwtUtil.parseJWT(token);
//        if (claims == null) {
//            return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
//        }
//
//        if (!"admin".equals(claims.get("roles"))) {
//            return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
//        }*/
//
//        // 使用拦截器预先验证可以减少代码冗余(不在每个方法中进行jwt鉴权)
//        Claims adminClaims = (Claims) httpServletRequest.getAttribute("admin_claims");
//        if (adminClaims == null) {
//            return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
//        }
//
//        userService.deleteById(id);
//        return new Result(true, StatusCode.OK, "删除成功");
//    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }

    /**
     * 用户注册
     *
     * @param user
     * @param code
     * @return
     */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@RequestBody User user, @PathVariable String code) {
        userService.addUser(user, code);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    /**
     * 登录
     * @param map
     * @return
     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Result login(@RequestBody Map<String, String> map) {
//        User user = userService.findByLoginMap(map);
//        if (user == null) {
//            return new Result(false, StatusCode.LOGIN_ERROR, "用户名或密码错误");
//        }
//        // 签发token
//        String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
//        Map<String, String> tokenMap = new HashMap<>(16);
//        tokenMap.put("token", token);
//        tokenMap.put("name", user.getNickname());
//        tokenMap.put("avatar", user.getAvatar());
//        return new Result(true, StatusCode.OK, "登录成功", tokenMap);
//    }

    /**
     * 更新用户粉丝
     * @param userId
     * @param count 1:增加,-1:减少
     * @return
     */
//    @RequestMapping(value = "/incfans/{userId}/{count}", method = RequestMethod.PUT)
//    public Result incFansCount(@PathVariable String userId, @PathVariable int count) {
//        userService.updateFansCount(userId, count);
//        return new Result(true, StatusCode.OK, "更新成功");
//    }

    /**
     * 更新用户关注
     * @param userId
     * @param count 1:增加,-1:减少
     * @return
     */
//    @RequestMapping(value = "/incfollow/{userId}/{count}", method = RequestMethod.PUT)
//    public Result incFollowCount(@PathVariable String userId, @PathVariable int count) {
//        userService.updateFollowCount(userId, count);
//        return new Result(true, StatusCode.OK, "更新成功");
//    }
}
