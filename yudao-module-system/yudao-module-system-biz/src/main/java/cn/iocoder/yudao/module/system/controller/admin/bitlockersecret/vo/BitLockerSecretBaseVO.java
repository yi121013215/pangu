package cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 密钥管理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BitLockerSecretBaseVO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "PC名称", required = true)
    @NotNull(message = "PC名称不能为空")
    private String pcName;

    @ApiModelProperty(value = "ip", required = true)
    @NotNull(message = "ip不能为空")
    private String ip;

    @ApiModelProperty(value = "密钥id", required = true)
    @NotNull(message = "密钥id不能为空")
    private String secretId;

    @ApiModelProperty(value = "密钥", required = true)
    @NotNull(message = "密钥不能为空")
    private String secret;

    @ApiModelProperty(value = "TPM状态")
    private Boolean tpmStatus;

    @ApiModelProperty(value = "加密状态")
    private Boolean encrypStatus;

}
