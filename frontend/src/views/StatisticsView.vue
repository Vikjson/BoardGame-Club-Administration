<script setup>
import { ref } from 'vue'

const allTimeWins = ref([
  { memberId: 1, name: 'Anna Andersson', wins: 12 },
  { memberId: 2, name: 'Bo Bengtsson', wins: 8 },
  { memberId: 3, name: 'Lisa Larsson', wins: 5 }
])

const mostPlayedGames = ref([
  { gameId: 1, title: 'Catan', sessions: 14 },
  { gameId: 2, title: 'Ticket to Ride', sessions: 10 },
  { gameId: 3, title: 'Carcassonne', sessions: 7 }
])
</script>

<template>
  <section>
    <h2>Visa statistik</h2>

    <article>
      <h3>Topplista vinster all time</h3>

      <ol>
        <li v-for="player in allTimeWins" :key="player.memberId">
          {{ player.name }} – {{ player.wins }} vinster
        </li>
      </ol>
    </article>

    <article>
      <h3>Mest spelade spel</h3>

      <ol>
        <li v-for="game in mostPlayedGames" :key="game.gameId">
          {{ game.title }} – {{ game.sessions }} spelomgångar
        </li>
      </ol>
    </article>
  </section>
</template>

<style scoped>
section {
  padding: 2rem;
}

article {
  margin-bottom: 2rem;
}
</style>