package cn.iocoder.yudao.module.system.dal.mysql.bitlockersecret;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;

/**
 * 密钥管理 Mapper
 *
 * @author 龙宇文
 */
@Mapper
public interface BitLockerSecretMapper extends BaseMapperX<BitLockerSecretDO> {

    default PageResult<BitLockerSecretDO> selectPage(BitLockerSecretPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BitLockerSecretDO>()
                .likeIfPresent(BitLockerSecretDO::getUsername, reqVO.getUsername())
                .likeIfPresent(BitLockerSecretDO::getPcName, reqVO.getPcName())
                .eqIfPresent(BitLockerSecretDO::getIp, reqVO.getIp())
                .eqIfPresent(BitLockerSecretDO::getSecretId, reqVO.getSecretId())
                .eqIfPresent(BitLockerSecretDO::getTpmStatus, reqVO.getTpmStatus())
                .eqIfPresent(BitLockerSecretDO::getEncrypStatus, reqVO.getEncrypStatus())
                .betweenIfPresent(BitLockerSecretDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(BitLockerSecretDO::getId));
    }

    default List<BitLockerSecretDO> selectList(BitLockerSecretExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BitLockerSecretDO>()
                .likeIfPresent(BitLockerSecretDO::getUsername, reqVO.getUsername())
                .likeIfPresent(BitLockerSecretDO::getPcName, reqVO.getPcName())
                .eqIfPresent(BitLockerSecretDO::getIp, reqVO.getIp())
                .eqIfPresent(BitLockerSecretDO::getSecretId, reqVO.getSecretId())
                .eqIfPresent(BitLockerSecretDO::getTpmStatus, reqVO.getTpmStatus())
                .eqIfPresent(BitLockerSecretDO::getEncrypStatus, reqVO.getEncrypStatus())
                .betweenIfPresent(BitLockerSecretDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(BitLockerSecretDO::getId));
    }

}
