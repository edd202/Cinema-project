<template>



    <div>

        <!-- 상단 메뉴  -->
        <NavBlack></NavBlack>

        <!-- 상영시간 영화 선택 -->
        <div >
            <SelectMovie></SelectMovie>

        </div>




    </div>
</template>

<script>
import NavBlack from '@/components/common/NavBlack.vue'
import SelectMovie from '@/components/reservation/SelectMovieTime.vue'
import custom from '@/assets/js/custom.js'

export default {
    components: {
        NavBlack,
        SelectMovie,
    },
    mounted() {
        custom();
    },
    data() {
        return {
            
        }
    },

}
</script>

<style >

</style>