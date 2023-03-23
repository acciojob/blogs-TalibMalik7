package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        User user = userRepository1.findById(userId).get();
       Blog blog  = new Blog();
       blog.setTitle(title);
       blog.setContent(content);
       blog.setPubDate(new Date());

       user.getBlogList().add(blog);
       userRepository1.save(user);
       return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        User user = blog.getUser();
        user.getBlogList().remove(blog);
        blogRepository1.deleteById(blogId);
        userRepository1.save(user);

    }
}
