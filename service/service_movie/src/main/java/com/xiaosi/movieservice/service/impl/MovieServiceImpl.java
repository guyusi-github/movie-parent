package com.xiaosi.movieservice.service.impl;

import com.xiaosi.movieservice.entity.Movie;
import com.xiaosi.movieservice.mapper.MovieMapper;
import com.xiaosi.movieservice.service.MovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影字典表 服务实现类
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-23
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

}
