<template>
  <div class="col-md-12">
    <p>리다이렉트 페이지 입니다.</p>
    {{ userData }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      userData: {},
    };
  },
  mounted() {
    let url = new URL(window.location.href);
    console.log()
  
    const urlParams =url.searchParams;
    // console.log("url은"+urlParams);
    const accessToken = urlParams.get("accessToken");
    const username = urlParams.get("username");
    const names = urlParams.get("names");
    const phone = urlParams.get("phone");
    const gender = urlParams.get("gender");
    const birthDate = urlParams.get("birthDate");
    const email = urlParams.get("email");
    // console.log(accessToken);
    // console.log(username);

    // 임시 객체 생성
    this.userData = {
      accessToken: accessToken,
      username: username,
      names: names,
      phone: phone,
      gender: gender,
      birthDate: birthDate,
      email: email,
      password: "",
      tokenType: "Bearer",
      roles: ["ROLE_USER"],
    };

    localStorage.setItem("user", JSON.stringify(this.userData));

    this.$store.commit('auth/loginSuccess', this.userData);

    this.$router.push("/");
  },
};
</script>
