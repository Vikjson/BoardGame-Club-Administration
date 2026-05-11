<script setup>
import {ref, onMounted} from 'vue'
import GameSessionService from '../service/GameSessionService.js'

const gameSessionService = new GameSessionService()

const topGames = ref([])
const topWinners = ref([])

onMounted(async () => {
  const sessions = await gameSessionService.getAll()

  calculateTopGames(sessions)
  calculateTopWinners(sessions)
})

function calculateTopGames(sessions) {
  const gameCounts = {}

  for (const session of sessions) {
    const gameName = session.game.gameName

    gameCounts[gameName] = (gameCounts[gameName] || 0) + 1
  }

  topGames.value = Object.entries(gameCounts)
      .map(([gameName, count]) => ({gameName, count}))
      .sort((a, b) => b.count - a.count)
      .slice(0, 3)
}

function calculateTopWinners(sessions) {
  const winCounts = {}

  for (const session of sessions) {
    for (const participant of session.participants) {
      if (participant.winner || participant.isWinner) {
        const memberName = participant.member.name

        winCounts[memberName] = (winCounts[memberName] || 0) + 1
      }
    }
  }

  topWinners.value = Object.entries(winCounts)
      .map(([memberName, wins]) => ({memberName, wins}))
      .sort((a, b) => b.wins - a.wins)
      .slice(0, 3)
}
</script>

<template>
  <section class="statistics-view">
    <h2>Statistik</h2>

    <article class="stats-card">
      <h3>Mest spelade spel</h3>

      <ol>
        <li v-for="game in topGames" :key="game.gameName">
          {{ game.gameName }} : {{ game.count }} gånger
        </li>
      </ol>
    </article>

    <article class="stats-card">
      <h3>Flest vinster</h3>

      <ol>
        <li v-for="member in topWinners" :key="member.memberName">
          {{ member.memberName }} : {{ member.wins }} vinster
        </li>
      </ol>
    </article>
  </section>
</template>

<style scoped>
.statistics-view {
  padding: 2rem;

  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.stats-card {
  margin-top: 1.5rem;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  background: white;
  width: 100%;
  max-width: 400px;
}

.stats-card h3 {
  margin-top: 0;
}

.stats-card ol {
  list-style-position: inside;
  padding-left: 0;
  margin: 1rem 0 0;
}

.stats-card li {
  margin-bottom: 0.5rem;
}
</style>