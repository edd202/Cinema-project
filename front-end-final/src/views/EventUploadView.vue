<template>
  <div>
    <!-- Upload 시작 -->
    <div class="row justify-content-md-center">
      <div class="col-12">
        <!-- 이미지명(evtTitle) 입력박스 시작 -->
        <div class="mb-3 col-md-5">
          <label for="evtTitle" class="form-label">이미지명</label>
          <input
            type="text"
            class="form-control"
            id="evtTitle"
            required
            name="evtTitle"
            v-model="evtTitle"
          />
        </div>
        <!-- 이미지명 입력박스 끝 -->
        <!-- 이미지명(evtTitle) 입력박스 시작 -->
        <div class="mb-3 col-md-5">
          <label for="evtContent" class="form-label">이미지내용</label>
          <input
            type="text"
            class="form-control"
            id="evtContent"
            required
            name="evtContent"
            v-model="evtContent"
          />
        </div>
        <!-- 이미지명 입력박스 끝 -->

        <!-- 이미지 선택상자 시작 -->
        <div class="mb-3 col-md-5">
          <label class="btn btn-default p-0">
            <!-- <!— 파일 선택상자 —> -->
            <input
              type="file"
              accept="image/*"
              ref="file"
              @change="selectImage"
            />
          </label>
        </div>
        <!-- 이미지 선택상자 끝 -->

        <!-- 이미지 선택상자 시작 -->
        <div class="mb-3 col-md-5">
          <label class="btn btn-default p-0">
            <!-- <!— 파일 선택상자 —> -->
            <input
              type="file"
              accept="image/*"
              ref="file2"
              @change="selectImage"
            />
          </label>
        </div>
        <!-- 이미지 선택상자 끝 -->

        <!-- upload 버튼 : insert 문 실행 시작 -->
        <div class="mb-3">
          <!-- <!— 서버에 insert 문 호출 —> -->
          <button
            class="btn btn-success btn-sm float-left"
            :disabled="!imgThumb"
            @click="upload"
          >
            Upload
          </button>
        </div>
        <!-- upload 버튼 : insert 문 실행 끝 -->
      </div>
    </div>
    <!-- Upload 끝 -->

    <!-- 미리보기 이미지 시작 -->
    <div v-if="previewImage">
      <div>
        <img class="preview my-3" :src="previewImage" alt="" />
      </div>
    </div>
    <!-- 미리보기 이미지 끝 -->

    <!-- 서버 에러 메세지가 있을 경우 아래 출력 시작 -->
    <div v-if="message" class="alert alert-secondary" role="alert">
      {{ message }}
    </div>
    <!-- 서버 에러 메세지가 있을 경우 아래 출력 끝 -->
  </div>
</template>

<script>
// axios 공통 함수 import
import EventDataService from "@/services/EventDataService";

export default {
  data() {
    return {
      imgThumb: undefined, // 현재 이미지 변수
      imgMain: undefined, // 현재 이미지 변수
      previewImage: undefined, // 미리보기 이미지 변수
      message: "", // 서버쪽 메세지를 저장할 변수
      event: [], // 이미지 객체 배열
      searchTitle: "", // 이미지명으로 검색하는 변수

      // springboot 요청할 변수 , 이미지명(evtTitle), 내용(content)
      evtTitle: "",
      evtContent: "",

      // 페이징을 위한 변수 정의
      page: 1, // 현재 페이지
      count: 0, // 전체 데이터 건수
      pageSize: 3, // 한페이지당 몇개를 화면에 보여줄지 결정하는 변수

      pageSizes: [3, 6, 9], // select box 에 넣을 기본 데이터
    };
  },
  methods: {
    // 전체 조회 함수
    retrieveEvent() {
      EventDataService.getFiles(this.searchTitle, this.page - 1, this.pageSize)
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          const { event, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.event = event; // 스프링부트에서 전송한 데이터
          this.count = totalItems; // 스프링부트에서 전송한 페이지정보(총 건수)
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // 파일 선택상자에서 선택한 이미지를 저장하는 함수
    selectImage() {
      // this.$refs : $refs 속성이 있는 컨트롤이 선택됨
      this.imgThumb = this.$refs.file.files.item(0);
      this.imgMain = this.$refs.file2.files.item(0);
      // .createObjectURL() : 이미지 주소만 참조해서 이미지 보여주기 함수
      this.previewImage = URL.createObjectURL(this.imgThumb);
      this.message = "";
    },
    // upload 함수
    upload() {
      EventDataService.upload(
        this.evtTitle,
        this.evtContent,
        this.imgThumb,
        this.imgMain
      )
        // insert 성공 then()
        .then((response) => {
          // 서버쪽 성공 메세지를 저장
          this.message = response.data.message;

          // 화면에 재조회 요청(axios 함수로 재조회 요청)
          return EventDataService.getFiles(
            this.searchTitle,
            this.page - 1,
            this.pageSize
          );
        })
        // 조회가 성공하면 실행되는 then()
        .then((response2) => {
          const { event, totalItems } = response2.data;
          this.event = event;
          this.count = totalItems;
          console.log(response2.data);
        })
        .catch((e) => {
          console.log(e);
          this.message = "Could not upload the image!" + e;
          this.imgThumb = undefined;
        });
    },
    // select box 값 변경시 실행되는 함수(재조회)
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; // 한페이지당 개수 저장(3, 6, 9)
      this.page = 1;
      // 재조회 함수 호출
      this.retrieveEvent();
    },

    deleteImage(gid) {
      EventDataService.delete(gid)
        .then((response) => {
          console.log(response);
          this.message = "정상적으로 삭제되었습니다.";

          // 삭제 후 재조회
          this.retrieveEvent();
        })
        .catch((e) => {
          console.log(e);
          this.message = " 삭제 시 에러가 발생했습니다." + e.message;
        });
    },
  },
  // 화면 뜨자 마자 실행되는 이벤트
  mounted() {
    this.retrieveEvent();
  },
};
</script>

<style>
.preview {
  max-width: 200px;
}
</style>
