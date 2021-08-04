<template>
    <div>
        <h1>Create Game</h1>
        <create-game-form />
        <send-invites />
    </div>
</template>

<script>
import CreateGameForm from '@/components/CreateGameForm';
import SendInvites from '@/components/SendInvites.vue';

export default {
    components: {
        CreateGameForm,
        SendInvites
    }
}
</script>

<style>

</style>