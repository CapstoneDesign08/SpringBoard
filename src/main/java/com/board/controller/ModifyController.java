package com.board.controller;

import com.board.data.PostRepository;
import com.board.exception.FoundBlankException;
import com.board.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModifyController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String modifyPost(@PathVariable int id, Post post) throws FoundBlankException {

        if(post.getNick() != "" && post.getSubject() != ""){
            postRepository.save(post);
        }
        else
            throw new FoundBlankException();

        return "redirect:/postview/{id}";
    }

    @ExceptionHandler(FoundBlankException.class)
    public String foundException(){
        System.out.print("foundException 호출");
        return "ErrorPage";
    }
}
