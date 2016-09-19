package com.eclipsesv.controller;

import com.eclipsesv.dao.*;
import com.eclipsesv.model.Comments;
import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.Groups;
import com.eclipsesv.model.User;
import com.eclipsesv.shiro.ShiroConfiguration;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by eclipse on 16/9/18.
 */
@Controller
@Transactional
public class Discussion {

    @RequestMapping(value = "/newDiscussion/{groupid}",method = RequestMethod.POST)
    public String newDiscussion(@PathVariable String groupid,ModelMap model, DiscussionGroup discussionGroup){
        if (discussionGroup != null) {
            System.out.print(groupid);
        }
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        helper.newDiscussionGroup(discussionGroup,helper.getCurrentUser().getUserId(),groupid);
        return "redirect:/group/"+groupid;
    }

    @RequestMapping(value = "/discussion",method = RequestMethod.GET)
    public String discussionGroup(ModelMap model) {
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        User currentUser = helper.getCurrentUser();
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            List<Groups> glist = groupUserDAOImpl.listGroup(currentUser.getUserId());
            model.addAttribute("groups", glist);
            Groups newgroup = new Groups();
            model.addAttribute("newgroup", newgroup);
            return "discussion";
        }
        return "redirect:" + ShiroConfiguration.loginUrl;
    }

    @RequestMapping(value = "/discuss/{id}",method = RequestMethod.GET)
    public String getDiscussion(@PathVariable String id,ModelMap model) {
        System.out.println(id);
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        DiscussionGroup discussionGroup = helper.getDiscussionGroup(id);
        model.addAttribute("discussion", discussionGroup);
        List<Comments> comments = helper.getComments(id);
        model.addAttribute("comments", comments);

        User user = helper.getCurrentUser();
        model.addAttribute("user", user);

        Comments newComment = new Comments();
        model.addAttribute("newComment", newComment);

        return "discussion";
    }

    @RequestMapping(value = "/comments/{id}",method = RequestMethod.POST)
    public String newComment(@PathVariable String id,ModelMap model,Comments newComment) {
        System.out.println(id);
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        helper.newComment(newComment, helper.getCurrentUser().getUserId(),helper.getCurrentUser().getUserName(), id);
        return "redirect:/discuss/"+id;
    }


    @RequestMapping(value = "/comments/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delComment(@RequestBody String[] userArray){
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        if (userArray[0] != null) {
            String groupid = helper.delComment(userArray[0]);
            System.out.println(userArray[0]+"评论已删除");
            return "redirect:/discuss/"+groupid;
        }
        else
            return "403";
    }


    @RequestMapping(value = "/discuss/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delDiscuss(@RequestBody String[] userArray) {
        ControllerHelper helper = new ControllerHelper(userDAOImpl, groupUserDAOImpl, groupDAOImpl,
                discussionDAOImpl,commentDAOImpl);
        if (userArray[0] != null) {
            String groupid = helper.delDiscussionGroup(userArray[0]);
            System.out.println(userArray[0]+"讨论组已删除");
            return "redirect:/group/"+groupid;
        }
        else
            return "403";
    }



    @Autowired
    private UserDAOImpl userDAOImpl;

    @Autowired
    private GroupUserDAOImpl groupUserDAOImpl;

    @Autowired
    private GroupDAOImpl groupDAOImpl;

    @Autowired
    private DiscussionDAOImpl discussionDAOImpl;

    @Autowired
    private CommentDAOImpl commentDAOImpl;

}
