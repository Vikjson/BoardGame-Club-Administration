<template>
  <nav class="admin-menu">

    <button @click="$emit('change-page', 'home')">
      Hem
    </button>

    <button @click="$emit('change-page', 'members')">
      Hantera medlemskap
    </button>

    <button @click="$emit('change-page', 'rounds')">
      Registrera spelomgång
    </button>

    <button @click="$emit('change-page', 'library')">
      Hantera spelbibliotek
    </button>

    <button @click="$emit('change-page', 'statistics')">
      Visa statistik
    </button>


  </nav>
</template>

<style scoped>
.admin-menu {
  display: flex;
  justify-content: center;
  gap: 1rem;
  padding: 1rem 2rem;
  border-bottom: 1px solid #ddd;
}

button {
  padding: 0.75rem 1rem;
  cursor: pointer;
  background-color:  #333333;
  border-color: #666666;
  margin:0rem;
}

button:hover {
  background-color: #ac3939;
  border-color: #666;
}
</style>
<script setup lang="ts">
</script>