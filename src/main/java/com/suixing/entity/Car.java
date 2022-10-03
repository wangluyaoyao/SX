package com.suixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    public Float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Float carPrice) {
        this.carPrice = carPrice;
    }
    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getCarDisp() {
        return carDisp;
    }

    public void setCarDisp(String carDisp) {
        this.carDisp = carDisp;
    }
    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }
    public String getCarCase() {
        return carCase;
    }

    public void setCarCase(String carCase) {
        this.carCase = carCase;
    }
    public String getCarExhaust() {
        return carExhaust;
    }

    public void setCarExhaust(String carExhaust) {
        this.carExhaust = carExhaust;
    }
    public String getCarTank() {
        return carTank;
    }

    public void setCarTank(String carTank) {
        this.carTank = carTank;
    }
    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }
    public String getBackuPlus() {
        return backuPlus;
    }

    public void setBackuPlus(String backuPlus) {
        this.backuPlus = backuPlus;
    }

    @Override
    public String toString() {
        return "SxCar{" +
            "carId=" + carId +
            ", carName=" + carName +
            ", carBrand=" + carBrand +
            ", carPrice=" + carPrice +
            ", busId=" + busId +
            ", carStatus=" + carStatus +
            ", carImg=" + carImg +
            ", carModel=" + carModel +
            ", carDisp=" + carDisp +
            ", carSeat=" + carSeat +
            ", carCase=" + carCase +
            ", carExhaust=" + carExhaust +
            ", carTank=" + carTank +
            ", backup=" + backup +
            ", backuPlus=" + backuPlus +
        "}";
    }
}
