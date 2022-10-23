<template>
  <div class="login-container">
    <div class="login-wrap">
      <div class="login-title" >注册</div>
      <el-row type="flex" justify="center">
      <!-- 注册表单 -->
      <el-form
        status-icon
        ref="ruleForm"
        class="login-form"
      >
        <!-- 用户名输入框 -->
        <el-form-item prop="username">
          <el-input
            v-model="username"
            prefix-icon="el-icon-user-solid"
            placeholder="请输入您的邮箱号"
            label="邮箱号"
            @keyup.enter="register"
          />
        </el-form-item>
      
        <!-- 密码输入框 -->
        <el-form-item prop="password">
          <el-input
            v-model="password"
            prefix-icon="iconfont el-icon-mymima"
            show-password
            placeholder="密码"
            @keyup.enter.native="register"
          />
        </el-form-item>
        <el-form-item style="text-align: center">
        <!-- 注册按钮 -->
      <el-button type="primary" @click="register" >注册</el-button>
      <span class="mt-10 login-tip" style="float:right">
              已有账号？<router-link to="/login" >登录</router-link>
            </span>
      
      </el-form-item>
      </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      username: "",
      code: "",
      password: "",
      flag: true,
      codeMsg: "发送",
      time: 60,
      show: false,
      // rules: {
      //   username: [
      //     { required: true, message: "用户名不能为空", trigger: "blur" },
      //   ],
      //   password: [{ required: true, message: "密码不能为空", trigger: "blur" }]
      // }
    };
  },
  methods: {
    register() {
      // var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      // if (!reg.test(this.username)) {
      //   this.$toast({ type: "error", message: "邮箱格式不正确" });
      //   return false;
      // }
      // if (this.code.trim().length != 6) {
      //   this.$toast({ type: "error", message: "请输入6位验证码" });
      //   return false;
      // }
      // if (this.password.trim().length < 6) {
      //   this.$toast({ type: "error", message: "密码不能少于6位" });
      //   return false;
      // }
      const user = {
        username: this.username,
        password: this.password,
        code: this.code
      };
      this.axios.post("/api/register", user).then(({ data }) => {
        if (data.flag) {
          this.$message.success("注册成功");
          this.$router.push({ path: "/login" });
        } else {
          this.$message.fail("注册失敗");
        }
      });
    },
    
  }
};
</script>

<style scoped>
.login-container {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: url(https://static.talkxj.com/config/0w3pdr.jpg) center center /
    cover no-repeat;
}
.login-card {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  background: #fff;
  padding: 170px 60px 180px;
  width: 350px;
}
.login-wrap {
  background: #fff;
  width: 400px;
  height: 350px;
  margin: 215px auto;
  overflow: hidden;
  padding-top: 10px 10px, 10px;
  line-height: 40px;
}
.login-title {
  color: #303133;
  font-weight: bold;
  font-size: 1rem;
  text-align:center
}
.login-form {
  margin-top: 1.2rem;
}
.login-card button {
  margin-top: 1rem;
  width: 100%;
}
</style>
