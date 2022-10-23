<template>
  <div>
    <el-dialog title="表单弹框" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="设备名">
          <el-input v-model="form.deviceName"></el-input>
        </el-form-item>
        <el-form-item label="设备功能">
          <el-input v-model="form.func"></el-input>
        </el-form-item>
        <el-form-item label="申请人">
          <el-input v-model="form.applyName"></el-input>
        </el-form-item>
        <el-form-item label="设备功能">
          <el-input v-model="form.reason"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false; applyAdd()">确 定</el-button>
      </span>
    </el-dialog>

    <el-card class="main-card">
    <div class="title">{{roleName}} {{ this.$route.name }}</div>
    <!-- 表格操作 -->
    <div class="operation-container">
      <!-- 条件筛选 -->
      <div style="margin-left:auto">

        <el-button
          type="primary"
          size="small"
          style="margin-left:1rem"
          @click="dialogVisible = true"
          v-if="role == 'devicemanager'"
        >
          申请新增
        </el-button>
      </div>
    </div>
    <!-- 设备管理员首页表格展示 -->
    <el-table border :data="deviceList" v-loading="loading" v-if="role == 'admin' || 'devicemanager'">
      <!-- 表格列 -->
      <el-table-column
        prop="avatar"
        label="设备图像"
        align="center"
        width="100"
      >
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="40" height="40" />
        </template>
      </el-table-column>
      <el-table-column
        prop="deviceName"
        label="设备名"
        align="center"
        width="140"
      />
     
      <el-table-column
        prop="createTime"
        label="创建时间"
        width="130"
        align="center"
      >
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px" />
          {{ scope.row.createTime | date }}
        </template>
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="上次更新时间"
        width="130"
        align="center"
      >
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px" />
          {{ scope.row.updateTime | date }}
        </template>
      </el-table-column>
      <!-- 列操作 -->
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="editDialog(scope.row)"
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="mini"
            @click="deleteDevice(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 审核员首页表格展示 -->
    <el-table border :data="applyList" v-if="role == 'checker'">
      <!-- 表格列 -->
      <el-table-column
        prop="avatar"
        label="设备图像"
        align="center"
        width="100"
      >
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="40" height="40" />
        </template>
      </el-table-column>
      <el-table-column
        prop="deviceName"
        label="设备名"
        align="center"
        width="140"
      />
      <el-table-column
        prop="applyName"
        label="申请人姓名"
        align="center"
        width="140"
      />
      
      <el-table-column
        prop="func"
        label="设备功能"
        align="center"
        width="80"
      >
      </el-table-column>
      <el-table-column
        prop="func"
        label="申请理由"
        align="center"
        width="80"
      >
      </el-table-column>
      <!-- <el-table-column prop="isDisable" label="禁用" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isDisable"
            active-color="#13ce66"
            inactive-color="#F4F4F5"
            :active-value="1"
            :inactive-value="0"
            @change="changeDisable(scope.row)"
          />
        </template>
      </el-table-column> -->
      <!-- 列操作 -->
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="approve(scope.row)"
          >
            同意
          </el-button>
          <el-button
            type="warning"
            size="mini"
            @click="disapprove(scope.row)"
          >
            驳回
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 分页 -->
    <el-pagination
      class="pagination-container"
      background
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="current"
      :page-size="size"
      :total="count"
      :page-sizes="[10, 20]"
      layout="total, sizes, prev, pager, next, jumper"
    />
    <!-- 修改对话框 -->
    <el-dialog :visible.sync="isEdit" width="30%">
      <div class="dialog-title-container" slot="title">
        修改用户
      </div>
      <el-form label-width="60px" size="medium" :model="userForm">
        <el-form-item label="昵称">
          <el-input v-model="userForm.nickname" style="width:220px" />
        </el-form-item>
        <el-form-item label="角色">
          <el-checkbox-group v-model="roleIdList">
            <el-checkbox
              v-for="item of userRoleList"
              :key="item.id"
              :label="item.id"
            >
              {{ item.roleName }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="isEdit = false">取 消</el-button>
        <el-button type="primary" @click="editUserRole">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </el-card>

  </div>
</template>



<script>
import "../../assets/js/china";
// 
export default {
  created() {
    this.role = this.$store.state.roleList[0];
    console.log(this.role)
    if (this.role == "admin") {
      this.roleName = "超级管理员";
      this.listDevices();
    } else if (this.role == "checker") {
      this.listApplyList();
      this.roleName = "审批员"
    } else if(this.role == "devicemanager"){
      this.listDevices()
      this.roleName = "设备管理员"
    }
  
    this.listRoles();
  },
  data: function() {
    return {
      dialogVisible:false,
        form: {
          deviceName: '',
          func:'',
          applyName: '',
          reason: '',
          avatar: 'https://static.talkxj.com/avatar/user.png',
        },
      role:"",
      roleName: "",
      loading: true,
      isEdit: false,
      userForm: {
        userInfoId: null,
        nickname: ""
      },
      loginType: null,
      userRoleList: [],
      roleIdList: [],
      deviceList: [], 
      applyList: [],
      keywords: null,
      current: 1,
      size: 10,
      count: 0
    };
  },
  methods: {

    applyAdd() {
    
      this.axios.post("/api/applylist/add", this.form)
      .then(({data}) => {
        if (data.flag) {
          this.$notify.success({
              title: "申请成功",
              message: data.message
            });
        }
      })
    },
    disapprove(applylist) {
      this.axios.delete("/api/applylist/delete", {
        params: {
          id: applylist.id
        }
      }).then(({data}) => {
              if (data.flag) {
                this.applyList = this.listApplyList();
              }
            })
    },
    approve(applylist) {
      console.log(applylist);
      this.axios.post("/api/deviceinfo/add", {
        deviceinfo: {
          deviceName: applylist.deviceName,
          avatar: applylist.avatar,
          func: applylist.func
        }
      }).then(({data})=> {
          if (data.flag) {
            this.axios.delete("/api/applylist/delete", {
              params: {
                id: applylist.id
              }
            }).then(({data}) => {
              if (data.flag) {
                this.applyList = this.listApplyList();
              }
            })
          }
      })
    },
    deleteDevice(deviceinfo) {
      console.log(deviceinfo.id);
      this.axios.delete("/api/deviceinfo/delete",{
        params: {
          id: deviceinfo.id
        }
      })
      .then(({data}) => {
        if (data.flag) {
          console.log("删除成功")
          this.$notify.success({
              title: "成功",
              message: data.message
            });
          this.listDevices();
        }
      })
      .catch(err => {
        console.error(err); 
      })
    },

    listApplyList() {
      this.axios.get("/api/applylist/list")
      .then(({data}) => {
        if (data.flag) {
          this.applyList = data.data;
        }
      })
    },
    searchUsers() {
      this.current = 1;
      this.listDevices();
    },
    sizeChange(size) {
      this.size = size;
      this.listDevices();
    },
    currentChange(current) {
      this.current = current;
      this.listDevices();
    },
    changeDisable(user) {
      this.axios.put("/api/admin/users/disable", {
        id: user.userInfoId,
        isDisable: user.isDisable
      });
    },
    openEditModel(user) {
      this.roleIdList = [];
      this.userForm = JSON.parse(JSON.stringify(user));
      this.userForm.roleList.forEach(item => {
        this.roleIdList.push(item.id);
      });
      this.isEdit = true;
    },
    editUserRole() {
      this.userForm.roleIdList = this.roleIdList;
      this.axios
        .put("/api/admin/users/role", this.userForm)
        .then(({ data }) => {
          if (data.flag) {
            this.$notify.success({
              title: "成功",
              message: data.message
            });
            this.listDevices();
          } else {
            this.$notify.error({
              title: "失败",
              message: data.message
            });
          }
          this.isEdit = false;
        });
    },
    listDevices() {
      this.axios
        .get("/api/deviceinfo/list", {
          params: {
            current: this.current,
            size: this.size,
            keywords: this.keywords,
            loginType: this.loginType
          }
        })
        .then(({ data }) => {
          this.deviceList = data.data;
          this.count = data.data.count;
          this.loading = false;
        });
    },
    listRoles() {
      this.axios.get("/api/admin/users/role").then(({ data }) => {
        this.userRoleList = data.data;
      });
    }
  },
  watch: {
    loginType() {
      this.current = 1;
      this.listDevices();
    }
  }
};
</script>

<style scoped>
.card-icon-container {
  display: inline-block;
  font-size: 3rem;
}
.area-wrapper {
  display: flex;
  justify-content: center;
}
.card-desc {
  font-weight: bold;
  float: right;
}
.card-title {
  margin-top: 0.3rem;
  line-height: 18px;
  color: rgba(0, 0, 0, 0.45);
  font-size: 1rem;
}
.card-count {
  margin-top: 0.75rem;
  color: #666;
  font-size: 1.25rem;
}
.echarts {
  width: 100%;
  height: 100%;
}
.e-title {
  font-size: 13px;
  color: #202a34;
  font-weight: 700;
}
</style>
