<template>
  <div class="flex flex-col w-full space-y-2">
    <!--banner-->
    <div class="flex flex-row justify-between">
      <div>
        <!-- 新增配置 -->
        <el-button type="primary" @click="addDialogVisible = true"
          >新增</el-button
        >
        <el-dialog v-model="addDialogVisible" title="新增配置" width="30%">
          <el-form
            ref="newConfigFormRef"
            :model="newConfigForm"
            label="70px"
            label-position="left"
            label-width="auto"
          >
            <el-form-item label="配置项" prop="configItem">
              <el-input
                v-model="newConfigForm.configItem"
                placeholder="单行输入"
              />
            </el-form-item>
            <el-form-item label="配置值" prop="configValue">
              <el-input
                v-model="newConfigForm.configValue"
                placeholder="单行输入"
              />
            </el-form-item>
            <el-form-item label="备注" prop="note">
              <el-input
                v-model="newConfigForm.remark"
                placeholder="多行输入"
                :rows="4"
                type="textarea"
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="closeAddDialogSubmitForm()">
                确定
              </el-button>
              <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
          </template>
        </el-dialog>
      </div>
      <div class="flex flex-row gap-2">
        <el-input v-model="queryKey" />
        <el-button type="primary" @click="query">查询</el-button>
      </div>
    </div>

    <!-- 表格主体 -->
    <div class="w-full flex flex-col items-center space-y-2">
      <el-table
        :data="
          configs.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        "
        stripe
        class="w-full"
      >
        <el-table-column prop="id" label="配置 ID"></el-table-column>
        <el-table-column prop="configItem" label="配置项"></el-table-column>
        <el-table-column prop="configValue" label="配置值"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间"></el-table-column>
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button link type="primary" @click="showEditDialog(row)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确认删除？"
              confirm-button-text="确认"
              cancel-button-text="取消"
              @confirm="deleteRow(row)"
            >
              <template #reference>
                <el-button link type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="configs.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>

  <!-- 编辑角色 -->
  <el-dialog v-model="editDialogVisible" title="编辑" width="30%">
    <el-form
      ref="editConfigFormRef"
      :model="editConfigForm"
      label="70px"
      label-position="left"
      label-width="auto"
    >
      <el-form-item label="配置 ID" prop="configId">
        <el-input v-model="editConfigForm.configId" disabled />
      </el-form-item>
      <el-form-item label="配置项" prop="configItem">
        <el-input v-model="editConfigForm.configItem" />
      </el-form-item>
      <el-form-item label="配置值" prop="configValue">
        <el-input v-model="editConfigForm.configValue" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="editConfigForm.remark" :rows="4" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="closeEditDialogSubmitForm">
          确定
        </el-button>
        <el-button @click="editDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";
import axiosInstance from "~/utils/axiosInstance";
import type { Config } from "~/types";

onMounted(() => {
  listConfigs();
});

const configs = ref<Config[]>([]);

const listConfigs = () => {
  configs.value = [];
  axiosInstance({
    method: "get",
    url: "/system/config/selectByPage/1/10000",
  }).then((response) => {
    for (const configFromResponse of response.data.data.list) {
      const config = {
        id: configFromResponse.id,
        configItem: configFromResponse.configName,
        configValue: configFromResponse.configValue,
        remark: configFromResponse.remark,
        createTime: configFromResponse.createTime,
        updateTime: configFromResponse.updateTime,
      };
      configs.value.push(config);
    }
  });
  console.log(configs.value);
};

const addConfig = (configItem: string, configValue: string, remark: string) => {
  axiosInstance({
    method: "post",
    url: "/system/config/createConfig",
    data: {
      configItem: configItem,
      configValue: configValue,
      remark: remark,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const deleteConfig = (configId: string) => {
  axiosInstance({
    method: "post",
    url: "/system/config/deleteConfig",
    data: {
      configId: configId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const updateConfig = (
  configId: string,
  configItem: string,
  configValue: string,
  remark: string
) => {
  axiosInstance({
    method: "post",
    url: "/system/config/updateConfig",
    data: {
      configId: configId,
      configItem: configItem,
      configValue: configValue,
      remark: remark,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

// add new config
const newConfigForm = reactive({
  configItem: "",
  configValue: "",
  remark: "",
});

const addDialogVisible = ref(false);

const closeAddDialogSubmitForm = () => {
  addConfig(
    newConfigForm.configItem,
    newConfigForm.configValue,
    newConfigForm.remark
  );
  window.setTimeout(() => {
    listConfigs();
  }, 250);
  newConfigForm.configItem = "";
  newConfigForm.configValue = "";
  newConfigForm.remark = "";
  addDialogVisible.value = false;
};

// edit user
const editConfigForm = reactive({
  configId: "",
  configItem: "",
  configValue: "",
  remark: "",
});
const editDialogVisible = ref(false);

const showEditDialog = (row: Config) => {
  editDialogVisible.value = true;
  editConfigForm.configId = row.id;
  editConfigForm.configItem = row.configItem;
  editConfigForm.configValue = row.configValue;
  editConfigForm.remark = row.remark;
};

const closeEditDialogSubmitForm = () => {
  updateConfig(
    editConfigForm.configId,
    editConfigForm.configItem,
    editConfigForm.configValue,
    editConfigForm.remark
  );
  window.setTimeout(() => {
    listConfigs();
  }, 250);
  editDialogVisible.value = false;
};

// delete user
const deleteRow = (row: Config) => {
  deleteConfig(row.id);
  window.setTimeout(() => {
    listConfigs();
  }, 250);
};

const small = ref(false);
const background = ref(true);
const disabled = ref(false);
const pageSize = ref(10);
const currentPage = ref(1);

const handleSizeChange = (val: number) => {
  pageSize.value = val;
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
};

const queryKey = ref("");
const query = () => {
  listConfigs();
  configs.value = configs.value.filter((config) =>
    config.configItem.includes(queryKey.value)
  );
};
</script>
