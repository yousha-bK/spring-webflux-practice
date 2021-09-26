package spring.webflux.practice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import static spring.webflux.practice.util.Utility.BASE_API_URL;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@RequestMapping(value = BASE_API_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AbstractController {


}
