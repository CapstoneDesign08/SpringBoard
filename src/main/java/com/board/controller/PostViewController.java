package com.board.controller;

import com.board.data.PostRepository;
import com.board.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostViewController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/postview/del/{id}")
    public String deletePost(@PathVariable int id) {
        postRepository.delete(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/postview/modify/{id}", method = RequestMethod.GET)
    public String moveToModify(@PathVariable int id, Model model) {
        Post post = postRepository.findById(id);
        model.addAttribute("post", post);

        return "Modify";
    }
}