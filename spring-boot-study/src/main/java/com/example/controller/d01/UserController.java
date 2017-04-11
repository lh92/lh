package com.example.controller.d01;

import com.example.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户Controller
 *
 * @author:lh
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    static {
        users.put(1,new User(1,"a1",21));
        users.put(2,new User(2,"a2",22));
        users.put(3,new User(3,"a3",23));
        users.put(4,new User(4,"a4",24));
        users.put(5,new User(5,"a5",25));
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="获取用户列表", notes="")
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value="获取用户列表", notes="")
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    /**
     * @ApiImplicitParam  定义单个参数
     * @ApiImplicitParams 定义多个参数
     * @ApiOperation    ● 展示每个API基本信息        ○ value api名称  ○ notes 备注说明
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    public String putUser(@PathVariable Integer id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value="删除用户", notes="根据用户id删除指定用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Integer")
    public String deleteUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }
}
