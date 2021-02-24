<template>
  <div class="button-area">
    <a-button type="primary" @click="modalVisible = true"
      >添加学籍信息</a-button
    >
    <a-modal v-model:visible="modalVisible" title="信息编辑" @ok="saveRecord">
      <a-form
        :model="currentRecord"
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 14 }"
      >
        <a-form-item label="姓名">
          <a-input v-model:value="currentRecord.name" />
        </a-form-item>
        <a-form-item label="出生日期">
          <a-date-picker
            v-model:value="currentRecord.birthday"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </a-form-item>
        <a-form-item label="入学日期">
          <a-date-picker
            v-model:value="currentRecord.enrollmentDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </a-form-item>
        <a-form-item label="入学学院选择">
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
      </a-form>
    </a-modal>
  </div>
  <a-table
    :dataSource="dataSource"
    rowKey="id"
    :columns="columns"
    bordered
    :pagination="false"
    ><template #operation="{ record }">
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
    title: '学生姓名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '出生日期',
    dataIndex: 'birthday',
    key: 'birthday',
  },
  {
    title: '入学日期',
    dataIndex: 'enrollmentDate',
    key: 'enrollmentDate',
  },
  {
    title: '入学学院',
    dataIndex: 'academyEntity.academyName',
    key: 'academy',
  },
  {
    title: '学生状态',
    dataIndex: 'status',
    key: 'status',
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: { customRender: 'operation' },
  },
]
export default {
  name: 'Students',
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
        .get('/student/list', {
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
      http.post('/student/delete/' + id).then(() => {
        message.info('删除成功')
        page.pageNum = 1
        pageChange(page.pageNum, page.pageSize)
      })
    }

    const modalVisible = ref(false),
      currentRecord = reactive({
        name: '',
        birthday: null,
        enrollmentDate: null,
        academy: null,
      })

    const saveRecord = () => {
      console.debug(currentRecord)
      http
        .post('/student/save', {
          name: currentRecord.name,
          birthday: currentRecord.birthday
            ? currentRecord.birthday.format('YYYY-MM-DD HH:mm:ss')
            : null,
          enrollmentDate: currentRecord.enrollmentDate
            ? currentRecord.enrollmentDate.format('YYYY-MM-DD')
            : null,
          academy: currentRecord.academy,
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
