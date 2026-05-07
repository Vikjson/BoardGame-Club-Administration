<script setup>
import { ref } from 'vue'
import AdminMenu from './components/AdminMenu.vue'
import MembersView from './views/MembersView.vue'
import GameRoundsView from './views/GameRoundsView.vue'
import GameLibraryView from './views/GameLibraryView.vue'
import StatisticsView from './views/StatisticsView.vue'

const currentPage = ref('member')


</script>

<template>
  <header>
    <h1>Board game-klubben</h1>
  </header>

  <AdminMenu @change-page="currentPage = $event" />

  <MembersView v-if="currentPage === 'members'" />

  <GameRoundsView v-if="currentPage === 'rounds'" />
  <GameLibraryView v-if="currentPage === 'library'" />
  <StatisticsView v-if="currentPage === 'statistics'" />

</template>