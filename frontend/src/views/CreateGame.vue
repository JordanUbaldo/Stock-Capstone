<template>
<div>
    <div>
        <div id="create_game" class="main-div">
            <h1>Create Game</h1>
            <create-game-form />
        </div>
    </div>
</div>
</template>

<script>
import CreateGameForm from '@/components/sidebar/CreateGameForm';

export default {
    components: {
        CreateGameForm
    }
}
</script>

<style>

</style>