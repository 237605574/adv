<style lang="less">
@import "../../../styles/common.less";
@import "upload.less";
</style>


<template>
    <div>
        <Row>
            <Col span="12">
            <div class="padding-left-10">
                <Card>
                    <p slot="title">
                        <Icon type="android-funnel"></Icon>
                        广告基本信息
                    </p>
                    <div>
                        <Form ref="advForm"
                            :model="advInfo"
                            :rules="rule">
                            <FormItem prop="name">
                                <label style="display:inline-block;width:100px">广告名字: </label>
                                <Input clearable
                                    placeholder="输入广告名字"
                                    style="width:400px"
                                    v-model="advInfo.name" />
                            </FormItem>
                            <FormItem prop="homepage">
                                <label style="display:inline-block;width:100px">广告主页: </label>
                                <Input clearable
                                    placeholder="输入广告主页"
                                    style="width:400px"
                                    v-model="advInfo.homepage" />
                            </FormItem>
                            <FormItem prop="startDate">
                                <label style="display:inline-block;width:100px">开始有效时间: </label>
                                <Input clearable
                                    id="start_date"
                                    type="datetime-local"
                                    placeholder="输入起始有效时间"
                                    style="width:400px"
                                    v-model="advInfo.startDate" />
                            </FormItem>
                            <FormItem prop="endDate">
                                <label style="display:inline-block;width:100px">结束有效时间: </label>
                                <Input clearable
                                    id="end_date"
                                    type="datetime-local"
                                    placeholder="输入结束有效时间"
                                    style="width:400px"
                                    v-model="advInfo.endDate" />
                            </FormItem>
                            <FormItem prop="displayDetail">
                                <label style="display:inline-block;width:100px">播放时间段: </label>
                                <select multiple="multiple"
                                    v-model="advInfo.displayDetail"
                                    id="detail_input"
                                    style="width:400px">
                                    <option value="0">0:00 —— 1:00</option>
                                    <option value="1">1:00 —— 2:00</option>
                                    <option value="2">2:00 —— 3:00</option>
                                    <option value="3">3:00 —— 4:00</option>
                                    <option value="4">4:00 —— 5:00</option>
                                    <option value="5">5:00 —— 6:00</option>
                                    <option value="6">6:00 —— 7:00</option>
                                    <option value="7">7:00 —— 8:00</option>
                                    <option value="8">8:00 —— 9:00</option>
                                    <option value="9">9:00 —— 10:00</option>
                                    <option value="10">10:00 —— 11:00</option>
                                    <option value="11">11:00 —— 12:00</option>
                                    <option value="12">12:00 —— 13:00</option>
                                    <option value="13">13:00 —— 14:00</option>
                                    <option value="14">14:00 —— 15:00</option>
                                    <option value="15">15:00 —— 16:00</option>
                                    <option value="16">16:00 —— 17:00</option>
                                    <option value="17">17:00 —— 18:00</option>
                                    <option value="18">18:00 —— 19:00</option>
                                    <option value="19">19:00 —— 20:00</option>
                                    <option value="20">20:00 —— 21:00</option>
                                    <option value="21">21:00 —— 22:00</option>
                                    <option value="22">22:00 —— 23:00</option>
                                    <option value="23">23:00 —— 24:00</option>
                                </select>
                            </FormItem>
                            <FormItem>
                                <Button v-on:click="handleSubmit"
                                    type="primary"
                                    style="width:100x">添加广告</Button>
                            </FormItem>
                        </Form>
                    </div>
                </Card>
            </div>
            </Col>
        </Row>
        <div class="margin-top-10">
            <Col span="24">
            <div class="padding-left-10">
                <Card>
                    <p slot="title">
                        <Icon type="ios-analytics"></Icon>
                        广告文件
                    </p>
                    <div style="margin-bottom: 20px">
                        <Button type="primary"
                            v-on:click="addPic">上传</Button>
                        <input type="file"
                            @change="onFileChange"
                            multiple
                            style="display: none;"
                            accept="image/*"
                            id="adv-file">

                    </div>
                    <div v-if="imageSrc != ''">
                        <img :src="imageSrc"
                            style="max-width:100%" />
                    </div>
                </Card>
            </div>

            </Col>
        </div>
        <Button @click="modalVal = true;modalText='hhhh'">Custom header and footer</Button>
        <Modal v-model="modalVal"
            width="360">
            <p slot="header"
                style="text-align:center">
                <span>信息回馈</span>
            </p>
            <div style="text-align:center">
                <p v-text="modalText"></p>
            </div>
            <div slot="footer">
                <Button type="primary"
                    size="large"
                    long
                    @click="modalVal = false">确定</Button>
            </div>
        </Modal>
    </div>

</template>

<script>
var advWebUrl = "localhost:8081";
export default {
  name: "file-upload",
  data() {
    return {
      imageSrc: "",
      modalVal: false,
      modalText: "",
      advInfo: {
        name: "",
        startDate: "",
        endDate: "",
        homepage: "",
        file: "",
        displayDetail: ""
      },
      rule: {
        name: [{ required: true, message: "请输入广告名字", trigger: "blur" }],
        startDate: [
          { required: true, message: "请输入广告起始时间", trigger: "blur" }
        ],
        endDate: [
          { required: true, message: "请输入广告结束时间", trigger: "blur" }
        ],
        homepage: [
          { required: true, message: "请输入广告跳转主页", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    checkDisplayDetail() {
      var detailInput = $("#detail_input").val();
      if (detailInput == null || detailInput.length == 0) {
        $("#detail_input").tips({
          side: 2,
          msg: "广告投放时间段不能为空",
          bg: "#ff293f",
          time: 3
        });
        return false;
      }
      this.advInfo.displayDetail = detailInput;
      return true;
    },
    checkDate() {
      var minDate = new Date(1900, 1, 1);
      var maxDate = new Date(2200, 1, 1);
      var dStart = new Date(Date.parse($("#start_date").val()));
      var dEnd = new Date(Date.parse($("#end_date").val()));
      if (dStart < minDate || dStart > maxDate) {
        $("#start_time").tips({
          side: 2,
          msg: "日期超出限制",
          bg: "#ff293f",
          time: 3
        });
        return false;
      }
      if (dEnd < minDate || dEnd > maxDate) {
        $("#end_time").tips({
          side: 2,
          msg: "日期超出限制",
          bg: "#ff293f",
          time: 3
        });
        return false;
      }
      return true;
    },
    checkAdvFile() {
      var filePath = $("#adv-file").val();
      if (filePath == null || filePath == "") {
        $("#adv-file").tips({
          side: 2,
          msg: "文件不能为空",
          bg: "#ff293f",
          time: 3
        });
        return false;
      }
      return true;
    },
    addPic(e) {
      e.preventDefault();
      $("#adv-file").trigger("click");
      return false;
    },
    handleSubmit() {
      console.log("te");
      this.$refs.advForm.validate(valid => {
        if (!this.$options.methods.checkDisplayDetail()) {
          return;
        }
         console.log("tooooo");
        if (valid) {
          //  检测广告文件
          if (!this.$options.methods.checkAdvFile()) {
            return;
          }
          //  检测有效时间段
          if (!this.$options.methods.checkDate()) {
            return;
          }
          //    发送ajax请求
          $.ajax({
            type: "POST",
            url: advWebUrl + "/advAction/addInfo",
            data: advData,
            dataType: "json",
            cache: false,
            success: function(result) {
              if (result.code === 0) {
                this.$options.methods.uploadAdvFile();
              } else {
                this.$options.methods.alertMsg(result.msg);
              }
            }
          });
          //   this.$router.push({
          //     name: "home_index"
          //   });
        }
      });
    },
    alertMsg(msg) {
      this.modalVal = true;
      this.modalText = "msg";
    },
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(files);
    },
    createImage(file) {
      if (typeof FileReader === "undefined") {
        alert("您的浏览器不支持图片上传，请升级您的浏览器");
        return false;
      }
      var image = new Image();
      var vm = this;
      var leng = file.length;
      for (var i = 0; i < leng; i++) {
        var reader = new FileReader();
        reader.readAsDataURL(file[i]);
        reader.onload = function(e) {
          vm.imageSrc = this.result;
        };
      }
    }
  }
};
</script>
