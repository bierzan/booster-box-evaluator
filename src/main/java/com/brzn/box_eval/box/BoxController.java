package com.brzn.box_eval.box;

import com.brzn.box_eval.box.domain.BoxFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/box")
class BoxController {

    private BoxFacade facade;

    BoxController(BoxFacade facade) {
        this.facade = facade;
    }

}

