package cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 密钥管理 Excel VO
 *
 * @author 龙宇文
 */
@Data
public class BitLockerSecretExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("PC名称")
    private String pcName;

    @ExcelProperty("ip")
    private String ip;

    @ExcelProperty("密钥id")
    private String secretId;

    @ExcelProperty("密钥")
    private String secret;

    @ExcelProperty(value = "TPM状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Boolean tpmStatus;

    @ExcelProperty(value = "加密状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Boolean encrypStatus;

    @ExcelProperty("创建时间")
    private Date createTime;

}
