package com.board.controller;

import com.board.data.PostRepository;
import com.board.exception.FoundBlankException;
import com.board.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WriteController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePost(Post post) throws FoundBlankException {
        Date d = new Date();
        SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        post.setDate(today.format(d));

        if(post.getNick() != "" && post.getSubject() != "") {
            postRepository.save(post);
        }
        else throw new FoundBlankException();

        return "redirect:/postview/" + post.getId();
    }

    @ExceptionHandler(FoundBlankException.class)
    public String foundException() {
        return "ErrorPage";
    }
}
