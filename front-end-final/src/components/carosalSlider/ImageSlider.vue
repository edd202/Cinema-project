<template>
  <div ref="root" class="slider">
    <div v-for="s of slideImg" :key="s.id">
      <div class="container">
        <img :src="s.img"  class="imgItem"/>
      </div>
    </div>
  </div>
</template>

<script>
import { tns } from "@/../node_modules/tiny-slider/src/tiny-slider";
import initCustom from "@/assets/js/custom";

export default {
  name: "AppSlider",
  data() {
    return {
      slider: null,
    };
  },
  props: {
    slideImg: Array,
  },
  mounted() {
    this.slider = tns({
      container: this.$refs.root,
      items: 1,
      slideBy: "page",
      loop: false,
      autoplay: false,
      rewind: false,
    }),
    initCustom();
  },
};
</script>

<style lang="scss">
.imgItem {
  height: 456px;
  width: auto;
  display: block;
  margin: 0 auto;
}
.slider .tns-item .slider_text {
  opacity: 0;
  transform: translateY(-150px);
  transition: 0.5s;
  transition-delay: 0.1s;
}

.slider .tns-item {
  position: relative;
}

.slider .tns-slide-active .slider_text {
  transform: translateY(0px);
  opacity: 1;
}

.slider .slider_text {
  position: absolute;
  top: 20px;
  left: 20px;
  color: #333;
  font-weight: bold;
  font-family: Verdana;
  text-transform: uppercase;
}

.tns-controls {
  button {
    position: absolute;
    top: 50%;
    border: none;
    width: 30px;
    height: 30px;
    background: #333;
    border-radius: 50%;
    font-size: 0;
    outline: none;
    margin-top: -15px;

    &:before,
    &:after {
      content: "";
      position: absolute;
      top: 19px;
      left: 8px;
      width: 12px;
      height: 1px;
      background: #fff;
    }

    &:before {
      transform: rotate(45deg);
    }

    &:after {
      transform: rotate(-45deg);
      top: 10px;
    }

    &:disabled {
      background: #888;
      cursor: auto;
    }
  }

  [data-controls="prev"] {
    left: -40px;
  }

  [data-controls="next"] {
    right: -40px;
    transform: rotate(180deg);
  }
}

// Dots
.tns-outer {
  position: relative;
}

.tns-nav {
  position: absolute;
  bottom: -20px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 3;

  button {
    width: 20px;
    height: 4px;
    // border-radius: 50%;
    margin: 0 5px;
    padding: 0;
    background: #333;
    outline: none;
    border: none;
  }

  .tns-nav-active {
    background: #888;
  }
}
</style>
