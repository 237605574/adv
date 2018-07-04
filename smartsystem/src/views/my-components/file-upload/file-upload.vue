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
                  element-id="adv_name"
                  placeholder="输入广告名字"
                  style="width:400px"
                  v-model="advInfo.name" />
              </FormItem>
              <FormItem prop="homepage">
                <label style="display:inline-block;width:100px">广告主页: </label>
                <Input clearable
                  element-id="adv_homepage"
                  placeholder="输入广告主页"
                  style="width:400px"
                  v-model="advInfo.homepage" />
              </FormItem>
              <FormItem prop="startDate">
                <label style="display:inline-block;width:100px">开始有效时间: </label>
                <Input clearable
                  element-id="start_date"
                  type="datetime-local"
                  placeholder="输入起始有效时间"
                  style="width:400px"
                  v-model="advInfo.startDate" />
              </FormItem>
              <FormItem prop="endDate">
                <label style="display:inline-block;width:100px">结束有效时间: </label>
                <Input clearable
                  element-id="end_date"
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
              id="file-btn"
              v-on:click="addPic">选择广告图片</Button>
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
function test() {
  console.log("test function");
  return true;
}
function checkDisplayDetail(advInfo) {
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
  advInfo.displayDetail = detailInput;
  return true;
}
function checkDate() {
  var minDate = new Date(1900, 1, 1);
  var maxDate = new Date(2200, 1, 1);
  var dStart = $("#start_date").val();
  var dEnd = $("#end_date").val();
  dStart = dStart.replace(/T/g, " ").replace(/-/g, "/");
  dEnd = dEnd.replace(/T/g, " ").replace(/-/g, "/");
  dStart = new Date(Date.parse(dStart));
  dEnd = new Date(Date.parse(dEnd));
  if (dStart < minDate || dStart > maxDate) {
    $("#start_date").tips({
      side: 2,
      msg: "日期超出限制",
      bg: "#ff293f",
      time: 3
    });
    return false;
  }
  if (dEnd < minDate || dEnd > maxDate) {
    $("#end_date").tips({
      side: 2,
      msg: "日期超出限制",
      bg: "#ff293f",
      time: 3
    });
    return false;
  }
  return true;
}
function checkAdvFile() {
  var filePath = $("#adv-file").val();
  if (filePath == null || filePath == "") {
    $("#file-btn").tips({
      side: 2,
      msg: "文件不能为空",
      bg: "#ff293f",
      time: 3
    });
    return false;
  }
  return true;
}
var advWebUrl = "http://localhost:8081";
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
    addPic(e) {
      e.preventDefault();
      $("#adv-file").trigger("click");
      return false;
    },
    handleSubmit() {
      console.log("te");
      this.$refs.advForm.validate(valid => {
        if (!checkDisplayDetail(this.advInfo)) {
          return;
        }
        console.log("tooooo");
        if (valid) {
          //  检测广告文件
          if (!checkAdvFile()) {
            return;
          }
          //  检测有效时间段
          if (!checkDate()) {
            return;
          }
          //    发送ajax请求
          var advName = $("#adv_name").val();
          var startDate = $("#start_date").val();
          var endDate = $("#end_date").val();
          var advUrl = $("#adv_homepage").val();
          var advType = 1;
          var displayDetail = $("#detail_input").val();
          var advData = {
            name: advName,
            type: advType,
            startDate: startDate,
            endDate: endDate,
            homepage: advUrl,
            displayDetail: JSON.stringify(displayDetail)
          };
          var ajaxUrl = advWebUrl + "/advAction/addInfo";
          console.log("ajax starting");
          var that = this;
          $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: advData,
            dataType: "json",
            cache: false,
            xhrFields: {
              withCredentials: true
            },
            success: function(result) {
              if (result.code === 0) {
                that.$options.methods.uploadAdvFile(that);
              } else {
                that.$options.methods.alertThatMsg(that,result.msg);
              }
            }
          });
        }
      });
    },
    uploadAdvFile(that) {
      var fd = new FormData();
      fd.append("file", $("#adv-file")[0].files[0]);
      $.ajax({
        type: "post",
        url: advWebUrl + "/advAction/uploadAdvFile",
        async: false,
        processData: false, // 不处理数据
        contentType: false, // 不设置内容类型
        data: fd,
        xhrFields: {
          withCredentials: true
        },
        dataType: "json", //返回类型json、text
        success: function(result) {
          if (result.code === 0) {
            that.$options.methods.addAdv(that);
          } else {
            that.$options.methods.alertThatMsg(that, result.msg);
          }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
          console.log(error);
        }
      });
      // $.ajaxFileUpload({
      //   url: advWebUrl + "/advAction/uploadAdvFile",
      //   secureuri: false, //是否需要安全协议，一般设置为false
      //   fileElementId: "adv-file", //文件上传域的ID
      //   xhrFields: {
      //     withCredentials: true
      //   },
      //   crossDomain: true,
      //   dataType: "json", //返回值类型 一般设置为json
      //   // contentType: "text/html; charset=utf-8",
      //   success: function(result) {
      //     if (result.code === 0) {
      //       that.$options.methods.addAdv();
      //     } else {
      //       that.$options.methods.alertMsg(result.msg);
      //     }
      //   },
      //   error: function(XMLHttpRequest, textStatus, errorThrown) {
      //     console.log(error);
      //   }
      // });
    },
    addAdv(that) {
      $.ajax({
        type: "POST",
        url: advWebUrl + "/advAction/addAdv",
        dataType: "json",
        cache: false,
        xhrFields: {
          withCredentials: true
        },
        success: function(result) {
          if (result.code === 0) {
             that.$options.methods.alertThatMsg(that,"添加广告成功");
          } else {
            that.$options.methods.alertThatMsg(that, result.msg);
          }
        }
      });
    },
    alertMsg(msg) {
      this.modalText = msg;
      this.modalVal = true;
    },
    alertThatMsg(that, msg) {
      that.modalText = msg;
      that.modalVal = true;
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
