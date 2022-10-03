package com.suixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@TableName("coupon")
@ApiModel(value = "SxCoupon对象", description = "")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠劵ID")
    @TableId(value = "cou_id", type = IdType.AUTO)
    private Integer couId;

    @ApiModelProperty("条件使用说明")
    private String couExplain;

    @ApiModelProperty("优惠卷类型")
    private String couType;

    @ApiModelProperty("使用周期")
    private String couCycle;

    @ApiModelProperty("活动结束时间")
    private LocalDate couStart;

    @ApiModelProperty("活动结束时间")
    private LocalDate couEnd;

    @ApiModelProperty("库存数量")
    private Integer couAmount;

    private String backup;

    private String backupPlus;

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }
    public String getCouExplain() {
        return couExplain;
    }

    public void setCouExplain(String couExplain) {
        this.couExplain = couExplain;
    }
    public String getCouType() {
        return couType;
    }

    public void setCouType(String couType) {
        this.couType = couType;
    }
    public String getCouCycle() {
        return couCycle;
    }

    public void setCouCycle(String couCycle) {
        this.couCycle = couCycle;
    }
    public LocalDate getCouStart() {
        return couStart;
    }

    public void setCouStart(LocalDate couStart) {
        this.couStart = couStart;
    }
    public LocalDate getCouEnd() {
        return couEnd;
    }

    public void setCouEnd(LocalDate couEnd) {
        this.couEnd = couEnd;
    }
    public Integer getCouAmount() {
        return couAmount;
    }

    public void setCouAmount(Integer couAmount) {
        this.couAmount = couAmount;
    }
    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }
    public String getBackupPlus() {
        return backupPlus;
    }

    public void setBackupPlus(String backupPlus) {
        this.backupPlus = backupPlus;
    }

    @Override
    public String toString() {
        return "SxCoupon{" +
            "couId=" + couId +
            ", couExplain=" + couExplain +
            ", couType=" + couType +
            ", couCycle=" + couCycle +
            ", couStart=" + couStart +
            ", couEnd=" + couEnd +
            ", couAmount=" + couAmount +
            ", backup=" + backup +
            ", backupPlus=" + backupPlus +
        "}";
    }
}
