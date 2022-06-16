package cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 密钥管理 Excel 导出 Request VO", description = "参数和 BitLockerSecretPageReqVO 是一致的")
@Data
public class BitLockerSecretExportReqVO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "PC名称")
    private String pcName;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "密钥id")
    private String secretId;

    @ApiModelProperty(value = "TPM状态")
    private Boolean tpmStatus;

    @ApiModelProperty(value = "加密状态")
    private Boolean encrypStatus;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
