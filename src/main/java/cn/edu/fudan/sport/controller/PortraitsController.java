package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.PortraitDao;
import cn.edu.fudan.sport.view.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/portraits/{id}")
public class PortraitsController {

    @Autowired
    private PortraitDao portraitDao;

    @RequestMapping(method = RequestMethod.POST)
    public BaseVo upload(@PathVariable Integer id, @RequestParam MultipartFile file) {
        if (file.isEmpty()) return new BaseVo(0);
        portraitDao.saveOrUpdate(id, file);
        return new BaseVo(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void download(@PathVariable Integer id, HttpServletResponse response) {
        byte[] bytes = portraitDao.load(id);
        response.setContentType("image/jpeg");
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
