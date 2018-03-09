package cn.gzcb.export.controller;

import cn.gzcb.export.common.constant.ExportConstant;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
@Controller
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping(value = "index")
    public String index(){
        return "index";
    }

    /**
     * 主页分页显示
     * @param request
     * @param p
     * @return
     */
    @GetMapping(value = "get/{p}")
    public String indexPage(HttpServletRequest request, int p) throws FileNotFoundException, SQLException {
        //防止页码错误注入
        int curPage = p < 0 || p > ExportConstant.PAGE_SIZE ? 1 : p;
        List<Customer> customers=exportService.getCustomerJdbc(curPage);
        int count =exportService.getCustomerCount();
        int pageSize=0;
        if((count%ExportConstant.PAGE_SIZE)!=0){
            pageSize=count%ExportConstant.PAGE_SIZE+1;
        }else {
            pageSize=count%ExportConstant.PAGE_SIZE;
        }
        request.setAttribute("customers",customers);
        return "index";
    }

}
