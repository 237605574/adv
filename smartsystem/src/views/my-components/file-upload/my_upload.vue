

<style lang="less">
@import "../../../styles/common.less";
@import "upload.less";
@import "my-upload.less";
</style>
<template>
        <div id="app">
            <div style="margin-bottom: 20px">
                <h2>选择图片</h2>
                <a id='addPic' href="" v-on:click="addPic">添加图片 </a>
                <input type="file" @change="onFileChange" multiple style="display: none;">
            </div>
            <div v-if="images.length >0">
               <ul>
                  <li v-for="(image,key) in images">
                     <img :src="image" @click='delImage(key)' />
                     <a href="#" style="position: absolute;" @click='delImage(key)'>
                        <span class="glyphicon glyphicon-remove"></span>
                    </a>
                  </li>
               </ul>
                <button @click="removeImage">移除全部图片</button>
                <button @click='uploadImage' >上传</button>
            </div>
        </div>
</template>
<script>
// Vue.config.debug = true; // 开启vue 调试功能
export default {
  name: "my-upload",
  data() {
    return {
      defaultList: [
        {
          name: "a42bdcc1178e62b4694c830f028db5c0",
          url:
            "https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/avatar"
        },
        {
          name: "bc7521e033abdd1e92222d733590f104",
          url:
            "https://o5wwk8baw.qnssl.com/bc7521e033abdd1e92222d733590f104/avatar"
        }
      ],
      imgName: "",
      visible: false,
      uploadList: [],
      images:[]
    };
  },
  methods: {
    addPic(e) {
      e.preventDefault();
      $("input[type=file]").trigger("click");
      return false;
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
          vm.images.push(this.result);
        };
      }
    },
    delImage: function(index) {
      this.images.shift(index);
    },
    removeImage: function(e) {
      this.images = [];
    },
    uploadImage: function() {
      console.log(this.images);
      return false;
      var obj = {};
      obj.images = this.images;
      $.ajax({
        type: "post",
        url: "upload.php",
        data: obj,
        dataType: "json",
        success: function(data) {
          if (data.status) {
            alert(data.msg);
            return false;
          } else {
            alert(data.msg);
            return false;
          }
        }
      });
    }
  }
};
</script>