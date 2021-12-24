package com.kevin.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2021-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TIAccount对象", description="")
public class TIAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录账号")
    private String account;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建账户")
    private String createAccount;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改账户")
    private String updateAccount;

    @ApiModelProperty(value = "删除标记(0为未删除，否则为删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "账号类型（与t_m_account_type表主键对应）")
    private Integer accountType;


}
