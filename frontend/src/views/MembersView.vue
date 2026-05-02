<script setup>
import { ref } from 'vue'

const members = ref([
  { id: 1, firstName: 'Anna', lastName: 'Andersson', email: 'anna@mail.com', paid: true },
  { id: 2, firstName: 'Bo', lastName: 'Bengtsson', email: 'bo@mail.com', paid: false }
])
</script>

<template>
  <section class="members-view">
    <h2>Hantera medlemskap</h2>

    <div class="actions">
      <button>Lägg till medlem</button>
      <button>Sök/filtrera medlemmar</button>
      <button>Visa obetalda</button>
    </div>

    <table>
      <thead>
      <tr>
        <th>Namn</th>
        <th>Email</th>
        <th>Betalat</th>
        <th>Åtgärder</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="member in members" :key="member.id">
        <td>{{ member.firstName }} {{ member.lastName }}</td>
        <td>{{ member.email }}</td>
        <td>{{ member.paid ? 'Ja' : 'Nej' }}</td>
        <td>
          <button>Redigera</button>
          <button>Ta bort</button>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped>
.members-view {
  padding: 2rem;
}

.actions {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 0.75rem;
  text-align: left;
}
</style>