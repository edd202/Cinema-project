<template>
  <div>
    <NavBlack></NavBlack>
    <div id="contents" class="contents_event" style="margin-top: 120px">
      <div class="evnt_img">
        <img src="https://caching.lottecinema.co.kr//Media/Event/34f75db92e87465bbcd1a33e4db4a597.jpg" alt="<장화신은 고양이> 무대인사">
      </div>
      
    </div>
  </div>
</template>

<script>
import NavBlack from "@/components/common/NavBlack.vue";
export default {
  components: {
    NavBlack,
  },
  data() {
    return {
      image: "",
    };
  },
  methods: {
    uploadImg() {
      console.log("upload");
      var image = this.$refs["image"].files[0];

      const url = URL.createObjectURL(image);
      this.image = url;
      console.log(url);
      console.log(this.image);
    },
  },
};
// function ChnImg(){
// 	document.getElementById("id").src = "b.PNG";
// }
</script>
