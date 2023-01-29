<template>
  <div>
    <NavBlack></NavBlack>
    <div id="contents" class="contents_event" style="margin-top: 120px">
      <div class="evnt_img">
        <img src="https://caching.lottecinema.co.kr//Media/Event/eacec403942248a49472c203fc2647fb.jpg" alt="[롯데시네마와 함께하는 <오늘 밤, 세계에서 이 사랑이 사라진다 해도> 흥행 감사 원작 도서 증정 앵콜 상영회]

<오늘 밤, 세계에서 이 사랑이 사라진다 해도>

흥행 감사 원작 도서 증정
전국 앵콜 상영회

영화 한편 가격으로 원작도서까지 GET할 수 있는 찬스!

지금 바로 롯데시네마에서 예매하시면
영화의 원작 도서[오늘 밤, 세계에서 이 사랑이 사라진다 해도]를
당 회차 관객 전원에게 증정합니다!

[EVENT INFO]
진행일정 : 1/15(일) 오후 2시
진행지점 : 건대입구(서울) / 대전둔산(대전) / 동성로(대구)
              센텀시티(부산) / 수원(수원)
경품내역 : 영화의 원작 도서 [오늘 밤, 세계에서 이 사랑이 사라진다 해도]

▲ 도서 [오늘 밤, 세계에서 이 사랑이 사라진다 해도](정가 : 14,000원)

※ 유의사항
- 경품 제공은 (주)미디어캐슬에서 주관하고 있습니다.
- 경품은 해당 회차 상영 후, 퇴장 시 증정됩니다. (이후 별도 수령 불가)
- 경품 이미지는 실제와 다를 수 있으며, 수급 사정에 따라 변경 될 수 있습니다.
- 해당 경품은 제세공과금이 없습니다.
- 모바일 티켓으로는 인증이 불가하오니, 지류 티켓을 꼭 지참해주시길 바랍니다.
- 경품의 경우, 공정 과정상 미세 스크래치, 흠집 등이 있을 수 있으며, 이와 같은 이유는 교환 사유가 될 수 없습니다.

[SYNOPSIS]
“카미야 토루에 대해 잊지 말 것”
자고 일어나면 전날의 기억을 잃는
‘선행성 기억상실증’에 걸린 소녀 ‘마오리’

“내일의 마오리도 내가 즐겁게 해줄 거야&quot;
누구에게도 기억되지 않는
무색무취의 평범한 소년 ‘토루’

매일 밤 사랑이 사라지는 세계,
그럼에도 불구하고, 다음 날 서로를 향한 애틋한 고백을 반복하는
두 소년, 소녀의 가장 슬픈 청춘담

절찬상영중">
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
