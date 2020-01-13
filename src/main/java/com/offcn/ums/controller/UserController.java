package com.offcn.ums.controller;

import com.offcn.ums.bean.User;
import com.offcn.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manageruser")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/")
    public String getUserList(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("userList" ,userList);
        //请求转发
        return "/user/list";
    }

    @RequestMapping("/toAdd")
    public String toadd(User user){
        return "/user/userAdd";//跳转到userAdd.html这个页面进行添加数据
    }

    @PostMapping("/add")
    public String createUser(User user){
        userService.createUser(user);
        //重定向  会跳到当前页面  / 代表显示数据的页面。
        return "redirect:/manageruser/";
    }

    @GetMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable Long id){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "/user/userEdit"; //先去修改数据的页面 进行数据的回显。
    }

    @PostMapping("/edit")
    public String update(User user){
        userService.updateUser(user.getId(),user);
        return "redirect:/manageruser/";  //进行数据的修改 ，然后跳到显示数据的页面。
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/manageruser/";
    }

}
