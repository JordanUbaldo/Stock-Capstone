<template>
    <div>
        <h2 class="title">Highscores</h2>
        <table>
            <tr v-for="score in highScores" v-bind:key="score.balanceId">
                <td>{{ score.username }}</td>
                <td>${{ new Intl.NumberFormat().format(score.amount) }}</td>
            </tr>
        </table>
        <p>Think you can do better?</p>
    </div>
</template>

<script>
import gamesService from '@/services/GamesService';
export default {
name: "high-scores",
data(){
    return {
        highScores: []
    }
},
created() {
    gamesService.getHighScores().then(response => {
        if (response.status === 200){
        this.highScores = response.data;
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