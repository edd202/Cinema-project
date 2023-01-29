<template>
  <div id="app">
    <!-- 테스트용 nav 추후 삭제예정 -->
    <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/movie-detail/info">movie-detail</router-link>
    </nav> -->
    <!-- 본문(main/section) -->
    <router-view />
    <!-- footerCom : 꼬리말 -->
    <FooterCom />
  </div>
</template>


<script>
import FooterCom from "@/components/common/FooterCom.vue";

export default {
  components: {
    FooterCom
  }
}
</script>


<style lang="scss">
// 자식 컴포넌트는 아래 css 파일을 공유함
@import "./assets/css/style.css";
</style>
