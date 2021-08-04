<!-- NOTE COMPLETED NEED TO FIGURE OUT HOW TO RETRIEVE DATA-->
<template>
    <div>
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
        users() {
            this.$store.state.users.filter()
            return 1;
        }
    }

}
</script>

<style>

</style>