package com.suixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@TableName("sx_user_coupno")
@ApiModel(value = "SxUserCoupno对象", description = "")
public class SxUserCoupno implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户优惠劵表id")
    @TableId(value = "user_cou_id", type = IdType.AUTO)
    private Integer userCouId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("优惠卷")
    private Integer couId;

    @ApiModelProperty("优惠劵编号")
    private Integer userCouNum;

    @ApiModelProperty("领取时间")
    private LocalDateTime userCouTime;

    @ApiModelProperty("有效开始")
    private LocalDateTime userCouStart;

    @ApiModelProperty("失效时间")
    private LocalDateTime userCouEnd;

    @ApiModelProperty("使用状态")
    private String userCouState;

    private String backup;

    private String backupPlus;

    public Integer getUserCouId() {
        return userCouId;
    }

    public void setUserCouId(Integer userCouId) {
        this.userCouId = userCouId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }
    public Integer getUserCouNum() {
        return userCouNum;
    }

    public void setUserCouNum(Integer userCouNum) {
        this.userCouNum = userCouNum;
    }
    public LocalDateTime getUserCouTime() {
        return userCouTime;
    }

    public void setUserCouTime(LocalDateTime userCouTime) {
        this.userCouTime = userCouTime;
    }
    public LocalDateTime getUserCouStart() {
        return userCouStart;
    }

    public void setUserCouStart(LocalDateTime userCouStart) {
        this.userCouStart = userCouStart;
    }
    public LocalDateTime getUserCouEnd() {
        return userCouEnd;
    }

    public void setUserCouEnd(LocalDateTime userCouEnd) {
        this.userCouEnd = userCouEnd;
    }
    public String getUserCouState() {
        return userCouState;
    }

    public void setUserCouState(String userCouState) {
        this.userCouState = userCouState;
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
        return "SxUserCoupno{" +
            "userCouId=" + userCouId +
            ", userId=" + userId +
            ", couId=" + couId +
            ", userCouNum=" + userCouNum +
            ", userCouTime=" + userCouTime +
            ", userCouStart=" + userCouStart +
            ", userCouEnd=" + userCouEnd +
            ", userCouState=" + userCouState +
            ", backup=" + backup +
            ", backupPlus=" + backupPlus +
        "}";
    }
}
