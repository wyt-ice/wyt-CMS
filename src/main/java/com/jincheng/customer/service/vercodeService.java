package com.jincheng.customer.service;

import com.jincheng.customer.util.ValidateCode;
import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class vercodeService {
    @Autowired
    HttpServletRequest request;

    public String getVerocde() throws IOException {
        ValidateCode vCode = new ValidateCode(100, 25, 4, 10);
        String vercode = vCode.getCode();
        request.getSession().setAttribute("vercode", vercode);
//        String path = "E:\\360MoveData\\Users\\asus\\Desktop\\crm\\src\\main\\resources\\static\\vercodeImg\\"+new Date().getTime()+".png";
//        vCode.write(path);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(vCode.getBuffImg(), "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        System.out.println("base:" + base64);
        return base64;
    }
}
