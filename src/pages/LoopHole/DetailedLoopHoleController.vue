<template>
  <div style="display: flex; flex-direction: column; width: 100%">
    <!--    <div>-->
    <!--&lt;!&ndash;      <el-form&ndash;&gt;-->
    <!--&lt;!&ndash;          style="display:flex; flex-direction: row"&ndash;&gt;-->
    <!--&lt;!&ndash;          :model="detailedLoopHoleForm" label="70px" label-position="left">&ndash;&gt;-->
    <!--&lt;!&ndash;&lt;!&ndash;        <el-page-header @back="goBack"></el-page-header>&ndash;&gt;&ndash;&gt;-->
    <!--&lt;!&ndash;        <el-form-item label="漏洞名称" prop="name" style="margin-right: 20px">&ndash;&gt;-->
    <!--&lt;!&ndash;          <el-input v-model="detailedLoopHoleForm.name" placeholder="单行输入" />&ndash;&gt;-->
    <!--&lt;!&ndash;        </el-form-item>&ndash;&gt;-->
    <!--&lt;!&ndash;        <el-form-item label="漏洞发现时间" prop="time">&ndash;&gt;-->
    <!--&lt;!&ndash;          <el-time-select&ndash;&gt;-->
    <!--&lt;!&ndash;              style="width: 300px"&ndash;&gt;-->
    <!--&lt;!&ndash;              v-model="detailedLoopHoleForm.time"&ndash;&gt;-->
    <!--&lt;!&ndash;              start="08:30" step="00:15" end="18:30"&ndash;&gt;-->
    <!--&lt;!&ndash;              placeholder="时间选择" />&ndash;&gt;-->
    <!--&lt;!&ndash;        </el-form-item>&ndash;&gt;-->
    <!--&lt;!&ndash;      </el-form>&ndash;&gt;-->

    <!--      <el-button type="primary" style="display: flex; float: right; margin-bottom: 20px" @click="queryLoopHole">查询漏洞</el-button>-->
    <!--      <el-button type="primary" style="display: flex; float: right; margin-bottom: 20px; margin-right: 10px" @click="queryCategory">查询分类</el-button>-->
    <!--      <el-select-->
    <!--          clearable-->
    <!--          v-model="value1"-->
    <!--          placeholder="请选择查询方式"-->
    <!--          style="width: 170px; margin-bottom: 20px">-->
    <!--        <el-option-->
    <!--            v-for="item in options1"-->
    <!--            :key="item.value1"-->
    <!--            :label="item.label1"-->
    <!--            :value="item.value1"/>-->
    <!--      </el-select>-->
    <!--      <el-input v-model="relateId" placeholder="请输入相应ID" style="width: 180px; margin-bottom: 20px"/>-->
    <!--      <el-button @click="queryCategoryByOption" style="margin-bottom: 20px; margin-right: 40px">查询</el-button>-->

    <!--      <el-input v-model="relateId2" placeholder="根据分类ID查询分类" style="width: 180px; margin-bottom: 20px"/>-->
    <!--      <el-button @click="queryCategoryById" style="margin-bottom: 20px">查询</el-button>-->

    <!--    </div>-->
    <!--    <div>-->
    <!--      <el-button type="primary" @click="addDialogVisible2 = true">新增分类</el-button>-->

    <!--      <el-button type="primary" @click="addDialogVisible1 = true">新增漏洞</el-button>-->

    <!--      <el-dialog v-model="addDialogVisible2" title="新增分类" width="30%">-->
    <!--        <el-form ref="newCategoryFormRef" :model="newCategoryForm" label-position="left" label-width="auto">-->
    <!--          <el-form-item label="分类名称" prop="categoryName">-->
    <!--            <el-input v-model="newCategoryForm.categoryName" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="分类描述" prop="descr">-->
    <!--            <el-input v-model="newCategoryForm.descr" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--        </el-form>-->
    <!--        <template #footer>-->
    <!--      <span class="dialog-footer">-->
    <!--        <el-button-->
    <!--            type="primary"-->
    <!--            @click="closeAddDialogSubmitForm2()">新增-->
    <!--        </el-button>-->
    <!--        <el-button @click="addDialogVisible2 = false"-->
    <!--        >取消</el-button-->
    <!--        >-->
    <!--      </span>-->
    <!--        </template>-->
    <!--      </el-dialog>-->

    <!--      <el-dialog v-model="addDialogVisible1" title="新增漏洞" width="30%">-->
    <!--        <el-form ref="newLoopHoleFormRef" :model="newLoopHoleForm" label-position="left" label-width="auto">-->
    <!--          <el-form-item label="分类ID" prop="categoryId">-->
    <!--            <el-input v-model="newLoopHoleForm.categoryId" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="漏洞名称" prop="holeName">-->
    <!--            <el-input v-model="newLoopHoleForm.holeName" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="漏洞级别" prop="level">-->
    <!--            <el-input v-model="newLoopHoleForm.level" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="元数据" prop="metadata">-->
    <!--            <el-input v-model="newLoopHoleForm.metadata" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="相关容器ID" prop="relateContainerId">-->
    <!--            <el-input v-model="newLoopHoleForm.relateContainerId" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="相关演练ID" prop="relateExerciseId">-->
    <!--            <el-input v-model="newLoopHoleForm.relateExerciseId" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="相关镜像ID" prop="relateImageId">-->
    <!--            <el-input v-model="newLoopHoleForm.relateImageId" placeholder="单行输入" />-->
    <!--          </el-form-item>-->
    <!--        </el-form>-->
    <!--        <template #footer>-->
    <!--      <span class="dialog-footer">-->
    <!--        <el-button-->
    <!--            type="primary"-->
    <!--            @click="closeAddDialogSubmitForm1()">新增-->
    <!--        </el-button>-->
    <!--        <el-button @click="addDialogVisible1 = false"-->
    <!--        >取消</el-button-->
    <!--        >-->
    <!--      </span>-->
    <!--        </template>-->
    <!--      </el-dialog>-->

    <!--      <div v-show="loopHoleShow">-->
    <!--        <el-table :data="datas.slice((currentPage - 1) * pageSize, currentPage * pageSize)">-->
    <!--          <el-table-column prop="categoryId" label="分类ID" />-->
    <!--          <el-table-column prop="id" label="ID" />-->
    <!--          <el-table-column prop="holeName" label="漏洞名称" />-->
    <!--          <el-table-column prop="level" label="漏洞等级" />-->
    <!--          <el-table-column prop="metadata" label="元数据" width="120" show-overflow-tooltip/>-->
    <!--          <el-table-column prop="relateContainerId" label="相关容器ID" />-->
    <!--          <el-table-column prop="relateExerciseId" label="相关演练ID" />-->
    <!--          <el-table-column prop="relateImageId" label="相关镜像ID" />-->
    <!--          <el-table-column prop="createTime" label="创建时间" />-->
    <!--          <el-table-column prop="updateTime" label="更新时间" />-->
    <!--          <el-table-column prop="source" label="漏洞源" />-->
    <!--          <el-table-column label="操作">-->
    <!--            <template #default="{ row }">-->
    <!--              <el-button link type="primary" @click="showEditDialog1(row)">编辑</el-button>-->
    <!--              <el-popconfirm title="确认删除？" confirm-button-text="确认" cancel-button-text="取消" @confirm="deleteRow1(row)">-->
    <!--                <template #reference>-->
    <!--                  <el-button link type="danger">删除</el-button>-->
    <!--                </template>-->
    <!--              </el-popconfirm>-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--        </el-table>-->
    <!--        <el-pagination-->
    <!--            v-model:current-page="currentPage"-->
    <!--            v-model:page-size="pageSize"-->
    <!--            :page-sizes="[5, 10, 20, 30, 40]"-->
    <!--            :small="small"-->
    <!--            :disabled="disabled"-->
    <!--            :background="background"-->
    <!--            layout="total, sizes, prev, pager, next, jumper"-->
    <!--            :total="datas.length"-->
    <!--            @size-change="handleSizeChange"-->
    <!--            @current-change="handleCurrentChange"-->
    <!--        />-->
    <!--      </div>-->
    <!--      <div v-show="!loopHoleShow">-->
    <!--        <el-table :data="datas2.slice((currentPage - 1) * pageSize, currentPage * pageSize)">-->
    <!--          <el-table-column prop="id" label="ID" />-->
    <!--          <el-table-column prop="descr" label="描述" />-->
    <!--          <el-table-column prop="categoryName" label="分类名称" />-->
    <!--          <el-table-column prop="updateTime" label="更新时间" />-->
    <!--          <el-table-column label="操作">-->
    <!--            <template #default="{ row }">-->
    <!--              <el-button link type="primary" @click="showEditDialog2(row)">编辑</el-button>-->
    <!--              <el-popconfirm title="确认删除？" confirm-button-text="确认" cancel-button-text="取消" @confirm="deleteRow2(row)">-->
    <!--                <template #reference>-->
    <!--                  <el-button link type="danger">删除</el-button>-->
    <!--                </template>-->
    <!--              </el-popconfirm>-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--        </el-table>-->
    <!--        <el-pagination-->
    <!--            v-model:current-page="currentPage"-->
    <!--            v-model:page-size="pageSize"-->
    <!--            :page-sizes="[10, 20, 30, 40]"-->
    <!--            :small="small"-->
    <!--            :disabled="disabled"-->
    <!--            :background="background"-->
    <!--            layout="total, sizes, prev, pager, next, jumper"-->
    <!--            :total="datas2.length"-->
    <!--            @size-change="handleSizeChange"-->
    <!--            @current-change="handleCurrentChange"-->
    <!--        />-->
    <!--      </div>-->
    <!--    </div>-->

    <!--    <el-dialog v-model="editDialogVisible1" title="编辑漏洞" width="30%">-->
    <!--      <el-form ref="editForm1Ref" :model="editForm1" label="70px" label-position="left">-->
    <!--        <el-form-item label="分类ID" prop="categoryId">-->
    <!--          <el-input v-model="editForm1.categoryId"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="漏洞名称" prop="holeName">-->
    <!--          <el-input v-model="editForm1.holeName"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="ID" prop="id">-->
    <!--          <el-input v-model="editForm1.id" disabled/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="级别" prop="level">-->
    <!--          <el-input v-model="editForm1.level"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="元数据" prop="metadata">-->
    <!--          <el-input v-model="editForm1.metadata"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="相关容器ID" prop="relateContainerId">-->
    <!--          <el-input v-model="editForm1.relateContainerId"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="相关演练ID" prop="relateExerciseId">-->
    <!--          <el-input v-model="editForm1.relateExerciseId"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="相关镜像ID" prop="relateImageId">-->
    <!--          <el-input v-model="editForm1.relateImageId"/>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--      <template #footer>-->
    <!--      <span class="dialog-footer">-->
    <!--        <el-button type="primary" @click="closeEditDialogSubmitForm1">-->
    <!--          确定-->
    <!--        </el-button>-->
    <!--        <el-button @click="editDialogVisible1 = false">取消</el-button>-->
    <!--      </span>-->
    <!--      </template>-->
    <!--    </el-dialog>-->

    <!--    <el-dialog v-model="editDialogVisible2" title="编辑角色" width="30%">-->
    <!--      <el-form ref="editForm2Ref" :model="editForm2" label="70px" label-position="left">-->
    <!--        <el-form-item label="分类名称" prop="categoryName">-->
    <!--          <el-input v-model="editForm2.categoryName"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="描述" prop="descr">-->
    <!--          <el-input v-model="editForm2.descr"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="ID" prop="id">-->
    <!--          <el-input v-model="editForm2.id" disabled/>-->
    <!--        </el-form-item>-->

    <!--      </el-form>-->
    <!--      <template #footer>-->
    <!--      <span class="dialog-footer">-->
    <!--        <el-button type="primary" @click="closeEditDialogSubmitForm2">-->
    <!--          确定-->
    <!--        </el-button>-->
    <!--        <el-button @click="editDialogVisible2 = false">取消</el-button>-->
    <!--      </span>-->
    <!--      </template>-->
    <!--    </el-dialog>-->
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";
import JSONBIG from "json-bigint";
import axiosInstance from "~/utils/axiosInstance";

onMounted(() => {
  listData();
});

axiosInstance.defaults.transformResponse = [
  function (data) {
    const json = JSONBIG({
      storeAsString: true,
    });
    const res = json.parse(data);
    return res;
  },
];

const value1 = ref("");

const options1 = [
  {
    value1: "0",
    label1: "根据分类ID查询漏洞",
  },
  {
    value1: "1",
    label1: "根据容器ID查询漏洞",
  },
  {
    value1: "2",
    label1: "根据演练ID查询漏洞",
  },
  {
    value1: "3",
    label1: "根据镜像ID查询漏洞",
  },
  {
    value1: "4",
    label1: "根据ID查询漏洞",
  },
];

const query1 = (str) => {
  datas.value = [];
  axiosInstance({
    method: "get",
    url: str,
  }).then((response) => {
    for (const data of response.data.data.list) {
      datas.value.push(data);
      for (const meta of data.metadata) {
        metadatas.value.push(meta);
      }
    }
    ElMessage(response.data.message);
  });
};

const relateId = ref("");

const queryCategoryByOption = () => {
  let str = "";
  if (value1.value === "0") {
    str = "/loophole/get/bycategory/1/1000?category_id=" + relateId.value;
  }
  if (value1.value === "1") {
    str = "/loophole/get/bycontainer/1/1000?container_id=" + relateId.value;
  }
  if (value1.value === "2") {
    str = "/loophole/get/byexercise/1/1000?exercise_id=" + relateId.value;
  }
  if (value1.value === "3") {
    str = "/loophole/get/byimage/1/1000?image_id=" + relateId.value;
  }
  if (value1.value === "4") {
    str = "/loophole/get/byid?id=" + relateId.value;
  }
  window.setTimeout(() => {
    query1(str);
  }, 250);
  relateId.value = "";
};

const query2 = (str) => {
  datas2.value = [];
  axiosInstance({
    method: "get",
    url: str,
  }).then((response) => {
    datas2.value.push(response.data.data);
    ElMessage(response.data.message);
  });
};

const relateId2 = ref("");

const queryCategoryById = () => {
  let str = "";
  str = "/loophole/category/getbyid?id=" + relateId2.value;
  window.setTimeout(() => {
    query2(str);
  }, 250);
  relateId2.value = "";
};

const loopHoleShow = ref(true);
const detailedLoopHoleForm = reactive({
  name: "",
  time: "",
});

interface Data {
  categoryId: string;
  createTime: string;
  holeName: string;
  id: string;
  level: string;
  metadata: object;
  relateContainerId: string;
  relateExerciseId: string;
  relateImageId: null;
  updateTime: string;
}
interface Data2 {
  categoryName: string;
  createTime: string;
  descr: string;
  id: string;
  updateTime;
}

const datas = ref<Data[]>([]);
const datas2 = ref<Data2[]>([]);
const metadatas = ref([]);

const listData = () => {
  datas.value = [];
  axiosInstance({
    method: "get",
    url: "/loophole/get/all/1/1000",
  }).then((response) => {
    for (const data of response.data.data.list) {
      datas.value.push(data);
      for (const meta of data.metadata) {
        metadatas.value.push(meta);
      }
    }
  });
};

const listData2 = () => {
  datas2.value = [];
  axiosInstance({
    method: "get",
    url: "/loophole/category/getall/1/1000",
  }).then((response) => {
    for (const data2 of response.data.data.list) {
      datas2.value.push(data2);
    }
  });
};

const queryLoopHole = () => {
  loopHoleShow.value = true;
  listData();
};

const queryCategory = () => {
  loopHoleShow.value = false;
  listData2();
};

const add1 = (form) => {
  axiosInstance({
    method: "post",
    url: "/loophole/create",
    data: {
      categoryId: form.categoryId,
      holeName: form.holeName,
      level: form.level,
      metadata: form.metadata,
      relateContainerId: form.relateContainerId,
      relateExerciseId: form.relateExerciseId,
      relateImageId: form.relateImageId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const add2 = (form) => {
  axiosInstance
    .post(
      "/loophole/category/create",
      {
        categoryName: form.categoryName,
        descr: form.descr,
      },
      {
        headers: {},
      }
    )
    .then((response) => {
      ElMessage(response.data.message);
    });
};
const addDialogVisible1 = ref(false);
const addDialogVisible2 = ref(false);
const newCategoryForm = reactive({
  categoryName: "",
  descr: "",
});
const newLoopHoleForm = reactive({
  categoryId: "",
  holeName: "",
  level: "",
  metadata: "",
  relateContainerId: "",
  relateExerciseId: "",
  relateImageId: "",
});
const closeAddDialogSubmitForm2 = () => {
  //console.log(newCategoryForm)
  add2(newCategoryForm);
  window.setTimeout(() => {
    listData2();
  }, 250);
  newCategoryForm.categoryName = "";
  newCategoryForm.descr = "";
  addDialogVisible2.value = false;
};
const closeAddDialogSubmitForm1 = () => {
  add1(newLoopHoleForm);
  window.setTimeout(() => {
    listData();
  }, 250);
  newLoopHoleForm.categoryId = "";
  newLoopHoleForm.holeName = "";
  newLoopHoleForm.level = "";
  newLoopHoleForm.metadata = "";
  newLoopHoleForm.relateContainerId = "";
  newLoopHoleForm.relateExerciseId = "";
  newLoopHoleForm.relateImageId = "";
  addDialogVisible1.value = false;
};

const update1 = (form) => {
  axiosInstance({
    method: "post",
    url: "/loophole/update",
    data: {
      categoryId: form.categoryId,
      holeName: form.holeName,
      id: form.id,
      level: form.level,
      metadata: form.metadata,
      relateContainerId: form.relateContainerId,
      relateExerciseId: form.relateExerciseId,
      relateImageId: form.relateImageId,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const editForm1 = reactive({
  categoryId: "",
  holeName: "",
  id: "",
  level: "",
  metadata: "",
  relateContainerId: "",
  relateExerciseId: "",
  relateImageId: "",
});

const editDialogVisible1 = ref(false);
const editDialogVisible2 = ref(false);

const showEditDialog1 = (row) => {
  editDialogVisible1.value = true;
  editForm1.categoryId = row.categoryId;
  editForm1.holeName = row.holeName;
  editForm1.id = row.id;
  editForm1.level = row.level;
  editForm1.metadata = row.metadata;
  editForm1.relateContainerId = row.relateContainerId;
  editForm1.relateExerciseId = row.relateExerciseId;
  editForm1.relateImageId = row.relateImageId;
};

const closeEditDialogSubmitForm1 = () => {
  update1(editForm1);
  window.setTimeout(() => {
    listData();
  }, 250);
  editDialogVisible1.value = false;
};

const update2 = (form) => {
  axiosInstance({
    method: "post",
    url: "/loophole/category/update",
    data: {
      categoryName: form.categoryName,
      id: form.id,
      descr: form.descr,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const editForm2 = reactive({
  categoryName: "",
  descr: "",
  id: "",
});

const showEditDialog2 = (row) => {
  editDialogVisible2.value = true;
  editForm2.categoryName = row.categoryName;
  editForm2.descr = row.descr;
  editForm2.id = row.id;
};

const closeEditDialogSubmitForm2 = () => {
  update2(editForm2);
  window.setTimeout(() => {
    listData2();
  }, 250);
  editDialogVisible2.value = false;
};

const delete1 = (id: string) => {
  axiosInstance({
    method: "post",
    url: "/loophole/delete",
    data: {
      id: id,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const deleteRow1 = (row) => {
  delete1(row.id);
  window.setTimeout(() => {
    listData();
  }, 250);
};

const delete2 = (id: string) => {
  axiosInstance({
    method: "post",
    url: "/loophole/category/delete",
    data: {
      id: id,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const deleteRow2 = (row) => {
  delete2(row.id);
  window.setTimeout(() => {
    listData2();
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
</script>
