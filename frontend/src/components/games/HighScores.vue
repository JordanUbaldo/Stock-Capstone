<template>
    <div class="main-div">
        <div v-if="isLoading">
            <img src="assets/loading.gif" alt="LoadingPanda">
        </div>
        <div>
        <h2>Highscores!</h2>
        <table>
            <tr v-for="score in highScores" v-bind:key="score.username">
                <td>{{ score.username }}</td>
                <td>{{ score.amount }}</td>
            </tr>
        </table>
        <p>Think you can do better?</p>
    </div>
    </div>
</template>

<script>
import gamesService from '@/services/GamesService';
export default {
name: "high-scores",
data(){
    return {
        highScores: [],
        isLoading: true
    }
},
created() {
    gamesService.getHighScores().then(response => {
        
        if (response.status === 200){
        this.highScores = response.data;
        this.isLoading = false;
        }
    });
}
}
</script>

<style>
#highScores{
    background-color: #04460e;
}
</style>