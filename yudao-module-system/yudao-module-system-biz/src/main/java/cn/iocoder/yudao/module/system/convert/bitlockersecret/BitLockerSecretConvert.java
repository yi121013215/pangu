package cn.iocoder.yudao.module.system.convert.bitlockersecret;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;

/**
 * 密钥管理 Convert
 *
 * @author 龙宇文
 */
@Mapper
public interface BitLockerSecretConvert {

    BitLockerSecretConvert INSTANCE = Mappers.getMapper(BitLockerSecretConvert.class);

    BitLockerSecretDO convert(BitLockerSecretCreateReqVO bean);

    BitLockerSecretDO convert(BitLockerSecretUpdateReqVO bean);

    BitLockerSecretRespVO convert(BitLockerSecretDO bean);

    List<BitLockerSecretRespVO> convertList(List<BitLockerSecretDO> list);

    PageResult<BitLockerSecretRespVO> convertPage(PageResult<BitLockerSecretDO> page);

    List<BitLockerSecretExcelVO> convertList02(List<BitLockerSecretDO> list);

}
