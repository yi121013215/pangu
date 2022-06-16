package cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 密钥管理 DO
 *
 * @author 龙宇文
 */
@TableName("system_bit_locker_secret")
@KeySequence("system_bit_locker_secret_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitLockerSecretDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * PC名称
     */
    private String pcName;
    /**
     * ip
     */
    private String ip;
    /**
     * 密钥id
     */
    private String secretId;
    /**
     * 密钥
     */
    private String secret;
    /**
     * TPM状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Boolean tpmStatus;
    /**
     * 加密状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Boolean encrypStatus;

}
