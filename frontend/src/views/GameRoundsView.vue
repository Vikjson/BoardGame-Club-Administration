<script setup>
import { ref } from 'vue'

const gameSessions = ref([
  {
    sessionId: 1,
    game: 'Catan',
    date: '2026-05-02',
    participants: [
      { memberId: 1, name: 'Anna Andersson', score: 10, winner: true },
      { memberId: 2, name: 'Bo Bengtsson', score: 7, winner: false }
    ]
  },
  {
    sessionId: 2,
    game: 'Ticket to Ride',
    date: '2026-05-02',
    participants: [
      { memberId: 1, name: 'Anna Andersson', score: 80, winner: false },
      { memberId: 3, name: 'Lisa Larsson', score: 110, winner: true }
    ]
  }
])

const showForm = ref(false)
const editingId = ref(null)

const formSession = ref({
  game: '',
  date: '',
  participants: []
})

function openAddForm() {
  editingId.value = null
  formSession.value = {
    game: '',
    date: '',
    participants: []
  }
  showForm.value = true
}

function openEditForm(session) {
  editingId.value = session.sessionId

  formSession.value = {
    game: session.game,
    date: session.date,
    participants: session.participants.map(player => ({ ...player }))
  }

  showForm.value = true
}

function addParticipant() {
  formSession.value.participants.push({
    memberId: Date.now(),
    name: '',
    score: 0,
    winner: false
  })
}

function removeParticipant(index) {
  formSession.value.participants.splice(index, 1)
}

function saveSession() {
  if (editingId.value === null) {
    gameSessions.value.push({
      sessionId: Date.now(),
      ...formSession.value
    })
  } else {
    const index = gameSessions.value.findIndex(
        session => session.sessionId === editingId.value
    )

    gameSessions.value[index] = {
      sessionId: editingId.value,
      ...formSession.value
    }
  }

  closeForm()
}

function deleteSession(sessionId) {
  gameSessions.value = gameSessions.value.filter(
      session => session.sessionId !== sessionId
  )
}

function closeForm() {
  showForm.value = false
  editingId.value = null
}
</script>

<template>
  <section class="game-rounds-view">
    <h2>Registrera spelomgång</h2>

    <button @click="openAddForm">
      Lägg till spelomgång
    </button>

    <form v-if="showForm" class="session-form" @submit.prevent="saveSession">
      <h3>{{ editingId === null ? 'Ny spelomgång' : 'Redigera spelomgång' }}</h3>

      <label>
        Spel
        <input v-model="formSession.game" type="text" required>
      </label>

      <label>
        Datum
        <input v-model="formSession.date" type="date" required>
      </label>

      <h4>Deltagare</h4>

      <div
          v-for="(player, index) in formSession.participants"
          :key="player.memberId"
          class="participant-row"
      >
        <input v-model="player.name" type="text" placeholder="Namn" required>
        <input v-model.number="player.score" type="number" placeholder="Poäng">

        <label>
          <input v-model="player.winner" type="checkbox">
          Vinnare
        </label>

        <button type="button" @click="removeParticipant(index)">
          Ta bort deltagare
        </button>
      </div>

      <button type="button" @click="addParticipant">
        + Lägg till deltagare
      </button>

      <div class="form-actions">
        <button type="submit">
          Spara
        </button>

        <button type="button" @click="closeForm">
          Avbryt
        </button>
      </div>
    </form>

    <article
        v-for="session in gameSessions"
        :key="session.sessionId"
        class="session-card"
    >
      <h3>{{ session.game }}</h3>
      <p>Datum: {{ session.date }}</p>

      <table>
        <thead>
        <tr>
          <th>Medlem</th>
          <th>Poäng</th>
          <th>Vinnare</th>
        </tr>
        </thead>

        <tbody>
        <tr
            v-for="player in session.participants"
            :key="player.memberId"
        >
          <td>{{ player.name }}</td>
          <td>{{ player.score }}</td>
          <td>{{ player.winner ? 'Ja' : 'Nej' }}</td>
        </tr>
        </tbody>
      </table>

      <button @click="openEditForm(session)">
        Redigera
      </button>

      <button @click="deleteSession(session.sessionId)">
        Ta bort
      </button>
    </article>
  </section>
</template>

<style scoped>
.game-rounds-view {
  padding: 2rem;
}

.session-form,
.session-card {
  margin-top: 1.5rem;
  padding: 1rem;
  border: 1px solid #ddd;
}

label {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 0.75rem;
}

input {
  margin-top: 0.25rem;
  width: 200px;
}

.participant-row {
  margin-bottom: 1rem;
}

.form-actions {
  margin-top: 1rem;
}

button {
  margin-right: 0.5rem;
}

table {
  width: 100%;
  margin: 1rem 0;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 0.5rem;
  text-align: center;
}
</style>