package cn.gzcb.export.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
@Controller
public class ExportController {

    @GetMapping(value = "index")
    public String index(){
    return "index";
    }

}
