<!-- NOTE COMPLETED NEED TO FIGURE OUT HOW TO RETRIEVE DATA-->
<template>
    <div>
        <!--{{users}}-->
        <ul>
            <!-- <li v-for="user in users" v-bind:key="user.username">
            {{user.username}} -->
            <button>Invite</button>
            <!-- </li> -->
        </ul>
    </div>
</template>

<script>
//import userService from "@/services/UserService.js";

export default {
    data() {
        return {
            invite: {

            }
        };

    },
    computed: {
/*        users() {

            let usersInGame = await userService.getUsersForGame(this.$store.state.currentGameId);
            const uninvited = this.$store.state.allUsers.filter(async user => {
                console.log(this.$store.state.currentGameId);
                return !usersInGame.includes(user);
            })
            return uninvited;
        }
*/
    }

}
</script>

<style>

</style>