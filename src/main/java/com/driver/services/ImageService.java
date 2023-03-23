package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
          image.setDimensions(dimensions);
          image.setDescription(description);
          image.setBlog(blog);
          blog.getImageList().add(image);
          blogRepository2.save(blog);
          return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        blog.getImageList().remove(image);
        imageRepository2.deleteById(id);
        blogRepository2.save(blog);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
       int count = 0;
       Image image = imageRepository2.findById(id).get();
      String [] screen = screenDimensions.split("X");
      String [] imageS = image.getDimensions().split("X");
      int l = Integer.parseInt(screen[0])/Integer.parseInt(imageS[0]);
      int b = Integer.parseInt(screen[1])/Integer.parseInt(screen[1]);
      count = l*b;

       return count;
    }
}
