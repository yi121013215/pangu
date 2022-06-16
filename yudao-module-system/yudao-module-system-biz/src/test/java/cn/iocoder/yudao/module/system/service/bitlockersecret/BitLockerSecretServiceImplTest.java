package cn.iocoder.yudao.module.system.service.bitlockersecret;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;
import cn.iocoder.yudao.module.system.dal.mysql.bitlockersecret.BitLockerSecretMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link BitLockerSecretServiceImpl} 的单元测试类
*
* @author 龙宇文
*/
@Import(BitLockerSecretServiceImpl.class)
public class BitLockerSecretServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BitLockerSecretServiceImpl bitLockerSecretService;

    @Resource
    private BitLockerSecretMapper bitLockerSecretMapper;

    @Test
    public void testCreateBitLockerSecret_success() {
        // 准备参数
        BitLockerSecretCreateReqVO reqVO = randomPojo(BitLockerSecretCreateReqVO.class);

        // 调用
        Long bitLockerSecretId = bitLockerSecretService.createBitLockerSecret(reqVO);
        // 断言
        assertNotNull(bitLockerSecretId);
        // 校验记录的属性是否正确
        BitLockerSecretDO bitLockerSecret = bitLockerSecretMapper.selectById(bitLockerSecretId);
        assertPojoEquals(reqVO, bitLockerSecret);
    }

    @Test
    public void testUpdateBitLockerSecret_success() {
        // mock 数据
        BitLockerSecretDO dbBitLockerSecret = randomPojo(BitLockerSecretDO.class);
        bitLockerSecretMapper.insert(dbBitLockerSecret);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BitLockerSecretUpdateReqVO reqVO = randomPojo(BitLockerSecretUpdateReqVO.class, o -> {
            o.setId(dbBitLockerSecret.getId()); // 设置更新的 ID
        });

        // 调用
        bitLockerSecretService.updateBitLockerSecret(reqVO);
        // 校验是否更新正确
        BitLockerSecretDO bitLockerSecret = bitLockerSecretMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, bitLockerSecret);
    }

    @Test
    public void testUpdateBitLockerSecret_notExists() {
        // 准备参数
        BitLockerSecretUpdateReqVO reqVO = randomPojo(BitLockerSecretUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> bitLockerSecretService.updateBitLockerSecret(reqVO), BIT_LOCKER_SECRET_NOT_EXISTS);
    }

    @Test
    public void testDeleteBitLockerSecret_success() {
        // mock 数据
        BitLockerSecretDO dbBitLockerSecret = randomPojo(BitLockerSecretDO.class);
        bitLockerSecretMapper.insert(dbBitLockerSecret);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBitLockerSecret.getId();

        // 调用
        bitLockerSecretService.deleteBitLockerSecret(id);
       // 校验数据不存在了
       assertNull(bitLockerSecretMapper.selectById(id));
    }

    @Test
    public void testDeleteBitLockerSecret_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> bitLockerSecretService.deleteBitLockerSecret(id), BIT_LOCKER_SECRET_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBitLockerSecretPage() {
       // mock 数据
       BitLockerSecretDO dbBitLockerSecret = randomPojo(BitLockerSecretDO.class, o -> { // 等会查询到
           o.setUsername(null);
           o.setPcName(null);
           o.setIp(null);
           o.setSecretId(null);
           o.setTpmStatus(null);
           o.setEncrypStatus(null);
           o.setCreateTime(null);
       });
       bitLockerSecretMapper.insert(dbBitLockerSecret);
       // 测试 username 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setUsername(null)));
       // 测试 pcName 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setPcName(null)));
       // 测试 ip 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setIp(null)));
       // 测试 secretId 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setSecretId(null)));
       // 测试 tpmStatus 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setTpmStatus(null)));
       // 测试 encrypStatus 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setEncrypStatus(null)));
       // 测试 createTime 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setCreateTime(null)));
       // 准备参数
       BitLockerSecretPageReqVO reqVO = new BitLockerSecretPageReqVO();
       reqVO.setUsername(null);
       reqVO.setPcName(null);
       reqVO.setIp(null);
       reqVO.setSecretId(null);
       reqVO.setTpmStatus(null);
       reqVO.setEncrypStatus(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       PageResult<BitLockerSecretDO> pageResult = bitLockerSecretService.getBitLockerSecretPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBitLockerSecret, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBitLockerSecretList() {
       // mock 数据
       BitLockerSecretDO dbBitLockerSecret = randomPojo(BitLockerSecretDO.class, o -> { // 等会查询到
           o.setUsername(null);
           o.setPcName(null);
           o.setIp(null);
           o.setSecretId(null);
           o.setTpmStatus(null);
           o.setEncrypStatus(null);
           o.setCreateTime(null);
       });
       bitLockerSecretMapper.insert(dbBitLockerSecret);
       // 测试 username 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setUsername(null)));
       // 测试 pcName 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setPcName(null)));
       // 测试 ip 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setIp(null)));
       // 测试 secretId 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setSecretId(null)));
       // 测试 tpmStatus 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setTpmStatus(null)));
       // 测试 encrypStatus 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setEncrypStatus(null)));
       // 测试 createTime 不匹配
       bitLockerSecretMapper.insert(cloneIgnoreId(dbBitLockerSecret, o -> o.setCreateTime(null)));
       // 准备参数
       BitLockerSecretExportReqVO reqVO = new BitLockerSecretExportReqVO();
       reqVO.setUsername(null);
       reqVO.setPcName(null);
       reqVO.setIp(null);
       reqVO.setSecretId(null);
       reqVO.setTpmStatus(null);
       reqVO.setEncrypStatus(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       List<BitLockerSecretDO> list = bitLockerSecretService.getBitLockerSecretList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBitLockerSecret, list.get(0));
    }

}
