package com.xiaosi.movieservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosi.commonutils.R;
import com.xiaosi.movieservice.entity.Movie;
import com.xiaosi.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 电影字典表 前端控制器
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/movieservice/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public R getAllMovie(){
        QueryWrapper queryWrapper = new QueryWrapper<Movie>();
        queryWrapper.eq("mid",1);
        Movie one = movieService.getOne(queryWrapper);
        return R.ok().data("movie",one);
    }

}

