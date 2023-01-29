<template>
  <div>
    <!-- 영화 시간 선택화면 시작-->
    <div v-if="goSelectMoive">
      <div id="contents" class="contents_full contents_reserve">
        <div class="wrap_reserve">
          <h2 class="hidden">예매하기</h2>

          <!-- 좌측 세로 메뉴 시작 -->
          <div class="section_step_tit">
            <ul>
              <li class="active step01">
                <a disabled
                  ><strong class="tit"><span>01</span><br />상영시간</strong>
                </a>
              </li>
              <li class="disabled">
                <a style="cursor: default"
                  ><strong class="tit"
                    ><span>02</span><br />인원/좌석</strong
                  ></a
                >
              </li>
              <li class="disabled">
                <a style="cursor: default"
                  ><strong class="tit"><span>03</span><br />결제</strong></a
                >
              </li>
              <li class="disabled">
                <a style="cursor: default"
                  ><strong class="tit"><span>04</span><br />결제완료</strong></a
                >
              </li>
            </ul>
          </div>
          <!-- 좌측 세로 메뉴 끝 -->

          <div id="reserveStep01" class="section_step_con step01 active">
            <h3 class="hidden">상영시간</h3>

            <!-- 1/3 부분 시작 -->
            <div class="article article_cinema area__movingbar litype2">
              <div class="group_top">
                <h4 class="tit">{{ selectTheater.cinemaname }}</h4>
              </div>
              <div class="inner">
                <ul class="tab_wrap outer actionmovingbar">
                  <li class="active">
                    <span
                      class="tab_tit"
                      style="width: 50%; left: 0%; text-align: center"
                    >
                      전체
                    </span>
                    <div class="tab_con">
                      <h5 class="hidden">전체</h5>
                      <div
                        class="cinema_select_wrap cinemaSelect basicCinema"
                        style="overflow: auto"
                      >
                        <ul>
                          <div v-for="(data, num) in theater" :key="num">
                            <li
                              class="depth1"
                              :class="{
                                active:
                                  selectTheater.cinemaname == data.cinemaname,
                              }"
                            >
                              <a @click="getTheater(data)">{{
                                data.cinemaname
                              }}</a>
                            </li>
                          </div>
                        </ul>
                      </div>
                    </div>
                  </li>
                  <li class="wrap_nav_underline">
                    <span class="nav_underline"></span>
                  </li>
                </ul>
              </div>
            </div>
            <!-- 1/3 부분 끝 -->

            <!-- 2/3 부분 시작 -->
            <div class="article article_movie">
              <div class="group_top">
                <h4 class="tit movie_name">{{ themaOfMovie.mtitle }}</h4>
              </div>
              <div class="inner">
                <!-- 영화 정렬 순서 시작 -->
                <div class="list_filter">
                  <!-- <select title="영화 정렬 방법 선택">
                    <option value="A">예매순</option>
                    <option value="B">관객순</option>
                    <option value="C">평점순</option>
                    <option value="D">예정작</option>
                  </select> -->
                  <div class="bx_btn_view"></div>
                </div>
                <!-- 영화 정렬 순서 끝 -->

                <!-- 영화 목록 보여주기 시작 -->
                <div class="movie_select_wrap list movieSelect">
                  <div
                    data-mcs-theme="minimal-dark"
                    class="mCustomScrollbar movieScroll _mCS_9 mCS-autoHide"
                    style="position: relative; overflow: visible"
                  >
                    <div
                      id="mCSB_9"
                      tabindex="0"
                      class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside"
                      style="max-height: none; overflow: auto"
                    >
                      <div
                        id="mCSB_9_container"
                        dir="ltr"
                        class="mCSB_container"
                        style="position: relative; top: 0px; left: 0px"
                      >
                        <ul>
                          <!-- 선택한 영화 제목 상단에 보여주기 -->
                          <li
                            :class="{
                              active: themaOfMovie.mtitle == data.mtitle,
                            }"
                            v-for="(data, index) in ScreeningList"
                            :key="index"
                            @click="getMovie(data)"
                          >
                            <a>
                              <div class="group_infor">
                                <div class="bx_tit">
                                  <span
                                    :class="{
                                      'ic_grade gr_12': data.mage == '12',
                                      'ic_grade gr_15': data.mage == '15',
                                      'ic_grade gr_18': data.mage == '18',
                                      'ic_grade gr_all': data.mage == 'all',
                                    }"
                                  ></span>

                                  <strong class="tit">{{ data.mtitle }}</strong>
                                </div>
                                <dl>
                                  <dd><strong class="expired"></strong></dd>
                                </dl>
                              </div>
                            </a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- 영화 목록 보여주기 끝 -->
              </div>
            </div>
            <!-- 2/3 부분 끝 -->

            <!-- 3/3 부분 시작  -->
            <div class="article article_time area__movingbar litype6">
              <div class="group_top">
                <!-- <h4 class="tit" v-if="selectedDate==''">
                  {{ yy }} - {{ mm }} - {{ dd }}
                </h4> -->
                <h4 class="tit" v-if="selectedDate">
                  {{ yy }} - {{ mm }} - {{ selectedDate }}
                </h4>
              </div>
              <div class="inner">
                <!-- 날짜 선택 시작 -->
                <div class="date_select_wrap dateReserveWrap">
                  <div class="slide_wrap slide_reserve_date">
                    <ul class="owl-carousel owl-loaded owl-drag">
                      <!-- 날짜 표시 시작 -->
                      <div class="owl-stage-outer">
                        <div class="owl-carousel owl-stage">
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd))"
                          >
                            <li class="item">
                              <strong class="month">{{ mm }}월</strong
                              ><span
                                class="date"
                                :class="{
                                  textStyleBlue: getDayOfWeek(nowDate) == '토',
                                  textStyleRed: getDayOfWeek(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                    data-displayyn="Y"
                                    :data-playdate="nowDate"
                                    data-isplaydate="Y"
                                    data-playweek="오늘"
                                    checked="checked"
                                    @click="getDayOfWeek(nowDate)"
                                  /><strong>{{ parseInt(dd) }}</strong
                                  ><em>오늘</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 1)"
                          >
                            <li class="item">
                              <span
                                class="date"
                                :class="{
                                  textStyleBlue: getDayOfWeek1(nowDate) == '토',
                                  textStyleRed: getDayOfWeek1(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 1 }}</strong
                                  ><em>{{ getDayOfWeek1(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 2)"
                          >
                            <li class="item">
                              <span
                                class="date"
                                :class="{
                                  textStyleBlue: getDayOfWeek2(nowDate) == '토',
                                  textStyleRed: getDayOfWeek2(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 2 }}</strong
                                  ><em>{{ getDayOfWeek2(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 3)"
                          >
                            <li class="item">
                              <span
                                class="date"
                                :class="{
                                  textStyleBlue: getDayOfWeek3(nowDate) == '토',
                                  textStyleRed: getDayOfWeek3(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 3 }}</strong
                                  ><em>{{ getDayOfWeek3(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 4)"
                          >
                            <li class="item">
                              <span
                                class="date sat"
                                :class="{
                                  textStyleBlue: getDayOfWeek4(nowDate) == '토',
                                  textStyleRed: getDayOfWeek4(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 4 }}</strong
                                  ><em>{{ getDayOfWeek4(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 5)"
                          >
                            <li class="item">
                              <span
                                class="date sun"
                                :class="{
                                  textStyleBlue: getDayOfWeek5(nowDate) == '토',
                                  textStyleRed: getDayOfWeek5(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 5 }}</strong
                                  ><em>{{ getDayOfWeek5(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 6)"
                          >
                            <li class="item">
                              <span
                                class="date disabled"
                                :class="{
                                  textStyleBlue: getDayOfWeek6(nowDate) == '토',
                                  textStyleRed: getDayOfWeek6(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 6 }}</strong
                                  ><em>{{ getDayOfWeek6(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 52.5px"
                            @click="selectDate(parseInt(dd) + 7)"
                          >
                            <li class="item">
                              <span
                                class="date disabled"
                                :class="{
                                  textStyleRed: getDayOfWeek(nowDate) == '토',
                                  textStyleRed: getDayOfWeek(nowDate) == '일',
                                }"
                                ><label
                                  ><input
                                    type="radio"
                                    name="radioDate1"
                                  /><strong>{{ parseInt(dd) + 7 }}</strong
                                  ><em>{{ getDayOfWeek(nowDate) }}</em></label
                                ></span
                              >
                            </li>
                          </div>
                        </div>
                      </div>
                    </ul>
                  </div>
                </div>
                <!-- 날짜 선택 끝 -->

                <!-- 영화 상영관 리스트 시작 -->
                <ul class="tab_wrap outer sml actionmovingbar">
                  <li class="active">
                    <button
                      type="button"
                      class="tab_tit"
                      style="width: 16.6667%; left: 0%"
                    >
                      <span></span>
                    </button>
                    <div class="tab_con ty5">
                      <div
                        data-mcs-theme="minimal-dark"
                        class="timeScroll mCustomScrollbar _mCS_73 mCS-autoHide"
                        style="position: relative; overflow: auto"
                      >
                        <div>
                          <div
                            id="mCSB_73_container"
                            dir="ltr"
                            class="mCSB_container"
                            style="  position: relative; top: 0px; left: 0px;  overflow: auto; " >
                            <div class="group_time_select">
                              <div class="time_select_tit">
                                <span
                                  :class="{
                                    'ic_grade gr_12': themaOfMovie.mage == '12',
                                    'ic_grade gr_15': themaOfMovie.mage == '15',
                                    'ic_grade gr_18': themaOfMovie.mage == '18',
                                    'ic_grade gr_all':
                                      themaOfMovie.mage == 'all',
                                  }"
                                ></span>
                                <strong>{{ themaOfMovie.mtitle }}</strong>
                              </div>
                              <!-- 3D -->
                              <div
                                class="time_select_wrap timeSelect"
                                v-if="thName3D.length!=0"
                              >
                                <ul class="list_hall">
                                  <li>3D</li>
                                </ul>
                                <ul class="list_time">
                                  <li
                                    class="near"
                                    v-for="(cineTimedata, index) in thName3D"
                                    :key="index"  v-show="cineTimedata.thdate == selectDateTime"
                                  >
                                    <a 
                                      role="button"
                                      @click="selectMovieTime(cineTimedata)"
                                      :class="{
                                        'btn disabled':
                                          cineTimedata.leftSeat == 0,
                                      }"
                                    >
                                      <dl>
                                        <dt>상영시간</dt>
                                        <dd class="time">
                                          <strong>{{
                                            cineTimedata.thtime
                                          }}</strong>
                                          <div class="tooltip"></div>
                                        </dd>
                                        <dt>잔여석</dt>
                                        <dd
                                          class="seat"
                                          v-if="cineTimedata.leftSeat != 0"
                                        >
                                          <strong>{{
                                            cineTimedata.leftSeat
                                          }}</strong>
                                          /
                                          {{ cineTimedata.totalSeat }}
                                        </dd>
                                        <dd
                                          class="full"
                                          v-if="cineTimedata.leftSeat == 0"
                                        >
                                          <strong>매진</strong>
                                        </dd>
                                        <dt>상영관</dt>
                                        <dd class="hall">{{cineTimedata.thname}}</dd>
                                      </dl>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                              <!-- 4D수퍼4D -->
                              <div
                                class="time_select_wrap timeSelect"
                                v-if="thName4D3D.length!=0"
                              >
                                <ul class="list_hall">
                                  <li>Super 4D</li>
                                </ul>
                                <ul class="list_time">
                                  <li
                                    class="near"
                                    v-for="(cineTimedata, index) in thName4D3D"
                                    :key="index" v-show="cineTimedata.thdate == selectDateTime"
                                  >
                                    <a 
                                      role="button"
                                      @click="selectMovieTime(cineTimedata)"
                                      :class="{
                                        'btn disabled':
                                          cineTimedata.leftSeat == 0,
                                      }"
                                    >
                                      <dl>
                                        <dt>상영시간</dt>
                                        <dd class="time">
                                          <strong>{{
                                            cineTimedata.thtime
                                          }}</strong>
                                          <div class="tooltip"></div>
                                        </dd>
                                        <dt>잔여석</dt>
                                        <dd
                                          class="seat"
                                          v-if="cineTimedata.leftSeat != 0"
                                        >
                                          <strong>{{
                                            cineTimedata.leftSeat
                                          }}</strong>
                                          /
                                          {{ cineTimedata.totalSeat }}
                                        </dd>
                                        <dd
                                          class="full"
                                          v-if="cineTimedata.leftSeat == 0"
                                        >
                                          <strong>매진</strong>
                                        </dd>
                                        <dt>상영관</dt>
                                        <dd class="hall">{{cineTimedata.thname}}</dd>
                                      </dl>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                              <!-- 2D -->
                              <div
                                class="time_select_wrap timeSelect"
                                v-if="thName2D.length!=0"
                              >
                                <ul class="list_hall">
                                  <li>2D</li>
                                </ul>
                                <ul class="list_time">
                                  <li
                                    class="near"
                                    v-for="(cineTimedata, index) in thName2D"
                                    :key="index" v-show="cineTimedata.thdate == selectDateTime"
                                  >
                                    <a 
                                      role="button"
                                      @click="selectMovieTime(cineTimedata)"
                                      :class="{
                                        'btn disabled':
                                          cineTimedata.leftSeat == 0,
                                      }"
                                    >
                                      <dl>
                                        <dt>상영시간</dt>
                                        <dd class="time">
                                          <strong>{{
                                            cineTimedata.thtime
                                          }}</strong>
                                          <div class="tooltip"></div>
                                        </dd>
                                        <dt>잔여석</dt>
                                        <dd
                                          class="seat"
                                          v-if="cineTimedata.leftSeat != 0"
                                        >
                                          <strong>{{
                                            cineTimedata.leftSeat
                                          }}</strong>
                                          /
                                          {{ cineTimedata.totalSeat }}
                                        </dd>
                                        <dd
                                          class="full"
                                          v-if="cineTimedata.leftSeat == 0"
                                        >
                                          <strong>매진</strong>
                                        </dd>
                                        <dt>상영관</dt>
                                        <dd class="hall">{{cineTimedata.thname}}</dd>
                                      </dl>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                              <!-- imax -->
                              <div
                                class="time_select_wrap timeSelect"
                                v-if="thName2D4K.length!=0"
                              >
                                <ul class="list_hall">
                                  <li>iMAX</li>
                                </ul>
                                <ul class="list_time">
                                  <li
                                    class="near"
                                    v-for="(cineTimedata, index) in thName2D4K"
                                    :key="index" v-show="cineTimedata.thdate == selectDateTime"
                                  >
                                    <a
                                      role="button"
                                      @click="selectMovieTime(cineTimedata)"
                                      :class="{
                                        'btn disabled':
                                          cineTimedata.leftSeat == 0,
                                      }"
                                    >
                                      <dl>
                                        <dt>상영시간</dt>
                                        <dd class="time">
                                          <strong>{{
                                            cineTimedata.thtime
                                          }}</strong>
                                          <div class="tooltip"></div>
                                        </dd>
                                        <dt>잔여석</dt>
                                        <dd
                                          class="seat"
                                          v-if="cineTimedata.leftSeat != 0"
                                        >
                                          <strong>{{
                                            cineTimedata.leftSeat
                                          }}</strong>
                                          /
                                          {{ cineTimedata.totalSeat }}
                                        </dd>
                                        <dd
                                          class="full"
                                          v-if="cineTimedata.leftSeat == 0"
                                        >
                                          <strong>매진</strong>
                                        </dd>
                                        <dt>상영관</dt>
                                        <dd class="hall">{{cineTimedata.thname}}</dd>
                                      </dl>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <span
                      type="button"
                      class="tab_tit"
                      style="width: 83%; left: 16.6667%"
                    ></span>
                  </li>
                  <li class="wrap_nav_underline">
                    <span class="nav_underline"></span>
                  </li>
                </ul>
                <!-- 영화 상영관 리스트 끝 -->
              </div>
            </div>
            <!-- 3/3 부분 끝 -->
          </div>
        </div>
      </div>
    </div>
    <!-- 영화 시간 선택화면 끝  -->

    <!-- 좌석 미니맵 시작 -->
    <div class="dim" v-if="seatPopup">
      <div
        id="layerReserveStep01"
        class="layer_wrap layer_reserve layer_reserve01_sum2 active"
        tabindex="0"
        style="left: 50%; top: 50%; margin-top: -256.5px; margin-left: -250px"
      >
        <div class="layer_header">
          <h4 class="hidden">예매 내용 확인 팝업</h4>
          <div id="stepOnePopupHeaderScreen">
            <strong
              >{{ selectedMovieTime.thtime }}~{{ cineEndTime }}({{selectedThname}})</strong
            >
          </div>
          <button type="button" class="btn_close" @click="closeScreenLayer">
            팝업 닫기
          </button>
        </div>
        <div class="layer_contents">
          <div class="seat_infor_sum">
            <div class="group_top" id="stepOnePopupContentsSeatCount">
              <dl>
                <dt>잔여좌석</dt>
                <dd>
                  <strong>{{ selectedMovieTime.leftSeat }}</strong
                  >/{{ selectedMovieTime.totalSeat }}
                </dd>
              </dl>
            </div>
            <div class="group_con screen_preview_box">
              <div class="screen_preview_con">
                <div class="screen_info cineCont">
                  <div class="smallScreen">
                    <span class="title_screen1">SCREEN</span>

                    <!-- 미니맵 시작-->
                    <div class="minimap" id="stepOnePopupMiniMap">
                      <div class="floor_bx seatSet1">
                        <span class="floor_tit" style="display: none">10F</span>
                        <div
                          :class="{seat_area:true, miniseat_locationA:aSeatLine.length<30 , miniseat_locationB:aSeatLine.length>30}"
                          style=" height: 58.7295px"
                        >
                          <a
                            :style="`left: ${index * 7.4}px; top: 0px`"
                            v-for="(data, index) in aSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f1: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 0px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 4.97541px`"
                            v-for="(data, index) in bSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>

                          <!-- style="left: 28.5957px; top: 4.97541px -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 9.95082px`"
                            v-for="(data, index) in cSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 9.95082px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 14.9262px`"
                            v-for="(data, index) in dSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 14.9262px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 19.9016px`"
                            v-for="(data, index) in eSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 19.9016px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 24.877px`"
                            v-for="(data, index) in fSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>

                          <!-- style="left: 28.5957px; top: 24.877px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 29.8525px`"
                            v-for="(data, index) in gSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 29.8525px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 34.8279px`"
                            v-for="(data, index) in hSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 28.5957px; top: 34.8279px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 39.8033px`"
                            v-for="(data, index) in iSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 0px; top: 39.8033px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 44.7787px`"
                            v-for="(data, index) in jSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 0px; top: 44.7787px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 49.7541px`"
                            v-for="(data, index) in kSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>
                          <!-- style="left: 0px; top: 49.7541px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 54.7295px`"
                            v-for="(data, index) in lSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>

                          <!-- style="left: 0px; top: 54.7295px" -->

                          <a
                            :style="`left: ${index * 7.4}px; top: 59.7049px`"
                            v-for="(data, index) in mSeatLine"
                            :key="data.index"
                            class="sel p0"
                          >
                            <span
                              :class="{
                                f2: true,
                                completed: data == 'X',
                                noSeat: data == 'A',
                              }"
                            ></span>
                          </a>

                          <!-- style="left: 0px; top: 59.7049px" -->
                        </div>
                      </div>
                    </div>
                    <!-- 미니맵 끝-->
                  </div>
                </div>
              </div>
            </div>

            <!-- 선택한 영화의 적정 관람가 안내 시작 , mage 가 12,15,18,all  임 -->
            <div class="group_btm" id="stepOnePopupContentsMsg">
              <div class="txt_notice gr_all" v-if="selectedMovieTime.mage == 'all'" >
                <p class="tit">
                  <span class="ic_grade gr_all">전체 관람가</span>본 영화는
                  <strong>전체관람가</strong> 영화입니다.
                </p>
              </div>
              <div class="txt_notice gr_12" v-if="selectedMovieTime.mage == '12'">
                <p class="tit">
                  <span class="ic_grade gr_12">만 12세 이상 관람가</span>본
                  영화는 <strong>만 12세 이상 관람가</strong> 영화입니다.
                </p>
                <p class="txt">
                  만 12세 미만의 고객님(영, 유아 포함)은 반드시 부모님 또는 성인
                  보호자의 동반하에<br />관람이 가능합니다. 연령 확인 불가 시
                  입장이 제한될 수 있습니다.
                </p>
              </div>
              <div class="txt_notice gr_15" v-if="selectedMovieTime.mage == '15'">
                <p class="tit">
                  <span class="ic_grade gr_15">만 15세 이상 관람가</span>본
                  영화는 <strong>만 15세 이상 관람가</strong> 영화입니다.
                </p>
                <p class="txt">
                  만 15세 미만의 고객님(영, 유아 포함)은 반드시 부모님 또는 성인
                  보호자의 동반하에<br />관람이 가능합니다. 연령 확인 불가 시
                  입장이 제한될 수 있습니다.
                </p>
              </div>
              <div class="txt_notice gr_18" v-if="selectedMovieTime.mage == '18'">
                <p class="tit">
                  <span class="ic_grade gr_18">만 18세 이상 관람가</span>본
                  영화는 <strong>만 18세 이상 관람가</strong> 영화입니다.
                </p>
                <p class="txt">연령 확인 불가 시 입장이 제한될 수 있습니다.</p>
              </div>
            </div>
            <!-- 선택한 영화의 적정 관람가 안내 끝 -->
          </div>
          <div class="btn_btm_wrap" id="stepOnePopupContentsButton">
            <a class="btn_col2 ty5" @click="closeScreenLayer">취소</a>
            <a class="btn_col1 ty5" @click="goSeatSelectLayer"
              >인원/좌석 선택</a
            >
          </div>
        </div>
      </div>
    </div>
    <!-- 좌석 미니맵 끝 -->

    <!-- 로그인 필요 팝업 시작 -->
    <div :class="{'dim200':loginPopup==true}"></div>
    <div id="layerCommonAlert" class="layer_wrap layer_alert layer_wrap200 active" v-if="loginPopup"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2"> 로그인이 필요한 서비스 입니다. </p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button type="button" class="btn_close btnCloseLayer" @click="loginPopup=false"> <router-link to="/login">확인</router-link> </button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
    <!-- 로그인 필요 팝업 끝 -->

    <!-- 좌석 선택 큰화면 시작-->
    <div v-if="seatReserVation">
      <div id="contents" class="contents_full contents_reserve">
        <div class="wrap_reserve">
          <h2 class="hidden">예매하기</h2>
          <div id="PersonSeatHeader" class="section_step_tit">
            <ul>
              <li class="step01 prev">
                <a href="#reserveStep01" @click="goTimeSelect"
                  ><strong class="tit"><span>01</span><br />상영시간</strong>
                </a>
              </li>
              <li class="step02 active">
                <a href="#reserveStep02" disabled
                  ><strong class="tit"><span>02</span><br />인원/좌석</strong>
                  <div class="bx_con">
                    <dl>
                      <dt>선택한 인원</dt>
                      <dd><span id="preview_person_info"></span></dd>
                      <dt>선택한 좌석</dt>
                      <dd class="seat_list">
                        <span id="preview_seat_info"></span>
                      </dd>
                    </dl>
                  </div>
                </a>
              </li>
              <li class="step03">
                <a style="cursor: default"
                  ><strong class="tit"><span>03</span><br />결제</strong>
                </a>
              </li>
              <li>
                <a style="cursor: default"
                  ><strong class="tit"><span>04</span><br />결제완료</strong></a
                >
              </li>
            </ul>
          </div>

          <div id="reserveStep01" class="section_step_con step01">
            <h3 class="hidden">상영시간</h3>
          </div>

          <div id="reserveStep02" class="section_step_con step02 active">
            <h3 class="hidden">인원/좌석</h3>
            <div class="article article_seat">
              <div class="group_top">
                <h4 class="tit">인원/좌석 선택</h4>
                <p class="txt" id="txtPeopleMaxcount">
                  인원은 최대 8명까지 선택 가능합니다.
                </p>
              </div>
              <div class="inner">
                <div id="PersonSeatCount">
                  <div class="select_num_people_wrap">
                    <h5 class="hidden">인원선택</h5>
                    <div class="movie_infor">
                      <h6 class="hidden">예매 정보</h6>
                      <span class="thm">
                        <!-- TODO: 선택한 영화 포스터 보여주기 -->
                        <img
                          :src="posterUrl"
                        />
                      </span>
                      <div class="group_infor">
                        <div class="bx_tit">
                          <span
                            :class="{
                              'ic_grade gr_12': themaOfMovie.mage == '12',
                              'ic_grade gr_15': themaOfMovie.mage == '15',
                              'ic_grade gr_18': themaOfMovie.mage == '18',
                              'ic_grade gr_all': themaOfMovie.mage == 'all',
                            }"
                            >관람가</span
                          ><strong>{{ seatData.mtitle }}</strong>
                        </div>
                        <dl>
                          <dt>일시</dt>
                          <dd class="sub_info1">
                            {{ seatData.thdate }}
                            <span class="time"
                              >{{ seatData.thtime }} ~
                              {{ cineEndTime }}</span
                            >
                          </dd>
                          <dt>영화관</dt>
                          <dd class="sub_info1">
                            {{ selectTheater.cinemaname }} · {{selectedThname}}
                          </dd>
                        </dl>
                      </div>
                    </div>

                    <!-- 인원 선택 및 계산 시작 -->
                    <div class="count_people">
                      <h6 class="hidden">인원선택</h6>
                      <ul>
                        <li
                          id="person_10"
                          data-code="10"
                          data-peple="성인"
                          data-count="0"
                        >
                          <strong class="tit">성인</strong>
                          <span class="bx_num">
                            <button
                              class="btn_mins"
                              id="Minus|10"
                              @click="adultMinus"
                              :class="{ diabled: numberOfAdult == 0 }"
                            >
                              <img
                                src="../../assets/images/icon/minus_12.png"
                              />
                              감소
                            </button>
                            <div class="txt_num">{{ numberOfAdult }}</div>
                            <button
                              class="btn_plus"
                              id="Plus|10"
                              @click="adultPlus"
                            >
                              증가
                            </button>
                          </span>
                        </li>
                        <li
                          id="person_20"
                          data-code="20"
                          data-peple="청소년"
                          data-count="0"
                        >
                          <strong class="tit">청소년</strong>
                          <span class="bx_num">
                            <button
                              class="btn_mins"
                              id="Minus|20"
                              @click="teenagerMinus"
                              :class="{ diabled: numberOfTeenager == 0 }"
                            >
                              <img
                                src="../../assets/images/icon/minus_12.png"
                              />감소
                            </button>
                            <div class="txt_num">{{ numberOfTeenager }}</div>
                            <button
                              class="btn_plus"
                              id="Plus|20"
                              @click="teenagerPlus"
                            >
                              증가
                            </button>
                          </span>
                        </li>
                        <li
                          id="person_12"
                          data-code="12"
                          data-peple="시니어"
                          data-count="0"
                        >
                          <strong class="tit">시니어</strong>
                          <span class="bx_num">
                            <button
                              class="btn_mins"
                              id="Minus|12"
                              @click="seniorMinus"
                              :class="{ diabled: numberOfSenior == 0 }"
                            >
                              <img
                                src="../../assets/images/icon/minus_12.png"
                              />감소
                            </button>
                            <div class="txt_num">{{ numberOfSenior }}</div>
                            <button
                              class="btn_plus"
                              id="Plus|12"
                              @click="seniorPlus"
                            >
                              증가
                            </button>
                          </span>
                        </li>

                        <button
                          type="button"
                          class="btn_txt_resetA"
                          @click="resetNumberSelect"
                        >
                         인원수 초기화
                        </button>
                      </ul>
                    </div>
                    <!-- 인원 선택 및 계산 끝 -->
                  </div>
                </div>

                <div class="select_seat_wrap">
                  <h5 class="hidden">좌석선택</h5>
                  <div class="top_txt_info">
                    <p id="ticketMessageInfo">인원을 선택하세요.</p>
                  </div>
                  <div id="PersonSeatSelect"></div>

                  <div id="container" class="seat_wrap">
                    <article class="mseat_wrap">
                      <div class="mseat_inner">
                        <div
                          class="mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
                          data-mcs-theme="minimal-dark"
                          style="position: relative; overflow: visible"
                        >
                          <div
                            id="mCSB_1"
                            class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside"
                            style="max-height: none"
                            tabindex="0"
                          >
                            <div
                              id="mCSB_1_container"
                              class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
                              style="position: relative; top: 0; left: 0"
                              dir="ltr"
                            >
                              <div class="meat_door_pos" id="scrollWrap">
                                <div class="mseat_hidden">
                                  <span class="title_screen1">SCREEN</span>

                                  <!-- 좌석 창 표시 시작 -->
                                  <div class="showMap">
                                    <div class="floor_bx seatSet1">
                                      <span
                                        class="floor_tit"
                                        style="display: none"
                                        >10F
                                      </span>

                                      <!-- 좌석 선택 시작 -->
                                      <div
                                        :class="{seat_area:true, seat_locationA:aSeatLine.length<30 , seat_locationB:aSeatLine.length>30}"
                                        style="  margin-top: 30px; height: 298.208px;  "
                                      >
                                      <!-- <div
                                        class="seat_area"
                                        style="  margin-top: 30px;
                                          width: 581px; height: 298.208px;  " > -->
                                        <!-- 좌석 A 라인 -->
                                        <span
                                          class="seat_tit"
                                          style="left: -30px; top: 0px"
                                        >
                                          A
                                        </span>
                                        <a
                                          href="javascript:void(0);"
                                          v-for="(data, index) in aSeatLine"
                                          :key="data.index"
                                          :class="{
                                            sel: true,
                                            'completed diabled':
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnA.includes(index),
                                          }"
                                          @click="selectedSeatA(data, index)"
                                          :alt="`좌석 번호:A${
                                            index
                                          } 일반석`"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 0px`"
                                        >
                                          <span
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            class="foot_seat f1"
                                            style="top: 0px"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 B 라인 -->
                                        <span
                                          class="seat_tit"
                                          style="  left: -30px; top: 25.291666666666668px;  "
                                          >B</span
                                        >

                                        <!-- :style="`left: ${index*25+117}px; top: 25.2917px`"  -->
                                        <a
                                          href="javascript:void(0);"
                                          v-for="(data, index) in bSeatLine"
                                          :key="data.index"
                                          :class="{
                                            sel: true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnB.includes(index),
                                          }"
                                          @click="selectedSeatB(data, index)"
                                          :alt="`좌석 번호:B${
                                            index
                                          } 일반석`"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 25.2917px`"
                                          ><span
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            class="f1"
                                            style="top: 0px"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 C 라인  -->
                                        <!-- :style="`left: ${index*25+117}px; top: 50.5833px`"  -->

                                        <span
                                          class="seat_tit"
                                          style=" left: -30px;
                                            top: 50.583333333333336px; "
                                          >C</span
                                        >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:C${
                                            index
                                          } 일반석`"
                                          :class="{
                                            sel: true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnC.includes(index),
                                          }"
                                          @click="selectedSeatC(data, index)"
                                          v-for="(data, index) in cSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 50.5833px`"
                                          ><span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 D 라인 -->
                                        <!-- :style="`left: ${index*25+117}px; top: 75.875px`" -->

                                        <span
                                          class="seat_tit"
                                          style="left: -30px; top: 75.875px"
                                          >D</span
                                        ><a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:D${
                                            index 
                                          } 일반석 SWEET SPOT`"
                                          :class="{
                                            'sel sweet_spot': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnD.includes(index),
                                          }"
                                          @click="selectedSeatD(data, index)"
                                          v-for="(data, index) in dSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 75.875px`"
                                          ><span
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            class="sweet_spot f1"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 E 라인 -->
                                        <!-- :style="`left: ${index*25+117}px; top: 101.167px`" -->

                                        <span
                                          class="seat_tit"
                                          style=" left: -30px;  top: 101.16666666666667px;  " >E</span
                                        >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:E${
                                            index
                                          } 일반석 SWEET SPOT`"
                                          :class="{
                                            'sel sweet_spot': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnE.includes(index),
                                          }"
                                          @click="selectedSeatE(data, index)"
                                          v-for="(data, index) in eSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 101.167px`"
                                          ><span
                                            class="sweet_spot f1"
                                            style="top: 0px"
                                            :class="{  onSeat: data == 'O',  noSeat: data == 'X',
                                              blankSeat: data == 'A',  }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 F 라인 -->
                                        <!-- :style="`left: ${index*25+117}px; top: 126.458px`" -->

                                        <span
                                          class="seat_tit"
                                          style="
                                            left: -30px;
                                            top: 126.45833333333333px;
                                          "
                                          >F</span
                                        ><a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:F${
                                            index 
                                          } 일반석`"
                                          :class="{
                                            'sel sweet_spot': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnF.includes(index),
                                          }"
                                          @click="selectedSeatF(data, index)"
                                          v-for="(data, index) in fSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 126.458px`"
                                          ><span
                                            class="sweet_spot f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 G 라인 -->
                                        <!-- :style="`left: ${index*25+117}px; top: 151.75px`" -->

                                        <span
                                          class="seat_tit"
                                          style="left: -30px; top: 151.75px"
                                          >G</span
                                        ><a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:G${
                                            index 
                                          } 일반석`"
                                          :class="{
                                            'sel sweet_spot': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnG.includes(index),
                                          }"
                                          @click="selectedSeatG(data, index)"
                                          v-for="(data, index) in gSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 151.75px`"
                                          ><span
                                            class="sweet_spot f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 H 라인 -->
                                        <!-- :style="`left: ${index*25+117}px; top: 177.042px`" -->

                                        <span
                                          class="seat_tit"
                                          style="
                                            left: -30px;
                                            top: 177.04166666666666px;
                                          "
                                          >H</span
                                        ><a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:H${
                                            index
                                          } 일반석`"
                                          :class="{
                                            'sel sweet_spot': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnH.includes(index),
                                          }"
                                          @click="selectedSeatH(data, index)"
                                          v-for="(data, index) in hSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top: 177.042px`"
                                          ><span
                                            class="sweet_spot f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 I 라인 -->
                                        <!-- :style="`left: ${index*25+21}px; top:202.333px`" -->

                                        <span
                                          class="seat_tit"
                                          style="  left: -30px;
                                            top: 202.33333333333334px;
                                          "
                                          >I</span
                                        >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:I${
                                            index
                                          } 일반석`"
                                          :class="{
                                            'sel p0': true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnI.includes(index),
                                          }"
                                          @click="selectedSeatI(data, index)"
                                          v-for="(data, index) in iSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top:202.333px`"
                                          ><span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 J 라인 -->
                                        <!-- :style="`left: ${index*25+21}px; top:227.625px`"  -->
                                        <span
                                          class="seat_tit"
                                          style="left: -30px; top: 227.625px"
                                          >J</span
                                        >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:J${
                                            index
                                          } 일반석`"
                                          :class="{
                                            sel: true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnJ.includes(index),
                                          }"
                                          @click="selectedSeatJ(data, index)"
                                          v-for="(data, index) in jSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top:227.625px`"
                                        >
                                          <span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 K 라인 -->
                                        <!-- :style="`left: ${index*25+21}px; top:252.917px`" -->

                                        <span
                                          class="seat_tit"
                                          style="
                                            left: -30px;
                                            top: 252.91666666666666px;
                                          "
                                          >K</span
                                        >
                                        <a href="javascript:void(0);"
                                          :alt="`좌석 번호:K${
                                            index
                                          } 일반석`"
                                          :class="{
                                            sel: true,
                                            completed:
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnK.includes(index),
                                          }"
                                          @click="selectedSeatK(data, index)"
                                          v-for="(data, index) in kSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top:252.917px`"
                                          ><span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 L 라인 -->
                                        <!-- :style="`left: ${index*25+21}px; top:278.208px`" -->

                                        <span
                                          class="seat_tit"
                                          style=" left: -30px; top: 278.2083333333333px; " >L</span >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:L${
                                            index
                                          } 일반석`"
                                          :class="{
                                            sel: true,
                                            'completed  disabled':
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnL.includes(index),
                                          }"
                                          @click="selectedSeatL(data, index)"
                                          v-for="(data, index) in lSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top:278.208px`"
                                          ><span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >

                                        <!-- 좌석 M 라인 -->
                                        <!-- :style="`left: ${index*25+21}px; top:278.208px`" -->

                                        <span class="seat_tit"
                                          style="  left: -30px; top: 303.499px; " >M</span  >
                                        <a
                                          href="javascript:void(0);"
                                          :alt="`좌석 번호:M${
                                            index
                                          } 일반석`"
                                          :class="{
                                            sel: true,
                                            'completed  disabled':
                                              (data == 'A') | (data == 'X'),
                                            on: selectSeatOnM.includes(index),
                                          }"
                                          @click="selectedSeatM(data, index)"
                                          v-for="(data, index) in mSeatLine"
                                          :key="data.index"
                                          :style="`left: ${
                                            index * 25
                                          }px; top:303.499px`"
                                          ><span
                                            class="f1"
                                            style="top: 0px"
                                            :class="{
                                              onSeat: data == 'O',
                                              noSeat: data == 'X',
                                              blankSeat: data == 'A',
                                            }"
                                            >{{ index }}</span
                                          ></a
                                        >



                                      </div>
                                      <!-- 좌석 선택 끝 -->

                                      <!-- 상영관 출입문 표시 시작 -->
                                      <span data-y="54px"  data-x="42px"
                                        :class="{w_center:true, door_locationA:aSeatLine.length<30 , door_locationB:aSeatLine.length>30}"
                                        style="top: 54px; " >상영관 출입문 입니다.
                                      </span>

                                      <!-- <span data-y="-41px" data-x="20px" 
                                        class="w_right" style="top: -25px; left: 369.5px;">
                                        상영관 출입문 입니다.</span> -->

                                      <!-- 상영관 출입문 표시 끝 -->
                                    </div>
                                  </div>
                                  <!-- 좌석창 표시 끝 -->
                                </div>
                              </div>
                            </div>
                          </div>
                          <div
                            id="mCSB_1_scrollbar_vertical"
                            class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical"
                            style="display: none"
                          >
                            <div class="mCSB_draggerContainer">
                              <div
                                id="mCSB_1_dragger_vertical"
                                class="mCSB_dragger"
                                style="  position: absolute;  min-height: 50px;
                                  height: 0px;  top: 0px; " >
                                <div
                                  class="mCSB_dragger_bar"
                                  style="line-height: 50px"
                                ></div>
                              </div>
                              <div class="mCSB_draggerRail"></div>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="seat_btm_box">
                        <div class="seat_type_box">
                          <div class="top_info">
                            <span class="seat_type1">선택좌석</span>
                            <span class="seat_type2">선택가능</span>
                            <span class="seat_type3">예매완료</span>
                          </div>
                          <div class="btm_info">
                            <span class="seat_type11">출입문</span>
                          </div>
                        </div>

                        <div class="notice_box" style="display: none">
                          <h6 class="tit_info">이용안내</h6>
                          <ul class="list_txt ty3">
                            <li>
                              12세 미만의 고객님(영,유아 포함)은 부모님 또는
                              보호자를 동반하여도 관람이 불가합니다.
                            </li>
                            <li>
                              영화 관람 시, 신분증을 지참하여 주시기 바랍니다.
                            </li>
                          </ul>
                        </div>
                      </div>
                    </article>
                  </div>
                </div>

                <div id="PersonSeatSummery">
                  <div class="select_seat_result">
                    <div class="group_lft">
                      <dl class="total_price">
                        <dt>총 합계</dt>
                        <dd>
                          <strong>{{ printTotalPrice }}</strong
                          >원
                        </dd>
                      </dl>
                    </div>
                    <div class="group_rgt">
                      <a
                        href="#"
                        class="btn_col1"
                        id="link_rpay"
                        @click="goPayment"
                        >결제하기</a
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div id="reserveStep03" class="section_step_con step03">
            <h3 class="hidden">결제</h3>
          </div>

          <div id="reserveStep04" class="section_step_con step04">
            <h3 class="hidden">결제완료</h3>
          </div>
        </div>
      </div>
    </div>
    <!-- 좌석 선택 큰화면 끝-->

    <!-- 좌석 / 인원 오류시 팝업 시작 -->
    <div :class="{'dim200':seatAlert==true}"></div>
    <div id="layerCommonAlert" class="layer_wrap layer_alert layer_wrap200 active" v-if="seatAlert"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2" v-if="numberOfTotal!=0">좌석을 다시 선택해 주십시요.</p>
          <p class="txt_para ty2" v-if="numberOfTotal==0">인원을 선택해 주십시요.</p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button type="button" class="btn_close btnCloseLayer" @click="seatAlert=false">확인</button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
     <!-- 좌석 / 인원 오류시 팝업 끝 -->

    <!-- 결제 화면 시작 -->
    <div v-if="selectPayment">
      <div id="contents" class="contents_full contents_reserve">
        <div class="wrap_reserve">
          <h2 class="hidden">예매하기</h2>
          <div class="section_step_title">
            <ul>
              <li class="step01 prev">
                <a href="#" @click="goTimeSelect"
                  ><strong class="tit"><span>01</span><br />상영시간</strong>
                </a>
              </li>
              <li class="step02 prev">
                <a href="#" @click="goSeatSelectLayer"
                  ><strong class="tit"><span>02</span><br />인원/좌석</strong>
                </a>
              </li>
              <li class="step03 active">
                <a href="#"
                  ><strong class="tit"><span>03</span><br />결제</strong>
                </a>
              </li>
              <li>
                <a href="#" style="cursor: default"
                  ><strong class="tit"><span>04</span><br />결제완료</strong></a
                >
              </li>
            </ul>
          </div>
          <div id="reserveStep01" class="section_step_con step01">
            <h3 class="hidden">상영시간</h3>
          </div>
          <div id="reserveStep02" class="section_step_con step02">
            <h3 class="hidden">인원/좌석</h3>
          </div>
          <div id="reserveStep03" class="section_step_con step03 active">
            <h3 class="hidden">결제</h3>
            <div class="article article_sum_infor">
              <div class="group_top">
                <h4 class="tit">예매정보</h4>
              </div>
              <div class="inner">
                <div class="movie_infor new2020">
                  <span class="thm"
                    ><img
                      :src="posterUrl"
                      :alt="themaOfMovie.mtitle" /></span
                  ><strong class="tit"
                    ><span class="ic_grade gr_12"></span>&nbsp;{{
                      themaOfMovie.mtitle
                    }}(3D)</strong
                  >
                  <dl class="dlist_infor">
                    <dt>일시</dt>
                    <dd>
                      <strong
                        >{{ yy }}-{{ mm }}-{{ selectedDate }}
                        {{ selectedMovieTime.thtime }}~{{
                          cineEndTime
                        }}</strong
                      >
                    </dd>
                    <dt>영화관</dt>
                    <dd>{{ selectTheater.cinemaname }} {{selectedThname}}</dd>
                    <dt>인원</dt>
                    <dd>
                      <span v-if="numberOfAdult != 0">
                        성인{{ numberOfAdult }}
                      </span>
                      <span v-if="numberOfTeenager != 0">
                        청소년{{ numberOfTeenager }}
                      </span>
                      <span v-if="numberOfSenior != 0">
                        시니어{{ numberOfSenior }}
                      </span>
                    </dd>
                  </dl>
                </div>
                <div class="seat_infor">
                  <dl class="dlist_infor">
                    <dt>좌석</dt>
                    <dd>
                      <strong
                        v-for="(data, num) in selectedSeatNumber.sort()"
                        :key="num"
                      >
                        {{ data }}
                      </strong>
                    </dd>
                  </dl>
                </div>
              </div>
            </div>
            <div class="article article_pay_method">
              <div class="group_top">
                <h4 class="tit">결제수단</h4>
                <!-- <button type="button" class="btn_txt_reset" @click="resetPayment">결제수단 초기화</button> -->
              </div>
              <div class="inner">
                <div
                  class="mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
                  data-mcs-theme="minimal-dark"
                  style="position: relative; overflow: visible"
                >
                  <div
                    id="mCSB_1"
                    class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside"
                    style="max-height: none"
                    tabindex="0"
                  >
                    <div
                      id="mCSB_1_container"
                      class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
                      style="position: relative; top: 0; left: 0"
                      dir="ltr"
                    >
                      <div class="group_payment">
                        <h5 class="tit_payment">최종 결제수단</h5>
                        <div class="bx_cate">
                          <ul class="list_pay_item cate6">
                            <li>
                              <button
                                type="button"
                                :class="{ cate1: true, active: credit == true }"
                                @click="selectCredit"
                              >
                                신용카드
                              </button>
                            </li>
                            <li>
                              <button
                                type="button"
                                :class="{ cate2: true, active: Lpay == true }"
                                @click="selectLpay"
                              >
                                엘페이
                              </button>
                            </li>
                            <li>
                              <button
                                type="button"
                                :class="{
                                  cate3: true,
                                  active: simplePay == true,
                                }"
                                @click="selectSimplePay"
                              >
                                간편결제
                              </button>
                            </li>
                            <li>
                              <button
                                type="button"
                                :class="{
                                  'cate5 ty2': true,
                                  active: myWallet == true,
                                }"
                                @click="selectMyWallet"
                              >
                                내통장결제
                              </button>
                            </li>
                            <li>
                              <button
                                type="button"
                                :class="{
                                  'cate4 ty2': true,
                                  active: phone == true,
                                }"
                                @click="selectPhone"
                              >
                                휴대폰
                              </button>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    id="mCSB_1_scrollbar_vertical"
                    class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical"
                    style="display: none"
                  >
                    <div class="mCSB_draggerContainer">
                      <div
                        id="mCSB_1_dragger_vertical"
                        class="mCSB_dragger"
                        style="
                          position: absolute;
                          min-height: 50px;
                          height: 0px;
                          top: 0px;
                        "
                      >
                        <div
                          class="mCSB_dragger_bar"
                          style="line-height: 50px"
                        ></div>
                      </div>
                      <div class="mCSB_draggerRail"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="article article_payment">
              <div class="group_top">
                <h4 class="tit">결제하기</h4>
              </div>
              <div class="inner">
                <div class="payment_sum_wrap">
                  <dl>
                    <dt>상품금액</dt>
                    <dd>
                      <strong>{{ printTotalPrice }}</strong
                      >원
                    </dd>
                  </dl>
                  <dl>
                    <dt>할인금액</dt>
                    <dd>-<strong>0</strong>원</dd>
                  </dl>
                  <dl>
                    <dt>결제금액</dt>
                    <dd>
                      총<strong>{{ printTotalPrice }}</strong
                      >원
                    </dd>
                  </dl>
                  <a href="#none" class="btn_col1 btn_confirm" @click="donePay"
                    >결제하기</a
                  >
                </div>
              </div>
            </div>
          </div>
          <div id="reserveStep04" class="section_step_con step04">
            <h3 class="hidden">결제완료</h3>
          </div>
        </div>
      </div>
    </div>
    <!-- 결제 화면 끝 -->

    <!-- 결제 완료 팝업 시작 -->
    <div :class="{'dim200':paymentPopup==true}"></div>
    <div id="layerCommonAlert" class="layer_wrap layer_alert layer_wrap200 active" v-if="paymentPopup"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2">{{ selectedPayMethod }}</p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button type="button" class="btn_close btnCloseLayer" @click="closePaymentPopup"> <router-link to="/reservation-check">확인</router-link></button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
    <!-- 결제 완료 팝업 끝 -->

    <!-- 결제 선택 요청 팝업 시작 -->
    <div :class="{'dim200':paymentSelectPopup==true}"></div>
    <div id="layerCommonAlert" class="layer_wrap layer_alert layer_wrap200 active" v-if="paymentSelectPopup"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2"> 결제 수단을 선택해 주세요 </p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button type="button" class="btn_close btnCloseLayer" @click="paymentSelectPopup=false">확인</button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
    <!-- 결제 선택 팝업 끝 -->

    <!-- 결제전 다른사람의 좌석 선택으로 새로운 좌석 선택 요청 팝업 시작 -->
    <div :class="{'dim200':seatSelectAgainPopup==true}"></div>
    <div id="layerCommonAlert" class="layer_wrap layer_alert layer_wrap200 active" v-if="seatSelectAgainPopup"
      tabindex="0"
      style="left: 50%; top: 50%; margin-top: -96.5px; margin-left: -190px"
    >
      <strong class="hidden">레이어 팝업 시작</strong>
      <div class="layer_contents">
        <div class="bx_alert1">
          <p class="txt_para ty2"> 예약된 좌석입니다. 다시 선택해 주세요 </p>
        </div>
      </div>
      <div class="layer_footer ty2">
        <ul>
          <li>
            <button type="button" class="btn_close btnCloseLayer" @click="seatSelectAgain">확인</button>
          </li>
        </ul>
      </div>
      <strong class="hidden txtTabIndex" tabindex="0">레이어 팝업 끝</strong>
    </div>
    <!-- 결제전 다른사람의 좌석 선택으로 새로운 좌석 선택 요청 팝업 끝 -->
  </div>
</template>

<script>
import ReservationService from "@/services/ReservationService";

/* eslint-disable */
export default {
  mounted() {
    // 캐러셀 초기화 실행 , jquery 사용
    $(".owl-stage").owlCarousel({
      autoplay: false, // 자동으로 이미지가 돌아가게 할 것인지
      nav: true, // 메뉴
      items: 8, // 초기화면에 표시할 이미지 개수
      loop: false, // 마지막 사진이 오면 처음 사진으로 돌아가게 할 것인지
    });

    // this.setTime();
    // this.retriebeTheater();
  },
  data() {
    return {
      // 회원아이디
      userId:"",

      // 비회원 예약 전화번호
      phone:"",

      // 비회원 예약 비밀번호
      password:"",

      // 상영중인 영화 목록
      ScreeningList: [],
      selectTheater: {}, // 지역 선택
      themaOfMovie: {}, // 영화 선택

      // 전체극장
      theater: [],

      // 현재 시간

      nowDate: "",
      nowDate2:"",
      yy: "",
      mm: "",
      dd: "",

      // 2D 극장 객체
      thName2D: [],

      // 2D4K 극장 객체
      thName2D4K: [],

      // 3D 극장 객체
      thName3D: [],

      // 4D3D 극장 객체
      thName4D3D: [],
      //  영화 시간 선택하는 창을 띄우는 객체
      goSelectMoive: true,

      // seatPopup 띄우는 객체
      seatPopup: false,

      // 좌석 선택시 팝업
      seatAlert:false,

      // 로그인 필요 서비스 알림 팝업
      loginPopup:false,

      // PaymentPopup 띄우는 객체
      paymentPopup: false,

      // 결제 선택 안된것 파악하는 객체
      paymentSelectPopup:false,

      // seatReserVation 좌석 선택 띄우는 객체
      seatReserVation: false,

      // 결제 페이지 띄우는 객체
      selectPayment: false,

      // 선택된 영화시간 객체
      selectedMovieTime: {},

      // 선택된 영화의 상영관
      selectedThname:"",

      // 선택된 영화의 scid
      selectedScid:"",

      // 선택된 영화의 끝나는 시간 객체
      cineEndTime: "",

      // TODO: 영화 상영시간 객체
      movieTime: 2,

      // 날짜가 선택되면 저장되는 객체
      // 230105 연월일 저장
      selectDateTime:"",
      // 05 일 저장
      selectedDate: "",

      //   좌석 배열을 나타내는 html
      seatLine: "",

      // 시니어 인원수
      numberOfSenior: 0,
      //   청소년 인원수
      numberOfTeenager: 0,
      // 성인 인원수
      numberOfAdult: 0,
      // 총 인원수
      numberOfTotal: 0,
      // 총 요금
      totalPrice: 0,
      // 출력되는 총 요금 , 콤마 표시
      printTotalPrice: "0",

      // 좌석 예약 여부 , 복도 등의 데이터
      seatData: [],

      aSeatLine: [],
      bSeatLine: [],
      cSeatLine: [],
      dSeatLine: [],
      eSeatLine: [],
      fSeatLine: [],
      gSeatLine: [],
      hSeatLine: [],
      iSeatLine: [],
      jSeatLine: [],
      kSeatLine: [],
      lSeatLine: [],
      mSeatLine: [],

      // 선택된 좌석 붉은 색으로 변경하기 위한 변수
      selectSeatOnA: [],
      selectSeatOnB: [],
      selectSeatOnC: [],
      selectSeatOnD: [],
      selectSeatOnE: [],
      selectSeatOnF: [],
      selectSeatOnG: [],
      selectSeatOnH: [],
      selectSeatOnI: [],
      selectSeatOnJ: [],
      selectSeatOnK: [],
      selectSeatOnL: [],
      selectSeatOnM: [],

      // 선택된 총 좌석수
      totalSelectedNumber: 0,

      // 선택된 표시할 좌석 번호
      selectedSeatNumber: [],
      // 백엔드로 보낼 좌석 번호
      pushSelectedSeatNumber:[],


      // 선택한 결제 방법 저장 객체
      selectedPayMethod: "",

      // 선택한 영화 포스터
      posterUrl:'',

      // 선택한 결제
      credit: false,
      Lpay: false,
      simplePay: false,
      myWallet: false,
      phone: false,

      // 상영관 리스트 보여주는 객체
      listShow:false,

      //  결제시 예약된 좌석으로 좌석 다시 선택하게하는 객체
      seatSelectAgainPopup:false,

    };
  },
  methods: {
    // 영화 선택
    getMovie(data) {
      this.themaOfMovie = data;
      let selectDateTime = String(this.yy) + this.mm + this.dd
      ReservationService.getCinemaMovies( this.themaOfMovie.mtitle ,this.selectTheater.cid)
      .then(response =>{
        this.thName2D = [];
          this.thName2D4K = [];
          this.thName3D = [];
          this.thName4D3D = [];

          for (let result of response.data) {
            if (result.thtype == "2D") {
              this.thName2D.push(result);
            } else if (result.thtype == "iMAX") {
              this.thName2D4K.push(result);
            } else if (result.thtype == "3D") {
              this.thName3D.push(result);
            } else if (result.thtype == "4D") {
              this.thName4D3D.push(result);
            }
          }
      })
      .catch(e=>{
        console.log(e);
      })
    },

    // 극장 선택
    getTheater(data) {

      this.selectDateTime = this.nowDate2;
      this.selectedDate = this.dd;
      console.log("선택 "+this.selectDateTime +"가져온시간"+this.nowDate2)

      
      this.selectTheater = data;
      ReservationService.getCinema(data.cid)
      .then(response=>{
        this.ScreeningList=response.data;
        console.log(this.ScreeningList)
      })
      .catch(e=>{
        console.log(e);
      })
    },

    // 오늘 날짜 구하는 함수 , 영화 시간선택에 적용
    setTime() {
      console.log("계산전"+this.nowDate2 +":"+this.nowDate)
      let myDate = new Date();
      this.yy = myDate.getFullYear();
      let month = myDate.getMonth() + 1;
      if( month <10){
        this.mm='0'+month;
      }
      else{
        this.mm = month;
      }
      let day = myDate.getDate();
      if(day<10){
        this.dd = '0'+day;
      }
      else{
        this.dd = day
      }
      this.nowDate = this.yy + "-" + this.mm + "-" + this.dd;
      this.nowDate2 = String(this.yy-2000)+String(this.mm)+String(this.dd);
      console.log("계산후"+this.nowDate2 +":"+this.nowDate)
      this.dayOfWeek = this.week[myDate.getDate()];
    },

    // TODO: 나열된 영화 시간을 선택하면 실행하는 함수
    selectMovieTime(data) {
      this.selectedMovieTime = data;
      this.selectedThname=data.thname;
      
      const endTime = data.thtime;
      let result = endTime.split("");
      if (result[0]=='2') {
        this.cineEndTime =
        (20+parseInt(result[1]) + this.movieTime)   + ":" + result[2] + result[3];
      } else if (result[0]=='1' & result[1]+ this.movieTime >= 10) {
        this.cineEndTime =
        (10+parseInt(result[1]) + this.movieTime)   + ":" + result[2] + result[3];
      } else if (result[0]=='0' & result[1]+ this.movieTime >= 10) {
        this.cineEndTime =
        (parseInt(result[1]) + this.movieTime)   + ":" + result[2] + result[3];
      } else {
        this.cineEndTime =
        '0' + (parseInt(result[1]) + this.movieTime) + ":"+ result[2] + result[3];
      }

      ReservationService.getScheduleSeat(data.thid, data.thdate , data.thtime )
      .then(response=>{
        this.seatData=response.data;
        this.posterUrl= this.seatData.mposterUri;
        console.log("받은 url"+this.posterUrl);

      this.aSeatLine = [];
      this.bSeatLine = [];
      this.cSeatLine = [];
      this.dSeatLine = [];
      this.eSeatLine = [];
      this.fSeatLine = [];
      this.gSeatLine = [];
      this.hSeatLine = [];
      this.iSeatLine = [];
      this.jSeatLine = [];
      this.kSeatLine = [];
      this.lSeatLine = [];
      this.mSeatLine = [];

      this.seatPopup = true;

      this.selectedScid=this.seatData.scid;

      this.aSeatLine = this.seatData.seatA.split("");
      this.bSeatLine = this.seatData.seatB.split("");
      this.cSeatLine = this.seatData.seatC.split("");
      this.dSeatLine = this.seatData.seatD.split("");
      this.eSeatLine = this.seatData.seatE.split("");
      this.fSeatLine = this.seatData.seatF.split("");
      this.gSeatLine = this.seatData.seatG.split("");
      this.hSeatLine = this.seatData.seatH.split("");
      this.iSeatLine = this.seatData.seatI.split("");
      this.jSeatLine = this.seatData.seatJ.split("");
      this.kSeatLine = this.seatData.seatK.split("");
      this.lSeatLine = this.seatData.seatL.split("");
      this.mSeatLine = this.seatData.seatM.split("");

      })
      .catch(e=>{
        console.log(e)
      })
    },

    // 팝업 끄는 함수
    closeScreenLayer() {
      this.seatPopup = false;
    },

    // 요일 표시 함수
    getDayOfWeek(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      const dayOfWeek = week[new Date(date).getDay()];
      return dayOfWeek;
    },

    getDayOfWeek1(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 1 >= 7) {
        days = -7;
      }
      const dayOfWeek1 = week[new Date(date).getDay() + 1 + days];
      return dayOfWeek1;
    },

    getDayOfWeek2(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 2 >= 7) {
        days = -7;
      }
      const dayOfWeek2 = week[new Date(date).getDay() + 2 + days];
      return dayOfWeek2;
    },

    getDayOfWeek3(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 3 >= 7) {
        days = -7;
      }
      const dayOfWeek3 = week[new Date(date).getDay() + 3 + days];
      return dayOfWeek3;
    },

    getDayOfWeek4(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 4 >= 7) {
        days = -7;
      }
      const dayOfWeek4 = week[new Date(date).getDay() + 4 + days];
      return dayOfWeek4;
    },

    getDayOfWeek5(date) {
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 5 >= 7) {
        days = -7;
      }
      const dayOfWeek5 = week[new Date(date).getDay() + 5 + days];
      return dayOfWeek5;
    },

    getDayOfWeek6(date) {
      let week = ["일", "월", "화", "수", "목", "금", "토"];
      let days = 0;
      if (new Date(date).getDay() + 6 >= 7) {
        days = -7;
      }
      const dayOfWeek6 = week[new Date(date).getDay() + 6 + days];
      return dayOfWeek6;
    },
    //  요일 표시 함수 끝

    //  좌석 선택 레이어로 이동 / 로그인 여부 확인
    goSeatSelectLayer() {
      console.log("회원로그인여부:"+this.loggedIn+"비회원로그인여부:"+this.loggedIn2)
      if(this.loggedIn){
      this.userId= JSON.parse(localStorage.getItem("user")).id;
      console.log("userID 는 "+ this.userId);
      
    }
    if(this.loggedIn2){
      this.phone= JSON.parse(localStorage.getItem("nonUser")).phone;
      this.password= JSON.parse(localStorage.getItem("nonUser")).password;
      console.log("phone 는 "+ this.phone);
      console.log("password 는 "+ this.password);
    }
      
      
    // 회원&비회원 로그인 여부로 함수 실행
    if(!this.loggedIn && !this.loggedIn2){
      this.loginPopup=true;
    }
    else {
      this.goSelectMoive = false;
      this.seatPopup = false;
      this.seatReserVation = true;
      this.selectPayment = false;
      this.paymentPopup = false;
    }
      
    },

    //  영화 시간 레이어로 이동
    goTimeSelect() {
      this.goSelectMoive = true;
      this.seatPopup = false;
      this.seatReserVation = false;
      this.selectPayment = false;
      this.paymentPopup = false;
    },

    //  좌석 선택 다시 하는 함수
    seatSelectAgain(){
      this.resetNumberSelect();
      this.resetPayment();
      this.seatSelectAgainPopup=false;
      this.goTimeSelect();
    },

    // 결제 선택 레이어로 이동
    goPayment() {
      if (
        (this.totalPrice != 0) &
        (this.totalSelectedNumber == this.numberOfTotal)
      ) {
        this.goSelectMoive = false;
        this.seatPopup = false;
        this.seatReserVation = false;
        this.selectPayment = true;
        this.paymentPopup = false;
      } else {
        this.seatAlert = true;
      }
    },

// TODO: 좌석 데이터 보낼때 예약번호와 좌석 번호 두개를 보내게.
      // GET http://localhost:8000/movie/movies/Ticketing/reserve?scid=1&seatId=0107#0208#0611
      
    // 결제화면에서 결제하기 누르면 실행되는 함수
    donePay() {
      
      if (this.selectedPayMethod.length != 0) {
        let result ="";
        for( let k of this.pushSelectedSeatNumber){
          result = result+k+"T";
        }
        console.log(result)

        if(this.loggedIn){
          
        this.userId = JSON.parse(localStorage.getItem("user")).id;

          ReservationService.getMovieReservationMem( this.selectedScid , result , this.totalPrice, this.userId)
        .then(response=>{
          
          this.paymentPopup = true;
        })
        .catch(e=>{
          console.log(e);
          this.seatSelectAgainPopup = true ;
        })
        }
        if(this.loggedIn2) {
        this.phone= JSON.parse(localStorage.getItem("nonUser")).phone;
      this.password= JSON.parse(localStorage.getItem("nonUser")).password;
      console.log(this.phone);
      console.log(this.password);
          ReservationService.getMovieReservationNonmem(this.selectedScid , result , this.totalPrice, this.phone , this.password  )
        .then(response=>{
          this.paymentPopup = true;
        })
        .catch(e=>{
          console.log(e);
          this.seatSelectAgainPopup = true ;
        })
        }
        
      }
      else {
        this.paymentSelectPopup = true;
      }
    },

    // 결제완료 팝업 닫기
    closePaymentPopup(){
      this.paymentPopup = false;
      // TODO: 결제완료 팝업닫고 다른 페이지로 이동

    },

    // 인원 선택 함수 시작
    adultMinus() {
      if (this.numberOfAdult <= 0) {
        this.numberOfAdult = 0;
      } else if (
        (this.numberOfTotal > 0) &
        (this.numberOfTotal > this.totalSelectedNumber)
      ) {
        this.numberOfAdult--;
        this.numberOfTotal--;
        this.totalPrice -= 14000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    adultPlus() {
      if (this.numberOfTotal < 8) {
        this.numberOfAdult++;
        this.numberOfTotal++;
        this.totalPrice += 14000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    teenagerMinus() {
      if (this.numberOfTeenager <= 0) {
        this.numberOfTeenager = 0;
      } else if (
        (this.numberOfTotal > 0) &
        (this.numberOfTotal > this.totalSelectedNumber)
      ) {
        this.numberOfTeenager--;
        this.numberOfTotal--;
        this.totalPrice -= 11000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    teenagerPlus() {
      if (this.numberOfTotal < 8) {
        this.numberOfTeenager++;
        this.numberOfTotal++;
        this.totalPrice += 11000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    seniorMinus() {
      if (this.numberOfSenior <= 0) {
        this.numberOfSenior = 0;
      } else if (
        (this.numberOfTotal > 0) &
        (this.numberOfTotal > this.totalSelectedNumber)
      ) {
        this.numberOfSenior--;
        this.numberOfTotal--;
        this.totalPrice -= 7000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    seniorPlus() {
      if (this.numberOfTotal < 8) {
        this.numberOfSenior++;
        this.numberOfTotal++;
        this.totalPrice += 7000;
        this.printTotalPrice = this.totalPrice
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    // 인원 선택 함수 끝

    // 좌석 선택하면 실행되는 함수
    selectedSeatA(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnA.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnA.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";
          if (String(index).length === 1) {
            seatNumber = "A0" + (index);
            pushSeatNumber = "010"+(index+1);
          } else {
            seatNumber = "A" + (index);
            pushSeatNumber = "01"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
        console.log(index);
        console.log(this.selectSeatOnA);
        console.log(this.selectedSeatNumber);
        console.log(this.pushSelectedSeatNumber);
      }
    },
    selectedSeatB(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnB.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnB.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "B0" + (index);
            pushSeatNumber = "020"+(index+1);
          } else {
            seatNumber = "B" + (index);
            pushSeatNumber = "02"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatC(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnC.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnC.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "C0" + (index);
            pushSeatNumber = "030"+(index+1);
          } else {
            seatNumber = "C" + (index);
            pushSeatNumber = "03"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatD(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnD.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnD.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "D0" + (index);
            pushSeatNumber = "040"+(index+1);
          } else {
            seatNumber = "D" + (index);
            pushSeatNumber = "04"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatE(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnE.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnE.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "E0" + (index);
            pushSeatNumber = "050"+(index+1);
          } else {
            seatNumber = "E" + (index);
            pushSeatNumber = "05"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatF(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnF.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnF.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "F0" + (index);
            pushSeatNumber = "060"+(index+1);
          } else {
            seatNumber = "F" + (index);
            pushSeatNumber = "06"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatG(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnG.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnG.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "G0" + (index);
            pushSeatNumber = "070"+(index+1);
          } else {
            seatNumber = "G" + (index);
            pushSeatNumber = "07"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatH(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnH.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnH.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "H0" + (index);
            pushSeatNumber = "080"+(index+1);
          } else {
            seatNumber = "H" + (index);
            pushSeatNumber = "08"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatI(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnI.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnI.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "I0" + (index);
            pushSeatNumber = "090"+(index+1);
          } else {
            seatNumber = "I" + (index);
            pushSeatNumber = "09"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatJ(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnJ.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnJ.push(index);
          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "J0" + (index);
            pushSeatNumber = "100"+(index+1);
          } else {
            seatNumber = "J" + (index);
            pushSeatNumber = "10"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatK(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnK.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnK.push(index);
          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "K0" + (index);
            pushSeatNumber = "110"+(index+1);
          } else {
            seatNumber = "K" + (index);
            pushSeatNumber = "11"+(index+1);
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatL(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnL.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnL.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "L0" + (index);
            pushSeatNumber = "120"+(index+1);
          } else {
            seatNumber = "L" + (index);
            pushSeatNumber = "12"+(index+1);
            
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },
    selectedSeatM(data, index) {
      if (this.numberOfTotal > this.totalSelectedNumber) {
        if (
          data != "A" &&
          data != "X" &&
          this.selectSeatOnM.includes(index) != true
        ) {
          this.totalSelectedNumber++;
          this.selectSeatOnM.push(index);

          let seatNumber = "";
          let pushSeatNumber ="";

          if (String(index).length === 1) {
            seatNumber = "M0" + (index);
            pushSeatNumber = "130"+(index+1);
          } else {
            seatNumber = "M" + (index);
            pushSeatNumber = "13"+(index+1);
            
          }
          this.selectedSeatNumber.push(seatNumber);
          this.pushSelectedSeatNumber.push(pushSeatNumber);
        }
      }
    },

    // 인원수 초기화 버튼 누르면 실행되는 함수
    resetNumberSelect() {
      // 총 인원 초기화
      this.numberOfTotal = 0;
      // 성인 인원 초기화
      this.numberOfAdult = 0;
      // 청소년 인원 초기화
      this.numberOfTeenager = 0;
      // 시니어 인원 초기화
      this.numberOfSenior = 0;

      // 선택된 좌석 붉은 색으로 변경하기 위한 변수 초기화
      this.selectSeatOnA = [];
      this.selectSeatOnB = [];
      this.selectSeatOnC = [];
      this.selectSeatOnD = [];
      this.selectSeatOnE = [];
      this.selectSeatOnF = [];
      this.selectSeatOnG = [];
      this.selectSeatOnH = [];
      this.selectSeatOnI = [];
      this.selectSeatOnJ = [];
      this.selectSeatOnK = [];
      this.selectSeatOnL = [];

      // 선택된 총 좌석수
      this.totalSelectedNumber = 0;

      // 출력될 총 금액
      this.printTotalPrice = 0;
      // 총 금액
      this.totalPrice = 0;
      console.log(this.selectSeatOnA);
      // 선택된 좌석
      this.selectedSeatNumber = [];
      this.pushSelectedSeatNumber =[];
      console.log(this.selectedSeatNumber);
    },

    // 결제방법 초기화 누르면 실행되는 함수
    resetPayment(){
      this.credit = false;
      this.Lpay = false;
      this.simplePay = false;
      this.myWallet = false;
      this.phone = false;
      this.selectedPayMethod = "";
    },
    
    // 날짜를 선택하면 selectedDate에 저장되는 함수
    selectDate(date) {
      if( parseInt(date) <10){
        this.selectDateTime = String(this.yy-2000) + this.mm + '0'+date;
        this.selectedDate = '0'+date;
      }
      else{
        this.selectDateTime =String(this.yy-2000) + this.mm + date;
        this.selectedDate = date;
      }
      console.log("상영관 조회를 위해 백엔드로 보내지는 날짜:"+this.selectDateTime)
    },

    // 신용카드선택
    selectCredit() {
      this.credit = true;
      this.Lpay = false;
      this.simplePay = false;
      this.myWallet = false;
      this.phone = false;
      this.selectedPayMethod = "신용카드로 결제되었습니다.";
    },

    // 엘페이 선택
    selectLpay() {
      this.credit = false;
      this.Lpay = true;
      this.simplePay = false;
      this.myWallet = false;
      this.phone = false;
      this.selectedPayMethod = "엘페이로 결제되었습니다.";
    },

    // 간편결제 선택
    selectSimplePay() {
      this.credit = false;
      this.Lpay = false;
      this.simplePay = true;
      this.myWallet = false;
      this.phone = false;
      this.selectedPayMethod = "간편결제로 결제되었습니다.";
    },

    // 내통장결제 선택
    selectMyWallet() {
      this.credit = false;
      this.Lpay = false;
      this.simplePay = false;
      this.myWallet = true;
      this.phone = false;
      this.selectedPayMethod = "내통장결제로 결제되었습니다.";
    },

    // 휴대폰 선택
    selectPhone() {
      this.credit = false;
      this.Lpay = false;
      this.simplePay = false;
      this.myWallet = false;
      this.phone = true;
      this.selectedPayMethod = "휴대폰으로 결제되었습니다.";
    },

    // axios , 모든 지역영화관 조회 요청 함수
    retriebeTheater() {
      ReservationService.getAll()
        //  axios 성공하면  .then  결과전송됨
        .then((response) => {
          this.theater = [];
          this.theater = response.data; // springboot 의 전송된 부서정보
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },

  },
  created(){


    this.retriebeTheater();
    this.setTime();
    
    
    
  },
  computed:{
     
    // 로그인여부 가져오는 함수
    loggedIn() {
      return this.$store.state.auth.initialState.status.loggedIn;
    },
    // 비회원로그인여부 가져오는 함수
    loggedIn2() {
      return this.$store.state.auth.initialState2.status.loggedIn2;
    },

  }

};
</script>

<style scoped>
.owl-dots {
  display: none;
}
.time_select_wrap ul.list_time li.disabled > a {
  pointer-events: none;
  cursor: default;
}

.textStyleRed {
  color: red;
  font-weight: bolder;
}

.textStyleBlue {
  color: blue;
  font-weight: bolder;
}

.wrap_reserve {
  overflow: hidden;
  position: relative;
  width: 1280px;
  height: 870px;
  margin: 0 auto;
  background-color: #fff;
}

.wrap_reserve .section_step_con.active {
  display: block;
  overflow: inherit;
  position: relative;
  width: 1202px;
  height: 100%;
  margin-left: 78px;
}

#contents.contents_reserve {
  padding-bottom: 70px;
  background-color: #444;
}

#contents.contents_full {
  width: 100%;
}

#contents {
  margin: 0 auto;
  padding: 50px 0 0 0;
}

div {
  display: block;
}

body,
input,
button,
select,
textarea {
  font-family: "Noto Sans KR", "Roboto", "dotum", "sans-serif";
  font-size: 12px;
  color: #000;
}

body,
textarea {
  line-height: 1.1;
  letter-spacing: -0.03em;
}

.wrap_reserve .section_step_con .article.article_cinema {
  width: 351px;
  border-bottom: 1px solid #ddd;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  .cinema_select_wrap
  > ul
  > div
  > li.depth1.active
  > a {
  color: #ff243e;
  background: #fff url("@/assets/images/icon/check.png") no-repeat 300px center;
  font-weight: bold;
}

.movie_select_wrap ul > li.active {
  border: 2px solid #000;
  background-color: #fff;
}

.wrap_reserve .section_step_con .article .group_top {
  overflow: hidden;
  position: relative;
  height: 55px;
  line-height: 55px;
  border-right: 1px solid #222;
  text-align: center;
  background-color: #000;
  padding-top: 15px;
}

.wrap_reserve .section_step_con .article .group_top .tit {
  font-size: 18px;
  color: #fff;
}

h1,
h2,
h3,
h4,
h5,
h6,
address,
caption,
em,
th {
  font-style: normal;
  font-weight: normal;
}

.wrap_reserve .section_step_con .article.article_cinema > .inner {
  overflow: hidden;
  height: 815px;
  border-right: 1px solid #ddd;
}

ul,
ol {
  list-style: none;
}

ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}

li {
  display: list-item;
  text-align: -webkit-match-parent;
  list-style: none;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .tab_wrap.outer
  > li.active
  > .tab_tit {
  height: 61px;
}

.tab_wrap.outer > li.active > .tab_tit {
  border-bottom: 2px solid #111;
  color: #000;
}

.tab_wrap.outer > li > .tab_tit {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #ccc;
  font-size: 15px;
  color: #7f7f7f;
}

.tab_wrap > li > .tab_tit {
  border: none;
}

button {
  border: 1px solid #ddd;
  cursor: pointer;
  background-color: #fff;
}

button {
  appearance: auto;
  writing-mode: horizontal-tb !important;

  text-rendering: auto;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: center;
  align-items: flex-start;
  cursor: default;
  box-sizing: border-box;
  margin: 0em;
  padding: 1px 6px;
  border-width: 2px;
  border-style: outset;
  border-color: buttonborder;
  border-image: initial;
}

.tab_wrap > li.active > .tab_con {
  overflow: inherit;
  position: relative;
  width: auto;
  height: auto;
}

.tab_wrap > li > .tab_con {
  overflow: hidden;
  position: absolute;
  width: 100%;
  height: 0;
}

.wrap_reserve .section_step_con {
  overflow: hidden;
  position: absolute;
  height: 0;
}

.hidden {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

h5 {
  display: block;
  font-size: 0.83em;
  margin-block-start: 1.67em;
  margin-block-end: 1.67em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}

ul,
ol {
  list-style: none;
}

.wrap_reserve .section_step_con .article.article_cinema .cinema_select_wrap {
  background-color: #f5f5f5;
}

.cinema_select_wrap {
  position: relative;
  height: 100%;
  background-color: #fff;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .cinema_select_wrap
  > ul,
.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .cinema_select_wrap
  > ul
  .depth2
  .mCustomScrollbar {
  height: 753px;
}

.mCustomScrollbar.mCS_no_scrollbar,
.mCustomScrollbar.mCS_touch_action {
  -ms-touch-action: auto;
  touch-action: auto;
}

.mCustomScrollbar {
  -ms-touch-action: pinch-zoom;
  touch-action: pinch-zoom;
}

.cinema_select_wrap > ul .depth2 {
  display: none;
  position: absolute;
  left: 175px;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: #f5f5f5;
}

.cinema_select_wrap > ul {
  min-height: 90px;
  height: 100%;
}

ul ul {
  list-style-type: circle;
  margin-block-start: 0px;
  margin-block-end: 0px;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  .cinema_select_wrap
  > ul
  li {
  padding: 2px;
  border-bottom: none;
}

.cinema_select_wrap > ul li {
  border-bottom: 1px solid #ebebeb;
}

.cinema_select_wrap > ul > div > li.depth1 > a {
  display: block;
  position: relative;
  width: 345px;
  box-sizing: border-box;
  padding: 10px 16px;
  font-size: 13px;
}

.cinema_select_wrap > ul > div > li.depth1 > a em {
  font-size: 10px;
  color: #666;
}

em {
  font-style: italic;
}

.wrap_reserve .section_step_tit {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  width: 78px;
}

.wrap_reserve .section_step_tit ul {
  height: 100%;
}

.wrap_reserve .section_step_tit ul li.active {
  border-color: #ff243e;
  border-bottom-color: #666;
  background-color: #ff243e;
}

.wrap_reserve .section_step_tit ul li {
  position: relative;
  height: 25%;
  box-sizing: border-box;
  border: 1px solid #666;
  border-top: none;
  background-color: #fff;
  z-index: 10;
}

.wrap_reserve .section_step_tit ul li.active > a {
  color: #fff;
}

.wrap_reserve .section_step_tit ul li > a > strong span {
  font-family: "Roboto";
  font-size: 13px;
}

.wrap_reserve .section_step_tit ul li.active > a > strong {
  font-size: 15px;
}

.wrap_reserve .section_step_tit ul li > a > strong {
  display: block;
  line-height: 1.5;
  padding-top: 80px;
  font-size: 13px;
  text-align: center;
}

strong {
  font-weight: bold;
}

.wrap_reserve .section_step_tit ul li.active > a .bx_con {
  background-color: #ff243e;
}

.wrap_reserve .section_step_tit ul li > a .bx_con {
  display: none;
  position: absolute;
  z-index: 1;
  left: 77px;
  top: -1px;
  bottom: 0;
  width: 172px;
  padding-left: 20px;
  border-top: 1px solid #666;
  background-color: #333;
}

.wrap_reserve .section_step_tit ul li > a .bx_con dl {
  margin-top: 52px;
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

.wrap_reserve .section_step_tit ul li > a .bx_con dl dt {
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}

dt {
  display: block;
}

.wrap_reserve .section_step_tit ul li > a .bx_con dl dd {
  margin-bottom: 17px;
}

.wrap_reserve .section_step_tit ul li > a {
  display: block;
  height: 100%;
  color: #666;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .tab_wrap.outer {
  padding-top: 61px;
}

.tab_wrap.outer {
  padding-top: 80px;
}

.tab_wrap > li > .tab_tit {
  position: absolute;
  top: 0;
}

input,
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}

button > * {
  position: relative;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  .cinema_select_wrap
  > ul
  .depth2 {
  background-color: #fff;
}

.cinema_select_wrap > ul > div > li.depth1.active .depth2 {
  display: block;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .cinema_select_wrap
  > ul,
.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .cinema_select_wrap
  > ul
  .depth2
  .mCustomScrollbar {
  height: 753px;
}
.time_select_wrap ul.list_hall:after {
  content: "";
  display: block;
  clear: both;
}

.time_select_wrap {
  margin-top: 20px;
}

.time_select_wrap ul.list_hall {
  margin-bottom: 13px;
}

.time_select_wrap ul.list_hall li {
  display: block;
  position: relative;
  float: left;
  margin-left: 6px;
  padding-left: 6px;
  font-size: 13px;
}

.mCustomScrollBox {
  position: relative;
  overflow: hidden;
  height: 100%;
  max-width: 100%;
  outline: none;
  direction: ltr;
}

.mCSB_container.mCS_no_scrollbar_y.mCS_y_hidden {
  margin-right: 0;
}

.mCSB_container {
  overflow: hidden;
  width: auto;
  height: auto;
}

.cinema_select_wrap .bx_notice {
  clear: both;
  margin-top: 100px;
  padding: 0 19px;
  text-align: center;
}

.cinema_select_wrap .bx_notice .txt {
  line-height: 1.5;
  margin-bottom: 14px;
  font-size: 13px;
}

p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

.btn_col1.rnd,
.btn_col2.rnd,
.btn_col3.rnd,
.btn_col4.rnd,
.btn_col5.rnd,
.btn_col6.rnd,
.btn_col7.rnd,
.btn_col8.rnd,
.btn_col9.rnd,
.btn_col10.rnd {
  border-radius: 52px;
}

.btn_col1.ty3,
.btn_col2.ty3,
.btn_col3.ty3,
.btn_col4.ty3,
.btn_col5.ty3,
.btn_col6.ty3,
.btn_col7.ty3,
.btn_col8.ty3,
.btn_col9.ty3,
.btn_col10.ty3 {
  height: 28px;
  line-height: 26px;
  padding: 0 14px;
}

.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #000000;
  font-size: 14px;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ffffff;
}

a:-webkit-any-link {
  color: -webkit-link;
  cursor: pointer;
}

.cinema_select_wrap li.active > a:before {
  content: "선택";
  overflow: hidden;
  position: absolute;
  width: 0;
  height: 0;
  text-indent: -9999em;
}

.cinema_select_wrap > ul .depth2 ul li a {
  display: table-cell;
  /* display: block; */
  overflow: hidden;
  padding: 10px 16px;
  font-size: 13px;
  vertical-align: middle;
}

.mCSB_outside + .mCS-minimal.mCSB_scrollTools_vertical,
.mCSB_outside + .mCS-minimal-dark.mCSB_scrollTools_vertical {
  right: 0;
  margin: 12px 0;
}

.mCS-autoHide > .mCustomScrollBox > .mCSB_scrollTools,
.mCS-autoHide > .mCustomScrollBox ~ .mCSB_scrollTools {
  opacity: 0;
  filter: "alpha(opacity=0)";
  -ms-filter: "alpha(opacity=0)";
}

.mCSB_outside + .mCSB_scrollTools {
  right: -26px;
}

.mCSB_scrollTools {
  opacity: 0.75;
  filter: "alpha(opacity=75)";
  -ms-filter: "alpha(opacity=75)";
}

.mCSB_scrollTools,
.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar,
.mCSB_scrollTools .mCSB_buttonUp,
.mCSB_scrollTools .mCSB_buttonDown,
.mCSB_scrollTools .mCSB_buttonLeft,
.mCSB_scrollTools .mCSB_buttonRight {
  -webkit-transition: opacity 0.2s ease-in-out,
    background-color 0.2s ease-in-out;
  -moz-transition: opacity 0.2s ease-in-out, background-color 0.2s ease-in-out;
  -o-transition: opacity 0.2s ease-in-out, background-color 0.2s ease-in-out;
  transition: opacity 0.2s ease-in-out, background-color 0.2s ease-in-out;
}

.mCSB_scrollTools {
  position: absolute;
  width: 16px;
  height: auto;
  left: auto;
  top: 0;
  right: 0;
  bottom: 0;
}

.mCSB_scrollTools .mCSB_draggerContainer {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  height: auto;
}

.mCS-minimal.mCSB_scrollTools_vertical .mCSB_dragger,
.mCS-minimal-dark.mCSB_scrollTools_vertical .mCSB_dragger {
  height: 50px;
}

.mCSB_scrollTools .mCSB_dragger {
  cursor: pointer;
  width: 100%;
  height: 30px;
  z-index: 1;
}

.mCS-minimal-dark.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  background-color: #000;
  background-color: rgba(0, 0, 0, 0.2);
  filter: "alpha(opacity=20)";
  -ms-filter: "alpha(opacity=20)";
}

.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  position: relative;
  width: 4px;
  height: 100%;
  margin: 0 auto;
  -webkit-border-radius: 16px;
  -moz-border-radius: 16px;
  border-radius: 16px;
  text-align: center;
}

.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  background-color: #fff;
  background-color: rgba(255, 255, 255, 0.75);
  filter: "alpha(opacity=75)";
  -ms-filter: "alpha(opacity=75)";
}

.mCSB_scrollTools .mCSB_draggerRail {
  width: 2px;
  height: 100%;
  margin: 0 auto;
  -webkit-border-radius: 16px;
  -moz-border-radius: 16px;
  border-radius: 16px;
}

.wrap_reserve
  .section_step_con
  .article.article_cinema
  > .inner
  .tab_wrap.outer
  > li
  > .tab_tit {
  height: 61px;
}

.article_cinema.area__movingbar.litype2
  .tab_wrap.outer.actionmovingbar
  .wrap_nav_underline {
  position: absolute;
  top: 61px;
  left: 50%;
  margin-left: -175.5px;
  width: 351px;
  height: 2px;
  margin-top: -2px;
  border: 0px solid #f00;
}

.area__movingbar.litype2 .tab_wrap.outer.actionmovingbar .wrap_nav_underline {
  position: absolute;
  top: 50px;
  left: 50%;
  margin-left: -490px;
  width: 980px;
  height: 2px;
  margin-top: -2px;
  border: 0px solid #f00;
}

.article_cinema.area__movingbar.litype2
  .tab_wrap.outer.actionmovingbar
  > li:nth-child(2).active
  ~ .wrap_nav_underline
  .nav_underline {
  left: 175.5px;
}

.area__movingbar.litype2
  .tab_wrap.outer.actionmovingbar
  > li:nth-child(2).active
  ~ .wrap_nav_underline
  .nav_underline {
  left: 490px;
}

.article_cinema.area__movingbar.litype2
  .tab_wrap.outer.actionmovingbar
  .nav_underline {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 175.5px;
  height: 2px;
  background: #111;
  transition: all 0.3s ease-in-out;
}

.area__movingbar.litype2 .tab_wrap.outer.actionmovingbar .nav_underline {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 490px;
  height: 2px;
  background: #111;
  transition: all 0.3s ease-in-out;
}

.wrap_reserve .section_step_con .article.article_movie {
  width: 351px;
  background-color: #f8f8f8;
}

h4 {
  display: block;
  margin-block-start: 1.33em;
  margin-block-end: 1.33em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}

.wrap_reserve .section_step_con .article.article_movie > .inner {
  overflow: hidden;
  height: 815px;
  border-right: 1px solid #ddd;
}

.wrap_reserve .section_step_con .article.article_movie .list_filter {
  overflow: hidden;
  position: relative;
  height: 60px;
  padding: 0 20px;
  border-bottom: 1px solid #ddd;
}

.wrap_reserve .section_step_con .article.article_movie .list_filter select {
  width: 180px;
  height: 30px;
  line-height: 29px;
  margin-top: 15px;
  padding-left: 10px;
}

select {
  border-radius: 4px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  border: 1px solid #ddd;
  background: #fff url("@/assets/images/icon/arr_dw_13.png");
  background-repeat: no-repeat;
  background-position: right 6px top 50%;
  height: 40px;
  line-height: 40px;
  padding: 0 35px 0 18px;
  font-size: 14px;
  box-sizing: border-box;
}

option {
  font-weight: normal;
  display: block;
  white-space: nowrap;
  min-height: 1.2em;
  padding: 0px 2px 1px;
}

.bx_btn_view {
  position: absolute;
  right: 18px;
  top: 20px;
}

.bx_btn_view ul li {
  float: left;
  margin-left: 12px;
}

.bx_btn_view .btn_view_txt.active {
  background-image: url("@/assets/images/icon/btn_list_view_txt_on.png");
}

.bx_btn_view .btn_view_txt {
  width: 20px;
  height: 20px;
  border: none;
  text-indent: -9999em;
  background: url("@/assets/images/icon/btn_list_view_txt.png") no-repeat center
    center;
}

.wrap_reserve .section_step_con .article.article_time {
  width: 500px;
}

.wrap_reserve .section_step_con .article {
  position: relative;
  float: left;
  height: 100%;
  box-sizing: border-box;
  background-color: #fff;
}

.wrap_reserve
  .section_step_con
  .article.article_movie
  > .inner
  .mCustomScrollbar {
  height: 754px;
}

.wrap_reserve .section_step_con .article.article_movie .inner .mCSB_container {
  padding-bottom: 30px;
}

.movie_select_wrap.list ul > li .bx_thm {
  display: none;
}

/* img 이미지 속성 */
img {
  border: none;
  vertical-align: top;
}

img {
  overflow-clip-margin: content-box;
  overflow: clip;
}

.movie_select_wrap ul > li .group_infor {
  float: left;
}

.movie_select_wrap.list ul > li .group_infor .bx_tit .ic_grade {
  position: absolute;
  left: 20px;
  top: 12px;
}

.movie_select_wrap ul > li .group_infor .bx_tit .ic_grade {
  margin-right: 5px;
}

.ic_grade {
  display: inline-block;
  width: 22px;
  height: 22px;
  line-height: 22px;
  border-radius: 50%;
  font-size: 0;
  text-align: center;
  vertical-align: middle;
}

.ic_grade.gr_all:before,
.ic_grade.gr_12:before,
.ic_grade.gr_15:before,
.ic_grade.gr_18:before,
.ic_grade.gr_non:before,
.ic_grade:before {
  content: "";
  display: block;
  width: 22px;
  height: 22px;
  border-radius: 3px;
}

.ic_grade:before {
  margin-left: -1px;
  font-size: 13px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}

.movie_select_wrap.list ul > li .group_infor .bx_tit .tit {
  display: block;
  overflow: hidden;
  width: 250px;
  padding-left: 30px;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.movie_select_wrap ul > li .group_infor .bx_tit .tit {
  font-size: 15px;
  vertical-align: middle;
}

.txt_ic_score.ty1 em {
  width: 10px;
  height: 10px;
  margin: -2px 3px 0 0;
}

.txt_ic_score em {
  overflow: hidden;
  display: inline-block;
  vertical-align: middle;
  text-indent: -9999em;
}

.txt_ic_score {
  display: inline-block;
  vertical-align: middle;
}

.movie_select_wrap ul > li > a:after {
  content: "";
  display: block;
  clear: both;
}

.movie_select_wrap ul > li {
  box-sizing: border-box;
  border: 2px solid transparent;
}

.mCustomScrollbar
  > .mCustomScrollBox
  > .mCSB_scrollTools.mCSB_scrollTools_onDrag,
.mCustomScrollbar
  > .mCustomScrollBox
  ~ .mCSB_scrollTools.mCSB_scrollTools_onDrag,
.mCustomScrollBox:hover > .mCSB_scrollTools,
.mCustomScrollBox:hover ~ .mCSB_scrollTools,
.mCS-autoHide:hover > .mCustomScrollBox > .mCSB_scrollTools,
.mCS-autoHide:hover > .mCustomScrollBox ~ .mCSB_scrollTools {
  opacity: 1;
  filter: "alpha(opacity=100)";
  -ms-filter: "alpha(opacity=100)";
}

.mCS-minimal.mCSB_scrollTools_vertical .mCSB_dragger,
.mCS-minimal-dark.mCSB_scrollTools_vertical .mCSB_dragger {
  height: 50px;
}

.mCS-minimal-dark.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
.mCS-minimal-dark.mCSB_scrollTools
  .mCSB_dragger.mCSB_dragger_onDrag
  .mCSB_dragger_bar {
  background-color: #000;
  background-color: rgba(0, 0, 0, 0.5);
  filter: "alpha(opacity=50)";
  -ms-filter: "alpha(opacity=50)";
}

.mCSB_scrollTools .mCSB_dragger:active .mCSB_dragger_bar,
.mCSB_scrollTools .mCSB_dragger.mCSB_dragger_onDrag .mCSB_dragger_bar {
  background-color: #fff;
  background-color: rgba(255, 255, 255, 0.9);
  filter: "alpha(opacity=90)";
  -ms-filter: "alpha(opacity=90)";
}

.date_select_wrap {
  height: 75px;
  margin: 22px 20px 10px;
}

.date_select_wrap .slide_reserve_date {
  padding: 0 20px;
}

.slide_wrap {
  position: relative;
}

.date_select_wrap .month {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  text-align: center;
  font-size: 10px;
}

.date_select_wrap .date {
  display: block;
  position: relative;
  padding-top: 20px;
}

.date_select_wrap .date label {
  display: block;
  cursor: pointer;
}

label {
  cursor: default;
}

.date_select_wrap .date label input[type="radio"] {
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
  clip: rect(0, 0, 0, 0);
}

input[type="radio"],
input[type="checkbox"] {
  top: 0;
  left: 0;
  padding: 0;
  margin: 0;
  vertical-align: middle;
  border: none 0;
}
input[type="radio"],
input[type="checkbox"] {
  position: relative;
  top: 0;
  left: 0;
  width: 0px;
  height: 0px;
  padding: 0;
  margin: 0;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  vertical-align: middle;
  border: none 0;
}

.date_select_wrap .date label input[type="radio"]:checked + strong {
  font-weight: bold;
  color: #fff;
  background: #000;
}

.date_select_wrap .date label strong {
  display: block;
  width: 30px;
  height: 30px;
  line-height: 30px;
  margin: 0 auto;
  border-radius: 50%;
  font-family: "Roboto";
  font-size: 15px;
  text-align: center;
}

.date_select_wrap .date label em {
  display: block;
  margin-top: 10px;
  font-size: 12px;
  text-align: center;
}

input[type="radio" i] {
  background-color: initial;
  cursor: default;
  appearance: auto;
  box-sizing: border-box;
  margin: 3px 3px 0px 5px;
  padding: initial;
  border: initial;
}

input {
  writing-mode: horizontal-tb !important;
  text-rendering: auto;
  color: fieldtext;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: auto;
  -webkit-rtl-ordering: logical;
  cursor: text;
  background-color: field;
  margin: 0em;
  padding: 1px 2px;
  border-width: 2px;
  border-style: inset;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
}

.owl-stage {
  transform: translate3d(0px, 0px, 0px);
  transition: all 0.4s ease 0s;
  width: 420px;
}

.owl-carousel .owl-stage:after {
  content: ".";
  display: block;
  clear: both;
  visibility: hidden;
  line-height: 0;
  height: 0;
}

.owl-carousel .owl-nav {
  position: static;
  right: auto;
}

.owl-carousel .owl-nav {
  top: 50%;
  height: 19px;
  margin-top: -10px;
}

.owl-carousel .owl-dots,
.owl-carousel .owl-nav {
  position: absolute;
  right: 0;
  left: 0;
  text-align: center;
  -webkit-tap-highlight-color: transparent;
}

.owl-carousel .owl-nav .owl-prev.disabled,
.owl-carousel .owl-nav .owl-next.disabled {
  opacity: 0.35;
  pointer-events: none;
}

.owl-carousel .owl-nav .owl-prev {
  opacity: 0.6;
  position: absolute;
  top: 50%;
  left: -15px;
  width: 25px;
  height: 39px;
  margin-top: -10px;
  background: url("@/assets/images/icon/arr_lf_11.png") no-repeat center center;
}

/* 
.owl-carousel .owl-nav .owl-prev {
    left: -15px;
    background-image: url('@/assets/images/icon/arr_lf_11.png');
} */

.owl-next {
  opacity: 0.6;
  position: absolute;
  right: -20px;
  top: 50%;
  width: 25px;
  height: 39px;
  margin-top: -10px;
  background: url("@/assets/images/icon/arr_rg_11.png") no-repeat center center;
}

/* 
.owl-carousel .owl-nav .owl-next {
    right: 5px;
    background-image: url('@/assets/images/icon/arr_rg_11.png');
} */

.owl-carousel .owl-nav .disabled {
  opacity: 0.5;
  cursor: default;
}

.owl-carousel .owl-nav [class*="owl-"] {
  position: absolute;
  width: 11px;
  height: 19px;
  z-index: 10;
  cursor: pointer;
  background-size: auto;
  background-position: center;
  background-repeat: no-repeat;
}

.owl-carousel .owl-nav .owl-prev,
.owl-carousel .owl-nav .owl-next,
.owl-carousel .owl-dot {
  cursor: pointer;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.owl-carousel .owl-dots,
.owl-carousel .owl-nav {
  position: absolute;
  right: 0;
  left: 0;
  text-align: center;
  -webkit-tap-highlight-color: transparent;
}

.owl-carousel .owl-nav [class*="owl-"] span {
  font-size: 0;
  text-indent: -9999em;
}

.owl-carousel .owl-nav .owl-prev.disabled,
.owl-carousel .owl-nav .owl-next.disabled {
  opacity: 0.35;
  pointer-events: none;
}

.owl-carousel .owl-nav button.owl-prev,
.owl-carousel .owl-nav button.owl-next,
.owl-carousel button.owl-dot {
  background-color: transparent;
  color: inherit;
  border: none;
  padding: 0 !important;
  font: inherit;
}

.owl-carousel .owl-nav [class*="owl-"] {
  position: absolute;
  width: 11px;
  height: 19px;
  z-index: 10;
  cursor: pointer;
  background-size: auto;
  background-position: center;
  background-repeat: no-repeat;
}

.owl-carousel .owl-nav .owl-prev,
.owl-carousel .owl-nav .owl-next,
.owl-carousel .owl-dot {
  cursor: pointer;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.slide_reserve_date .owl-nav .owl-next,
.slide_reserve_date_wide .owl-nav .owl-next {
  right: -20px;
}

.wrap_reserve
  .section_step_con
  .article.article_time.area__movingbar.litype6
  > .inner
  .tab_wrap.outer {
  width: 500px;
}

.tab_wrap {
  position: relative;
}

.tab_wrap.outer.sml > li > .tab_tit {
  font-size: 13px;
}

.tab_wrap > li > .tab_tit span {
  vertical-align: top;
  white-space: nowrap;
}

.tab_wrap > li > .tab_con.ty5 {
  margin-top: -30px;
}

.wrap_reserve
  .section_step_con
  .article.article_time
  > .inner
  .mCustomScrollbar {
  height: 658px;
  padding: 0 20px;
}

.wrap_reserve .section_step_con .article.article_time > .inner .mCSB_container {
  padding-bottom: 30px;
}

.group_time_select {
  margin-top: 30px;
}

.time_select_tit {
  display: flex;
  align-items: center;
  font-size: 15px;
}

.ic_grade.gr_12 {
  background-color: transparent;
}

.time_select_tit .ic_grade {
  margin-right: 5px;
}

.time_select_tit {
  display: flex;
  align-items: center;
  font-size: 15px;
}

.time_select_tit {
  display: flex;
  align-items: center;
  font-size: 15px;
}

ul,
ol {
  list-style: none;
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

.time_select_wrap ul.list_hall li:first-child {
  margin-left: 0;
  padding-left: 0;
}

.time_select_wrap ul.list_hall li:before {
  content: "";
  position: absolute;
  top: 3px;
  left: 0;
  height: 9px;
  /* border-left: 0px solid #DDD; */
}

.time_select_wrap .txt_infor {
  margin-bottom: 8px;
  color: #666;
}

/* 시간선택 첫번째 버튼의 위치가 틀어져 있어 적용하지 않음 */
/* .time_select_wrap ul.list_time li:nth-child(4n+1) {
    margin-left: 0;
} */

.time_select_wrap ul.list_time li {
  float: left;
  width: 29%;
  margin: 0 0 11px 2%;
}

dl {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

.time_select_wrap ul.list_time li > a dl dt {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}

.time_select_wrap ul.list_time li > a dl dd.time strong {
  display: block;
  font-size: 15px;
  font-weight: bold;
  text-align: center;
}

.time_select_wrap ul.list_time li > a dl dd.seat strong {
  color: #427652;
}

.time_select_wrap ul.list_time li > a dl dd.seat {
  font-family: "Roboto";
  font-size: 11px;
  color: #666;
  margin-top: 4px;
}

body {
  overflow-y: scroll;
}

.time_select_wrap ul.list_time li > a {
  display: block;
  position: relative;
  height: 58px;
  padding: 8px 14px 5px;
  border-radius: 4px;
  border: 1px solid #a1a1a1;
  cursor: pointer;
  background: #f8f8f8;
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

.time_select_wrap ul.list_time li > a dl .tooltip {
  display: none;
  position: absolute;
  left: 50%;
  top: -41px;
  height: 32px;
  line-height: 34px;
  margin-left: -38px;
  padding: 0 8px;
  border-radius: 4px;
  font-family: "Roboto";
  font-size: 13px;
  color: #fff;
  white-space: nowrap;
  background-color: #333;
}

.time_select_wrap ul.list_time li > a dl dd.hall {
  position: absolute;
  right: 15px;
  bottom: 5px;
  font-size: 11px;
}

.time_select_wrap ul.list_time:after {
  content: "";
  display: block;
  clear: both;
}

.movie_select_wrap ul > li.active > a:before {
  content: "선택";
  position: absolute;
  right: 19px;
  top: 18px;
  width: 18px;
  height: 14px;
  text-indent: -9999em;
  background: url("@/assets/images/icon/check.png") no-repeat 0 0;
}

.movie_select_wrap.list ul > li > a {
  padding: 15px 20px;
}

.movie_select_wrap.list ul > li .group_infor .bx_tit .tit {
  display: block;
  overflow: hidden;
  width: 250px;
  padding-left: 30px;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.movie_select_wrap.list ul > li .group_infor dl {
  display: none;
}

.movie_select_wrap ul > li > a:after {
  content: "";
  display: block;
  clear: both;
}

.time_select_wrap ul.list_time li.near > a dl dd.full strong {
  color: #f24a6a;
  font-size: small;
  vertical-align: super;
  margin-right: 60px;
}

/* button  추가함 */
.time_select_wrap ul.list_time li.near > button dl dd.full strong {
  color: #f24a6a;
  font-size: small;
  vertical-align: super;
}

.time_select_wrap ul.list_time li > a .txt_ntc {
  position: absolute;
  width: 1px;
  height: 1px;
  text-indent: -9999em;
}

.movie_select_wrap ul > li {
  box-sizing: border-box;
  border: 2px solid transparent;
}

.movie_select_wrap ul > li > a {
  display: block;
  position: relative;
  box-sizing: border-box;
}

.ic_grade.gr_15:before {
  background: #dd7430 url("@/assets/images/common/grade_15.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_grade.gr_all:before {
  background: #229c56 url("@/assets/images/common/grade_all.png") 0 0 no-repeat;
  background-size: 100%;
}

.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}

/*  seatPopup 영화시간 선택후 팝업되는 페이지의 css */

.layer_wrap.active.layer_reserve01_sum2 {
  width: 500px;
}

.layer_wrap.layer_reserve {
  box-sizing: border-box;
  border-radius: 0;
  border: 1px solid #666;
  background-color: #fff;
}

.layer_wrap.active {
  display: block;
  position: fixed;
  z-index: 100;
  height: auto;
  border-radius: 10px;
  background-color: #fff;
}

.layer_wrap {
  overflow: hidden;
  position: absolute;
  z-index: -1;
  width: 0px;
  height: 0px;
  border: none;
}

.layer_wrap.layer_reserve .layer_header {
  border-bottom: 1px solid #666;
  color: #fff;
  background-color: #666;
}

.layer_wrap .layer_header {
  position: relative;
  height: 54px;
  line-height: 50px;
  border-bottom: 1px solid #ccc;
  color: #fff;
  text-align: center;
}
.layer_wrap .layer_contents {
  padding: 30px 25px;
}

.layer_wrap.layer_reserve .layer_header strong {
  font-size: 18px;
}

.layer_wrap.layer_reserve .layer_header .btn_close {
  background-image: url("@/assets/images/icon/close_19_wht.png");
}

.layer_wrap .layer_header .btn_close {
  position: absolute;
  right: 15px;
  top: 15px;
  width: 19px;
  height: 19px;
  border: none;
  text-indent: -9999em;
  background: transparent url("@/assets/images/icon/close_19_wht.png") no-repeat
    0 0;
  outline-color: black;
}

.seat_infor_sum {
  text-align: center;
}

.btn_btm_wrap a,
.btn_btm_wrap button {
  margin: 0 4px;
  min-width: 160px;
  /* padding: 0 !important; */
}

.seat_infor_sum .group_top dl dt {
  display: inline-block;
  font-size: 16px;
}

.seat_infor_sum .group_top dl dd strong {
  font-family: "Roboto";
  font-size: 22px;
  font-weight: bold;
}

.seat_infor_sum .group_con {
  padding: 20px 0;
}

.screen_preview_box .smallScreen {
  width: 100%;
  height: 140px;
  position: relative;
  top: 0;
  left: 0;
  background: #fff;
  padding: 0;
}

.screen_preview_box .smallScreen .title_screen1 {
  position: relative;
  z-index: 1;
  color: #bdbdbd;
  letter-spacing: 5px;
  background: none;
}

.smallScreen .title_screen1 {
  display: block;
  text-align: center;
}

.screen_preview_box .minimap {
  position: relative;
  top: 50%;
  transform: translate(0, -50%);
}

.screen_preview_box .smallScreen .floor_bx {
  position: relative;
  margin: 10px 0;
  padding: 5px 0;
}

.screen_preview_box .smallScreen .floor_bx .floor_tit {
  background: #fff;
  color: #000;
  font-size: 12px;
  font-family: "Roboto", "Noto Sans KR";
  text-align: right;
}

element.style {
  width: 78.0968px;
  height: 48.9384px;
}

.smallScreen .floor_bx .seat_area {
  position: relative;
  margin: 5px auto 0;
}

element.style {
  left: 0px;
  top: 0px;
}

.smallScreen .floor_bx .seat_area .sel {
  position: absolute;
}

a,
a:link,
a:visited,
a:active,
a:hover {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

/* a태그 선택시 붉은색 변경 */

a:active {
  color: red;
  text-decoration: none;
  cursor: pointer;
}

.screen_preview_box .smallScreen .floor_bx .seat_area .sel > span.noSeat {
  background: rgb(255, 255, 255) none !important;
}

.screen_preview_box .smallScreen .floor_bx .seat_area .sel > span {
  background: #000 none !important;
}

.smallScreen .floor_bx .seat_area .sel > span {
  overflow: hidden;
  display: block;
  width: 4px;
  height: 3px;
  background: #fff;
  line-height: 0;
  font-size: 0;
  text-indent: -7000000em;
  border-radius: 1px 1px 0 0;
}

.txt_notice {
  padding: 18px 0 26px;
  text-align: center;
}

body,
input,
button,
select,
textarea {
  font-family: "Noto Sans KR", "Roboto", "dotum", "sans-serif";
  font-size: 12px;
  color: #000;
}
.dim,
.dim2,
.dimMulti {
  position: fixed;
  top: 0;
  right: 0;
  bottom: -1px;
  left: 0;
  z-index: 98;
  background: rgba(0, 0, 0, 0.5);
  overflow-y: scroll;
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.ic_grade.gr_all:before,
.ic_grade.gr_12:before,
.ic_grade.gr_15:before,
.ic_grade.gr_18:before,
.ic_grade.gr_non:before,
.ic_grade:before {
  content: "";
  display: block;
  width: 22px;
  height: 22px;
  border-radius: 3px;
}
.ic_grade:before {
  margin-left: -1px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}
.ic_grade.gr_all:before {
  font-size: 11px;
}

.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_15:before {
  background: #e98930 url("@/assets/images/common/grade_15.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_18:before {
  background: #e93030 url("@/assets/images/common/grade_18.png") 0 0 no-repeat;
  background-size: 100%;
}

.seat_infor_sum .group_btm .txt_notice.gr_12 strong {
  border-bottom: 1px solid #e9b630;
  color: #e9b630;
}
.seat_infor_sum .group_btm .txt_notice.gr_15 strong {
  border-bottom: 1px solid #e98930;
  color: #e98930;
}
.seat_infor_sum .group_btm .txt_notice.gr_18 strong {
  border-bottom: 1px solid #e93030;
  color: #e93030;
}
.seat_infor_sum .group_btm .txt_notice.gr_all strong {
  border-bottom: 1px solid #229c56;
  color: #229c56;
}
.ic_grade.gr_all {
  background-color: transparent;
}
.ic_grade.gr_12 {
  background-color: transparent;
}
.ic_grade.gr_15 {
  background-color: transparent;
}
.ic_grade.gr_18 {
  background-color: transparent;
}
.seat_infor_sum .group_btm .txt_notice .tit {
  margin-bottom: 5px;
  font-size: 13px;
  color: #111;
}
.ic_grade {
  display: inline-block;
  width: 22px;
  height: 22px;
  border-radius: 50%;
  font-size: 0;
  text-align: center;
  vertical-align: middle;
}
.seat_infor_sum .group_btm .txt_notice .tit .ic_grade {
  margin-right: 5px;
}

.seat_infor_sum .group_btm .txt_notice .txt {
  line-height: 1.4;
  font-size: 11px;
  color: #666;
  letter-spacing: -0.5px;
}

.layer_wrap .btn_btm_wrap {
  margin: 30px 0 20px;
}

.btn_btm_wrap {
  margin: 60px 0;
  text-align: center;
}

.btn_col1.ty5,
.btn_col2.ty5,
.btn_col3.ty5,
.btn_col4.ty5,
.btn_col5.ty5,
.btn_col6.ty5,
.btn_col7.ty5,
.btn_col8.ty5,
.btn_col9.ty5,
.btn_col10.ty5 {
  height: 40px;
  line-height: 38px;
  padding: 0 18px;
}

.btn_col2 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #414141;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #414141;
}

.btn_col1 {
  display: inline-block;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ff243e;
  font-size: 14px;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}

.screen_preview_box .smallScreen .floor_bx .seat_area .sel > span.completed {
  background: #c2c2c2 !important;
}

/*  SeatReservation css  적용 부분 */

.wrap_reserve .section_step_tit ul li > a > strong {
  display: block;
  line-height: 1.5;
  padding-top: 80px;
  font-size: 13px;
  text-align: center;
}

h1,
h2,
h3,
h4,
h5,
h6,
address,
caption,
em,
th {
  font-style: normal;
  font-weight: normal;
}
body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}

.wrap_reserve .section_step_tit ul {
  height: 100%;
}
.wrap_reserve .section_step_tit ul li.prev {
  border-color: #333;
  border-bottom-color: #666;
  background-color: #333;
}
.wrap_reserve .section_step_tit ul li {
  position: relative;
  height: 25%;
  box-sizing: border-box;
  border: 1px solid #666;
  border-top: none;
  background-color: #fff;
  z-index: 10;
}
.wrap_reserve .section_step_tit ul li.prev > a {
  color: #fff;
}
.wrap_reserve .section_step_tit ul li > a .bx_con {
  display: none;
  position: absolute;
  z-index: 1;
  left: 77px;
  top: -1px;
  bottom: 0;
  width: 172px;
  padding-left: 20px;
  border-top: 1px solid #666;
  background-color: #333;
}
.wrap_reserve .section_step_tit ul li.active {
  border-color: #ff243e;
  border-bottom-color: #666;
  background-color: #ff243e;
}
.wrap_reserve .section_step_tit ul li.active > a:before {
  content: "현재 단계";
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
  text-indent: -9999em;
}
.wrap_reserve:after {
  content: "";
  display: block;
  clear: both;
}
.wrap_reserve .section_step_con.active {
  display: block;
  overflow: inherit;
  position: relative;
  width: 1202px;
  height: 100%;
  margin-left: 78px;
}
h3 {
  display: block;
  font-size: 1.17em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.wrap_reserve .section_step_con .article.article_seat {
  width: 1202px;
}

.wrap_reserve .section_step_con .article .group_top .txt {
  position: absolute;
  top: 5px;
  right: 30px;
  font-size: 13px;
  color: #fff;
}
p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.wrap_reserve .section_step_con .article .group_top .txt:before {
  content: "";
  display: inline-block;
  width: 3px;
  height: 3px;
  margin-right: 5px;
  border-radius: 50%;
  vertical-align: middle;
  background: #666;
}
.wrap_reserve .section_step_con .article.article_seat > .inner {
  overflow: hidden;
  height: 815px;
}
.select_num_people_wrap {
  overflow: hidden;
  height: 118px;
  padding: 0 20px;
  background-color: #fff;
}
.select_num_people_wrap .movie_infor {
  position: relative;
  float: left;
  width: 270px;
  padding: 16px 0 0 54px;
}
body,
textarea {
  line-height: 1.1;
  letter-spacing: -0.03em;
}
h6 {
  display: block;
  font-size: 0.67em;
  margin-block-start: 2.33em;
  margin-block-end: 2.33em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.select_num_people_wrap .movie_infor .thm {
  display: block;
  position: absolute;
  top: 26px;
  left: 0;
  overflow: hidden;
  width: 46px;
  height: 65px;
  border-radius: 4px;
}
.select_num_people_wrap .movie_infor .thm img {
  width: 100%;
}

div {
  display: block;
}

.select_num_people_wrap .movie_infor .group_infor .bx_tit .ic_grade {
  margin: -5px 4px 0 0;
}
.ic_grade.gr_12 {
  background-color: transparent;
}
.ic_grade {
  display: inline-block;
  width: 22px;
  height: 22px;
  line-height: 22px;
  border-radius: 50%;
  font-size: 0;
  text-align: center;
  vertical-align: middle;
}
.ic_grade.gr_12:before {
  background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
  background-size: 100%;
}
.ic_grade.gr_all:before,
.ic_grade.gr_12:before,
.ic_grade.gr_15:before,
.ic_grade.gr_18:before,
.ic_grade.gr_non:before,
.ic_grade:before {
  content: "";
  display: block;
  width: 22px;
  height: 22px;
  border-radius: 3px;
}
.ic_grade:before {
  margin-left: -1px;
  font-size: 13px;
  font-weight: bold;
  color: #fff;
  white-space: nowrap;
}
.select_num_people_wrap .movie_infor .group_infor .bx_tit strong {
  font-size: 17px;
}
strong {
  font-weight: bold;
}
.select_num_people_wrap .movie_infor .group_infor .bx_tit {
  display: block;
  margin-bottom: 5px;
}
.select_num_people_wrap .movie_infor .group_infor dl {
  position: relative;
}
.select_num_people_wrap .movie_infor .group_infor dl dt {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 5px;
  font-size: 11px;
}
body,
input,
button,
select,
textarea {
  font-family: "Noto Sans KR", "Roboto", "dotum", "sans-serif";
  font-size: 12px;
  color: #000;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 .time:before {
  content: "";
  display: block;
  position: absolute;
  top: 3px;
  left: 0;
  width: 1px;
  height: 10px;
  background: #ddd;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 em {
  font-size: 10px;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 .time {
  position: relative;
  padding-left: 9px;
  margin-left: 10px;
}

.select_num_people_wrap .movie_infor .group_infor dl dt {
  overflow: hidden;
  position: absolute !important;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.select_num_people_wrap .count_people {
  float: right;
  width: 825px;
  margin-top: 36px;
}
.select_num_people_wrap .count_people > ul > li:first-child {
  margin-left: 0;
}
.select_num_people_wrap .count_people > ul > li {
  float: left;
  margin-left: 20px;
}
.select_num_people_wrap .count_people > ul > li .tit {
  margin-right: 8px;
  /* font-size: 13px; */
}
strong {
  font-weight: bold;
}
.select_num_people_wrap .count_people > ul > li .bx_num {
  display: inline-block;
  width: 120px;
}
.bx_num {
  position: relative;
  border: 1px solid #ddd;
  background-color: #fff;
  box-sizing: border-box;
}
.bx_num .btn_mins {
  left: 0;
}
.bx_num .btn_plus,
.bx_num .btn_mins {
  position: absolute;
  top: 0;
  bottom: 0;
  background: no-repeat 50% 50%;
  border: 0 none;
  padding: 0 15px;
  text-indent: -9999em;
  font: 0 auto;
}

button {
  cursor: pointer;
}
input,
select,
button {
  vertical-align: middle;
  appearance: none;
}
button {
  writing-mode: horizontal-tb !important;
  text-rendering: auto;
  letter-spacing: normal;
  word-spacing: normal;
  text-transform: none;
  text-shadow: none;
  display: inline-block;
  text-align: center;
  align-items: flex-start;
  box-sizing: border-box;
  margin: 0em;
}
.bx_num .txt_num {
  padding: 11px 0;
  font-family: "Roboto";
  font-size: 15px;
  font-weight: bold;
  color: #000;
  text-align: center;
  vertical-align: middle;
}
.bx_num .btn_plus {
  right: 0;
  background-image: url("@/assets/images/icon/plus_12.png");
  padding-right: 30px;
}
.select_num_people_wrap .count_people > ul:after {
  content: "";
  display: block;
  clear: both;
}
.select_seat_wrap .top_txt_info {
  margin: 15px 0 25px;
  text-align: center;
}

.mseat_inner .floor_bx .seat_area .sel.completed {
  cursor: inherit;
}

.mseat_inner .floor_bx .seat_area .sel {
  position: absolute;
  font-size: 10px;
  outline-color: white;
}

/* 좌석이 비어있는 예약 가능할 때 O */
.mseat_inner .floor_bx .seat_area .sel > .onSeat {
  position: relative;
  display: block;
  overflow: hidden;
  width: 21px;
  height: 16px;
  /* line-height: 17px; */
  background: #e8e8e8;
  color: #000;
  font-size: 10px;
  text-align: center;
  letter-spacing: -0.5px;
  box-sizing: border-box;
  border-radius: 7px 7px 2px 0px;
  font-family: "Roboto", "Noto Sans KR";
}

/* TODO: 좌석 배열 마추고 나중에 삭제 해야함 */
/* .mseat_inner .floor_bx .seat_area .sel > span {
  position: relative;
  display: block;
  overflow: hidden;
  width: 21px;
  height: 16px;
  background: #e8e8e8;
  color: #000;
  font-size: 10px;
  text-align: center;
  letter-spacing: -0.5px;
  box-sizing: border-box;
  border-radius: 7px 7px 2px 0px;
  font-family: "Roboto", "Noto Sans KR";
} */

/* 좌석 빈공간 통로 A */
.mseat_inner .floor_bx .seat_area .sel > .blankSeat {
  position: relative;
  display: block;
  overflow: hidden;
  width: 21px;
  height: 16px;
  /* line-height: 17px; */
  background: #000;
  color: #000;
  font-size: 10px;
  text-align: center;
  letter-spacing: -0.5px;
  box-sizing: border-box;
  border-radius: 7px 7px 2px 0px;
  font-family: "Roboto", "Noto Sans KR";
  cursor: inherit;
  /* z-index: 1; */
}

/* 좌석 선택되면 적용 X */
.mseat_inner .floor_bx .seat_area .sel > .noSeat {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 21px;
  height: 16px;
  background: #fff;
  opacity: 0.5;
  text-align: center;
  box-sizing: border-box;
  border-radius: 7px 7px 2px 0px;
  cursor: inherit;
  /* z-index: 1; */
}

.btn_txt_resetA {
  position: absolute;
  height: 28px;
  right: 150px;
  box-sizing: border-box;
  padding: 0 10px 0 25px;
  top: 100px;
  border-radius: 28px;
  border: 1px solid #bebebe;
  font-size: 11px;
  color: #000;
  font-family: "Roboto", "Noto Sans KR";
  background: #fff url("@/assets/images/icon/reset.png") no-repeat 12px 51%;
}

.btn_txt_reset {
  height: 28px;
  line-height: 28px;
  box-sizing: border-box;
  padding: 0 10px 0 25px;
  border-radius: 28px;
  border: 1px solid #bebebe;
  font-size: 11px;
  color: #000;
  background: #fff url("@/assets/images/icon/reset.png") no-repeat 12px 51%;
}

.mseat_inner .floor_bx .seat_area .sel > .sweet_spot {
  min-height: 14px;
  /* border: 1px solid #d41017; */
  line-height: 14px;
  /* background-color: #fff; */
}
.mseat_inner .floor_bx .seat_area .sel.on > .sweet_spot {
  border: 0 none;
  line-height: 17px;
}
.mseat_inner .floor_bx .seat_area .sel.no_select:not(.disabled):not(.on) span {
  overflow: hidden;
  text-indent: -7000000em;
  font-size: 0;
  line-height: 0;
  cursor: inherit;
  background: #e8e8e8 url(@/assets/images/common/seat_no_select.png) no-repeat
    center center;
  background-size: 28px 21px;
  opacity: 0.5;
}

.select_seat_wrap .top_txt_info p {
  position: relative;
  display: inline-block;
  padding-left: 9px;
  color: #fff;
  font-size: 11px;
}
ul,
ol {
  list-style: none;
}

.select_seat_wrap .top_txt_info p:before {
  content: "";
  display: block;
  position: absolute;
  top: 6px;
  left: 0;
  width: 3px;
  height: 1px;
  background: #fff;
}
.seat_wrap {
  padding-top: 0 !important;
}
.select_seat_wrap {
  overflow: hidden;
  height: 638px;
  color: #fff;
  background-color: #000;
}
.mseat_wrap {
  position: relative;
  background: #000;
  /* padding-top: 150px; */
}
article {
  display: block;
}
.mseat_inner {
  overflow: hidden;
  width: 1200px;
  height: 470px !important;
  max-height: 470px;
  margin: 0 auto;
  box-sizing: border-box;
  padding: 0 15px;
}
.mCustomScrollBox {
  position: relative;
  overflow: hidden;
  height: 100%;
  max-width: 100%;
  outline: none;
  direction: ltr;
}
.mCSB_container.mCS_no_scrollbar_y.mCS_y_hidden {
  margin-right: 0;
}
.meat_door_pos {
  position: relative;
}
.mseat_hidden {
  overflow: hidden;
  margin: 0 auto;
}
.mseat_inner .title_screen1 {
  display: block;
  padding: 5px 0 5px;
  color: #fff;
  font-size: 16px;
  font-family: "Roboto", "Noto Sans KR";
  text-align: center;
  letter-spacing: 15px;
  margin: 0 0 30px;
  background: #6e6e6e;
}
.mseat_inner .floor_bx {
  position: relative;
  padding: 0 0 35px;
  box-sizing: border-box;
}
.mseat_inner .floor_bx .floor_tit {
  display: block;
  /* padding: 5px 0; */
  text-align: right;
  color: #fff;
  font-size: 17px;
  font-weight: bold;
  font-family: "Roboto", "Noto Sans KR";
}
.mseat_inner .floor_bx .seat_area {
  position: relative;
  margin: 10px auto 0;
}
element.style {
  margin-top: 30px;
  width: 581px;
  height: 298.208px;
}
element.style {
  left: -30px;
  top: 0px;
}
.seat_tit {
  position: absolute;
  display: inline-block;
  width: 20px;
  height: 16px;
  line-height: 16px;
  text-align: left;
  font-size: 11px;
  font-weight: bold;
  font-family: "Roboto", "Noto Sans KR";
  color: #fff;
}
.mseat_inner .floor_bx .seat_area .sel.no_select {
  width: 21px;
  height: 16px;
  background: url(@/assets/images/common/seat_no_select.png) no-repeat center
    center;
  cursor: inherit;
}

.mseat_inner .floor_bx .seat_area .sel.no_select:not(.disabled):not(.on) span {
  overflow: hidden;
  text-indent: -7000000em;
  font-size: 0;
  line-height: 0;
  cursor: inherit;
  background: #e8e8e8 url(@/assets/images/common/seat_no_select.png) no-repeat
    center center;
  background-size: 28px 21px;
  opacity: 0.5;
}

.mseat_inner .floor_bx .seat_area .sel > .handicapped {
  color: #fff;
}
.mseat_inner
  .floor_bx
  .seat_area
  .sel.no_select:not(.disabled):not(.on)
  span:before {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 21px;
  height: 16px;
  background: #000;
  opacity: 0.5;
}

/* .mseat_inner .floor_bx .seat_area .sel > .foot_seat:after {
  content: "";
  display: block;
  position: absolute;
  top: 3px;
  left: 50%;
  width: 9px;
  height: 1px;
  background: #a1a1a1;
  -webkit-transform: translate(-50%, 0);
  transform: translate(-50%, 0);
} */

.mseat_inner .floor_bx .seat_area .sel.on span {
  background: #ff243e;
  color: #fff;
}
.w_center {
  position: absolute;
  top: 0;
  left: 300px;
  background: url(@/assets/images/common/bg_window_top.png) no-repeat center
    center;
  background-size: 28px 4.5px;
  display: block;
  width: 28px;
  height: 4.5px;
  text-indent: -9999em;
}
.mCSB_outside + .mCS-minimal.mCSB_scrollTools_vertical,
.mCSB_outside + .mCS-minimal-dark.mCSB_scrollTools_vertical {
  right: 0;
  margin: 12px 0;
}

.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  position: relative;
  width: 4px;
  height: 100%;
  margin: 0 auto;
  border-radius: 16px;
  text-align: center;
}
.seat_btm_box {
  overflow: hidden;
  margin: -20px 0 0 35px;
}
.seat_btm_box .seat_type_box {
  width: 775px;
}
.seat_btm_box .seat_type_box {
  float: left;
}
.seat_btm_box .seat_type_box .top_info {
  margin-bottom: 15px;
}
.seat_btm_box .seat_type_box span.seat_type1 {
  background: url("@/assets/images/icon/ic_seat_type1.png") no-repeat 0 4px;
}
/*  좌석 디자인  */
.seat_type1 {
  background: url("@/assets/images/icon/ic_seat_type1.png") no-repeat 0 4px;
}
.seat_type2 {
  background: url("@/assets/images/icon/ic_seat_type2.png") no-repeat 0 4px;
}

.seat_btm_box .seat_type_box span {
  padding-left: 17px;
  margin-left: 6px;
}
.seat_btm_box .seat_type_box span.seat_type2 {
  background: url("@/assets/images/icon/ic_seat_type2.png") no-repeat 0 4px;
}

.seat_btm_box .seat_type_box span.seat_type3 {
  background: url("@/assets/images/icon/ic_seat_type3.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type4 {
  background: url("@/assets/images/icon/ic_seat_type4.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type12 {
  background: url("@/assets/images/icon/ic_seat_type12_distance.png") no-repeat
    0 4px;
}
.seat_btm_box .seat_type_box span.seat_type5 {
  background: url("@/assets/images/icon/ic_seat_type5.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type6 {
  background: url("@/assets/images/icon/ic_seat_type6.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type7 {
  background: url("@/assets/images/icon/ic_seat_type7.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type8 {
  background: url("@/assets/images/icon/ic_seat_type8.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type9 {
  background: url("@/assets/images/icon/ic_seat_type9.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type10 {
  background: url("@/assets/images/icon/ic_seat_type10.png") no-repeat 0 4px;
}
.seat_btm_box .seat_type_box span.seat_type11 {
  background: url("@/assets/images/icon/ic_seat_type11.png") no-repeat 0 6px;
}
.seat_btm_box .notice_box {
  float: left;
}
.seat_btm_box .notice_box .tit_info {
  font-size: 15px;
}
.list_txt {
  font-size: 13px;
}
.seat_btm_box .list_txt.ty3 li {
  margin-top: 7px;
  color: #fff;
  font-size: 11px;
}
.select_seat_result {
  overflow: hidden;
  height: 60px;
  color: #fff;
  background-color: #888;
}
.select_seat_result .group_lft {
  float: left;
}
.select_seat_result dl {
  padding: 16px 0 0 30px;
}
dl.total_price dt {
  float: left;
  padding: 6px 12px 0 0;
  font-size: 15px;
}
dl.total_price dd {
  float: left;
  font-size: 15px;
}
dl.total_price dd strong {
  font-family: "Roboto";
  font-size: 25px;
  font-weight: bold;
}
dl.total_price:after {
  content: "";
  display: block;
  clear: both;
}
.select_seat_result .group_rgt {
  float: right;
}
.select_seat_result a {
  float: left;
  width: 180px;
  height: 60px;
  line-height: 60px;
  border-radius: 0;
  border: none;
  font-size: 15px;
}
.btn_col1 {
  display: inline-block;
  box-sizing: border-box;
  color: #ffffff !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ff243e;
}

.btn_col3 {
  display: inline-block;
  box-sizing: border-box;
  color: #000000 !important;
  text-align: center;
  vertical-align: middle;
  background-color: #ffffff;
}
.ico_lpay {
  display: inline-block;
  background: url("@/assets/images/icon/txt_lpay_48_20210407.png") no-repeat 50%
    50%;
  width: 48px;
  height: 16px;
  margin-right: 5px;
  text-indent: -9999em;
  font-size: 0;
  vertical-align: middle;
}
.wrap_reserve .section_step_con:after {
  content: "";
  display: block;
  clear: both;
}
div {
  display: block;
}

/* 결제 페이지 css */

.article_sum_infor .movie_infor .tit .ic_grade {
  margin-top: -3px;
}

.mCS-minimal.mCSB_scrollTools .mCSB_draggerRail,
.mCS-minimal-dark.mCSB_scrollTools .mCSB_draggerRail {
  background-color: transparent;
}

.mCSB_outside + .mCS-minimal.mCSB_scrollTools_vertical,
.mCSB_outside + .mCS-minimal-dark.mCSB_scrollTools_vertical {
  right: 0;
  margin: 12px 0;
}

.payment_sum_wrap {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #414141;
}

.payment_sum_wrap .btn_confirm {
  display: block;
  border-radius: 0;
  height: 64px;
  line-height: 64px;
  font-size: 17px;
}

.payment_sum_wrap dl dd {
  float: right;
  clear: right;
  line-height: 25px;
  font-size: 13px;
  color: #fff;
}

.payment_sum_wrap dl dd strong {
  display: inline-block;
  line-height: 25px;
  margin: 0 4px 0 8px;
  font-family: "Roboto";
  font-size: 17px;
  font-weight: bold;
  vertical-align: bottom;
}

.payment_sum_wrap dl dt {
  clear: both;
  float: left;
  line-height: 25px;
  padding: 0px 0;
  font-size: 13px;
  color: #fff;
}

.payment_sum_wrap dl:first-child {
  border-top: none;
}

.payment_sum_wrap dl {
  padding: 25px 30px;
  border-top: 1px solid rgba(255, 255, 255, 0.2);
}

.benifit_infor_wrap dd {
  float: left;
  width: calc(100% - 95px);
  line-height: 1.3;
  font-size: 11px;
  color: #666;
}

.benifit_infor_wrap dd strong {
  color: #000;
}

.benifit_infor_wrap dt {
  float: left;
  width: 95px;
  font-size: 11px;
}

.benifit_infor_wrap dt img {
  max-width: 100%;
}

.benifit_infor_wrap dt img {
  vertical-align: top;
}

.benifit_infor_wrap dt {
  padding-right: 5px;
  box-sizing: border-box;
}

.benifit_infor_wrap dl {
  position: relative;
  margin-top: 6px;
}

.benifit_infor_wrap .mCustomScrollbar {
  height: 170px;
  padding-right: 18px;
}

.benifit_infor_wrap {
  padding: 30px;
}

input[type="radio"] + label:before {
  content: "";
  display: inline-block;
  width: 18px;
  height: 18px;
  margin-right: 5px;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 50%;
  vertical-align: -5px;
}

input[type="radio"]:checked + label:before {
  border-color: #000;
  background: #fff url("@/assets/images/icon/rad_12.png") no-repeat center
    center;
}

.article_payment .select_item_wrap .group_item .bx_item .area_rdo {
  position: absolute;
  right: 30px;
  top: 15px;
}

.article_payment .select_item_wrap .group_item .bx_item .area_rdo span {
  margin-left: 10px;
}

input[type="checkbox"] + label:before {
  content: "";
  display: inline-block;
  border: 1px solid #ddd;
  width: 18px;
  height: 18px;
  margin-right: 5px;
}

input[type="checkbox"] + label.ty2:before {
  border-radius: 50%;
  vertical-align: -5px;
}

input[type="checkbox"]:checked + label.ty2:before {
  border-color: #000;
  background: #000 url("@/assets/images/icon/chk_8_3.png") no-repeat center 6px;
}

input[type="radio"] + label,
input[type="checkbox"] + label {
  position: relative;
  vertical-align: middle;
}

input[type="radio"] + label:last-of-type,
input[type="checkbox"] + label:last-of-type {
  margin-right: 0px;
}

.article_payment
  .select_item_wrap
  .group_item
  .bx_item
  input[type="checkbox"]
  + label {
  display: block;
  float: left;
  width: auto;
  font-family: "Roboto";
  font-size: 13px;
}
.article_payment .select_item_wrap .group_item .bx_item input[type="checkbox"] {
  position: absolute;
}

.article_payment .select_item_wrap .group_item {
  padding: 10px 0 25px 0;
  border-bottom: 1px solid #ddd;
  background-color: #fff;
}

.article_payment .select_item_wrap .group_item .bx_item {
  position: relative;
  padding: 15px 30px 10px;
}

.group_payment .bx_cate .list_pay_item li button {
  overflow: visible;
  position: relative;
  height: 68px;
  line-height: 95px;
  background-repeat: no-repeat;
}

.group_payment .bx_cate .list_pay_item li button.cate1 {
  background-image: url("@/assets/images/payment/method_ic_card.png");
  background-position: center 17px;
}

.group_payment .bx_cate .list_pay_item li button.cate2 {
  background-image: url("@/assets/images/payment/method_ic_lpay_20210407.png");
  background-position: center 17px;
}
.group_payment .bx_cate .list_pay_item li button.cate3 {
  background-image: url("@/assets/images/payment/method_ic_simple.png");
  background-position: center 17px;
}

.group_payment .bx_cate .list_pay_item li button.cate5 {
  background-image: url("@/assets/images/payment/method_ic_deposit.svg");
  background-position: center 14px;
}

.group_payment .bx_cate .list_pay_item li button.cate4 {
  background-image: url("@/assets/images/payment/method_ic_phone.png");
  background-position: center 14px;
}

.wrap_reserve .section_step_con .article.article_payment {
  width: 414px;
}

.list_pay_item li {
  float: left;
  margin: 0 1% 10px;
}

.list_pay_item.cate6 li {
  position: relative;
  width: 31.3%;
}

.article_pay_method .tit_payment {
  display: block;
  margin: 18px 0 16px;
  font-size: 15px;
}

.group_payment {
  padding: 15px 30px;
}

.toggle_wrap.couplechk {
  margin: 10px 0 20px 0;
  border: 0;
  border-top: 1px solid #ddd;
}

.list_pay_item:after {
  content: "";
  display: block;
  clear: both;
}

.group_discount .wrap_coupon .bx_cate .list_pay_item li button {
  overflow: visible;
  position: relative;
  height: 55px;
  line-height: 55px;
  font-size: 11px;
}

.group_discount .wrap_coupon .bx_cate .list_pay_item .txt_cnt {
  position: absolute;
  right: 4px;
  top: 4px;
  width: 15px;
  height: 15px;
  line-height: 15px;
  border-radius: 15px;
  font-family: "Roboto";
  font-size: 10px;
  font-weight: bold;
  color: #fff;
  text-align: center;
  background-color: #000;
}

.list_pay_item li button {
  border: 1px solid #ddd;
  width: 100%;
  border-radius: 4px;
  background-color: #fff;
}

.list_pay_item.cate4 li {
  position: relative;
  width: 23%;
}

input,
select,
button {
  vertical-align: middle;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}

.tab_wrap3 .tab_tit li button {
  display: block;
  position: relative;
  width: 100%;
  height: 40px;
  line-height: 40px;
  border: none;
  border-bottom: 1px solid #ddd;
  border-left: 1px solid #ddd;
  font-family: "Roboto";
  font-size: 14px;
}

.tab_wrap3.reset.selected .tab_tit li button {
  color: #000;
  background-color: #fff;
}

.tab_wrap3.reset .tab_tit li button {
  border-bottom-width: 0;
}

.list_pay_item {
  margin: 0 -1%;
}

.tab_wrap3 .tab_tit li:first-child button {
  border-radius: 4px 0 0 0;
  border-left: none;
}
.tab_wrap3 .tab_tit li button.active {
  border-bottom-width: 0;
  color: #000;
  background-color: #fff;
}

.tab_wrap3 .tab_tit li button .point {
  color: #2aa3fe;
}

.tab_wrap3 .tab_tit li {
  flex: 2;
}

.tab_wrap3 .tab_tit ul {
  display: flex;
}

.bx_cate {
  width: auto;
  margin: auto;
}
.tab_wrap3 {
  margin: 10px 0 10px;
  border-radius: 4px;
  border: 1px solid #ddd;
  background-color: #fff;
}

.tab_wrap3 .tab_tit {
  margin-bottom: -1px;
}

.tab_wrap3.reset {
  overflow: hidden;
}

.tab_wrap3.reset.selected {
  border: 1px solid #000;
}

.group_discount {
  min-height: 365px;
  padding: 15px 30px;
  border-bottom: 1px solid #eee;
}

.group_discount {
  padding: 0 18px;
  border-top: 5px solid #f8f8f8;
}

.wrap_reserve
  .section_step_con
  .article.article_pay_method
  > .inner
  .mCustomScrollbar {
  height: 815px;
}

.wrap_reserve .section_step_con .article.article_pay_method > .inner {
  overflow: hidden;
  height: 815px;
  border-right: 1px solid #ddd;
}

h1,
h2,
h3,
h4,
h5,
h6,
address,
caption,
em,
th {
  font-style: normal;
  font-weight: normal;
}

body,
h1,
h2,
h3,
h4,
h5,
h6,
fieldset,
ul,
ol,
dl,
dt,
dd,
p,
figure {
  margin: 0;
  padding: 0;
}

.wrap_reserve .section_step_title {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  padding-left: -10px;
  margin-left: -40px;
  width: 120px;
}

.wrap_reserve .section_step_title ul li.active {
  border-color: #ff243e;
  border-bottom-color: #666;
  background-color: #ff243e;
}
.wrap_reserve .section_step_title ul li.active > a:before {
  content: "현재 단계";
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
  text-indent: -9999em;
}
.wrap_reserve .section_step_title ul li.active > a > strong {
  font-size: 15px;
}
.wrap_reserve .c ul li.active > a .bx_con {
  background-color: #ff243e;
}

.wrap_reserve .section_step_title ul li.active > a {
  color: #fff;
}
.wrap_reserve .section_step_title ul {
  height: 100%;
}
ul,
ol {
  list-style: none;
}
.wrap_reserve .section_step_title ul li.prev {
  border-color: #333;
  border-bottom-color: #666;
  background-color: #333;
}
.wrap_reserve .section_step_title ul li.prev > a {
  color: #fff;
}
.wrap_reserve .section_step_title ul li > a {
  display: block;
  height: 100%;
  color: #666;
}
.wrap_reserve .section_step_title ul li > a > strong {
  display: block;
  line-height: 1.5;
  padding-top: 80px;
  padding-left: 40px;
  font-size: 13px;
  text-align: center;
}
.wrap_reserve .section_step_tit ul li > a > strong span {
  font-family: "Roboto";
  font-size: 13px;
}
.wrap_reserve .section_step_title ul li > a .bx_con {
  display: none;
  position: absolute;
  z-index: 1;
  left: 77px;
  top: -1px;
  bottom: 0;
  width: 172px;
  padding-left: 20px;
  border-top: 1px solid #666;
  background-color: #333;
}
.wrap_reserve .section_step_title ul li > a .bx_con dl {
  margin-top: 52px;
}
.wrap_reserve .section_step_title ul li > a .bx_con dl dt {
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
  clip: rect(1px, 1px, 1px, 1px);
}
.wrap_reserve .section_step_title ul li > a .bx_con dl dd {
  margin-bottom: 17px;
}

.wrap_reserve .section_step_title ul li {
  position: relative;
  height: 25%;
  box-sizing: border-box;
  border: 1px solid #666;
  border-top: none;
  background-color: #fff;
  z-index: 10;
}

.wrap_reserve .section_step_con .article.article_sum_infor {
  width: 301px;
}

.wrap_reserve .section_step_con .article.article_sum_infor > .inner {
  overflow: hidden;
  height: 815px;
  border-right: 1px solid #ddd;
}

.article_sum_infor .movie_infor {
  padding: 30px 30px 18px;
  border-bottom: 1px solid #ddd;
}

.article_sum_infor .movie_infor.new2020 .thm {
  margin-bottom: 20px;
  text-align: center;
}

.article_sum_infor .movie_infor .thm {
  display: block;
  overflow: hidden;
  border-radius: 4px;
}

.article_sum_infor .movie_infor.new2020 .thm img {
  width: 120px;
}

.article_sum_infor .movie_infor .tit {
  display: block;
  margin-bottom: 10px;
  font-size: 17px;
}

.article_sum_infor dl.dlist_infor dd {
  float: left;
  margin-bottom: 7px;
  font-size: 11px;
  width: 200px;
  word-break: keep-all;
}

.article_sum_infor dl.dlist_infor dd strong {
  font-size: 11px;
}

.article_sum_infor dl.dlist_infor dt {
  clear: both;
  float: left;
  min-width: 40px;
  margin-bottom: 7px;
  font-size: 11px;
}

.article_sum_infor dl.dlist_infor:after {
  content: "";
  display: block;
  clear: both;
}
.article_sum_infor .seat_infor {
  padding: 24px 30px 15px;
  border-bottom: 1px solid #ddd;
}

.article_sum_infor .addprodlistwrap {
  position: relative;
  padding: 25px 20px 25px 30px;
}

.article_sum_infor .addprodlistwrap .tit {
  padding-bottom: 5px;
  font-size: 13px;
  line-height: 18px;
}

.addprodlistwrap .mCustomScrollbar {
  width: 250px;
  height: 360px;
  padding-right: 18px;
}

.addprodlistwrap .addprodlist {
  width: 240px;
}

.addprodlistwrap .addprodlist li {
  position: relative;
}

.addprodlistwrap .addprodlist .pay_movie_infor {
  display: flex;
  position: relative;
  min-height: 80px;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
  background-color: #fff;
  box-sizing: border-box;
}

.addprodlistwrap .pay_movie_infor .img_product {
  min-height: 68px;
  margin-right: 10px;
  line-height: 68px;
}

.addprodlistwrap .pay_movie_infor .img_product .thm img {
  width: 100%;
  height: 100%;
}

.addprodlistwrap .pay_movie_infor .img_product .thm {
  display: inline-block;
  overflow: hidden;
  width: 72px;
  height: 58px;
  vertical-align: middle;
}

.addprodlistwrap .pay_movie_infor .img_product + .bx_infor {
  display: flex;
  flex: 1;
  flex-flow: column;
  justify-content: center;
}

.addprodlistwrap .pay_movie_infor .bx_infor .badge_wrap {
  display: inline-block;
  padding-bottom: 3px;
}

.addprodlistwrap .pay_movie_infor .bx_infor .between_txt {
  display: initial;
  font-size: 11px;
  text-align: right;
}
.addprodlistwrap .pay_movie_infor .bx_infor .between_txt .pay,
.addprodlistwrap .pay_movie_infor .bx_infor .between_txt .num {
  font-family: "Roboto";
  font-size: 17px;
  font-weight: normal;
}

.wrap_reserve .section_step_con .article.article_pay_method {
  width: 487px;
  background-color: #f8f8f8;
}

.wrap_reserve .section_step_con .article .group_top .btn_txt_reset {
  position: absolute;
  right: 32px;
  top: 14px;
}

.list_pay_item li .active {
  border-color: #000;
}

/*   팝업  */

.layer_wrap.active.layer_alert {
    width: 380px;
}


.layer_wrap200 {
    z-index: 200 !important;
}

.bx_alert1 {
    padding: 30px 0;
}
.txt_para {
    line-height: 1.4;
    text-align: center;
}

.txt_para.ty2 {
    font-size: 16px;
}
.layer_wrap .layer_footer {
    clear: both;
    border-top: 1px solid #DDD;
    text-align: center;
    background-color: #F8F8F8;
}
.layer_wrap .layer_footer.ty2 ul li {
    width: 100%;
}
.layer_wrap .layer_footer ul li {
    position: relative;
    float: left;
    width: 50%;
}
.layer_wrap .layer_footer ul li a, .layer_wrap .layer_footer ul li button {
    display: block;
    width: 100%;
    height: 50px;
    line-height: 50px;
    border: none;
    font-size: 16px;
    background-color: #F8F8F8;
}

.dim200 {
    position: fixed;
    top: 0;
    right: 0;
    bottom: -1px;
    left: 0;
    z-index: 199;
    background: rgba(0, 0, 0, 0.5);
    overflow-y: scroll;
}

.seat_locationA {
  width: 581px;
}
.seat_locationB {
  width: 881px;
}
.miniseat_locationA {
  width: 166.851px;
}
.miniseat_locationB {
  width: 250px;
}

.door_locationA {
  left:336.5px;
}
.door_locationB {
  left:184.5px;
}
</style>
