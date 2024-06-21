<template>
  <div class="flex flex-col w-full">
    <!-- banner -->
    <div class="flex flex-row justify-between">
      <div>
        <el-button type="primary" @click="addDialogVisible = true">
          上传镜像
        </el-button>
        <el-dialog v-model="addDialogVisible" title="上传镜像" width="30%">
          <el-form
            ref="newImageFormRef"
            :model="newImageForm"
            label-position="left"
            label-width="auto"
          >
            <el-form-item label="镜像名称" prop="imageName">
              <el-input
                v-model="newImageForm.imageName"
                placeholder="单行输入"
              />
            </el-form-item>
            <el-form-item label="版本号" prop="version">
              <el-input v-model="newImageForm.version" placeholder="单行输入" />
            </el-form-item>
            <el-form-item label="资源类型" prop="imageType">
              <el-select-v2
                v-model="newImageForm.imageType"
                :options="imageOptions"
                clearable
                placeholder="请选择"
                style="width: 300px"
              />
            </el-form-item>
            <el-form-item label="资源类型(压缩包文件)" prop="resourceType">
              <el-upload
                :on-change="handleChange"
                class="upload-demo"
                action=""
                :auto-upload="false"
                ref="uploadRefs"
              >
                <template #trigger>
                  <el-button type="primary">选择文件</el-button>
                </template>
              </el-upload>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="closeAddDialogSubmitForm()">
                上传
              </el-button>
              <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
          </template>
        </el-dialog>
      </div>
      <el-form
        ref="queryFormRef"
        :model="queryForm"
        label-position="left"
        class="flex flex-row gap-2"
      >
        <el-form-item label="镜像名称" prop="imageName">
          <el-input v-model="queryForm.imageName" placeholder="单行输入" />
        </el-form-item>
        <el-form-item label="资源类型" prop="imageType">
          <el-select-v2
            v-model="queryForm.imageType"
            :options="imageOptions"
            placeholder="请选择"
            clearable
            style="width: 200px"
          />
        </el-form-item>
        <el-button
          :disabled="queryForm.imageName === '' && queryForm.imageType === ''"
          type="primary"
          @click="query"
          style="display: flex; float: right"
          >查询</el-button
        >
      </el-form>
    </div>
    <!-- table -->
    <div class="flex flex-col items-center space-y-2">
      <el-table
        :data="
          images.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        "
        stripe
        style="width: 100%"
      >
        <!--          <el-table-column prop="id" label="镜像ID"></el-table-column>-->
        <el-table-column prop="imageName" label="镜像名称"></el-table-column>
        <el-table-column prop="version" label="版本名称"></el-table-column>
        <el-table-column prop="imageType" label="镜像类型"></el-table-column>
        <el-table-column prop="createTime" label="上传时间"></el-table-column>
        <el-table-column prop="updateTime" label="修改时间"></el-table-column>
        <el-table-column label="操作">
          <!--        <template #header>-->
          <!--          <el-input v-model="search" size="small" placeholder="搜索" />-->
          <!--        </template>-->
          <template #default="{ row }">
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
        :total="images.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import instance from "~/services/api";

onMounted(() => {
  listImages();
});

interface Image {
  id: string;
  imageName: string;
  imageType: string;
  version: string;
  path: string;
  createTime: string;
  updateTime: string;
}

const imageOptions = [
  { value: "0", label: "漏洞挖掘" },
  { value: "1", label: "包含漏洞数据镜像" },
  { value: "2", label: "网络攻击镜像" },
  { value: "3", label: "网络防御镜像" },
  { value: "4", label: "靶机镜像" },
];

const images = ref<Image[]>([]);

const listImages = () => {
  images.value = [];
  instance({
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    method: "post",
    url: "/image/selectByPage",
    data: {
      imageName: "",
      imageType: "",
      pageNum: 1,
      pageSize: 1000,
    },
  }).then((response) => {
    for (const image of response.data.data.list) {
      for (let i = 0; i < imageOptions.length; i++) {
        if (image.imageType === imageOptions[i].value)
          image.imageType = imageOptions[i].label;
      }
      images.value.push(image);
    }
  });
};

const query = () => {
  if (!queryForm.imageType && queryForm.imageName === "") {
    window.setTimeout(() => {
      listImages();
    }, 250);
  } else {
    images.value = [];
    instance({
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
      method: "post",
      url: "/image/selectByPage",
      data: {
        imageName: queryForm.imageName,
        imageType: queryForm.imageType,
        pageNum: 1,
        pageSize: 1000,
      },
    }).then((response) => {
      for (const image of response.data.data.list) {
        for (let i = 0; i < imageOptions.length; i++) {
          if (image.imageType === imageOptions[i].value)
            image.imageType = imageOptions[i].label;
        }
        images.value.push(image);
      }
      ElMessage(response.data.message);
    });
  }
};

const deleteImage = (id: string) => {
  instance({
    method: "post",
    url: "/image/deleteImage",
    headers: {
      Authorization: localStorage.getItem("Authorization"),
    },
    data: {
      id: id,
    },
  }).then((response) => {
    ElMessage(response.data.message);
  });
};

const addImage = (formData) => {
  instance
    .post("/image/uploadImage", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: localStorage.getItem("Authorization"),
      },
    })
    .then((response) => {
      ElMessage(response.data.message);
    });
};

const addDialogVisible = ref(false);

const queryForm = reactive({
  imageName: "",
  imageType: "",
});

const newImageForm = reactive({
  imageName: "",
  imageType: "",
  version: "",
});

const uploadedFile = ref(null);

const uploadRefs = ref();

const handleChange = (file) => {
  uploadedFile.value = file.raw;
};

const closeAddDialogSubmitForm = () => {
  const formData = new FormData();
  formData.append("file", uploadedFile.value);
  formData.append("imageName", newImageForm.imageName);
  formData.append("imageType", newImageForm.imageType);
  formData.append("version", newImageForm.version);
  addImage(formData);
  window.setTimeout(() => {
    listImages();
  }, 450);
  newImageForm.imageName = "";
  newImageForm.imageType = "";
  newImageForm.version = "";
  addDialogVisible.value = false;
  uploadRefs.value.clearFiles();
};

const deleteRow = (row) => {
  deleteImage(row.id);
  window.setTimeout(() => {
    listImages();
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
