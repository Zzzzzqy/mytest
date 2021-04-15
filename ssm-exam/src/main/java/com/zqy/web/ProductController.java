package com.zqy.web;

import com.github.pagehelper.PageInfo;
import com.zqy.pojo.Category;
import com.zqy.pojo.Product;
import com.zqy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ProductController
 * @Description TODO
 * @Author: zqy
 * @Date: 2021/4/13 15:20
 * @Version 1.0
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/page/{pageSize}/{pageNum}")
    public PageInfo getPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        return productService.getPage(pageNum,pageSize);

    }
    @GetMapping("category")
    public List<Category> getCategory(){
        return productService.getCategory();
    }
    @PutMapping("remove/{pid}")
    public Integer remove(@PathVariable Integer pid){
        return productService.remove(pid);
    }

    @PostMapping("addProductImg")
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        //检查文件是否为空
        if (file.isEmpty()){
            return  "文件为空";
        }
        //检查文件大小  2097152 =2M
       /* if(file.getSize() > 2097152) {
            return "文件大于2M";
        }*/
        //检查是否是图片
        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if(bi == null){
                return "不是图片";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取文件存储路径（绝对路径）
        String path = request.getServletContext().getRealPath("/products");
//        System.out.println("服务器中的相对/WEB-INF/upload/完整路径："+path);
        //获取上传文件后缀名
//        System.out.println("客户端上传的文件名:"+file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String suff = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 获取文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+suff;
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return fileName;
    }

    @GetMapping("one/{pid}")
    public Product getOne(@PathVariable Integer pid){
        return productService.getOne(pid);
    }

    @PostMapping("addProduct")
    public Integer add(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("updateProduct")
    public Integer update(@RequestBody Product product){
        return productService.updateProduct(product);
    }
}
