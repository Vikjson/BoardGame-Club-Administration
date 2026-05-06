<script setup>
import { ref } from 'vue'

const games = ref([
  {
    id: 1,
    name: 'Catan',
    purchaseDate: '2024-01-01',
    players: '3-4',
    category: 'Strategi',
    age: 10,
    playTime: 90,
    description: 'Bygg och handla resurser',
    clubComment: 'Populärt!'
  }
])

const showForm = ref(false)
const editingId = ref(null)

const formGame = ref({
  name: '',
  purchaseDate: '',
  players: '',
  category: '',
  age: 0,
  playTime: 0,
  description: '',
  clubComment: ''
})

function openAddForm() {
  editingId.value = null
  formGame.value = {
    name: '',
    purchaseDate: '',
    players: '',
    category: '',
    age: 0,
    playTime: 0,
    description: '',
    clubComment: ''
  }
  showForm.value = true
}

function openEditForm(game) {
  editingId.value = game.id
  formGame.value = { ...game }
  showForm.value = true
}

function saveGame() {
  if (editingId.value === null) {
    games.value.push({
      id: Date.now(),
      ...formGame.value
    })
  } else {
    const index = games.value.findIndex(g => g.id === editingId.value)

    games.value[index] = {
      id: editingId.value,
      ...formGame.value
    }
  }

  closeForm()
}

function deleteGame(id) {
  games.value = games.value.filter(g => g.id !== id)
}

function closeForm() {
  showForm.value = false
  editingId.value = null
}
</script>

<template>
  <section class="game-library-view">
    <h2>Hantera spelbibliotek</h2>

    <button @click="openAddForm">Lägg till spel</button>

    <form v-if="showForm" class="form" @submit.prevent="saveGame">
      <h3 class="form-title">
        {{ editingId === null ? 'Nytt spel' : 'Redigera spel' }}
      </h3>

      <label for="game-name">Spelnamn</label>
      <input id="game-name" v-model="formGame.name" type="text" required>

      <label for="purchase-date">Inköpsdatum</label>
      <input id="purchase-date" v-model="formGame.purchaseDate" type="date">

      <label for="players">Antal spelare</label>
      <input id="players" v-model="formGame.players" type="text" placeholder="t.ex. 2-4">

      <label for="category">Kategori</label>
      <input id="category" v-model="formGame.category" type="text">

      <label for="age">Ålder</label>
      <input id="age" v-model.number="formGame.age" type="number" min="0">

      <label for="play-time">Genomsnittlig speltid</label>
      <input id="play-time" v-model.number="formGame.playTime" type="number" min="0" placeholder="minuter">

      <label for="description">Spelbeskrivning</label>
      <textarea id="description" v-model="formGame.description"></textarea>

      <label for="club-comment">Klubbens kommentar</label>
      <textarea id="club-comment" v-model="formGame.clubComment"></textarea>

      <div class="form-actions">
        <button type="submit">Spara</button>
        <button type="button" @click="closeForm">Avbryt</button>
      </div>
    </form>

    <table>
      <thead>
      <tr>
        <th>Namn</th>
        <th>Kategori</th>
        <th>Spelare</th>
        <th>Ålder</th>
        <th>Speltid</th>
        <th>Åtgärder</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="game in games" :key="game.id">
        <td>{{ game.name }}</td>
        <td>{{ game.category }}</td>
        <td>{{ game.players }}</td>
        <td>{{ game.age }}+</td>
        <td>{{ game.playTime }} min</td>
        <td>
          <button @click="openEditForm(game)">Redigera</button>
          <button @click="deleteGame(game.id)">Ta bort</button>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped>
.game-library-view {
  padding: 2rem;
}

.form {
  display: grid;
  grid-template-columns: 180px 320px;
  gap: 0.75rem 1rem;
  align-items: center;

  width: fit-content;
  margin: 1rem 0;
  padding: 1rem;
  border: 1px solid #ccc;
}

.form-title {
  grid-column: 1 / -1;
}

label {
  text-align: right;
}

input,
textarea {
  width: 100%;
  box-sizing: border-box;
  padding: 0.4rem;
}

textarea {
  min-height: 80px;
  resize: vertical;
}

.form-actions {
  grid-column: 2;
  display: flex;
  gap: 0.5rem;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th,
td {
  border: 1px solid #ddd;
  padding: 0.75rem;
  text-align: center;
}

td button {
  margin-right: 0.5rem;
}
</style>