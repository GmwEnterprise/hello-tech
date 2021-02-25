<template>
  <div class="button-area">
    <a-button type="primary" @click="modalVisible = true">选课</a-button>
    <a-modal v-model:visible="modalVisible" title="信息编辑" @ok="saveRecord">
      <a-form
        :model="currentRecord"
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 14 }"
      >
        <a-form-item label="学生">
          <a-select
            v-model:value="currentRecord.student"
            style="width: 120px"
            ref="academySelect"
            @select="studentChange"
          >
            <a-select-option
              v-for="item in studentList"
              :value="item.id"
              :key="item.id"
              >{{ item.name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="课程">
          <a-select
            v-model:value="currentRecord.course"
            style="width: 120px"
            ref="academySelect"
          >
            <a-select-option
              v-for="item in courseList[currentRecord.currentAcademy]"
              :value="item.id"
              :key="item.id"
              >{{ item.courseName }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="选课时间">
          <a-date-picker
            v-model:value="currentRecord.selectedTime"
            type="date"
            placeholder="选择日期"
            style="width: 100%"
          />
        </a-form-item>
        <a-form-item label="选课情况">
          <a-radio-group
            name="courseSelectionStatus"
            v-model:value="currentRecord.status"
          >
            <a-radio value="开课中">开课中</a-radio>
            <a-radio value="课程完结">结束</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="是否合格">
          <a-radio-group
            name="courseRequired"
            v-model:value="currentRecord.pass"
          >
            <a-radio :value="true">合格</a-radio>
            <a-radio :value="false">不合格</a-radio>
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
    ><template #pass="{ text }">
      <span>{{ text ? '合格' : '不合格' }}</span>
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
    title: '学生',
    dataIndex: 'studentEntity.name',
    key: 'studentEntity.name',
  },
  {
    title: '课程',
    dataIndex: 'courseEntity.courseName',
    key: 'courseEntity.courseName',
  },
  {
    title: '选课时间',
    dataIndex: 'selectedTime',
    key: 'selectedTime',
  },
  {
    title: '选课情况',
    dataIndex: 'status',
    key: 'status',
  },
  {
    title: '是否合格',
    dataIndex: 'pass',
    key: 'pass',
    slots: { customRender: 'pass' },
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: { customRender: 'operation' },
  },
]
export default {
  name: 'CourseSelection',
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
        .get('/selection/list', {
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
      http.post('/selection/delete/' + id).then(() => {
        message.info('删除成功')
        page.pageNum = 1
        pageChange(page.pageNum, page.pageSize)
      })
    }

    const modalVisible = ref(false),
      currentRecord = reactive({
        student: null, // int
        course: null, // int
        selectedTime: null, // Moment
        status: '开课中',
        pass: false,

        // courseList下标
        currentAcademy: 0,
      }),
      studentList = ref([]),
      courseList = ref({})

    http
      .get('/selection/itemList')
      .then((resp) => resp.data)
      .then((data) => {
        studentList.value = data.students
        courseList.value = data.courses
      })

    const saveRecord = () => {
      console.debug(currentRecord)
      http
        .post('/selection/save', {
          student: currentRecord.student,
          course: currentRecord.course,
          selectedTime: currentRecord.selectedTime
            ? currentRecord.selectedTime.format('YYYY-MM-DD HH:mm:ss')
            : null,
          status: currentRecord.status,
          pass: currentRecord.pass,
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
      studentList,
      courseList,
      studentChange: (studentId) => {
        const student = studentList.value.filter(
          (student) => student.id === studentId,
        )
        currentRecord.currentAcademy = student[0].academy
        currentRecord.course = null
      },
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
