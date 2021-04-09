package se.devnews;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    @RequestMapping
    public String index(){
        return "Welcome to Exercise 1";
    }

}
