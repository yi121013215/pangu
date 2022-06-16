package cn.iocoder.yudao.module.system.service.bitlockersecret;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.bitlockersecret.BitLockerSecretConvert;
import cn.iocoder.yudao.module.system.dal.mysql.bitlockersecret.BitLockerSecretMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 密钥管理 Service 实现类
 *
 * @author 龙宇文
 */
@Service
@Validated
public class BitLockerSecretServiceImpl implements BitLockerSecretService {

    @Resource
    private BitLockerSecretMapper bitLockerSecretMapper;

    @Override
    public Long createBitLockerSecret(BitLockerSecretCreateReqVO createReqVO) {
        // 插入
        BitLockerSecretDO bitLockerSecret = BitLockerSecretConvert.INSTANCE.convert(createReqVO);
        bitLockerSecretMapper.insert(bitLockerSecret);
        // 返回
        return bitLockerSecret.getId();
    }

    @Override
    public void updateBitLockerSecret(BitLockerSecretUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBitLockerSecretExists(updateReqVO.getId());
        // 更新
        BitLockerSecretDO updateObj = BitLockerSecretConvert.INSTANCE.convert(updateReqVO);
        bitLockerSecretMapper.updateById(updateObj);
    }

    @Override
    public void deleteBitLockerSecret(Long id) {
        // 校验存在
        this.validateBitLockerSecretExists(id);
        // 删除
        bitLockerSecretMapper.deleteById(id);
    }

    private void validateBitLockerSecretExists(Long id) {
        if (bitLockerSecretMapper.selectById(id) == null) {
            throw exception(BIT_LOCKER_SECRET_NOT_EXISTS);
        }
    }

    @Override
    public BitLockerSecretDO getBitLockerSecret(Long id) {
        return bitLockerSecretMapper.selectById(id);
    }

    @Override
    public List<BitLockerSecretDO> getBitLockerSecretList(Collection<Long> ids) {
        return bitLockerSecretMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BitLockerSecretDO> getBitLockerSecretPage(BitLockerSecretPageReqVO pageReqVO) {
        return bitLockerSecretMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BitLockerSecretDO> getBitLockerSecretList(BitLockerSecretExportReqVO exportReqVO) {
        return bitLockerSecretMapper.selectList(exportReqVO);
    }

}
