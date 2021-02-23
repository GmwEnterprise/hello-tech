<template>
  <div class="button-area">
    <a-button type="primary" @click="modalVisible = true"
      >添加学籍信息</a-button
    >
    <a-modal v-model:visible="modalVisible" title="信息编辑" @ok="addRecord">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
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
import { onMounted, readonly, ref, reactive } from 'vue'
import http from '../../plugins/axios'
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
    }

    const onShowSizeChange = (_current, size) => {
      page.pageNum = 1
      page.pageSize = size
      pageChange(page.pageNum, page.pageSize)
    }

    onMounted(() => {
      pageChange(page.pageNum, page.pageSize)
    })

    const deleteLine = (id) => {
      http.post('/student/delete/' + id).then(() => {
        page.pageNum = 1
        pageChange(page.pageNum, page.pageSize)
      })
    }

    const modalVisible = ref(false)
    const addRecord = () => {
      modalVisible.value = false
    }

    return {
      columns,
      dataSource,
      page,
      pageChange,
      onShowSizeChange,
      deleteLine,
      modalVisible,
      addRecord,
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
