<template>
  <div id="game-instructions">
<h2 class="title">Getting Started</h2>
<ul id="instructions">
<li>Select ‘Create Game’ from the sidebar, set a name and an end date, then invite other members to join you</li>
<li>Each player starts out with $$100,000 Stonk Dollars</li>
<li>The goal is to increase the value of your portfolio as much as possible before time runs out by trading stocks</li>
<li>All stocks are matched to their real world market value, due diligence should yield a competitive advantage</li>
<li>The player with the most money at the end of the game is declared the winner</li>
</ul>
<h2 class="title">Frequently Asked Questions</h2>
<ul>
<li class="q">Is Super Stonks commission free?</li>
<li class="a">No, to more realistically simulate day trading all orders include a $19.95 commission withdrawn directly from the player’s cash balance</li>
<li class="q">Can I see a record of my trades?</li>
<li class="a">Yes, for each game the option to view your trade history is available below your portfolio by selecting the ‘Show Trade History’ button</li>
<li class="q">Can I undo a trade?</li>
<li class="a">No, all trades are final, however, you can buy or sell shares back at the current market value</li>
<li class="q">Do I get anything for winning?</li>
<li class="a">A sense of pride and accomplishment</li>
</ul>
  </div>
</template>

<script>
export default {
    name: "game-instructions"
}
</script>

<style>
.title {
    text-decoration: underline;
}

#game-instructions {
    text-align: left;
    padding-left: 20px;
}
#instructions li {
    padding-bottom: 25px;
}

.q {
    padding-bottom: 10px;
    font-weight: bolder;
    color: gold;
}

.a {
    padding-bottom: 30px;
}

.title {
    text-align: center;
}

</style>