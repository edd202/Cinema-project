<template>
  <div>
    <NavBlack></NavBlack>
    <div id="contents" class="contents_event" style="margin-top: 120px">
      <div class="evnt_img">
        <img
          src="https://caching.lottecinema.co.kr//Media/Event/2917ca3615aa45c78093e34ac06c4228.jpg"
          alt="롯데시네마와 함께하는 <해시태그 시그네> 이동진 평론가 &amp; 이다혜 작가 GV 상영회

<사랑할 땐 누구나 최악이 된다> 제작사
<해시태그 시그네>

이동진 평론가 &amp; 이다혜 작가 GV 상영회

언로맨틱 코미디 <해시태그 시그네>를 관람할 수 있는 특별한 기회!
이동진 평론가, 이다혜 작가와 함께하는 GV 상영회
지금 바로 롯데시네마 홈페이지 또는 모바일 앱에서 예매하세요.

EVENT INFO
진행 일정 : 1월 18일(수) 19:00
진행 극장 : 롯데시네마 월드타워 9관
    참석자 : 이동진 평론가, 이다혜 작가 

유의사항
유의사항
- 영화 상영 후 1시간 정도 GV 행사가 진행됩니다.
- 본 행사는 마스크 미 착용시 입장이 제한됩니다.
- 발열 및 호흡기 질환 증세가 있으실 경우 이용을 자제해주시기 바랍니다.
- 본 행사는 모든 할인 및 관람권 사용이 제한됩니다.
- 본 행사는 현금(오프라인), 신용카드, LPAY로만 결제 가능합니다.
- 예매 취소는 상영 시작 24시간 전까지 가능합니다.
- 본 행사는 사정에 의해 예고 없이 변경/취소될 수 있습니다.


MOVIE STILL

STORY
카페 바리스타로 따분한 인생을 살던 '시그네'에겐 행위 예술가로 매거진 표지를 장식한 남자친구 '토마스'가 있다.  점점 유명해지는 '토마스' 옆에서 자꾸만 소외당하던 '시그네'는 인터넷에서 발견한 정체불명의 알약으로 남자친구의 사랑은 물론, 세상의 관심까지 독차지할 황당한 계획을 세운다

2023.01.11
"
        />
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
