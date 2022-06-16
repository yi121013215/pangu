package cn.iocoder.yudao.module.system.controller.admin.bitlockersecret;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.bitlockersecret.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.bitlockersecret.BitLockerSecretDO;
import cn.iocoder.yudao.module.system.convert.bitlockersecret.BitLockerSecretConvert;
import cn.iocoder.yudao.module.system.service.bitlockersecret.BitLockerSecretService;

@Api(tags = "管理后台 - 密钥管理")
@RestController
@RequestMapping("/system/bit-locker-secret")
@Validated
public class BitLockerSecretController {

    @Resource
    private BitLockerSecretService bitLockerSecretService;

    @PostMapping("/create")
    @ApiOperation("创建密钥管理")
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:create')")
    public CommonResult<Long> createBitLockerSecret(@Valid @RequestBody BitLockerSecretCreateReqVO createReqVO) {
        return success(bitLockerSecretService.createBitLockerSecret(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新密钥管理")
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:update')")
    public CommonResult<Boolean> updateBitLockerSecret(@Valid @RequestBody BitLockerSecretUpdateReqVO updateReqVO) {
        bitLockerSecretService.updateBitLockerSecret(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除密钥管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:delete')")
    public CommonResult<Boolean> deleteBitLockerSecret(@RequestParam("id") Long id) {
        bitLockerSecretService.deleteBitLockerSecret(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得密钥管理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:query')")
    public CommonResult<BitLockerSecretRespVO> getBitLockerSecret(@RequestParam("id") Long id) {
        BitLockerSecretDO bitLockerSecret = bitLockerSecretService.getBitLockerSecret(id);
        return success(BitLockerSecretConvert.INSTANCE.convert(bitLockerSecret));
    }

    @GetMapping("/list")
    @ApiOperation("获得密钥管理列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:query')")
    public CommonResult<List<BitLockerSecretRespVO>> getBitLockerSecretList(@RequestParam("ids") Collection<Long> ids) {
        List<BitLockerSecretDO> list = bitLockerSecretService.getBitLockerSecretList(ids);
        return success(BitLockerSecretConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得密钥管理分页")
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:query')")
    public CommonResult<PageResult<BitLockerSecretRespVO>> getBitLockerSecretPage(@Valid BitLockerSecretPageReqVO pageVO) {
        PageResult<BitLockerSecretDO> pageResult = bitLockerSecretService.getBitLockerSecretPage(pageVO);
        return success(BitLockerSecretConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出密钥管理 Excel")
    @PreAuthorize("@ss.hasPermission('system:bit-locker-secret:export')")
    @OperateLog(type = EXPORT)
    public void exportBitLockerSecretExcel(@Valid BitLockerSecretExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BitLockerSecretDO> list = bitLockerSecretService.getBitLockerSecretList(exportReqVO);
        // 导出 Excel
        List<BitLockerSecretExcelVO> datas = BitLockerSecretConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "密钥管理.xls", "数据", BitLockerSecretExcelVO.class, datas);
    }

}
