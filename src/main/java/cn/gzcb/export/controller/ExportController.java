package cn.gzcb.export.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
@Controller
public class ExportController {

    @GetMapping(value = "index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping(value = "doMain")
    public ModelAndView doMain(){
        ModelAndView mav = new ModelAndView("index");
        Map<String,String> map=new HashMap<>();
        mav.addObject("entitys", map);
        return mav;
    }

}
