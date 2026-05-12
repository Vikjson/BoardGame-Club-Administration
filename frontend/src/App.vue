<script setup>
import { ref } from 'vue'
import AdminMenu from './components/AdminMenu.vue'
import HomeView from './views/HomeView.vue'
import MembersView from './views/MembersView.vue'
import GameRoundsView from './views/GameRoundsView.vue'
import GameLibraryView from './views/GameLibraryView.vue'
import StatisticsView from './views/StatisticsView.vue'

const currentPage = ref('home')


</script>

<template>
  <header>
    <h1>Board game-klubben</h1>
  </header>

  <AdminMenu @change-page="currentPage = $event" />
  <HomeView v-if="currentPage === 'home'" />
  <MembersView v-if="currentPage === 'members'" />
  <GameRoundsView v-if="currentPage === 'rounds'" />
  <GameLibraryView v-if="currentPage === 'library'" />
  <StatisticsView v-if="currentPage === 'statistics'" />

</template>
<style scoped>
header {
  display: flex;
  justify-content: center;

  margin-top: 1rem;
  margin-bottom: 2rem;
}

h1 {
  color: white;
}
</style>