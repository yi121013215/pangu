package cn.iocoder.yudao.module.system.service.bitlockersecret;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 密钥管理 Service 接口
 *
 * @author 龙宇文
 */
public interface BitLockerSecretService {

    /**
     * 创建密钥管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBitLockerSecret(@Valid BitLockerSecretCreateReqVO createReqVO);

    /**
     * 更新密钥管理
     *
     * @param updateReqVO 更新信息
     */
    void updateBitLockerSecret(@Valid BitLockerSecretUpdateReqVO updateReqVO);

    /**
     * 删除密钥管理
     *
     * @param id 编号
     */
    void deleteBitLockerSecret(Long id);

    /**
     * 获得密钥管理
     *
     * @param id 编号
     * @return 密钥管理
     */
    BitLockerSecretDO getBitLockerSecret(Long id);

    /**
     * 获得密钥管理列表
     *
     * @param ids 编号
     * @return 密钥管理列表
     */
    List<BitLockerSecretDO> getBitLockerSecretList(Collection<Long> ids);

    /**
     * 获得密钥管理分页
     *
     * @param pageReqVO 分页查询
     * @return 密钥管理分页
     */
    PageResult<BitLockerSecretDO> getBitLockerSecretPage(BitLockerSecretPageReqVO pageReqVO);

    /**
     * 获得密钥管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 密钥管理列表
     */
    List<BitLockerSecretDO> getBitLockerSecretList(BitLockerSecretExportReqVO exportReqVO);

}
