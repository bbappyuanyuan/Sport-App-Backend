package cn.edu.fudan.sport.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Repository
public class PortraitDao {

    private String path = System.getProperty("catalina.home") + "/SPORT_DB";

    public void saveOrUpdate(Integer id, MultipartFile file) {
        File dest = new File(path + "/portrait/" + id);
        if (!dest.exists())
            dest.mkdirs();
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] load(Integer id) {
        File dest = new File(path + "/portrait/" + id);
        if (!dest.exists())
            dest = new File(path + "/portrait/default");
        try {
            FileInputStream fis = new FileInputStream(dest);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n;
            while ((n = fis.read(buf)) != -1)
                bos.write(buf, 0, n);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
