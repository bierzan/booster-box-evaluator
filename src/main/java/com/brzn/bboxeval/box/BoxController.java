package com.brzn.bboxeval.box;

import com.brzn.bboxeval.box.domain.BoxFacade;
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

