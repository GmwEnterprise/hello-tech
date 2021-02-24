<template>
  <div class="button-area">
    <a-button type="primary" @click="modalVisible = true"
      >添加课程信息</a-button
    >
    <a-modal v-model:visible="modalVisible" title="信息编辑" @ok="saveRecord">
      <a-form
        :model="currentRecord"
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 14 }"
      >
        <a-form-item label="课程名称">
          <a-input v-model:value="currentRecord.courseName" />
        </a-form-item>
        <a-form-item label="学分">
          <a-input-number
            v-model:value="currentRecord.score"
            :min="1"
            :max="10"
          />
        </a-form-item>
        <a-form-item label="所属学院">
          <a-select
            v-model:value="currentRecord.academy"
            style="width: 120px"
            ref="academySelect"
          >
            <a-select-option
              v-for="item in academyList"
              :value="item.id"
              :key="item.id"
              >{{ item.academyName }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="课程性质">
          <a-radio-group
            name="courseRequired"
            v-model:value="currentRecord.required"
          >
            <a-radio :value="true">必修</a-radio>
            <a-radio :value="false">选修</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
  <a-table
    :dataSource="dataSource"
    rowKey="id"
    :columns="columns"
    bordered
    :pagination="false"
  >
    <template #required="{ text }">
      <span>{{ text ? '必修' : '选修' }}</span>
    </template>
    <template #operation="{ record }">
      <div class="editable-row-operations">
        <a @click="editLine(record.id)">修改</a>
        <a @click="deleteLine(record.id)">删除</a>
      </div>
    </template>
  </a-table>
  <a-pagination
    :style="{ marginTop: '1em' }"
    v-model:current="page.pageNum"
    :total="page.total"
    show-less-items
    showQuickJumper
    show-size-changer
    :defaultPageSize="12"
    :pageSizeOptions="['12', '24', '36', '48']"
    @change="pageChange"
    @showSizeChange="onShowSizeChange"
  />
</template>

<script>
import { onMounted, readonly, ref, reactive, inject } from 'vue'
import http from '../../plugins/axios'
import { message } from 'ant-design-vue'
const columnsVal = [
  {
    title: '编号',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '课程名称',
    dataIndex: 'courseName',
    key: 'courseName',
  },
  {
    title: '学分',
    dataIndex: 'score',
    key: 'score',
  },
  {
    title: '所属学院',
    dataIndex: 'academyEntity.academyName',
    key: 'academy',
  },
  {
    title: '课程性质',
    dataIndex: 'required',
    key: 'required',
    slots: { customRender: 'required' },
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: { customRender: 'operation' },
  },
]
export default {
  name: 'Courses',
  setup() {
    const errorHandler = inject('errorHandler')

    const dataSource = ref([]),
      columns = readonly(columnsVal),
      page = reactive({
        pageNum: 1,
        pageSize: 12,
        total: 0,
      })

    const pageChange = (pageNum, pageSize) => {
      http
        .get('/course/list', {
          params: {
            pageNum,
            pageSize,
          },
        })
        .then((res) => {
          console.log(res)
          dataSource.value = res.data.list
          page.total = res.data.total
        })
        .catch(errorHandler)
    }

    const onShowSizeChange = (_current, size) => {
      page.pageNum = 1
      page.pageSize = size
      pageChange(page.pageNum, page.pageSize)
    }

    const refreshData = () => {
      pageChange(page.pageNum, page.pageSize)
    }

    onMounted(refreshData)

    const deleteLine = (id) => {
      http.post('/course/delete/' + id).then(() => {
        message.info('删除成功')
        page.pageNum = 1
        pageChange(page.pageNum, page.pageSize)
      })
    }

    const modalVisible = ref(false),
      currentRecord = reactive({
        courseName: '',
        score: 3,
        academy: null,
        required: false,
      })

    const saveRecord = () => {
      console.debug(currentRecord)
      http
        .post('/course/save', {
          ...currentRecord,
        })
        .then(refreshData)
        .catch(errorHandler)
      modalVisible.value = false
    }

    const editLine = (id) => {
      console.log('修改line，id=' + id)
    }

    return {
      columns,
      dataSource,
      page,
      pageChange,
      onShowSizeChange,
      deleteLine,
      modalVisible,
      currentRecord,
      saveRecord,
      editLine,
      academyList: inject('academyList'),
    }
  },
}
</script>

<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
.button-area {
  margin-bottom: 1em;
  display: flex;
  justify-content: flex-end;
}
</style>
