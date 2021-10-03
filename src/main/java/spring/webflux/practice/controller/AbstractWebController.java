package spring.webflux.practice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ahsuoy Yekoraf
 * @since 10/3/21
 */
@RequestMapping(value = "/web",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.TEXT_HTML_VALUE)
public class AbstractWebController {
}
