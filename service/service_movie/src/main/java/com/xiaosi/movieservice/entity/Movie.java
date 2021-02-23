package com.xiaosi.movieservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 电影字典表
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Movie对象", description="电影字典表")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mid", type = IdType.ID_WORKER_STR)
    private Integer mid;

    @ApiModelProperty(value = "电影id")
    private Integer movieId;

    @ApiModelProperty(value = "评分")
    private Double rating;

    @ApiModelProperty(value = "评分人数")
    private Integer ratingsCount;

    @ApiModelProperty(value = "电影名")
    private String title;

    @ApiModelProperty(value = "初始电影名")
    private String originalTitle;

    @ApiModelProperty(value = "电影别名")
    private String akaList;

    @ApiModelProperty(value = "电影时长")
    private String durationsList;

    @ApiModelProperty(value = "电影年份")
    private String year;

    @ApiModelProperty(value = "首播时间")
    private String pubdatesList;

    @ApiModelProperty(value = "图片")
    private String movieImage;

    @ApiModelProperty(value = "语言")
    private String languagesList;

    @ApiModelProperty(value = "制片国家地区")
    private String countriesList;

    @ApiModelProperty(value = "编剧的人数")
    private String writersListLen;

    @ApiModelProperty(value = "电音编剧id列表")
    private String writerIdList;

    @ApiModelProperty(value = "电影主演人数")
    private String castsListLen;

    @ApiModelProperty(value = "主演id列表")
    private String castIdList;

    @ApiModelProperty(value = "导演人数")
    private String directorsListLen;

    @ApiModelProperty(value = "导演id列表")
    private String directorIdList;

    @ApiModelProperty(value = "电影标签")
    private String tagsList;

    @ApiModelProperty(value = "电影类型")
    private String genresList;

    @ApiModelProperty(value = "编剧列表")
    private String writerList;

    @ApiModelProperty(value = "主演列表")
    private String castList;

    @ApiModelProperty(value = "导演列表")
    private String directorList;


}
