<script setup>
import {ref, onMounted} from 'vue'
import GameService from '../service/GameService.js'

const games = ref([])
const gameService = new GameService()

async function loadGames() {
  games.value = await gameService.getAll()
}

async function openLibrary() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  await loadGames()
}

onMounted(async () => {
  await loadGames()
})

onMounted(async () => {
  games.value = await gameService.getAll()
})

function isDuplicateName(name) {
  return games.value.some(
      g => g.gameName?.toLowerCase() === name?.toLowerCase()
  )
}

async function saveGame() {
  try {
    if (editingId.value === null) {
      if (isDuplicateName(formGame.value.gameName)) {
        alert("Det här spelet finns redan, inga fler kan läggas till!")
        return
      }
    }
    if (editingId.value !== null) {
      await gameService.update(editingId.value, formGame.value)
    } else {
      await gameService.create(formGame.value)
    }
    await loadGames()
    closeForm()

  } catch (err) {
    console.error(err)
  }
}


const showForm = ref(false)
const editingId = ref(null)

const showFetchForm = ref(false)
const fetchGameId = ref('')

const showFetchByNameForm = ref(false)
const fetchGameName = ref('')

const errorMessage = ref('')
const showError = ref(false)

function openFetchForm() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  showFetchForm.value = true
}

function closeFetchForm() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  fetchGameId.value = ''
}

function openFetchByNameForm() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  showFetchByNameForm.value = true
}

function closeFetchByNameForm() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  fetchGameName.value = ''
}

async function fetchGameById() {
  try {
    const game = await gameService.getById(fetchGameId.value)
    games.value = [game]
    closeFetchForm()
  } catch (err) {
    console.error(err)
    errorMessage.value = "Detta ID finns inte."
    showError.value = true
  }
}

async function fetchGameByName() {
  try {
    const game = await gameService.getByName(fetchGameName.value)
    games.value = [game]
    closeFetchByNameForm()
  } catch (err) {
    console.error(err)
    errorMessage.value = "Detta game finns inte."
    showError.value = true
  }
}

const formGame = ref({
  gameName: '',
  purchaseDate: '',
  totalPlayers: 0,
  category: '',
  recommendedAge: 0,
  averagePlayTime: 0,
  description: '',
  memberComment: ''
})

function openAddForm() {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  editingId.value = null
  formGame.value = {
    gameName: '',
    purchaseDate: '',
    totalPlayers: 0,
    category: '',
    recommendedAge: 0,
    averagePlayTime: 0,
    description: '',
    memberComment: ''
  }
  showForm.value = true
}

function openEditForm(game) {
  showForm.value = false
  showFetchForm.value = false
  showFetchByNameForm.value = false
  editingId.value = game.gameId
  formGame.value = {...game}
  showForm.value = true
}


async function deleteGame(gameId) {
  try {
    await gameService.delete(gameId)
    games.value = games.value.filter(game => game.gameId !== gameId)
  } catch (err) {
    console.error(err)

    errorMessage.value = ("Kan inte ta bort spelet eftersom det fortfarande används.")
    showError.value = true
  }
}

function closeForm() {
  showForm.value = false
  editingId.value = null
}
</script>

<template>
  <section class="game-library-view">
    <h2>Hantera spelbibliotek</h2>
    <div class="actions">
    <button @click="openLibrary">Spel lista</button>
    <button @click="openAddForm">Lägg till spel</button>
    <button @click="openFetchForm">Hämta spel via id</button>
    <button @click="openFetchByNameForm">Hämta spel via name</button>
    </div>

    <form v-if="showForm" class="form" @submit.prevent="saveGame">
      <h3 class="form-title">
        {{ editingId === null ? 'Nytt spel' : 'Redigera spel' }}
      </h3>

      <label for="game-name">Spelnamn</label>
      <input id="game-name" v-model="formGame.gameName" type="text" required>

      <label for="purchase-date">Inköpsdatum</label>
      <input id="purchase-date" v-model="formGame.purchaseDate" type="date">

      <label for="players">Antal spelare</label>
      <input id="players" v-model="formGame.totalPlayers" type="text" placeholder="t.ex. 2-4">

      <label for="category">Kategori</label>
      <input id="category" v-model="formGame.category" type="text">

      <label for="age">Ålder</label>
      <input id="age" v-model.number="formGame.recommendedAge" type="number" min="0">

      <label for="play-time">Genomsnittlig speltid</label>
      <input id="play-time" v-model.number="formGame.averagePlayTime" type="number" min="0" placeholder="minuter">
      <label for="description">Spelbeskrivning</label>
      <textarea id="description" v-model="formGame.description"></textarea>

      <label for="club-comment">Klubbens kommentar</label>
      <textarea id="club-comment" v-model="formGame.memberComment"></textarea>

      <div class="form-actions">
        <button type="submit">Spara</button>
        <button type="button" @click="closeForm">Avbryt</button>
      </div>
    </form>

    <div v-if="showFetchForm" class="fetch-form">

      <label for="fetch-id">Game ID</label>

      <input
          id="fetch-id"
          v-model="fetchGameId"
          type="number"
      >

      <div class="form-actions">

        <button @click="fetchGameById">
          Hämta
        </button>

        <button @click="closeFetchForm">
          Avbryt
        </button>

      </div>

    </div>

    <div v-if="showFetchByNameForm" class="fetch-form">

      <label for="fetch-name">Spelnamn</label>

      <input
          id="fetch-name"
          v-model="fetchGameName"
          type="text"
      >

      <div class="form-actions">

        <button @click="fetchGameByName">
          Hämta
        </button>

        <button @click="closeFetchByNameForm">
          Avbryt
        </button>

      </div>

    </div>

    <div v-if="showError" class="popup">
      <p>{{ errorMessage }}</p>
      <button @click="showError = false">OK</button>
    </div>

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
      <tr v-for="game in games" :key="game.gameId">
        <td>{{ game.gameName }}</td>
        <td>{{ game.category }}</td>
        <td>{{ game.totalPlayers }}</td>
        <td>{{ game.recommendedAge }}+</td>
        <td>{{ game.averagePlayTime }} min</td>
        <td>
          <button @click="openEditForm(game)">Redigera</button>
          <button @click="deleteGame(game.gameId)">Ta bort</button>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped>
.game-library-view {
  background-attachment: fixed;
  padding: 2rem;
  min-height: 100vh;
  background-image: linear-gradient(rgba(0, 0, 0, 0.6),
  rgba(0, 0, 0, 0.6)),
  url('../assets/2h-media-hH3JuONz7CQ-unsplash.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.game-library-view h2 {
  text-align: center;
}

.game-library-view > button {
  margin: 0.25rem;
}
.actions {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 1rem;
}

.form {
  display: grid;
  grid-template-columns: 140px 280px;
  gap: 0.75rem 1rem;
  align-items: center;

  max-width: 540px;
  margin: 1rem auto;
  padding: 1.5rem;
  border: 1px solid #ccc;
  background-color: #333333;
  border-radius: 12px;
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
  grid-column: 1 / -1;
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}

.game-library-view > table {
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
}

.fetch-form {
  max-width: 400px;
  margin: 1rem auto;
  padding: 1rem;
  background-color: #333333;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.fetch-form input {
  width: 100%;
  max-width: 400px;
}

table {
  width: 100%;
  max-width: 1000px;
  border-collapse: collapse;
  margin-top: 1rem;
  background-color: rgba(51, 51, 51, 0.9);
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