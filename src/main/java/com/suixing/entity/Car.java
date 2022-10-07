package com.suixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@TableName("car")
@ApiModel(value = "SxCar对象", description = "")
@Data
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("车辆id")
    @TableId(value = "car_id", type = IdType.AUTO)
    private Integer carId;
    @ApiModelProperty("车辆名")
    private String carName;
    @ApiModelProperty("品牌名")
    private String carBrand;
    @ApiModelProperty("日租价格")
    private Float carPrice;
    @ApiModelProperty("营业网点id")
    private Integer busId;
    @ApiModelProperty("车辆状态")
    private String carStatus;
    @ApiModelProperty("车辆图片")
    private String carImg;
    @ApiModelProperty("车型")
    private String carModel;
    @ApiModelProperty("排量")
    private String carDisp;
    @ApiModelProperty("座位数")
    private String carSeat;
    @ApiModelProperty("变速箱")
    private String carCase;
    @ApiModelProperty("进气")
    private String carExhaust;
    @ApiModelProperty("邮箱")
    private String carTank;
    private String backup;
    private String backuPlus;
}
