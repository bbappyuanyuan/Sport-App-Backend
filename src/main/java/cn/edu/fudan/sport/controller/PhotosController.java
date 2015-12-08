package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.PhotoDao;
import cn.edu.fudan.sport.vo.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/photos/{id}")
public class PhotosController {

    @Autowired
    private PhotoDao photoDao;

    @RequestMapping(method = RequestMethod.POST)
    public BaseVo upload(@PathVariable Integer id, @RequestParam MultipartFile file) {
        if (file.isEmpty()) return new BaseVo(0);
        photoDao.saveOrUpdate(id, file);
        return new BaseVo(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void download(@PathVariable Integer id, HttpServletResponse response) {
        byte[] bytes = photoDao.load(id);
        response.setContentType("image/jpeg");
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
