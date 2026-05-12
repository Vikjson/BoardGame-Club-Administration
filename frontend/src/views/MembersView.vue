<script setup>
import {ref, computed} from 'vue'
import MemberService from "../service/MemberService.js";
import {Member} from "../entities/Member.js";


const members = ref([])
getMembers();

const showForm = ref(false)
const editingId = ref(null)
const showOnlyUnpaid = ref(false)

const formMember = ref({
  memberId: '',
  firstName: '',
  lastName: '',
  totalWins: '',
  email: '',
  membershipFeePaid: false,
  age: '',
})

const visibleMembers = computed(() => {
  if (!showOnlyUnpaid.value) {
    return members.value
  }

  return members.value.filter(member => !member.membershipFeePaid)
})

async function getMembers() {
  const memberService = new MemberService();
  members.value = await memberService.getAll();
}

function openAddForm() {
  editingId.value = null
  showForm.value = true
}

async function openEditForm(member) {
  const service = new MemberService();
  const memberFromDB = await service.getByEmail(member.email);

  editingId.value = memberFromDB.memberId;
  formMember.value = {...member}
  showForm.value = true
}

async function saveMember() {
  const service = new MemberService();
  const memberFromForm = new Member(formMember.value)

  if (editingId.value === null) {
    await service.registerNewMember(memberFromForm);
  } else {
    await service.updateMember(editingId.value, memberFromForm);
  }

  members.value = await service.getAll();
  closeForm();
}

async function deleteMember(email) {
  const service = new MemberService();
  const member = await service.getByEmail(email);
  await service.deleteMember(member.memberId);

  members.value = await service.getAll();
}

function closeForm() {
  showForm.value = false
  editingId.value = null
}
</script>

<template>
  <section class="members-view">
    <h2>Hantera medlemskap</h2>

    <div class="actions">
      <button @click="openAddForm">Lägg till medlem</button>
      <button @click="showOnlyUnpaid = !showOnlyUnpaid">
        {{ showOnlyUnpaid ? 'Visa alla' : 'Visa obetalda' }}
      </button>
    </div>

    <form v-if="showForm" class="member-form" @submit.prevent="saveMember">
      <h3>{{ editingId === null ? 'Lägg till medlem' : 'Redigera medlem' }}</h3>

      <label>
        För- och efternamn
        <input v-model="formMember.name" type="text" required>
      </label>

      <label>
        Email
        <input v-model="formMember.email" type="email" required>
      </label>

      <label>
        Ålder
        <input v-model="formMember.age" type="number" required>
      </label>

      <label class="checkbox-label">
        <input v-model="formMember.membershipFeePaid" type="checkbox">
        Har betalat medlemskapet
      </label>

      <div class="form-actions">
        <button type="submit">Spara</button>
        <button type="button" @click="closeForm">Avbryt</button>
      </div>
    </form>

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
      <tr v-for="member in visibleMembers" :key="member.memberId">
        <td>{{ member.name }}</td>
        <td>{{ member.email }}</td>
        <td>{{ member.membershipFeePaid ? 'Ja' : 'Nej' }}</td>
        <td>
          <button @click="openEditForm(member)">Redigera</button>
          <button @click="deleteMember(member.email)">Ta bort</button>
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

.actions,
.form-actions {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

.member-form {
  border: 1px solid #ddd;
  padding: 1rem;
  margin-bottom: 1.5rem;
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

.checkbox-label {
  display: flex;
  gap: 0.5rem;
  align-items: center;
}

.checkbox-label input {
  margin-top: 0;
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