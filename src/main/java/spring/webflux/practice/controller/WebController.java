package spring.webflux.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.webflux.practice.service.PostService;

/**
 * @author Ahsuoy Yekoraf
 * @since 10/3/21
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController extends AbstractWebController {

    private final PostService postService;

    @GetMapping(value = "/posts")
    public String index(Model model) {
        model.addAttribute("posts", postService.getViewAllList());
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String get(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("post", postService.get(id));
        return "single";
    }
}
