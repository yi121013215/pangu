<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="queryParams.username" placeholder="请输入用户名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="PC名称" prop="pcName">
        <el-input v-model="queryParams.pcName" placeholder="请输入PC名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="ip" prop="ip">
        <el-input v-model="queryParams.ip" placeholder="请输入ip" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="密钥id" prop="secretId">
        <el-input v-model="queryParams.secretId" placeholder="请输入密钥id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="TPM状态" prop="tpmStatus">
        <el-select v-model="queryParams.tpmStatus" placeholder="请选择TPM状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.COMMON_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="加密状态" prop="encrypStatus">
        <el-select v-model="queryParams.encrypStatus" placeholder="请选择加密状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.COMMON_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['system:bit-locker-secret:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:bit-locker-secret:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="PC名称" align="center" prop="pcName" />
      <el-table-column label="ip" align="center" prop="ip" />
      <el-table-column label="密钥id" align="center" prop="secretId" />
      <el-table-column label="密钥" align="center" prop="secret" />
      <el-table-column label="TPM状态" align="center" prop="tpmStatus">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.COMMON_STATUS" :value="scope.row.tpmStatus" />
        </template>
      </el-table-column>
      <el-table-column label="加密状态" align="center" prop="encrypStatus">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.COMMON_STATUS" :value="scope.row.encrypStatus" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:bit-locker-secret:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:bit-locker-secret:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="PC名称" prop="pcName">
          <el-input v-model="form.pcName" placeholder="请输入PC名称" />
        </el-form-item>
        <el-form-item label="ip" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入ip" />
        </el-form-item>
        <el-form-item label="密钥id" prop="secretId">
          <el-input v-model="form.secretId" placeholder="请输入密钥id" />
        </el-form-item>
        <el-form-item label="密钥" prop="secret">
          <el-input v-model="form.secret" placeholder="请输入密钥" />
        </el-form-item>
        <el-form-item label="TPM状态" prop="tpmStatus">
          <el-select v-model="form.tpmStatus" placeholder="请选择TPM状态">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.COMMON_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="加密状态" prop="encrypStatus">
          <el-select v-model="form.encrypStatus" placeholder="请选择加密状态">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.COMMON_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createBitLockerSecret, updateBitLockerSecret, deleteBitLockerSecret, getBitLockerSecret, getBitLockerSecretPage, exportBitLockerSecretExcel } from "@/api/system/bitLockerSecret";

export default {
  name: "BitLockerSecret",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 密钥管理列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        username: null,
        pcName: null,
        ip: null,
        secretId: null,
        tpmStatus: null,
        encrypStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
        pcName: [{ required: true, message: "PC名称不能为空", trigger: "blur" }],
        ip: [{ required: true, message: "ip不能为空", trigger: "blur" }],
        secretId: [{ required: true, message: "密钥id不能为空", trigger: "blur" }],
        secret: [{ required: true, message: "密钥不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getBitLockerSecretPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        username: undefined,
        pcName: undefined,
        ip: undefined,
        secretId: undefined,
        secret: undefined,
        tpmStatus: undefined,
        encrypStatus: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加密钥管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getBitLockerSecret(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改密钥管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateBitLockerSecret(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createBitLockerSecret(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除密钥管理编号为"' + id + '"的数据项?').then(function() {
          return deleteBitLockerSecret(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$modal.confirm('是否确认导出所有密钥管理数据项?').then(() => {
          this.exportLoading = true;
          return exportBitLockerSecretExcel(params);
        }).then(response => {
          this.$download.excel(response, '密钥管理.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
